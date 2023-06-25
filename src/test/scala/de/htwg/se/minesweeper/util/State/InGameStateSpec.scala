package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Tile}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class InGameStateSpec extends AnyWordSpec:

  "The default State should" when {

    val field = new Field(10, 10, Easy)
    val controller = Controller(field)
    controller.state = InGameState(controller)
    "methods" should {
      "printed the Field with the toString-Methode" in {
        controller.toString should be(controller.field.toString)
      }
      "check State" in {
        controller.isInGameState should be(true)
        controller.isPreGameState should be(false)
        controller.isPostGameState should be(false)
      }
      "getCountOfUnopenedTiles" in {
        controller.getCountOfUnopenedTiles should be(controller.field.getCountOfUnopenedTiles)
      }
      "gameOver" in {
        controller.gameOver should be(false)
      }
      "gameWon" in {
        controller.gameWon should be(false)
      }
    }
  }


