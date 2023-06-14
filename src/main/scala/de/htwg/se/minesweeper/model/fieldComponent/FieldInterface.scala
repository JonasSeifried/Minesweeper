package de.htwg.se.minesweeper.model.fieldComponent

import de.htwg.se.minesweeper.model.Difficulty.Difficulty


trait FieldInterface extends Serializable:

  def openTile(row: Int, col: Int): FieldInterface

  def closeTile(row: Int, col: Int): FieldInterface

  def flagTile(row: Int, col: Int): FieldInterface

  def renewField: FieldInterface

  def getTile(row: Int, col: Int): TileInterface

  def rowSize: Int

  def colSize: Int

  def getDifficulty: Difficulty

  def isTileFlagged(row: Int, col: Int): Boolean

  def openBombExists: Boolean

  def getCountOfUnopenedTiles: Int



trait TileInterface extends Serializable:
  def isBomb: Boolean
  def bombCount: Int
  def isHidden: Boolean
  def isFlagged: Boolean
