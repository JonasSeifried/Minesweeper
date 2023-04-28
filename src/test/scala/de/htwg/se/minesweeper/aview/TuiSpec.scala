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
    "with difference inputs" should {
      "renew the field" in {
        val newField = tui.processInput("p", field)
        newField should not be(field)
      }
    }
    "input starts with nothing" in {
      val newField = tui.processInput("", field)
      newField should be(false)
    }
    "input starts with o and valid input" in {
      val newField = tui.processInput("o", field)
      val newField1 = tui.processInput("o a2", field)
      newField should be(false)
      newField1 should be(true)
    }
    "input starts with f and valid input" in {
      val newField = tui.processInput("f", field)
      newField should be(false)
    }
    "input starts with o or a and bad input" should {
      "input < 4 or input > 5" in {
        val newField = tui.processInput("f", field)
        val newField1 = tui.processInput("f a2", field)
        val newField2 = tui.processInput("f x2", field)
        newField should be(false)
        newField1 should be(true)
      }
      "input with out of bounds coords" in {
        val newField = tui.processInput("o", field)
        newField should be(false)
      }
      "input r" in {
        val newField = tui.processInput("r", field)
        newField should be(true)
      }
    }
    "input starts with anything else" in {
      val field = new Field(3, 3)
      val newField = tui.processInput("csad", field)
      newField should be(false)
    }
  }
}