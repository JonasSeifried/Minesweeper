package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.{Field, Tile}

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Minesweeper Controller" when {
    "new" should {
      val field = new Field(10, 10, 10)
      val controller = Controller(field)

      "have a field" in {
        controller.field should be(field)
      }

      "have a renewField function" in {
        val newField = controller.renewField
        newField should not be field
        newField.rowSize should be(field.rowSize)
        newField.colSize should be(field.colSize)
      }

      "be able to open a tile" in {
        val x = 0
        val y = 0
        controller.openTile(x, y)
        controller.field.tiles(x)(y).isOpened should be(true)
      }

      "be able to flag a tile" in {
        val x = 0
        val y = 0
        controller.flagTile(x, y)
        controller.field.tiles(x)(y).isFlagged should be(true)
      }
    }
  }
}
