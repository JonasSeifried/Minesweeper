
package de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MatrixSpec extends AnyWordSpec:
  "A Matrix is a tailor-made immutable data type that contains a two-dimensional Vector of something. A Matrix" when {
    "empty " should {
      "be created by using a dimension and a sample cell" in {
        val matrix = new Matrix[String](2, 2, "x")
        matrix.colSize should be(2)
        matrix.rowSize should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector("x")))
        testMatrix.colSize should be(1)
      }

    }
    "filled" should {
      val matrix = new Matrix[String](2, 4, "x")
      "give access to its cells" in {
        matrix.cell(0, 0) should be("x")
      }
      "give access to its rows" in {
        matrix.row(0) should be(Vector("x", "x", "x", "x"))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, "o")
        matrix.cell(0, 0) should be("x")
        returnedMatrix.cell(0, 0) should be("o")
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill("x")
        returnedMatrix.cell(0, 0) should be("x")
      }
    }
  }

