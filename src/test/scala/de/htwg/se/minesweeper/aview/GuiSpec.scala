package de.htwg.se.minesweeper.aview

import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.Easy
import de.htwg.se.minesweeper.model.fieldComponent.fieldBaseImpl.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.util.Event

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}
import org.scalatest.matchers.should.Matchers.*

import scala.swing.{Action, Dialog, Menu, MenuBar, MenuItem}


class GuiSpec extends AnyWordSpec:
  val field: Field = new Field(3, 3, Easy).openTile(0, 0)
  val controller: Controller = Controller(field)
  val gui: Gui = Gui(controller)
  gui.close()
  "when creating" should {
    "should not crash xd" in {
      true should be(true)
    }
  }
