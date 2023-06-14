package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.util.{Failure, Success, Try}

object SaveManager:
  def saveGame(field: FieldInterface): Boolean =
    val fileStream = new FileOutputStream("gameData")
    val outputStream = new ObjectOutputStream(fileStream)
    val result =Try(outputStream.writeObject(field))
    fileStream.close()
    outputStream.close()
    result.isSuccess

  def restoreGame(): FieldInterface =
    val fileStream = new FileInputStream("gameData")
    val objStream = new ObjectInputStream(fileStream)
    val result =Try(objStream.readObject().asInstanceOf[FieldInterface])
    fileStream.close()
    objStream.close()
    result.getOrElse(null)
