package de.htwg.se.minesweeper.model

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

object SaveManager:
  def saveGame(field: Field): Boolean =
    val fileStream = new FileOutputStream("gameData")
    val outputStream = new ObjectOutputStream(fileStream)
    try {
      outputStream.writeObject(field)

    } catch {
      case e: Exception =>
        fileStream.close()
        outputStream.close()
        return false
    }
    fileStream.close()
    outputStream.close()
    true
  def restoreGame(): Field =
    val fileStream = new FileInputStream("gameData")
    val objStream = new ObjectInputStream(fileStream)
    try {
      val field = objStream.readObject().asInstanceOf[Field]
      fileStream.close()
      objStream.close()
      field
    } catch {
      case e: Exception =>
        fileStream.close()
        objStream.close()
        null
    }



