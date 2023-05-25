package de.htwg.se.minesweeper
package controller

import model.Field
import util.Command
import util.UndoManager


class FlagCommand(x: Int, y: Int) extends Command[Field]:
  override def noStep(field: Field): Field = field
  override def doStep(field: Field): Field = field.flagTile(x, y)
  override def undoStep(field: Field): Field = field.flagTile(x, y)
  override def redoStep(field: Field): Field = field.flagTile(x, y)