
package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class TileMatrixSpec extends AnyWordSpec:
    "A Tile Matrix contains a two-dimensional Vector of Tiles. A Matrix" when {
      "empty" should {
        "be created by using 2 dimensions" in {
          val matrix = new TileMatrix(5,8)
          matrix.rowsSize should be(5)
          matrix.columnsSize should be(8)
        }
        "for test purposes only be created with a Vector of Vectors" in {
          val testMatrix = TileMatrix(Vector(Vector(Tile.Bomb)))
          testMatrix.columnsSize should be(1)
        }
      }
      "filled" should {
        val matrix = TileMatrix(Vector(Vector(Tile.Bomb, Tile.Flag), Vector(Tile.Hidden, Tile.Empty)))
        "give access to its tiles" in {
          matrix.getTile(1,1) shouldBe a [Tile]
        }
        "replace a tile and return a new data structure" in {
          val changedMatrix = matrix.replaceTile(0,0, Tile.Flag)
          matrix.getTile(0,0) shouldBe a [Tile]
          changedMatrix.getTile(0,0) should be(Tile.Flag)
        }
        "custom toString that returns a String" in {
          matrix.toString should be("\uf1e2\udb80\ude3b\n\uea720\n")
        }
      }
    }
