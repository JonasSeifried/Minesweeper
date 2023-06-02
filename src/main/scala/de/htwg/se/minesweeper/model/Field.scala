package de.htwg.se.minesweeper.model

import de.htwg.se.minesweeper.model.Difficulty.Difficulty

import scala.util.Random

case class Field(tiles: Matrix[Tile], difficulty: Difficulty) extends Serializable {
    private val chars= ('a' to 'z') ++ ('A' to 'Z')
    val rowSize: Int = tiles.rowSize
    val colSize: Int = tiles.colSize

    def this(sizeX: Int, sizeY: Int, difficulty: Difficulty) = {
        this(new Matrix[Tile](sizeX, sizeY, Tile(false, 0, true, false)), difficulty)
        generateBombs()
    }

    def replaceTile(row: Int, col: Int, tile: Tile): Field = Field(tiles.replaceCell(row, col, tile), difficulty)

    def openTile(row: Int, col: Int): Field = {
        val oldTile = tiles.cell(row, col)
        if (!oldTile.isHidden || oldTile.isFlagged) return this
        val newTiles = tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, false, false))
        if (oldTile.isBomb) return Field(newTiles, difficulty)

        val updatedTiles = updateEmptyTiles(row, col, tiles)
        Field(updatedTiles, difficulty)
    }

    def closeTile(row: Int, col: Int): Field =
        val oldTile = tiles.cell(row, col)
        val newTiles = tiles.replaceCell(row, col, Tile(oldTile.isBomb, oldTile.bombCount, true, oldTile.isFlagged))
        Field(newTiles, difficulty)

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
    private def updateEmptyTiles(row: Int, col: Int, tiles: Matrix[Tile]): Matrix[Tile] =
        val tile = getTile(row, col)
        updateEmptyTilesR(row, col, tiles, Set.empty)



    private def updateEmptyTilesR(row: Int, col: Int, tiles: Matrix[Tile], visited: Set[(Int, Int)]): Matrix[Tile] = {
        if (row < 0 || row >= rowSize || col < 0 || col >= colSize || visited.contains((row, col))) return tiles
        val tile = tiles.cell(row, col)
        if (!tile.isHidden || tile.isFlagged || tile.isBomb) return tiles
        var newTiles = tiles.replaceCell(row, col, Tile(tile.isBomb, tile.bombCount, false, false))
        val updatedVisited = visited + ((row, col))
        if(tile.bombCount != 0) return newTiles
        for (i <- Math.max(row - 1, 0) to Math.min(row + 1, rowSize - 1)) do
            for (j <- Math.max(col - 1, 0) to Math.min(col + 1, colSize - 1)) do
            newTiles = updateEmptyTilesR(i, j, newTiles, updatedVisited)
        newTiles
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

    def isTileFlagged(row: Int, col: Int): Boolean = tiles.cell(row, col).isFlagged

}
