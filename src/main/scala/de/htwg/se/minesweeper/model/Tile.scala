package de.htwg.se.minesweeper.model

import scala.util.Random

case class Tile(isBomb: Boolean, isHidden: Boolean):

    override def toString: String =
        if(!isBomb) "◻️"
        else "💣"






