package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer

import scala.swing.{BorderPanel, Button, Frame, GridPanel, Label}
class Gui(controller: Controller) extends Frame with Observer{
  controller.add(this)
  title = "Minesweeper"

  contents = new BorderPanel {
    add(new Label("Minesweeper"), BorderPanel.Position.North)
    add(gridCreate(controller.getRowSize, controller.getColSize), BorderPanel.Position.Center)
  }


  pack()
  centerOnScreen()
  open()

  override def update(): Unit = ???

  def gridCreate(rowSize: Int, colSize: Int): GridPanel =
    new GridPanel(rowSize, colSize) {
      (for (
        x <- 0 until rowSize;
        y <- 0 until colSize
      ) yield (x, y)).foreach(t => contents += btnCreate(t._1, t._2))
    }

  def btnCreate(row: Int, col: Int): Button =
    new Button(tileToString(row, col)) {

    }

  def tileToString(row: Int, col: Int): String =
    if(controller.getTileIsHidden(row, col)) return "⬜"
    if(controller.getTileIsBomb(row, col)) return "💣"
    controller.getTile(row, col).toString
}







