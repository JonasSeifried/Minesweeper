package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Tile}
import de.htwg.se.minesweeper.model.Difficulty
import org.scalatest._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class OpenCommandSpec extends AnyWordSpec with Matchers {
  "An OpenCommand" when {
    "executed on a Field" should {
      val sizeX = 5
      val sizeY = 5
      val field = new Field(sizeX, sizeY, Difficulty.Easy)
      val command = new OpenCommand(2, 2)

      "open the specified tile" in {
        val newField = command.doStep(field)
        newField.getTile(2, 2).isHidden shouldBe false
      }

      "close the previously opened tile when undone" in {
        val openedField = command.doStep(field)
        val closedField = command.undoStep(openedField)
        closedField.getTile(2, 2).isHidden shouldBe true
      }

      "open the previously closed tile when redone" in {
        val openedField = command.doStep(field)
        val closedAndOpenedField = command.undoStep(openedField)
        val reopenedField = command.redoStep(closedAndOpenedField)
        reopenedField.getTile(2, 2).isHidden shouldBe false
      }
    }
  }
}
