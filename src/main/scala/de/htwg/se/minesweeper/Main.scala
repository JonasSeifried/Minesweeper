package de.htwg.se.minesweeper

import aview.Tui
import model.{Field, FieldCreator, SaveManager}
import controller.Controller
import de.htwg.se.minesweeper.model.Difficulty.{Easy, Hard}

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")
  val fieldCreator = new FieldCreator
  val controller = Controller(fieldCreator.createField(new Field(15,15, Hard)))
  val tui = Tui(controller)
  tui.run()
