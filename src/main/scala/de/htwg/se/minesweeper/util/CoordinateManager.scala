package de.htwg.se.minesweeper.util

import scala.util.{Try, Success, Failure}

class CoordinateManager {
  private val chars = ('a' to 'z') ++ ('A' to 'Z')

  def decrypt(input: String): Option[(Int, Int)] = {
    val y = alphaToNum(input(0))
    if (input.length == 2) {
      val res = Try(input.substring(1).toInt)
      if (res.isSuccess) Some(res.get, y)
      else None
    } else if (input.length == 3) {
      val res = Try(input.substring(2).toInt)
      if (res.isSuccess) Some(res.get, y)
      else None
    } else {
      None
    }
  }
  private def alphaToNum(char: Char): Int =
    chars.indexOf(char)
}
