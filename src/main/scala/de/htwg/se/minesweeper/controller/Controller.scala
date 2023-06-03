package de.htwg.se.minesweeper
package controller

import de.htwg.se.minesweeper.util.State.{PostGameState, PreGameState, State}
import util.{Observable, UndoManager}
import model.{Field, FieldCreator, SaveManager, Tile}

case class Controller(var field: Field) extends Observable {
  private val undoManager = new UndoManager[Field]
  private val fieldCreator = new FieldCreator
  var state: State = PreGameState(this)

  def openTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = undoManager.doStep(field, OpenCommand(x, y))
      if(gameWon || gameOver) state = PostGameState(this)
      notifyObservers()
      true
    }

  def flagTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = undoManager.doStep(field, FlagCommand(x, y))
      if(gameWon) state = PostGameState(this)
      notifyObservers()
      true
    }

  def undo: Boolean =
    val oldField = field
    field = undoManager.undoStep(field)
    if (!(oldField eq field))
      notifyObservers()
      true
    else
      false

  def redo: Boolean =
    val oldField = field
    field = undoManager.redoStep(field)
    if (!(oldField eq field))
      notifyObservers()
      true
    else
      false

  def getTile(row: Int, col: Int): Tile =
    if (isOutOfBounds(row, col)) null
    else field.getTile(row, col)

  def getColSize: Int = field.colSize

  def getRowSize: Int = field.rowSize

  def getTileIsHidden(row: Int, col: Int): Boolean = getTile(row, col).isHidden

  def getTileIsBomb(row: Int, col: Int): Boolean = getTile(row, col).isBomb

  def getTileIsFlagged(row: Int, col: Int): Boolean = getTile(row, col).isFlagged

  def getCountOfUnopenedTiles: Int = state.getCountOfUnopenedTiles

  def gameOver: Boolean = state.gameOver

  def gameWon: Boolean = state.gameWon

  def isInGameState: Boolean = state.isInGameState

  def isPreGameState: Boolean = state.isPreGameState

  def isPostGameState: Boolean = state.isPostGameState

  def renewField(): Field = {
    field = fieldCreator.createField(new Field(field.rowSize, field.colSize, field.difficulty))
    notifyObservers()
    field
  }

  private def isOutOfBounds(x: Int, y: Int): Boolean =
    x >= getRowSize || x < 0 || y >= getColSize || y < 0


  def restoreGame(): Boolean =
    val newField = SaveManager.restoreGame()
    if (newField == null) return false
    field = newField
    true

  def saveGame(): Boolean =
    SaveManager.saveGame(field)

  override def toString: String = state.fieldToString
}

