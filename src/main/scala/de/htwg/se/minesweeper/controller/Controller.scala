package de.htwg.se.minesweeper
package controller

import util.Observable
import model.{Field, FieldCreator, Tile}
case class Controller(var field: Field) extends Observable:
  private val fieldCreator = new FieldCreator
  def openTile(x: Int, y: Int): Boolean =
    if(isOutOfBounds(x,y)) false
    else
      field = field.openTile(x, y)
      notifyObservers()
      true

  def flagTile(x: Int, y: Int): Boolean =
    if(isOutOfBounds(x,y)) false
    else
      field = field.flagTile(x, y)
      notifyObservers()
      true

  def getTile(row: Int, col: Int): Tile =
    if(isOutOfBounds(row,col)) null
    else field.getTile(row, col)

  def getColSize: Int = field.colSize
  def getRowSize: Int = field.rowSize
  def getTileIsHidden(row: Int, col: Int): Boolean = getTile(row, col).isHidden
  def getTileIsBomb(row: Int, col: Int) : Boolean = getTile(row, col).isBomb
  def renewField : Field =
    field = fieldCreator.createField(new Field(field.rowSize, field.colSize))
    field
  private def isOutOfBounds(coords: (Int, Int)): Boolean =
    coords(0) >= getRowSize || coords(0) < 0 || coords(1) >= getColSize || coords(1) < 0

  override def toString: String = field.toString
