package de.htwg.se.minesweeper.controller

import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.model.{Difficulty, Field, FieldCreator, Tile}

case class Controller(var field: Field, difficulty: Difficulty) extends Observable {
  private val fieldCreator = new FieldCreator

  def openTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = field.openTile(x, y)
      notifyObservers()
      true
    }

  def flagTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = field.flagTile(x, y)
      notifyObservers()
      true
    }

  def getTile(row: Int, col: Int): Tile =
    if (isOutOfBounds(row, col)) null
    else field.getTile(row, col)

  def getColSize: Int = field.colSize

  def getRowSize: Int = field.rowSize

  def getTileIsHidden(row: Int, col: Int): Boolean = getTile(row, col).isHidden

  def getTileIsBomb(row: Int, col: Int): Boolean = getTile(row, col).isBomb

  def getUnopenedTiles: Int = field.getUnopenedTiles

  def isGameEnd: Boolean = field.isGameEnd

  def hasWon: Boolean = field.hasWon

  def renewField(): Field = {
    field = fieldCreator.createField(new Field(field.rowSize, field.colSize, difficulty))
    notifyObservers()
    field
  }

  private def isOutOfBounds(x: Int, y: Int): Boolean =
    x >= getRowSize || x < 0 || y >= getColSize || y < 0

  override def toString: String = field.toString
}
