package de.htwg.se.minesweeper.model.fileIO.fileIOXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions.*
import de.htwg.se.minesweeper.MinesweeperModule
import de.htwg.se.minesweeper.model.fieldComponent.{FieldInterface, TileInterface}
import de.htwg.se.minesweeper.model.fileIO.FileIOInterface

import scala.util.Try
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface:

  override def save(field: FieldInterface): Boolean =
    import java.io._
    val pw = new PrintWriter(new File("gameData.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    val res = Try(pw.write(xml))
    pw.close()
    res.isSuccess

  override def load: FieldInterface =
    val res = Try(scala.xml.XML.loadFile("gameData.xml"))
    if (res.isFailure)
      return null

    val file = res.get
    val size = (file \\ "field" \ "@size").text.toInt
    val injector = Guice.createInjector(new MinesweeperModule)

    var field = size match
      case 5 => injector.instance[FieldInterface](Names.named("small"))
      case 10 => injector.instance[FieldInterface](Names.named("normal"))
      case 15 => injector.instance[FieldInterface](Names.named("big"))

    val tiles = (file \\ "tile")
    for (tile <- tiles) {
      val row = (tile \ "@row").text.toInt
      val col = (tile \ "@col").text.toInt
      val bombCount = (tile \ "@bombCount").text.toInt
      val isBomb = (tile \ "@isBomb").text.toBoolean
      val isHidden = (tile \ "@isHidden").text.toBoolean
      val isFlagged = (tile \ "@isFlagged").text.toBoolean
      field = field.setTile(row, col, isBomb, bombCount, isHidden, isFlagged)
    }
    field

  private def fieldToXml(field: FieldInterface) =
    <field size={ field.rowSize.toString }>
      {
      for
        row <- 0 until field.rowSize
        col <- 0 until field.rowSize
      yield
        tileToXml(field, row, col)
      }
    </field>

  private def tileToXml(field: FieldInterface, row: Int, col: Int) =
    val tile = field.getTile(row, col)
    <tile
    row={ row.toString }
    col={ col.toString }
    bombCount = { tile.bombCount.toString }
    isBomb = { tile.isBomb.toString }
    isFlagged = { tile.isFlagged.toString }
    isHidden = { tile.isHidden.toString }
    />