package de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface
import de.htwg.se.minesweeper.model.SaveManager
import de.htwg.se.minesweeper.model.fieldComponent.{FieldInterface, TileInterface}
import de.htwg.se.minesweeper.util.State.{PostGameState, PreGameState, State}
import de.htwg.se.minesweeper.util.{Event, Observable, UndoManager}

case class Controller(var field: FieldInterface) extends ControllerInterface {
  private val undoManager = new UndoManager[FieldInterface]
  var state: State = PreGameState(this)

  def openTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = undoManager.doStep(field, OpenCommand(x, y))
      notifyObservers(Event.Move)
      if(gameWon || gameOver)
        state = PostGameState(this)
        notifyObservers(Event.GameOver)
      true
    }

  def flagTile(x: Int, y: Int): Boolean =
    if (isOutOfBounds(x, y)) false
    else {
      field = undoManager.doStep(field, FlagCommand(x, y))
      notifyObservers(Event.Move)
      if(gameWon)
        state = PostGameState(this)
        notifyObservers(Event.GameOver)
      true
    }

  def undo: Boolean =
    val oldField = field
    field = undoManager.undoStep(field)
    if (!(oldField eq field))
      notifyObservers(Event.Move)
      true
    else
      false

  def redo: Boolean =
    val oldField = field
    field = undoManager.redoStep(field)
    if (!(oldField eq field))
      notifyObservers(Event.Move)
      true
    else
      false

  def quit(): Unit = notifyObservers(Event.Quit)

  def getTile(row: Int, col: Int): TileInterface =
    if (isOutOfBounds(row, col)) return null
    field.getTile(row, col)

  def getColSize: Int = field.colSize

  def getRowSize: Int = field.rowSize

  def getTileIsHidden(row: Int, col: Int): Boolean = getTile(row, col).isHidden

  def getTileIsBomb(row: Int, col: Int): Boolean = getTile(row, col).isBomb

  def getTileIsFlagged(row: Int, col: Int): Boolean = getTile(row, col).isFlagged

  def getCountOfUnopenedTiles: Int = state.getCountOfUnopenedTiles

  def getState: State = state

  def setState(state: State): State =
    val oldState = this.state
    this.state = state
    oldState

  def gameOver: Boolean = state.gameOver

  def gameWon: Boolean = state.gameWon

  def isInGameState: Boolean = state.isInGameState

  def isPreGameState: Boolean = state.isPreGameState

  def isPostGameState: Boolean = state.isPostGameState

  def renewField: FieldInterface = {
    field = field.renewField
    notifyObservers(Event.Move)
    field
  }

  private def isOutOfBounds(x: Int, y: Int): Boolean =
    x >= getRowSize || x < 0 || y >= getColSize || y < 0


  def restoreGame: Boolean =
    val newField = SaveManager.restoreGame()
    if (newField == null) return false
    field = newField
    notifyObservers(Event.Move)
    true

  def saveGame: Boolean =
    SaveManager.saveGame(field)

  override def toString: String = state.fieldToString
}

