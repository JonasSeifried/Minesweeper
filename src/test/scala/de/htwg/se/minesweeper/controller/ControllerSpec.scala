package de.htwg.se.minesweeper.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.{Field, Tile}

class ControllerSpec extends AnyWordSpec with Matchers {

  "A Minesweeper Controller" when {
    "new" should {
      val field = new Field(10, 10)
      val controller = Controller(field)

      "have a renewField function" in {
        val newField = controller.renewField
        newField should not be field
        newField.rowSize should be(field.rowSize)
        newField.colSize should be(field.colSize)
      }
      "printed the Field with the toString-Methode" in {
        val controllerfield = controller.toString
        val fieldtoString = field.toString

        controllerfield should be(fieldtoString)
      }
    }
  }
}