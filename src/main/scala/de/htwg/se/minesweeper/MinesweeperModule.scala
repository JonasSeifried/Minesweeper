package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.{Difficulty, Medium}
import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fieldComponent.fieldAdvancedImpl.Field

class MinesweeperModule extends AbstractModule with ScalaModule:
  private val defaultSize: Int = 5

  override def configure(): Unit =
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[FieldInterface].to[Field]
    bind[ControllerInterface].to[Controller]
