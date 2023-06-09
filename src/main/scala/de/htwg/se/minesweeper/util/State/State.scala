package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface

abstract class State(controller: ControllerInterface):

  def fieldToString: String

  def gameOver: Boolean = controller.field.openBombExists

  def gameWon: Boolean = !gameOver && getCountOfUnopenedTiles == 0

  def isInGameState: Boolean = false

  def isPreGameState: Boolean = false

  def isPostGameState: Boolean = false

  def getCountOfUnopenedTiles: Int = controller.field.getCountOfUnopenedTiles










