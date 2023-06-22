package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.util.Command


class FlagCommand(x: Int, y: Int) extends Command[FieldInterface]:
  override def doStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)

  override def undoStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)

  override def redoStep(field: FieldInterface): FieldInterface = field.flagTile(x, y)