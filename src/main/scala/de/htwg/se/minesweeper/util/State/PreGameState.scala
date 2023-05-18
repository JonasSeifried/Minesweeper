package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.Controller

class PreGameState(controller: Controller) extends State(controller):
  override def getCountOfUnopenedTiles: Int = -1

  override def gameOver: Boolean = false

  override def gameWon: Boolean = false

  override def isPreGameState: Boolean = true

  override def fieldToString: String = ""
