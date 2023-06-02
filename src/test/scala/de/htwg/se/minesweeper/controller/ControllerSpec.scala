package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.Difficulty.Easy
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.{Field, Tile}
import de.htwg.se.minesweeper.util.State.InGameState

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Minesweeper Controller" when {
    "new" should {
      val field = new Field(10, 10, Easy)
      var controller: Option[Controller] = None

      "have a renewField function" in {
        controller = Some(Controller(field))
        controller.foreach { c =>
          c.state = InGameState(c)
          val newField = c.renewField()
          newField should not be field
          newField.rowSize should be(field.rowSize)
          newField.colSize should be(field.colSize)
        }
      }

      "printed the Field with the toString-Methode" in
        controller.foreach(c => c.toString should be(field.toString))


      "get if Tile is hidden" in
        controller.foreach { c =>
          c.openTile(0,0)
          c.getTileIsHidden(0,0) should be (false)
        }

      "get if a Tile is a Bomb" in
        controller.foreach { c =>
          c.field = c.field.replaceTile(0,0, Tile(true, 0, false, false))
          c.getTileIsBomb(0,0) should be (true)
        }

      "get a Tile" should {
        "if out of bounds return null" in {
          controller.foreach(c => c.getTile(100,100) should be(null))
        }
      }

      "saving and restoring" should {
        "saving the game" in {
          controller.foreach(c => c.saveGame() should be(true))
        }
        "restore game" in {
          controller.foreach { c =>
            val field = c.field
            c.restoreGame()
            c.field should be(field)
          }
        }
      }

      "set the state to PostGameState when the game is won" in {
        var gameWon = true
        import scala.util.control.Breaks._
        controller.foreach { c =>
          c.state = InGameState(c)
          breakable {
            for (i <- 0 until c.getRowSize) {
              for (j <- 0 until c.getColSize) {
                if (!c.getTileIsBomb(i, j)) {
                  c.openTile(i, j)
                  if (!c.gameWon) {
                    gameWon = false
                    break()
                  }
                }
              }
              if (!gameWon) break()
            }
          }
        }
        controller.foreach(c => c.state.isPostGameState should be(true))
      }

      "flag a Tile" should {
        "return false if the given coordinates are out of bounds" in
          controller.foreach { c =>
            c.flagTile(-1, 0) should be(false)
            c.flagTile(0, -1) should be(false)
            c.flagTile(c.getRowSize, 0) should be(false)
            c.flagTile(0, c.getColSize) should be(false)
          }

        "return true and flag the tile at the given coordinates" in
          controller.foreach { c =>
            val tile = c.getTile(0, 0)
            c.flagTile(0, 0) should be(true)
            val fieldString = c.toString
          }
      }
    }
  }
}
