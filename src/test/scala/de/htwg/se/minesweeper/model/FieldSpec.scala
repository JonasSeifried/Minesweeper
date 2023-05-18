package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Difficulty.Easy
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldSpec extends AnyWordSpec {
  "A Minesweeper Field" when {
    "Filled with 3x3 Hidden Tiles" should {
      val field3x3 = new Field(3, 3, Easy)
      "Should have be 3x3" in {
        field3x3.colSize should be(3)
        field3x3.rowSize should be(3)
      }
      "replace Tiles and return new field" in {
        val replacedField = field3x3.replaceTile(1,1, Tile(true, 0, false, false))
        field3x3.getTile(1,1).toString should be("⬜")
        replacedField.getTile(1,1).toString should be("💣")
      }
      "Show Tiles (make them visible)" in {
        val sfield = field3x3.openTile(1,1)
        sfield.getTile(1,1).toString should be("0️⃣")
      }
      "Flag tiles" in {
        val ffield = field3x3.flagTile(1, 1)
        ffield.getTile(1, 1).toString should be("🚩")
      }
      "print field" in {
        val pfield = new Field(2, 2, Easy).replaceTile(0,0, Tile(true, 0, false, false))
        pfield.toString should be("0💣⬜\n1⬜⬜\n  a b")
      }
    }
  }
}
