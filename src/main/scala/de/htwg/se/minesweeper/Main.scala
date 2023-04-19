package de.htwg.se.minesweeper

import de.htwg.se.minesweeper.model.{FieldCreator, Field}

@main def main(): Unit =
  println("Hello world my project is Minesweeper!")

  val emptyField = new Field(15, 15)
  val field = new FieldCreator().createField(emptyField)
  println(field)
  println(field.toString.length)