package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.Controller

abstract class State(controller: Controller):

  def fieldToString: String

  def gameOver: Boolean = controller.field.tiles.rows.flatten.exists(tile => tile.isBomb && !tile.isHidden)

  def gameWon: Boolean = !gameOver && getCountOfUnopenedTiles == 0

  def isInGameState: Boolean = false

  def isPreGameState: Boolean = false

  def isPostGameState: Boolean = false

  def getCountOfUnopenedTiles: Int = controller.field.getCountOfUnopenedTiles










