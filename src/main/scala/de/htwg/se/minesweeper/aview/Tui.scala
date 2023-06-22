package de.htwg.se.minesweeper
package aview

import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface
import util.{CoordinateManager, Event, Observer}
import de.htwg.se.minesweeper.util.State.InGameState

class Tui(controller: ControllerInterface) extends Observer:
  private val coordManager = new CoordinateManager
  private var running = true
  controller.add(this)

  override def update(e: Event): Unit = e match
    case Event.Move =>
      println("Anzahl unentdeckter Felder: " + controller.getCountOfUnopenedTiles)
      println(controller)
    case Event.GameOver =>
      if (controller.gameWon) println("Spiel gewonnen!")
      else if (controller.gameOver) println("Spiel verloren!")
      running = false
    case Event.Quit => running = false

  def run(): Boolean =
    controller.setState(InGameState(controller))
    println(controller)
    if (inputLoop()) true
    else false

  def inputLoop(): Boolean =
    val input = scala.io.StdIn.readLine.replaceAll(" ", "")
    if (!running) return false
    if (processInput(input))
      if (controller.isPostGameState) return false
    if (input.isEmpty) return false
    inputLoop()

  def processInput(input: String): Boolean =
    if (input.isEmpty) return false
    input(0) match
      case 'n' =>
        controller.renewField
        true
      case 'o' =>
        if (openOrFlag(input, true)) true
        else
          println("Wrong usage of the open command")
          false
      case 'f' =>
        if (openOrFlag(input, false)) true
        else
          println("Wrong usage of the flag command")
          false
      case 'q' =>
        println("Thanks for playing!")
        controller.quit()
        false
      case 's' =>
        if (controller.saveGame)
          println("Game Saved!")
          return false
        println("Couldn't save Game")
        false
      case 'l' =>
        if (controller.restoreGame)
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

  private def openOrFlag(input: String, open: Boolean): Boolean =
    if (input.length < 3 || input.length > 4) return false
    coordManager.decrypt(input.substring(1)) match
      case None => false
      case Some(coords) =>
        if (open)
          controller.openTile(coords._1, coords._2)
        else
          controller.flagTile(coords._1, coords._2)

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

