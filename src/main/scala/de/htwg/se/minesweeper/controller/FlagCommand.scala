package de.htwg.se.minesweeper
package controller

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import util.Command
import util.UndoManager


class FlagCommand(x: Int, y: Int) extends Command[FieldInterface]:
  override def doStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)
  override def undoStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)
  override def redoStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)