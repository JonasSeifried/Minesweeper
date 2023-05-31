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
      val controller = Controller(field)
      controller.state = InGameState(controller)

      "have a renewField function" in {
        val newField = controller.renewField()
        newField should not be field
        newField.rowSize should be(field.rowSize)
        newField.colSize should be(field.colSize)
      }
      "printed the Field with the toString-Methode" in {
        controller.toString should be(field.toString)
      }
      "get if Tile is hidden" in {
        controller.openTile(0,0)
        controller.getTileIsHidden(0,0) should be (false)
      }
      "get if a Tile is a Bomb" in {
        controller.field = controller.field.replaceTile(0,0, Tile(true, 0, false, false))
        controller.getTileIsBomb(0,0) should be (true)
      }
      "get a Tile" should {
        "if out of bounds return null" in {
          controller.getTile(100,100) should be(null)
        }
      }
      "saving and restoring" should {
        "saving the game" in {
          controller.saveGame() should be(true)
        }
        "restore game" in {
          val field = controller.field
          controller.restoreGame()
          controller.field should be(field)
        }
      }
      "set the state to PostGameState when the game is won" in {
        var gameWon = true // Assume game won initially
        import scala.util.control.Breaks._
        // Open all tiles except for one bomb tile
        breakable {
          for (i <- 0 until controller.getRowSize) {
            for (j <- 0 until controller.getColSize) {
              if (!controller.getTileIsBomb(i, j)) {
                controller.openTile(i, j)
                if (!controller.gameWon) {
                  gameWon = false // Set the flag to indicate game not won
                  break() // Exit the inner loop
                }
              }
            }
            if (!gameWon) break() // Exit the outer loop if game not won
          }
        }
        controller.state.isPostGameState should be(true)
      }
    }
  }
}
