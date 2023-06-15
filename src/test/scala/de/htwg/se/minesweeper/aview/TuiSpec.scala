package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.util.Event

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers.*


class TuiSpec extends AnyWordSpec {
  val field: Field = new Field(3, 3, Easy).openTile(0, 0)
  val controller: Controller = Controller(field)
  val tui = new Tui(controller)
    "processing input" should {
      "renew the field when input is 'n'" in {
        val initialField = controller.field
        tui.processInput("n")
        controller.field should not be initialField
      }
      "return false when input starts with nothing" in {
        tui.processInput("") should be(false)
      }
      "return true when input starts with 'o' and valid input" in {
        tui.processInput("oa2") should be(true)
      }
      "return true when input starts with 'f' and valid input" in {
        tui.processInput("fa1") should be(true)
      }
      "return false when input starts with 'o' or 'f' and bad input" should {
        "return false when input length < 4" in {
          tui.processInput("f") should be(false)
        }
        "return false when input length > 5" in {
          tui.processInput("f a244") should be(false)
        }
        "return false when input with out of bounds coords" in {
          tui.processInput("o d0") should be(false)
        }
        "return false when input is 'q'" in {
          tui.processInput("q") should be(false)
        }
        "return false when input is 'h'" in {
          tui.processInput("h") should be(false)
        }
      }
      "return true when input is 's' and game is successfully saved" in {
        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          tui.processInput("s") should be(false)
          val consoleOutput = outputStream.toString
          consoleOutput should include("Game Saved!")
        }
      }

      "return false when input is 's' and game fails to save" in {
        val failingController = new Controller(field) {
          override def saveGame: Boolean = false
        }
        val tui = new Tui(failingController)

        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          tui.processInput("s") should be(false)
          val consoleOutput = outputStream.toString
          consoleOutput should include("Couldn't save Game")
        }
      }

      "return true when input is 'l' and game is successfully loaded" in {
        val successfulController = new Controller(field) {
          override def restoreGame: Boolean = true
        }
        val tui = new Tui(successfulController)

        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          tui.processInput("l") should be(true)
          val consoleOutput = outputStream.toString
          consoleOutput.trim should include("successfully loaded game")
        }
      }

      "return false when input is 'l' and game fails to load" in {
        val failingController = new Controller(field) {
          override def restoreGame: Boolean = false
        }
        val tui = new Tui(failingController)

        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          tui.processInput("l") should be(false)
          val consoleOutput = outputStream.toString
          consoleOutput should include("Failed to load game")
        }
      }

      "return false when input starts with anything else" in {
        tui.processInput("csad") should be(false)
      }
    }
    "undo and redo" should {
      tui.processInput("ob1")
      "return true when input is 'u' and undo is successful" in {
        tui.processInput("u") should be(true)
      }
      "return true when input is 'r' and redo is successful" in {
        tui.processInput("u")
        tui.processInput("r") should be(true)
      }
    }

    "run() method" should {
      "return true if inputLoop() returns true" in {
        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          val tui = new Tui(controller) {
            override def inputLoop(): Boolean = true
          }
          val result = tui.run()
          result should be(true)
        }
      }

      "return false if inputLoop() returns false" in {
        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          val tui = new Tui(controller) {
            override def inputLoop(): Boolean = false
          }
          val result = tui.run()
          result should be(false)
        }
      }
    }

    "The update() method" should {
      "print correct messages when in post-game state" in {
        val controllerGameWon = new Controller(field) {
          override def isPostGameState: Boolean = true

          override def gameWon: Boolean = true

          override def gameOver: Boolean = false
        }

        val tuiGameWon = new Tui(controllerGameWon)

        val outputStreamGameWon = new ByteArrayOutputStream()
        Console.withOut(outputStreamGameWon) {
          tuiGameWon.update(Event.GameOver)
          val consoleOutputGameWon = outputStreamGameWon.toString
          consoleOutputGameWon should include("Spiel gewonnen!")
        }

        val controllerGameOver = new Controller(field) {
          override def isPostGameState: Boolean = true

          override def gameWon: Boolean = false

          override def gameOver: Boolean = true
        }

        val tuiGameOver = new Tui(controllerGameOver)

        val outputStreamGameOver = new ByteArrayOutputStream()
        Console.withOut(outputStreamGameOver) {
          tuiGameOver.update(Event.GameOver)

          val consoleOutputGameOver = outputStreamGameOver.toString
          consoleOutputGameOver should include("Spiel verloren!")
        }
      }
    }
    "inputLoop() method" should {
      "return false when input is empty" in {
        val inputStream = new ByteArrayInputStream("\n".getBytes)
        Console.withIn(inputStream) {
          val result = tui.inputLoop()
          result should be(false)
        }
      }
      "return false when input is 'q'" in {
        val inputStream = new ByteArrayInputStream("q\n".getBytes)
        Console.withIn(inputStream) {
          val result = tui.inputLoop()
          result should be(false)
        }
      }

      "return false when processInput() returns true and in post-game state" in {
        val inputStream = new ByteArrayInputStream("o a1\n".getBytes)
        val outputStream = new ByteArrayOutputStream()
        val postGameStateController = new Controller(field) {
          override def isPostGameState: Boolean = true
        }
        val tui = new Tui(postGameStateController)
        Console.withIn(inputStream) {
          Console.withOut(outputStream) {
            val result = tui.inputLoop()
            result should be(false)
            val consoleOutput = outputStream.toString
            consoleOutput should not include "controller"
          }
        }
      }
    }
}
