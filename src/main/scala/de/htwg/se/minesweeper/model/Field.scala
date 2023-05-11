package de.htwg.se.minesweeper.model

import scala.util.Random

case class Field(tiles: Matrix[Tile]):
    private val chars = ('a' to 'z') ++ ('A' to 'Z')
    def this(sizeX: Int, sizeY: Int) = this(new Matrix[Tile](sizeX, sizeY, Tile(false, 0, true, false)))
    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile))

    def openTile(row: Int, col: Int): Field =
        val oldTile = tiles.cell(row, col)
        Field(tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, false, false)))

    def flagTile(row: Int, col: Int): Field =
        val oldTile = tiles.cell(row, col)
        Field(tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, oldTile.isHidden, true)))

    def getTile(row: Int, col: Int): Tile = tiles.cell(row, col)

    override def toString: String =
        val sb = new StringBuilder()
        for ((row, row_idx) <- tiles.rows.zipWithIndex) {
            sb.append(row_idx)
            for (tile <- row) {
                sb.append(tile)
            }
            sb.append("\n")
        }
        sb.append(" ")
        for i <- 0 until colSize do sb.append(" ").append(chars(i))
        sb.toString


