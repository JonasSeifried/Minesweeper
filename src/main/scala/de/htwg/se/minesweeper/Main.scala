package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.aview.Tui
import de.htwg.se.minesweeper.model.{Field, FieldCreator}

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")
  val tui = new Tui
  val fieldCreator = new FieldCreator

  var field = fieldCreator.createField(new Field(3,3))
  var input = ""

  while (input != "q")
    print(field)
    input = readLine()
    field = tui.processInput(input, field)