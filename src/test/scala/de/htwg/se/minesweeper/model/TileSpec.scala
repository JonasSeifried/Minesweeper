package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TileSpec extends AnyWordSpec {
  "A Tile" when {
    "Hidden and not Flagged" should {
      val tileBH = Tile(true, 0, true, false)
      "Should be hidden as a String" in {
        tileBH.toString should be("⬜")
      }
    }
    "Hidden and Flagged" should {
      val tileHF = Tile(false, 0, true, true)
      "Should be a Flag as a String" in {
        tileHF.toString should be("🚩")
      }
    }
    "Bomb, not Hidden" should {
      val tileB = Tile(true, 0, false, false)
      "Should be a Bomb as a String" in {
        tileB.toString should be ("💣")
      }
    }
    "Not Bomb and not Hidden" should {
      val tile = Tile(false, 0, false, false)
      "Should be a Number as a String" in {
        tile.toString should be ("0️⃣")
      }
    }
  }
}
