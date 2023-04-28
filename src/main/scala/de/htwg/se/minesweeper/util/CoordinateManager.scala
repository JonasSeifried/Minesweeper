package de.htwg.se.minesweeper.util

class CoordinateManager {
  private val chars = ('a' to 'z') ++ ('A' to 'Z')

  def decrypt(input: String): (Int, Int) =
    input.length match
      case 2 => (input.substring(1).toInt, alphaToNum(input(0)))
      case 3 => (input.substring(1).toInt, alphaToNum(input(0)))
      case _ => (-1, -1)

  private def alphaToNum(char: Char): Int =
    chars.indexOf(char)
}
