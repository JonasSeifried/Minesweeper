package de.htwg.se.minesweeper.util.State

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface

class PostGameState(controller: ControllerInterface) extends State(controller):

  override def isPostGameState: Boolean = true
  override def fieldToString: String =
  //Todo openAllTiles
    controller.field.toString
