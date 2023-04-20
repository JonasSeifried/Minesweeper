package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.model.{CoordinateManager, Field, FieldCreator}

class Tui {
  private val coordManager = new CoordinateManager
  private val fieldCreator = new FieldCreator
  def processInput(input: String, field:Field): Field =
    input(0) match
      case 'r' => fieldCreator.createField(field)
      case 'o' => openOrFlag(input,true, field)
      case 'f' => openOrFlag(input, false, field)
      case _ => field

  private def openOrFlag(input: String, open: Boolean, field: Field): Field =
    if (input.length < 4 ) field
    else
      val coords = coordManager.decrypt(input.substring(2))
      println(coords)
      if (!checkInBound(coords, field)) field
      else if (open) field.openTile(coords(0), coords(1))
      else field.flagTile(coords(0), coords(1))

  private def checkInBound(coords: (Int, Int), field: Field): Boolean =
    coords(0) > field.rowSize || coords(0) < 0 || coords(1) > field.colSize || coords(1) < 0 || !field.getTile(coords(0), coords(1)).isHidden

}