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
    if (controller.isPostGameState) {
      println(controller)
      if (controller.gameWon) println("Spiel gewonnen!")
      else if (controller.gameOver) println("Spiel verloren!")
    }
  }

  def run(): Boolean = {
    controller.state = InGameState(controller)
    println(controller)
    if (inputLoop()) true
    else false
  }

  private def inputLoop(): Boolean = {
    val input = scala.io.StdIn.readLine()
    if (processInput(input)) {
      if (controller.isPostGameState) return false
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
            println("Falsche Verwendung des Öffnen-Befehls")
            false
          }
        case 'f' =>
          if (openOrFlag(input, false)) true
          else {
            println("Falsche Verwendung des Flaggen-Befehls")
            false
          }
        case 'q' =>
          println("Vielen Dank fürs Spielen!")
          false
        case 's' =>
          if (controller.saveGame())
            println("Spiel gespeichert!")
          else
            println("Spiel konnte nicht gespeichert werden")
          false
        case 'l' =>
          if (controller.restoreGame())
            println("Spiel erfolgreich geladen")
          else
            println("Spiel konnte nicht geladen werden")
          false
        case 'h' =>
          println(helpText)
          false
        case _ =>
          println("Unbekannter Befehl")
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
      |h              - Öffnet die Hilfe
      |o [a-z0-99]    - Öffnet ein Feld
      |f [a-z0-99]    - Flaggt ein Feld
      |r              - Startet ein neues Spiel
      |s              - Speichert das aktuelle Spiel
      |l              - Lädt ein gespeichertes Spiel
      |q              - Beendet das Spiel
      |-----------------------------------------
      |""".stripMargin
}
