package de.htwg.se.minesweeper.model

class CoordinateManager {
  private val chars = ('a' to 'z') ++ ('A' to 'Z')

  def decrypt(input: String): (Int, Int) =
    input.length match
      case 2 => (alphaToNum(input(0)), input.substring(1).toInt)
      case 3 => (alphaToNum(input(0)), input.substring(1).toInt)
      case _ => (-1, -1)

  private def alphaToNum(char: Char): Int =
    chars.indexOf(char)
}
