package de.htwg.se.minesweeper.model

import scala.util.Random

case class TileMatrix(rows: Vector[Vector[Tile]]):
    def this(rows: Int, columns: Int) = this(Vector.tabulate(rows, columns)((row, col) => if(Random.nextInt(4) == 0) Tile.Bomb else Tile.Empty))
    val rowsSize: Int = rows.size
    val columnsSize: Int = rows(0).size

    def getTile(row: Int, col: Int): Tile = rows(row)(col)

    def replaceTile(row: Int, col: Int, tile: Tile): TileMatrix = copy(rows.updated(row, rows(row).updated(col, tile)))


    override def toString: String =
        val sb = new StringBuilder()
        for (row <- rows) {
            for (tile <- row) {
                sb.append(tile)
            }
            sb.append("\n")
        }
        sb.toString

