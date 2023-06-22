package de.htwg.se.minesweeper.model.fileIO

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface

trait FileIOInterface:
  def save(field: FieldInterface): Boolean

  def load: FieldInterface

