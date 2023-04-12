package de.htwg.se.minesweeper.model



enum Tile(stringRep: String):
    case Bomb extends Tile("\uf1e2")
    case Empty extends Tile("0")
    case Flag extends Tile("\udb80\ude3b")
    case Hidden extends Tile("\uea72")

    override def toString: String = stringRep


end Tile


