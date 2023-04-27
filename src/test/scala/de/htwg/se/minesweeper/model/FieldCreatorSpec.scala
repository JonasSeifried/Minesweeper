package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldCreatorSpec extends AnyWordSpec {
  "A Field Creator" should {
    "Create an Minesweeper Field" in {
      val fieldCreator = new FieldCreator
      val field = fieldCreator.createField(new Field(3,3))
      field.getTile(0,0).isHidden should be(true)

    }
  }
}
