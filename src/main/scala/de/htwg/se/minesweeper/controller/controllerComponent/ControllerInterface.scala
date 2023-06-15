package de.htwg.se.minesweeper.controller.controllerComponent

import de.htwg.se.minesweeper.model.fieldComponent.{FieldInterface, TileInterface}
import de.htwg.se.minesweeper.util.Observable
import de.htwg.se.minesweeper.util.State.State

trait ControllerInterface extends Observable:
  def openTile(x: Int, y: Int): Boolean
  def flagTile(x: Int, y: Int): Boolean

  def field: FieldInterface
  def getState: State
  def setState(state: State): State
  def undo: Boolean
  def redo: Boolean
  def quit(): Unit
  def getTile(row: Int, col: Int): TileInterface
  def getColSize: Int
  def getRowSize: Int
  def getTileIsHidden(row: Int, col: Int): Boolean
  def getTileIsBomb(row: Int, col: Int): Boolean
  def getTileIsFlagged(row: Int, col: Int): Boolean
  def getCountOfUnopenedTiles: Int
  def gameOver: Boolean
  def gameWon: Boolean
  def isInGameState: Boolean
  def isPreGameState: Boolean
  def isPostGameState: Boolean
  def renewField: FieldInterface
  def restoreGame: Boolean
  def saveGame: Boolean
