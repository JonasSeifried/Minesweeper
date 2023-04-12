package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model._

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")

  val field = new TileMatrix(15, 15)
  println(field)
  println(field.toString.length)