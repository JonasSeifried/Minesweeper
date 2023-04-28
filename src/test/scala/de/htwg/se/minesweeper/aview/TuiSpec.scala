package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.model.Field

import java.io.ByteArrayInputStream

class TuiSpec extends AnyWordSpec {
  val field: Field = new Field(3, 3).openTile(0,0)
  val controller: Controller = Controller(field)
  val tui = new Tui(controller)


  "A TUI when running" when {
    "input starts with r" should {
      "renew the field" in {
        tui.processInput("r 123", field)
        controller.field should not be field
        controller.field = field
      }
    }
    "input starts with o and valid input" in {
          tui.processInput("o a1", field)
          controller.field.getTile(1, 0).isHidden should be(false)
          controller.field = field
      }
    "input starts with f and valid input" in {
          tui.processInput("f a1", field)
          controller.field.getTile(1, 0).isFlagged should be(true)
          controller.field = field
      }
    "input starts with o or a and bad input" should {
      "input < 4 or input > 5" in {
        tui.processInput("f 7", field)
        controller.field should be(field)
      }
      "input with out of bounds coords" in {
        tui.processInput("o a3", field)
        controller.field should be(field)
      }
    }
    "input strts with p" in {
      tui.processInput("p ", field) should be(false)
    }
    "input starts with anything else" in {
      tui.processInput("csad", field)
      controller.field should be(field)
    }
  }
}
