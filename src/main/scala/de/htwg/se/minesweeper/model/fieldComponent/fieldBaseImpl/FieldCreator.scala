package de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl


import de.htwg.se.minesweeper.model.Difficulty.Difficulty

import scala.util.Random

class FieldCreator:

  def createField(sizeX: Int, sizeY: Int, difficulty: Difficulty): Field = {
    updateTile(0, 0, updateTile(0, 0, new Field(sizeX, sizeY, difficulty), false), true)

  }

  private def updateTile(row: Int, col: Int, field: Field, addBombCount: Boolean): Field =
    if (row == field.rowSize || (col == field.colSize && row + 1 == field.rowSize)) return field

    val newField =
      if (addBombCount) field.replaceTile(row, col, Tile(
          field.getTile(row, col).isBomb,
          countBombs(row, col, field),
          true,
          false))
      else if (Random.nextInt(8) == 0)
        field.replaceTile(row, col, Tile(true, 0, true, false))
      else
        field.replaceTile(row, col, Tile(false, 0, true, false))

    if (col + 1 == field.colSize && row + 1 != field.rowSize) return updateTile(row + 1, 0, newField, addBombCount)
    updateTile(row, col + 1, newField, addBombCount)

  private def countBombs(row: Int, col: Int, field: Field): Int =
    var c = 0
    for (i <- Math.max(row - 1, 0) to Math.min(row + 1, field.rowSize-1)) do
      for (j <- Math.max(col - 1, 0) to Math.min(col + 1, field.colSize-1)) do
        if ((i != row || j != col) && field.getTile(i, j).isBomb) c += 1
    c

