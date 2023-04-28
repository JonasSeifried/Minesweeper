package de.htwg.se.minesweeper
package aview

import util.Observer
import controller.Controller
import util.CoordinateManager

import scala.annotation.tailrec

class Tui(controller: Controller) extends Observer{
  private val coordManager = new CoordinateManager
  controller.add(this)

  override def update(): Unit = println("notified")

  def run(): Unit =
    println(controller)
    inputLoop()

  @tailrec
  private def inputLoop(): Unit =
    val input = scala.io.StdIn.readLine
    if(processInput(input))
      println(controller)
    if(input.isEmpty || input(0) !='q') inputLoop()

  def processInput(input: String): Boolean =
    if(input.isEmpty) false
    else
      input(0) match
        case 'r' =>
          controller.renewField
          true
        case 'o' =>
          if(openOrFlag(input,true)) true
          else
            println("Wrong usage of the open command")
            false
        case 'f' =>
          if(openOrFlag(input, false)) true
          else
            println("Wrong usage of the flag command")
            false
        case 'q' =>
          println("Thanks for playing!")
          false
        case 'h' =>
          println(helpText)
          false
        case _ =>
          println("Unknown command")
          false

  private def openOrFlag(input: String, open: Boolean): Boolean =
    if (input.length < 4 || input.length > 5 ) false
    else
      val coords = coordManager.decrypt(input.substring(2))
      if (notInBound(coords)) false
      else if (open)
        controller.openTile(coords(0), coords(1))
        true
      else
        controller.flagTile(coords(0), coords(1))
        true

  private def notInBound(coords: (Int, Int)): Boolean =
    coords(0) >= controller.getRowSize || coords(0) < 0 || coords(1) >= controller.getColSize || coords(1) < 0 || !controller.getTileIsHidden(coords(0), coords(1))

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