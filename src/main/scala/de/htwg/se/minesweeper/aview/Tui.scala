package de.htwg.se.minesweeper
package aview

import util.{CoordinateManager, InGameState, Observer}
import controller.Controller

import scala.annotation.tailrec

class Tui(controller: Controller) extends Observer {
  private val coordManager = new CoordinateManager
  controller.add(this)

  override def update(): Unit = {
    println("Anzahl unentdeckter Felder: " + controller.getUnopenedTiles)
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

  private def inputLoop(): Boolean = {
    val input = scala.io.StdIn.readLine
    if (processInput(input)) {
      if(controller.isPostGameState) return false
      println(controller)
    }
    if (input.isEmpty || input(0) != 'q') return inputLoop()
    true
  }

  def processInput(input: String): Boolean =
    if (input.isEmpty) false
    else
      input(0) match {
        case 'r' =>
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
        case 'h' =>
          println(helpText)
          false
        case _ =>
          println("Unknown command")
          false
      }

  private def openOrFlag(input: String, open: Boolean): Boolean =
    if (input.length < 4 || input.length > 5) false
    else {
      val coords = coordManager.decrypt(input.substring(2))
      if (open)
        controller.openTile(coords._1, coords._2)
      else
        controller.flagTile(coords._1, coords._2)
    }

  private val helpText =
    """
      |Minesweeper man
      |-----------------------------------------
      |h              - Opens Minesweeper man
      |o [a-z0-99]    - Open a Tile
      |f [a-z0-99]    - Flag a Tile
      |r              - Restart with a new Field
      |q              - Quit the game
      |-----------------------------------------
      |""".stripMargin
}

