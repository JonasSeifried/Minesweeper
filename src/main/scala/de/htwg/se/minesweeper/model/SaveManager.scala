package de.htwg.se.minesweeper.model

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}
import scala.util.{Try, Success, Failure}

object SaveManager:
  def saveGame(field: Field): Boolean =
    val fileStream = new FileOutputStream("gameData")
    val outputStream = new ObjectOutputStream(fileStream)
    val result =Try(outputStream.writeObject(field))
    fileStream.close()
    outputStream.close()
    result.isSuccess

  def restoreGame(): Field =
    val fileStream = new FileInputStream("gameData")
    val objStream = new ObjectInputStream(fileStream)
    val result =Try(objStream.readObject().asInstanceOf[Field])
    fileStream.close()
    objStream.close()
    result.getOrElse(null)
