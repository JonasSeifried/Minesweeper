package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.model.Field
import java.io.ByteArrayOutputStream


class TuiSpec extends AnyWordSpec {
  val field: Field = new Field(3, 3, Easy).openTile(0,0)
  val controller: Controller = Controller(field)
  val tui= new Tui(controller)

  "A TUI" when {
    "with difference inputs" should {
      "renew the field" in {
        tui.processInput("r ")
        controller.field should not be field
      }
    }

    "The update() method" should {
      "print correct messages when in post-game state" in {
        // Mock the controller to simulate the post-game state with game won
        val controllerGameWon = new Controller(field) {
          override def isPostGameState: Boolean = true
          override def gameWon: Boolean = true
          override def gameOver: Boolean = false
        }

        // Create a new Tui instance with the mocked controller
        val tuiGameWon = new Tui(controllerGameWon)

        // Capture the console output
        val outputStreamGameWon = new ByteArrayOutputStream()
        Console.withOut(outputStreamGameWon) {
          // Call the update() method
          tuiGameWon.update()

          // Assert that the console output contains the expected messages
          val consoleOutputGameWon = outputStreamGameWon.toString
          consoleOutputGameWon should include("Anzahl unentdeckter Felder: " + controllerGameWon.getCountOfUnopenedTiles)
          consoleOutputGameWon should include("Spiel gewonnen!")
        }

        // Mock the controller to simulate the post-game state with game over
        val controllerGameOver = new Controller(field) {
          override def isPostGameState: Boolean = true
          override def gameWon: Boolean = false
          override def gameOver: Boolean = true
        }

        // Create a new Tui instance with the mocked controller
        val tuiGameOver = new Tui(controllerGameOver)

        // Capture the console output
        val outputStreamGameOver = new ByteArrayOutputStream()
        Console.withOut(outputStreamGameOver) {
          // Call the update() method
          tuiGameOver.update()

          // Assert that the console output contains the expected messages
          val consoleOutputGameOver = outputStreamGameOver.toString
          consoleOutputGameOver should include("Anzahl unentdeckter Felder: " + controllerGameOver.getCountOfUnopenedTiles)
          consoleOutputGameOver should include("Spiel verloren!")
        }
      }
    }

    "The run() method" should {
      "return true" in {
        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          val result =tui.run()
          result should be(true)
        }
      }
      "return false if inputLoop() returns false" in {
        val outputStream = new ByteArrayOutputStream()
        Console.withOut(outputStream) {
          // Mocking inputLoop() to always return false
          val tui = new Tui(controller) {
            override protected def inputLoop(): Boolean = false
          }
          val result = tui.run()
          result should be(false)
        }
      }
    }

    "input starts with nothing" in {
      tui.processInput("") should be(false)
    }
    "input starts with o and valid input" in {
      tui.processInput("o") should be(false)
      tui.processInput("o a2") should be(true)
    }
    "input starts with f and valid input" in {
      tui.processInput("f a1") should be(true)
    }
    "input starts with o or a and bad input" should {
      "input < 4 or input > 5" in {
        tui.processInput("f") should be(false)
        tui.processInput("f a244") should be(false)
      }
      "input with out of bounds coords" in {
        tui.processInput("o d0") should be(false)
      }
      "input q" in {
        tui.processInput("q") should be(false)
      }
      "input h" in {
        tui.processInput("h ") should be(false)
      }
    }
    "input starts with anything else" in {
      tui.processInput("csad") should be(false)
    }

    "processInput() method" should {
      "handle save game command correctly" in {
        // Mock the controller to simulate a successful save game operation
        val controllerSaveGame = new Controller(field) {
          override def saveGame(): Boolean = true
        }

        // Create a new Tui instance with the mocked controller
        val tuiSaveGame = new Tui(controllerSaveGame)

        // Capture the console output
        val outputStreamSaveGame = new ByteArrayOutputStream()
        Console.withOut(outputStreamSaveGame) {
          // Call the processInput() method with save game command
          tuiSaveGame.processInput("s")

          // Assert that the console output contains the expected message
          val consoleOutputSaveGame = outputStreamSaveGame.toString
          consoleOutputSaveGame should include("Spiel gespeichert!")
        }
      }

      "handle save game failure correctly" in {
        // Mock the controller to simulate a failed save game operation
        val controllerSaveGame = new Controller(field) {
          override def saveGame(): Boolean = false
        }

        // Create a new Tui instance with the mocked controller
        val tuiSaveGame = new Tui(controllerSaveGame)

        // Capture the console output
        val outputStreamSaveGame = new ByteArrayOutputStream()
        Console.withOut(outputStreamSaveGame) {
          // Call the processInput() method with save game command
          tuiSaveGame.processInput("s")

          // Assert that the console output contains the expected message
          val consoleOutputSaveGame = outputStreamSaveGame.toString
          consoleOutputSaveGame should include("Spiel konnte nicht gespeichert werden")
        }
      }

      "handle restore game command correctly" in {
        // Mock the controller to simulate a successful restore game operation
        val controllerRestoreGame = new Controller(field) {
          override def restoreGame(): Boolean = true
        }

        // Create a new Tui instance with the mocked controller
        val tuiRestoreGame = new Tui(controllerRestoreGame)

        // Capture the console output
        val outputStreamRestoreGame = new ByteArrayOutputStream()
        Console.withOut(outputStreamRestoreGame) {
          // Call the processInput() method with restore game command
          tuiRestoreGame.processInput("l")

          // Assert that the console output contains the expected message
          val consoleOutputRestoreGame = outputStreamRestoreGame.toString
          consoleOutputRestoreGame should include("Spiel erfolgreich geladen")
        }
      }

      "handle restore game failure correctly" in {
        // Mock the controller to simulate a failed restore game operation
        val controllerRestoreGame = new Controller(field) {
          override def restoreGame(): Boolean = false
        }

        // Create a new Tui instance with the mocked controller
        val tuiRestoreGame = new Tui(controllerRestoreGame)

        // Capture the console output
        val outputStreamRestoreGame = new ByteArrayOutputStream()
        Console.withOut(outputStreamRestoreGame) {
          // Call the processInput() method with restore game command
          tuiRestoreGame.processInput("l")

          // Assert that the console output contains the expected message
          val consoleOutputRestoreGame = outputStreamRestoreGame.toString
          consoleOutputRestoreGame should include("Spiel konnte nicht geladen werden")
        }
      }
    }
  }
}
