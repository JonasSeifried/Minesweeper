package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.Controller
import de.htwg.se.minesweeper.util.{Event, Observer}

import scala.swing.*
import scala.swing.event.MouseClicked
class Gui(controller: Controller) extends Frame with Observer:
  controller.add(this)
  title = "Minesweeper"
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        controller.quit()
      })
      contents += new MenuItem(Action("Save") {
        controller.saveGame()
      })
      contents += new MenuItem(Action("Restore") {
        controller.restoreGame()
      })
    }
  }

  contents = updateContent()


  pack()
  centerOnScreen()
  open()

  private def updateContent() =
    new BorderPanel:
      add(sideBar, BorderPanel.Position.West)
      add(gridCreate(controller.getRowSize, controller.getColSize), BorderPanel.Position.Center)

  override def update(e: Event): Unit = e match
    case Event.Move =>
      contents = updateContent()
      repaint()
    case Event.GameOver =>
      contents = updateContent()
      if(controller.gameWon)
        Dialog.showMessage(contents.head, "You won the game!",  title = "Won")
      else
        Dialog.showMessage(contents.head, "You lost the game",  title = "Game Over")
    case Event.Quit =>
      this.dispose()

  private def gridCreate(rowSize: Int, colSize: Int): GridPanel =
    new GridPanel(rowSize, colSize):
      (for (
        x <- 0 until rowSize;
        y <- 0 until colSize
      ) yield (x, y)).foreach(t => contents += btnCreate(t._1, t._2))

  private def btnCreate(row: Int, col: Int): Button =
    new Button(tileToString(row, col)):
      focusPainted = false
      borderPainted = false
      listenTo(mouse.clicks)

      reactions += {
        case evt @ MouseClicked(src, pt, mod, clicks, props) =>
          val mouseBtn = evt.peer.getButton
          if(mouseBtn == 1)
            controller.openTile(row, col)
          else if(mouseBtn == 3)
            controller.flagTile(row, col)

    }

  private def tileToString(row: Int, col: Int): String =
    if(controller.getTileIsFlagged(row, col)) return "🚩"
    if(controller.getTileIsHidden(row, col)) return "⬜"
    if(controller.getTileIsBomb(row, col)) return "💣"
    controller.getTile(row, col).bombCount.toString

  private def sideBar: GridPanel =
    new GridPanel(3, 1):
      contents += Button("Restart") { controller.renewField() }
      contents += Button("Undo") { controller.undo }
      contents += Button("Redo") { controller.redo }
      border =  Swing.EmptyBorder(10, 10, 10, 10)

  private def restartButton: Button =
    new Button("Restart"):
      listenTo(mouse.clicks)

      reactions += {
        case MouseClicked(src, pt, mod, clicks, props) =>
          controller.renewField()
      }