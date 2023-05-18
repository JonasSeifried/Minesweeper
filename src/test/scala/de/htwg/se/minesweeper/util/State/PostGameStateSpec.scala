package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.{Field, Tile}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class PostGameStateSpec extends AnyWordSpec {

"The default State should" when {

  val field = new Field(10, 10, Easy)
  val controller = Controller(field)
  controller.field = controller.field.replaceTile(0,0, Tile(true, 0, false, false))
  controller.state = PostGameState(controller)
  "methods" should {
    "printed the Field with the toString-Methode" in {
      controller.toString should be(controller.field.toString)
    }
    "check State" in {
        controller.isPostGameState should be(true)
    }
    "getCountOfUnopenedTiles" in  {
      controller.getCountOfUnopenedTiles should be(controller.field.getCountOfUnopenedTiles)
    }
    "gameOver" in {
      controller.gameOver should be(true)
    }
    "gameWon" in {
      controller.gameWon should be(false)
    }
  }
}
}


