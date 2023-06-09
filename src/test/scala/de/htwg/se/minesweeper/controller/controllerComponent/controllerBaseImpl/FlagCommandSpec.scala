package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.FlagCommand
import de.htwg.se.minesweeper.model.Difficulty
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Matrix, Tile}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FlagCommandSpec extends AnyWordSpec with Matchers:
  "A FlagCommand" when {
    val difficulty = Difficulty.Easy
    val sizeX = 5
    val sizeY = 5
    val tiles = Matrix(Vector.tabulate(sizeX, sizeY)((_, _) => Tile(false, 0, true, false)))
    val field = fieldBaseImpl.Field(tiles, difficulty)
    val x = 2
    val y = 3
    val flagCommand = new FlagCommand(x, y)

    "executed with doStep" should {
      val result = flagCommand.doStep(field)

      "flag the tile at the specified coordinates" in {
        result.isTileFlagged(x, y) should be(true)
      }
    }

    "executed with undoStep after doStep" should {
      val intermediateResult = flagCommand.doStep(field)
      val undoResult = flagCommand.undoStep(intermediateResult)

      "remove the flag from the tile at the specified coordinates" in {
        undoResult.isTileFlagged(x, y) should be(false)
      }
    }

    "executed with redoStep after undoStep" should {
      val intermediateResult = flagCommand.doStep(field)
      val undoResult = flagCommand.undoStep(intermediateResult)
      val redoResult = flagCommand.redoStep(undoResult)

      "flag the tile at the specified coordinates again" in {
        redoResult.isTileFlagged(x, y) should be(true)
      }
    }
  }
