package de.htwg.se.minesweeper
package aview

import model.{CoordinateManager, Field, FieldCreator}
import util.Observer
import controller.Controller

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
    if(processInput(input, controller.field))
      println(controller)
    if(input.isEmpty || input(0) !='p') inputLoop()

  def processInput(input: String, field:Field): Boolean =
    if(input.isEmpty) false
    else
      input(0) match
        case 'r' =>
          controller.renewField
          true
        case 'o' =>
          if(openOrFlag(input,true, field)) true
          else
            println("Wrong usage of the open command")
            false
        case 'f' =>
          if(openOrFlag(input, false, field)) true
          else
            println("Wrong usage of the flag command")
            false
        case 'p' =>
          println("Thanks for playing!")
          false
        case _ =>
          println("Unknown command")
          false

  private def openOrFlag(input: String, open: Boolean, field: Field): Boolean =
    if (input.length < 4 || input.length > 5 ) false
    else
      val coords = coordManager.decrypt(input.substring(2))
      if (notInBound(coords, field)) false
      else if (open)
        controller.openTile(coords(0), coords(1))
        true
      else
        controller.flagTile(coords(0), coords(1))
        true

  private def notInBound(coords: (Int, Int), field: Field): Boolean =
    coords(0) >= field.rowSize || coords(0) < 0 || coords(1) >= field.colSize || coords(1) < 0 || !field.getTile(coords(0), coords(1)).isHidden

}