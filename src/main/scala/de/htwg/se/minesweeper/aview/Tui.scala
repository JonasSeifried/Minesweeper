package de.htwg.se.minesweeper
package aview

import util.{CoordinateManager, Observer}
import controller.Controller
import de.htwg.se.minesweeper.util.State.InGameState

class Tui(controller: Controller) extends Observer {
  private val coordManager = new CoordinateManager
  controller.add(this)

  override def update(): Unit = {
    println("Anzahl unentdeckter Felder: " + controller.getCountOfUnopenedTiles)
    if(controller.isPostGameState)
      println(controller)
      if (controller.gameWon) println("Spiel gewonnen!")
      else if (controller.gameOver) println("Spiel verloren!")
  }
  def run(): Boolean =
    controller.state = InGameState(controller)
    println(controller)
    if (inputLoop()) true
    else false

  def inputLoop(): Boolean = {
    val input = scala.io.StdIn.readLine.replaceAll(" ", "")
    if (processInput(input)) {
      if (controller.isPostGameState) return false
      println(controller)
    }
    if (input.isEmpty || input(0) == 'q') return false
    inputLoop()
  }

  def processInput(input: String): Boolean =
    if (input.isEmpty) false
    else
      input(0) match {
        case 'n' =>
          controller.renewField()
          true
        case 'o' =>
          if (openOrFlag(input, true)) true
          else {
            println("Wrong usage of the open command")
            false
          }
        case 'f' =>
          if (openOrFlag(input, false)) true
          else {
            println("Wrong usage of the flag command")
            false
          }
        case 'q' =>
          println("Thanks for playing!")
          false
        case 's' =>
          if(controller.saveGame())
            println("Game Saved!")
            return false
          println("Couldn't save Game")
          false
        case 'l' =>
          if (controller.restoreGame())
            println("successfully loaded game")
            true
          else
            println("Failed to load game")
            false
        case 'r' =>
          controller.redo
        case 'u' =>
          controller.undo
        case 'h' =>
          println(helpText)
          false
        case _ =>
          println("Unknown command")
          false
      }

  private def openOrFlag(input: String, open: Boolean): Boolean =
    if (input.length < 3 || input.length > 4) false
    else {
      coordManager.decrypt(input.substring(1)) match
        case None => false
        case Some(coords) =>
          if (open) controller.openTile(coords._1, coords._2)
          else controller.flagTile(coords._1, coords._2)
    }

  private val helpText =
    """
      |Minesweeper man
      |-----------------------------------------
      |h              - Opens Minesweeper man
      |o [a-z0-99]    - Open a Tile
      |f [a-z0-99]    - Flag a Tile
      |n              - New Field
      |r              - Redo last Command
      |u              - Undo last Command
      |n              - New Field
      |s              - Save your current game
      |l              - Load game
      |q              - Quit the game
      |-----------------------------------------
      |""".stripMargin
}

