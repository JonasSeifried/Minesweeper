package de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl

import de.htwg.se.minesweeper.model.Difficulty.Easy
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class FieldCreatorSpec extends AnyWordSpec:
  "A Field Creator" should {
    "Create an Minesweeper Field" in {
      val fieldCreator = new FieldCreator
      val field = fieldCreator.createField(3, 3, Easy)
      field.getTile(0, 0).isHidden should be(true)

    }
  }
