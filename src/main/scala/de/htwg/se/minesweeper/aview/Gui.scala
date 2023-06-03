package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.Observer

import scala.swing.*
import scala.swing.event.MouseClicked
class Gui(controller: Controller) extends Frame with Observer:
  controller.add(this)
  title = "Minesweeper"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
    }
  }

  contents = updateContent()


  pack()
  centerOnScreen()
  open()

  private def updateContent() =
    new BorderPanel {
      add(new Label("Minesweeper"), BorderPanel.Position.North)
      add(gridCreate(controller.getRowSize, controller.getColSize), BorderPanel.Position.Center)
    }

  override def update(): Unit = contents = updateContent();  repaint()

  private def gridCreate(rowSize: Int, colSize: Int): GridPanel =
    new GridPanel(rowSize, colSize) {
      (for (
        x <- 0 until rowSize;
        y <- 0 until colSize
      ) yield (x, y)).foreach(t => contents += btnCreate(t._1, t._2))
    }

  private def btnCreate(row: Int, col: Int): Button =
    new Button(tileToString(row, col)) {
      focusPainted = false
      borderPainted = false
      listenTo(mouse.clicks)

      reactions += {
        case evt @ MouseClicked(src, pt, mod, clicks, props) => {
          val mouseBtn = evt.peer.getButton
          if(mouseBtn == 1)
            controller.openTile(row, col)
          else if(mouseBtn == 3)
            controller.flagTile(row, col)
        }
      }

    }

  private def tileToString(row: Int, col: Int): String =
    if(controller.getTileIsHidden(row, col)) return "⬜"
    if(controller.getTileIsFlagged(row, col)) return "🚩"
    if(controller.getTileIsBomb(row, col)) return "💣"
    controller.getTile(row, col).toString




