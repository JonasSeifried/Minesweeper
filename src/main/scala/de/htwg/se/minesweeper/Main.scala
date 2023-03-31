package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model._

@main def hello(): Unit =
  println("Hello world my project is Minesweeper!")
  println(msg)

  val field = TileMatrix(15, 15)
  println(field)
  val tile = field.getTile(10, 2)
  println(tile.isBomb)


def msg = "I was compiled by Scala 3. 💣:)"