package de.htwg.se.minesweeper

import aview.Tui
import model.{Field, FieldCreator}
import controller.Controller

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")
  val fieldCreator = new FieldCreator
  val controller = Controller(fieldCreator.createField(new Field(10,10)))
  val tui = Tui(controller)
  tui.run()
  println("test")
