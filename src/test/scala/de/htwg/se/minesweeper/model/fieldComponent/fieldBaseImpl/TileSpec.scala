package de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

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
      "when 0 bombs around it" should {
        val tile = Tile(false, 0, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("0️⃣")
        }
      }
      "when 1 bombs around it" should {
        val tile = Tile(false, 1, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("1️⃣")
        }
      }
      "when 2 bombs around it" should {
        val tile = Tile(false, 2, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("2️⃣")
        }
      }
      "when 3 bombs around it" should {
        val tile = Tile(false, 3, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("3️⃣")
        }
      }
      "when 4 bombs around it" should {
        val tile = Tile(false, 4, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("4️⃣")
        }
      }
      "when 5 bombs around it" should {
        val tile = Tile(false, 5, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("5️⃣")
        }
      }
      "when 6 bombs around it" should {
        val tile = Tile(false, 6, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("6️⃣")
        }
      }
      "when 7 bombs around it" should {
        val tile = Tile(false, 7, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("7️⃣")
        }
      }
      "when 8 bombs around it" should {
        val tile = Tile(false, 8, false, false)
        "Should be a Number as a String" in {
          tile.toString should be("8️⃣")
        }
      }

    }
  }
}
