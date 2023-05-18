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
      "State is Ingame" should {
        controller.state = InGameState(controller)
        "State is PreGame" in {
          controller.isPreGameState should be(false)
        }
        "State is InGame" in {
          controller.isInGameState should be(true)
        }
        "State is PostGame" in {
          controller.isPostGameState should be(false)
        }
      }
      "saving and restoring" should {
        "saving the game" in {
          controller.saveGame() should be(true)
        }
        "restore game" in {
          val field = controller.field
          controller.renewField()
          controller.restoreGame()
          controller.field should be(field)
        }
      }
    }
  }
}