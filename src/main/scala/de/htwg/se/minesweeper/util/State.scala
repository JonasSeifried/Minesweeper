package de.htwg.se.minesweeper.util

import de.htwg.se.minesweeper.controller.Controller

abstract class State(controller: Controller):

  def fieldToString: String

  def gameOver: Boolean = controller.field.tiles.rows.flatten.exists(tile => tile.isBomb && !tile.isHidden)

  def gameWon: Boolean = !gameOver && getCountOfUnopenedTiles == 0

  def isInGameState: Boolean = false

  def isPreGameState: Boolean = false

  def isPostGameState: Boolean = false

  def getCountOfUnopenedTiles: Int = controller.field.getCountOfUnopenedTiles




class PreGameState(controller: Controller) extends State(controller):
  override def getCountOfUnopenedTiles: Int = -1

  override def gameOver: Boolean = false

  override def gameWon: Boolean = false

  override def isPreGameState: Boolean = true

  override def fieldToString: String = ""


class InGameState(controller: Controller) extends State(controller):

  override def isInGameState: Boolean = true
  override def fieldToString: String = controller.field.toString


class PostGameState(controller: Controller) extends State(controller):

  override def isPostGameState: Boolean = true
  override def fieldToString: String =
  //Todo openAllTiles
    controller.field.toString
