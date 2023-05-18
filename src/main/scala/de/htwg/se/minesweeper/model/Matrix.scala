package de.htwg.se.minesweeper.model

case class Matrix[T](rows: Vector[Vector[T]]) extends Serializable:
  def this(sizeX: Int, sizeY: Int, filling: T) = this(Vector.tabulate(sizeX, sizeY) { (row, col) => filling })
  val rowSize: Int = rows.size
  val colSize: Int = rows(0).size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int): Vector[T] = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(rowSize, colSize) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))




