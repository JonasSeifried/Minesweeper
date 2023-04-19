package de.htwg.se.minesweeper.model

import scala.util.Random

class FieldCreator {

  def createField(field: Field): Field = {

    updateTile(0,0, field)
  }

  private def updateTile(row: Int, col: Int, field: Field): Field =
    if (row == field.rowSize || col == field.colSize && row + 1 == field.rowSize) return field
    val newField = if (Random.nextInt(4) == 0) field.replaceTile(row, col, Tile(true, true)) else field
    if (col+1 == field.colSize && row + 1 != field.rowSize) return updateTile(row + 1, 0, newField)
    updateTile(row, col + 1, newField)


}
