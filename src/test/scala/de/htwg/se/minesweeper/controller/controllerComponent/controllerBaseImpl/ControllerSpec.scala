package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Tile}
import de.htwg.se.minesweeper.util.State.{InGameState, PostGameState}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers:

  "A Minesweeper Controller" when {
    "new" should {
      val field = new Field(10, 10, Easy)
      val controller = Controller(field)
      controller.state = InGameState(controller)

      "have a renewField function" in {
        controller.renewField
        controller.field should not be field
      }

      "printed the Field with the toString-Methode" in {
        controller.toString should be(field.toString)
      }

      "get if Tile is hidden" in {
        controller.openTile(0, 0)
        controller.getTileIsHidden(0, 0) should be(false)
      }

      "get if a Tile is a Bomb" in {
        controller.field = field.replaceTile(0, 0, Tile(true, 0, false, false))
        controller.getTileIsBomb(0, 0) should be(true)
      }
      "saving and restoring" should {
        "saving the game" in {
          controller.saveGame should be(true)
        }
        "restore game" in {
          controller.restoreGame should be(true)
        }
      }
      "set the state to PostGameState when the game is won" in {
        var gameWon = true
        import scala.util.control.Breaks.*
        breakable {
          controller.state = InGameState(controller)
          for (i <- 0 until controller.getRowSize)
            for (j <- 0 until controller.getColSize)
              if (!controller.getTileIsBomb(i, j))
                controller.openTile(i, j)
                if (!controller.gameWon)
                  gameWon = false
                  break()
            if (!gameWon) break()
          controller.flagTile(0, 0)
          controller.state = PostGameState(controller)
          controller.state.isPostGameState should be(true)
        }

      }
      "get a Tile" should {
        "if out of bounds return null" in {
          controller.getTile(100, 100) should be(null)
        }
      }

      "flag a Tile" should {
        "return false if the given coordinates are out of bounds" in {
          controller.flagTile(-1, 0) should be(false)
          controller.flagTile(0, -1) should be(false)
          controller.flagTile(controller.getRowSize, 0) should be(false)
          controller.flagTile(0, controller.getColSize) should be(false)
        }

        "return true and flag the tile at the given coordinates" in {
          val tile = controller.getTile(0, 0)
          controller.flagTile(0, 0) should be(true)
          val fieldString = controller.toString
        }

        "open a Tile" should {
          "return false if the given coordinates are out of bounds" in {
            controller.openTile(-1, 0) should be(false)
            controller.openTile(0, -1) should be(false)
            controller.openTile(controller.getRowSize, 0) should be(false)
            controller.openTile(0, controller.getColSize) should be(false)
          }

          "return true and open the tile at the given coordinates" in {
            val tile = controller.getTile(0, 0)
            controller.openTile(0, 0) should be(true)
            val fieldString = controller.toString
          }
        }

        "change the state to PostGameState when a tile is flagged and the game is won" in {
          controller.state = InGameState(controller)
          for (i <- 0 until controller.getRowSize)
            for (j <- 0 until controller.getColSize)
              if (!controller.getTileIsBomb(i, j))
                controller.openTile(i, j)
          controller.flagTile(0, 0)
          controller.state.isPostGameState should be(true)
        }
      }
    }
  }
