package de.htwg.se.minesweeper.model.fileIO

import de.htwg.se.minesweeper.model.Difficulty._
import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.minesweeper.model.fileIO.fileIOXmlImpl.FileIO

class FileIOSpec extends AnyWordSpec with Matchers {
  val fileIO = new FileIO
  val testField: FieldInterface = new Field(10, 10, Easy)

  "FileIO" should {
    "save and load a field correctly" in {
      val saveResult = fileIO.save(testField)
      saveResult should be(true)

      val loadedField = fileIO.load
      loadedField should not be null

      loadedField.rowSize should be(testField.rowSize)
      loadedField.colSize should be(testField.colSize)
      for {
        row <- 0 until loadedField.rowSize
        col <- 0 until loadedField.colSize
      } {
        val originalTile = testField.getTile(row, col)
        val loadedTile = loadedField.getTile(row, col)
        loadedTile.isBomb should be(originalTile.isBomb)
        loadedTile.isHidden should be(originalTile.isHidden)
        loadedTile.isFlagged should be(originalTile.isFlagged)
        loadedTile.bombCount should be(originalTile.bombCount)
      }
    }

    "load a field of size 5 correctly" in {
      val smallField = new Field(5, 5, Easy)
      fileIO.save(smallField)

      val loadedField = fileIO.load
      loadedField should not be null
      loadedField.rowSize should be(smallField.rowSize)
      loadedField.colSize should be(smallField.colSize)
    }

    "load a field of size 15 correctly" in {
      val bigField = new Field(15, 15, Easy)
      fileIO.save(bigField)

      val loadedField = fileIO.load
      loadedField should not be null
      loadedField.rowSize should be(bigField.rowSize)
      loadedField.colSize should be(bigField.colSize)
    }
  }
}
