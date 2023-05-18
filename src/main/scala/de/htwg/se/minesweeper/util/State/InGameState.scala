package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.Controller

class InGameState(controller: Controller) extends State(controller):

  override def isInGameState: Boolean = true
  override def fieldToString: String = controller.field.toString