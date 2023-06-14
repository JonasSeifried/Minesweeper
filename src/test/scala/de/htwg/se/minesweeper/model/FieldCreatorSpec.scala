package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.{Field, FieldCreator}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldCreatorSpec extends AnyWordSpec {
  "A Field Creator" should {
    "Create an Minesweeper Field" in {
      val fieldCreator = new FieldCreator
      val field = fieldCreator.createField(new Field(3, 3, Easy))
      field.getTile(0,0).isHidden should be(true)

    }
  }
}
