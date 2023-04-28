package de.htwg.se.minesweeper
package controller

import util.Observable
import model.{Field, FieldCreator, Tile}
case class Controller(var field: Field) extends Observable:
  private val fieldCreator = new FieldCreator
  def openTile(x: Int, y: Int): Unit =
    field = field.openTile(x, y)
    notifyObservers()

  def flagTile(x: Int, y: Int): Unit =
    field = field.flagTile(x, y)
    notifyObservers()

  def getColSize = field.colSize
  def getRowSize = field.rowSize

  def getTileIsHidden(x: Int, y: Int) = field.getTile(x, y).isHidden

  def renewField : Field =
    field = fieldCreator.createField(new Field(field.rowSize, field.colSize))
    field
  override def toString: String = field.toString
