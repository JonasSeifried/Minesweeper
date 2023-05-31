package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.model.{Difficulty, Field, Tile}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterEach

class OpenCommandSpec extends AnyFlatSpec with Matchers with BeforeAndAfterEach {
  private var field: Field = _

  override protected def beforeEach(): Unit = {
    field = new Field(5, 5, Difficulty.Easy)
  }

  "An OpenCommand" should "open a tile at the specified coordinates when executing the doStep method" in {
    val command = new OpenCommand(2, 2)
    val updatedField = command.doStep(field)
    updatedField.getTile(2, 2).isOpened shouldBe true
  }

  it should "close a previously opened tile when executing the undoStep method" in {
    val command = new OpenCommand(2, 2)
    field = command.doStep(field)
    val updatedField = command.undoStep(field)
    updatedField.getTile(2, 2).isOpened shouldBe false
  }

  it should "open a tile again at the specified coordinates when executing the redoStep method" in {
    val command = new OpenCommand(2, 2)
    field = command.doStep(field)
    val updatedField = command.undoStep(field)
    val redoUpdatedField = command.redoStep(updatedField)
    redoUpdatedField.getTile(2, 2).isOpened shouldBe true
  }
}
