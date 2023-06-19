package de.htwg.se.minesweeper.model.fileIO.fileIOJsonImpl

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fileIO.FileIOInterface

class FileIO extends FileIOInterface:

  override def save(field: FieldInterface): Boolean =
    import java.io._
    val pw = new PrintWriter(new File("field.json"))

    true

  override def load: FieldInterface = ???
