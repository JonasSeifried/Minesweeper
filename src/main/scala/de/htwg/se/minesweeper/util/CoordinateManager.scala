package de.htwg.se.minesweeper.util

class CoordinateManager {
  private val chars = ('a' to 'z') ++ ('A' to 'Z')

  def decrypt(input: String): Option[(Int, Int)] =
    val y = alphaToNum(input(0))
    if (input.length == 2)
      Some((input.substring(1).toInt, y))
    else
      Some((input.substring(2).toInt, y))



  private def alphaToNum(char: Char): Int =
    chars.indexOf(char)
}
