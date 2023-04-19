package de.htwg.se.minesweeper.model

import scala.util.Random

case class Field(tiles: Matrix[Tile]):
    def this(sizeX: Int, sizeY: Int) = this(new Matrix[Tile](sizeX, sizeY, Tile(false, false)))
    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile))
    override def toString: String =
        val sb = new StringBuilder()
        for (row <- tiles.rows) {
            for (tile <- row) {
                sb.append(tile)
            }
            sb.append("\n")
        }
        sb.toString


