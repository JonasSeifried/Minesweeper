package de.htwg.se.minesweeper.model

import scala.util.Random

case class Field(tiles: Matrix[Tile]):
    def this(sizeX: Int, sizeY: Int) = this(new Matrix[Tile](sizeX, sizeY, Tile(false, true)))
    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile))

    def openTile(row: Int, col: Int): Field =
        val oldTile = tiles.cell(row, col)
        Field(tiles.replaceCell(row, col, Tile(oldTile.isBomb, false)))

    def flagTile(row: Int, col: Int): Field =
        val oldTile = tiles.cell(row, col)
        Field(tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.isHidden, true)))

    def getTile(row: Int, col: Int): Tile = tiles.cell(row, col)

    override def toString: String =
        val sb = new StringBuilder()
        for (row <- tiles.rows) {
            for (tile <- row) {
                sb.append(tile)
            }
            sb.append("\n")
        }
        sb.toString

