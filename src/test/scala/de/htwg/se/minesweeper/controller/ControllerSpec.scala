import de.htwg.se.minesweeper.controller.{Controller, FlagCommand}
import de.htwg.se.minesweeper.model.{Difficulty, Field, Tile}
import de.htwg.se.minesweeper.util.State.{InGameState, PostGameState}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Minesweeper Controller" when {
    "new" should {
      val field = new Field(10, 10, Difficulty.Easy)
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
        "save the game" in {
          controller.saveGame() should be(true)
        }
        "restore the game" in {
          val field = controller.field
          controller.restoreGame()
          controller.field should be(field)
        }
      }
      "undo the last step" should {
        "return true and revert the field" in {
          val flagCommand = new FlagCommand(3, 5)
          val initialField = controller.field
          val flaggedField = flagCommand.doStep(initialField)
          controller.field = flaggedField

          val result = controller.undo

          result should be(true)
          controller.field should be(initialField)
        }

        "return false if there is no previous step" in {
          val initialField = controller.field

          val result = controller.undo

          result should be(false)
          controller.field should be(initialField)
        }
      }
      "flag a tile" should {
        "return true and flag the tile" in {
          val initialField = controller.field
          val x = 3
          val y = 5

          val result = controller.flagTile(x, y)

          result should be(true)
          controller.field.getTile(x, y).isFlagged should be(true)
        }

        "return false if the coordinates are out of bounds" in {
          val initialField = controller.field
          val x = 100
          val y = 100

          val result = controller.flagTile(x, y)

          result should be(false)
          controller.field should be(initialField)
        }

        "update the game state to PostGameState if game is won" in {
          val initialField = controller.field
          val x = 3
          val y = 5

          // Set the remaining unopened tiles to bombs
          for {
            row <- 0 until controller.getRowSize
            col <- 0 until controller.getColSize
            if !(row == x && col == y)
          } {
            controller.field = controller.field.replaceTile(row, col, Tile(true, 0, false, false))
          }

          val result = controller.flagTile(x, y)

          result should be(true)
          controller.state should be(PostGameState(controller))
        }
      }
    }
  }
}
