package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.model.Field

class TuiSpec extends AnyWordSpec {
  val field: Field = new Field(3, 3, Easy).openTile(0, 0)
  val controller: Controller = Controller(field)
  val tui = new Tui(controller)

  "A TUI" when {
    "processing input" should {
      "renew the field when input is 'n'" in {
        val initialField = controller.field.copy()
        tui.processInput("n")
        controller.field should not be initialField
      }
      "return false when input starts with nothing" in {
        tui.processInput("") should be(false)
      }
      "return true when input starts with 'o' and valid input" in {
        tui.processInput("o a2") should be(false)
      }
      "return true when input starts with 'f' and valid input" in {
        tui.processInput("f a1") should be(false)
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
        tui.processInput("s") should be(false)
      }
      /*"return true when input is 'l' and game is successfully loaded" in {
        tui.processInput("l") should be(true)
      }*/
      "return false when input is 'l' and game fails to load" in {
        tui.processInput("l") should be(false)
      }
      "return false when input starts with anything else" in {
        tui.processInput("csad") should be(false)
      }
    }
  }
}
