package de.htwg.se.minesweeper.model

import scala.util.Random

case class Tile(isBomb: Boolean, bombCount: Int, isHidden: Boolean, isFlagged: Boolean):

    private def bombCountToString =
        bombCount match
            case 0 => "0️⃣"
            case 1 => "1️⃣"
            case 2 => "2️⃣"
            case 3 => "3️⃣"
            case 4 => "4️⃣"
            case 5 => "5️⃣"
            case 6 => "6️⃣"
            case 7 => "7️⃣"
            case 8 => "8️⃣"

    override def toString: String =
        if(isHidden) if(isFlagged) "🚩" else "⬜"
        else if (isBomb) "💣"
        else bombCountToString





