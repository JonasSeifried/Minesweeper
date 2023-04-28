package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Field

class TuiSpec extends AnyWordSpec {
  val field = new Field(3, 3).openTile(0,0)
  val controller = new Controller(field)
  val tui = new Tui(controller)

  "A TUI" when {
    "with difference inputs" should {
      "renew the field" in {
        tui.processInput("r ")
        controller.field should not be(field)
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
    }
    "input starts with anything else" in {
        tui.processInput("csad") should be(false)
    }
  }
}