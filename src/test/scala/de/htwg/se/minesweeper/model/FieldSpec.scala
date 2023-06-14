package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Tile}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldSpec extends AnyWordSpec {
  "A Minesweeper Field" when {
    "Filled with 3x3 Hidden Tiles" should {
      val field3x3 = new Field(3, 3, Easy)

      "have size 3x3" in {
        field3x3.colSize should be(3)
        field3x3.rowSize should be(3)
      }

      "replace Tiles and return new field" in {
        val replacedField = field3x3.replaceTile(1, 1, Tile(true, 0, false, false))
        field3x3.getTile(1, 1).toString should be("⬜")
        replacedField.getTile(1, 1).toString should be("💣")
      }

      "show tiles (make them visible)" in {
        val updatedField = field3x3.openTile(1, 1)
        updatedField.getTile(1, 1).toString should be("0️⃣")
      }

      "flag tiles" in {
        val flaggedField = field3x3.flagTile(1, 1)
        flaggedField.getTile(1, 1).toString should be("🚩")
      }

      /*"print field" in {
        val field2x2 = new Field(2, 2, Easy).replaceTile(0, 0, Tile(true, 0, false, false))
        field2x2.toString should be("0💣⬜\n1⬜⬜\n  a b")
      }*/

      "get count of unopened tiles" in {
        field3x3.getCountOfUnopenedTiles should be(9)
        val updatedField = field3x3.openTile(1, 1)
        updatedField.getCountOfUnopenedTiles should be(0)
      }

      "not open flagged tiles" in {
        val flaggedField = field3x3.flagTile(1, 1)
        val updatedField = flaggedField.openTile(1, 1)
        updatedField.getTile(1, 1).toString should be("🚩")
      }

      "not replace already visible tiles" in {
        val updatedField = field3x3.openTile(1, 1)
        val replacedField = updatedField.replaceTile(1, 1, Tile(true, 0, false, false))
        replacedField.getTile(1, 1) should be(Tile(true, 0, false, false))
      }
    }
  }
}
