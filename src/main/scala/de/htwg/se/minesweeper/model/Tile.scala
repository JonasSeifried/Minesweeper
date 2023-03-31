package de.htwg.se.minesweeper.model

import scala.util.Random

case class Tile(xPos: Int, yPos: Int) {
    val isBomb: Boolean = Random.nextDouble() > 0.75

    override def toString(): String = if(isBomb) "B" else "O"
}
    

