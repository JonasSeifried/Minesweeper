package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface

class InGameState(controller: ControllerInterface) extends State(controller):

  override def isInGameState: Boolean = true

  override def fieldToString: String = controller.field.toString