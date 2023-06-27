package de.htwg.se.minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.minesweeper.controller.controllerComponent.ControllerInterface
import de.htwg.se.minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.minesweeper.model.Difficulty.{Difficulty, Medium}
import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fieldComponent.fieldAdvancedImpl.Field
import de.htwg.se.minesweeper.model.fileIO.FileIOInterface
import de.htwg.se.minesweeper.model.fileIO.fileIOXmlImpl.FileIO as FileIOXml
import de.htwg.se.minesweeper.model.fileIO.fileIOJsonImpl.FileIO as FileIOJson

class MinesweeperModule extends AbstractModule with ScalaModule:
  private val small = 5
  private val normal = 10
  private val big = 15

  override def configure(): Unit =
    bindConstant().annotatedWith(Names.named("normal")).to(normal)
    bind[FieldInterface].annotatedWithName("small").toInstance(new Field(small))
    bind[FieldInterface].annotatedWithName("normal").toInstance(new Field(normal))
    bind[FieldInterface].annotatedWithName("big").toInstance(new Field(big))

    bind[FieldInterface].to[Field]
    bind[ControllerInterface].to[Controller]
    bind[FileIOInterface].to[FileIOJson]
