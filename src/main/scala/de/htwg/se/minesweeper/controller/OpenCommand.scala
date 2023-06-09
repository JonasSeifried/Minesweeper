package de.htwg.se.minesweeper
package controller

import model.Field
import util.Command
import util.UndoManager

class OpenCommand(x: Int, y: Int) extends Command[Field]:
  override def doStep(field: Field): Field = field.openTile(x, y)
  override def undoStep(field: Field): Field = field.closeTile(x, y)
  override def redoStep(field: Field): Field = field.openTile(x, y)