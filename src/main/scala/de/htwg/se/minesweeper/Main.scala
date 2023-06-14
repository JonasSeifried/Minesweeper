package de.htwg.se.minesweeper

import aview.{Tui, Gui}
import model.SaveManager
import controller.Controller
import de.htwg.se.minesweeper.model.Difficulty.{Easy, Hard}
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field

import scala.io.StdIn.readLine

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")
  val controller = Controller(new Field(5, 5, Hard).renewField)
  val tui = Tui(controller)
  val gui = Gui(controller)
  tui.run()
