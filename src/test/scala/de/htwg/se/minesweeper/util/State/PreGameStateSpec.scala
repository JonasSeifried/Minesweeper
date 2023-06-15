package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field

class PreGameStateSpec extends AnyWordSpec {

"The default State should" when {

  val field = new Field(10, 10, Easy)
  val controller = Controller(field)
  "methods" should {
    "printed the Field with the toString-Methode" in {
      controller.toString should be("")
    }
    "check State" in {
        controller.isPreGameState should be(true)
        controller.isInGameState should be(false)
    }
    "getCountOfUnopenedTiles" in  {
      controller.getCountOfUnopenedTiles should be(-1)
    }
    "gameOver" in {
      controller.gameOver should be(false)
    }
    "gameWon" in {
      controller.gameWon should be(false)
    }
  }
}
}


