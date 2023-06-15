package de.htwg.se.minesweeper

import aview.{Gui, Tui}
import com.google.inject.Guice
import model.SaveManager
import de.htwg.se.minesweeper.model.Difficulty.{Easy, Hard}
import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface

import scala.io.StdIn.readLine

object Minesweeper:
  private val injector = Guice.createInjector(new MinesweeperModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])

  @main def main(): Unit =
    println("Hello world my project is Minesweeper!")
    val tui = Tui(controller)
    val gui = Gui(controller)
    tui.run()
