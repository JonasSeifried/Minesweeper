package de.htwg.se.minesweeper.model

case class TileMatrix(rows: Int, columns: Int) {
  val matrix = Vector.tabulate(rows, columns)((x,y) => new Tile(x,y))
  def getTile(x: Int, y: Int) = matrix(x)(y)

  override def toString(): String =
    val sb = new StringBuilder()
    for (i <- 0 until rows) {
        for (j <- 0 until columns) {
            sb.append(matrix(i)(j).toString() + " ")
        }
        sb.append("\n")
    }
    sb.toString()

}

