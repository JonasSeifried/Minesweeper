package de.htwg.se.minesweeper.model

import scala.util.Random

case class Field(tiles: Matrix[Tile]) {
    private val chars = ('a' to 'z') ++ ('A' to 'Z')

    def this(sizeX: Int, sizeY: Int) = this(new Matrix[Tile](sizeX, sizeY, Tile(false, 0, true, false)))

    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile))

    def openTile(row: Int, col: Int): Field = {
        val oldTile = tiles.cell(row, col)
        if (oldTile.isHidden && !oldTile.isFlagged) {
            val newTiles = tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, false, false))
            if (oldTile.isBomb) {
                Field(newTiles)
            }
            else {
                //val updatedField = updateEmptyTiles(row, col, newTiles)
                //Field(updatedField)
                Field(newTiles)
            }
        } else {
            this
        }
    }

    def flagTile(row: Int, col: Int): Field = {
        val oldTile = tiles.cell(row, col)
        if (oldTile.isHidden) {
            val newTile = Tile(oldTile.isBomb, oldTile.bombCount, oldTile.isHidden, !oldTile.isFlagged)
            Field(tiles.replaceCell(row, col, newTile))
        } else {
            this
        }
    }

    def getTile(row: Int, col: Int): Tile = tiles.cell(row, col)

    def getCountOfUnopenedTiles: Int = tiles.rows.flatten.count(tile => tile.isHidden && !tile.isFlagged)

    private def updateEmptyTiles(row: Int, col: Int, field: Matrix[Tile]): Matrix[Tile] = {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize) {
            field
        } else {
            val tile = field.cell(row, col)
            if (tile.isHidden || tile.isFlagged || tile.isBomb) {
                field
            } else {
                val updatedField = field.replaceCell(row, col, Tile(tile.isBomb, tile.bombCount, false, false))
                val updatedField1 = updateEmptyTiles(row - 1, col, updatedField)
                val updatedField2 = updateEmptyTiles(row + 1, col, updatedField1)
                val updatedField3 = updateEmptyTiles(row, col - 1, updatedField2)
                updateEmptyTiles(row, col + 1, updatedField3)
            }
        }
    }

    override def toString: String = {
        val sb = new StringBuilder()
        for ((row, row_idx) <- tiles.rows.zipWithIndex) {
            sb.append(row_idx)
            for (tile <- row) {
                sb.append(tile)
            }
            sb.append("\n")
        }
        sb.append(" ")
        for (i <- 0 until colSize) sb.append(" ").append(chars(i))
        sb.toString()
    }
}

