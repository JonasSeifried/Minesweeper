package de.htwg.se.minesweeper.model

import scala.util.Random

case class Field(tiles: Matrix[Tile], difficulty: Difficulty) extends Serializable {
    private val chars = ('a' to 'z') ++ ('A' to 'Z')
    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def this(sizeX: Int, sizeY: Int, difficulty: Difficulty) = {
        this(new Matrix[Tile](sizeX, sizeY, Tile(false, 0, true, false)), difficulty)
        generateBombs()
    }

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile), difficulty)

    def openTile(row: Int, col: Int): Field = {
        val oldTile = tiles.cell(row, col)
        if (oldTile.isHidden && !oldTile.isFlagged) {
            val newTiles = tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, false, false))
            if (oldTile.isBomb) {
                Field(newTiles, difficulty)
            } else {
                val updatedField = updateEmptyTiles(row, col, newTiles, Set.empty)
                Field(updatedField, difficulty)
            }
        } else {
            this
        }
    }

    def flagTile(row: Int, col: Int): Field = {
        val oldTile = tiles.cell(row, col)
        if (oldTile.isHidden) {
            val newTile = Tile(oldTile.isBomb, oldTile.bombCount, oldTile.isHidden, !oldTile.isFlagged)
            Field(tiles.replaceCell(row, col, newTile), difficulty)
        } else {
            this
        }
    }

    def getTile(row: Int, col: Int): Tile = tiles.cell(row, col)

    def getCountOfUnopenedTiles: Int = tiles.rows.flatten.count(tile => tile.isHidden && !tile.isFlagged)

    def isGameEnd: Boolean = tiles.rows.flatten.exists(tile => tile.isBomb && !tile.isHidden)

    def hasWon: Boolean = !isGameEnd && getCountOfUnopenedTiles == 0

    private def generateBombs(): Unit = {
        val random = new Random()
        for {
            row <- 0 until rowSize
            col <- 0 until colSize
        } {
            if (random.nextDouble() < difficulty.getBombProbability) {
                val tile = tiles.cell(row, col)
                tiles.replaceCell(row, col, Tile(isBomb = true, tile.bombCount, tile.isHidden, tile.isFlagged))
            }
        }
    }

    private def updateEmptyTiles(row: Int, col: Int, field: Matrix[Tile], visited: Set[(Int, Int)]): Matrix[Tile] = {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize || visited.contains((row, col))) {
            field
        } else {
            val tile = field.cell(row, col)
            if (tile.isHidden || tile.isFlagged || tile.isBomb) {
                field
            } else {
                val updatedField = field.replaceCell(row, col, Tile(tile.isBomb, tile.bombCount, false, false))
                val updatedVisited = visited + ((row, col))
                val updatedField1 = updateEmptyTiles(row - 1, col, updatedField, updatedVisited)
                val updatedField2 = updateEmptyTiles(row + 1, col, updatedField1, updatedVisited)
                val updatedField3 = updateEmptyTiles(row, col - 1, updatedField2, updatedVisited)
                updateEmptyTiles(row, col + 1, updatedField3, updatedVisited)
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
