package de.htwg.se.minesweeper.model

import scala.util.Random

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val difficulty: Difficulty = Easy
    val field3x3 = new Field(9, 9, difficulty)

    val difficulty2: Difficulty = Medium
    val field4x4 = new Field(16, 16, difficulty2)

    val difficulty3: Difficulty = Hard
    val field5x5 = new Field(25, 25, difficulty3)

    println(field3x3)
    println(field4x4)
    println(field5x5)
  }
}