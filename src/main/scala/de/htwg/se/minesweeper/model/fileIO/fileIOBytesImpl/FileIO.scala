package de.htwg.se.minesweeper.model.fileIO.fileIOBytesImpl

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fileIO.FileIOInterface

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.util.Try

class FileIO extends FileIOInterface:

  override def save(field: FieldInterface): Boolean =
    val fileStream = new FileOutputStream("gameData")
    val outputStream = new ObjectOutputStream(fileStream)
    val result = Try(outputStream.writeObject(field))
    fileStream.close()
    outputStream.close()
    result.isSuccess


  override def load: FieldInterface =
    val fileStream = new FileInputStream("gameData")
    val objStream = new ObjectInputStream(fileStream)
    val result = Try(objStream.readObject().asInstanceOf[FieldInterface])
    fileStream.close()
    objStream.close()
    result.getOrElse(null)



