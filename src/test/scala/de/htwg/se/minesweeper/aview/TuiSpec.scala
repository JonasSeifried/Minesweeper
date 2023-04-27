package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Field

class TuiSpec extends AnyWordSpec {
  val field = new Field(3, 3)
  val controller = new Controller(field)
  val tui = new Tui(controller)


  "A TUI" when {
    "input starts with r" should {
      "renew the field" in {
        val newField = tui.processInput("r 123", field)
        newField should not be(field)
      }
    }
    "input starts with o and valid input" in {
          val newField = tui.processInput("o a1", field)
          newField.getTile(1, 0).isHidden should be(false)
      }
    "input starts with f and valid input" in {
          val newField = tui.processInput("f a1", field)
          newField.getTile(1, 0).isFlagged should be(true)
      }
    "input starts with o or a and bad input" should {
      "input < 4 or input > 5" in {
        val newField = tui.processInput("f 7", field)
        newField should be(field)
      }
      "input with out of bounds coords" in {
        val newField = tui.processInput("o a3", field)
        newField should be(field)
      }
    }
    "input starts with anything else" in {
      val field = new Field(3, 3)
      val newField = tui.processInput("csad", field)
      newField should be(field)
    }
  }
}
