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


  def renewField : Field = fieldCreator.createField(new Field(field.rowSize, field.colSize))
  override def toString: String = field.toString
