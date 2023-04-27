package de.htwg.se.minesweeper.model

import scala.util.Random

case class Tile(isBomb: Boolean, isHidden: Boolean, isFlagged: Boolean = false):

    override def toString: String =
        if(isHidden) if(isFlagged) "🚩" else "⬜"
        else if (isBomb) "💣"
        else "0️⃣"






