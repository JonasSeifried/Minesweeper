package de.htwg.se.minesweeper.model.fileIO.fileIOJsonImpl

import de.htwg.se.minesweeper.MinesweeperModule
import de.htwg.se.minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.minesweeper.model.fileIO.FileIOInterface
import com.google.inject.Guice
import com.google.inject.name.Names
import play.api.libs.json.*
import net.codingwell.scalaguice.InjectorExtensions.*

import scala.io.Source
import scala.util.Try

class FileIO extends FileIOInterface:

  override def load: FieldInterface =
    val res = Try(Source.fromFile("gameData.json"))
    if (res.isFailure)
      return null
    val source = res.get
    val data = source.getLines.mkString
    source.close()
    val json: JsValue = Json.parse(data)
    val size = (json \ "field" \ "size").get.toString.toInt

    val injector = Guice.createInjector(new MinesweeperModule)
    var field = size match
      case 5 => injector.instance[FieldInterface](Names.named("small"))
      case 10 => injector.instance[FieldInterface](Names.named("normal"))
      case 15 => injector.instance[FieldInterface](Names.named("big"))

    for (index <- 0 until size * size)
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val tile = (json \\ "tile")(index)
      val isBomb = (tile \ "isBomb").as[Boolean]
      val isHidden = (tile \ "isHidden").as[Boolean]
      val isFlagged = (tile \ "isFlagged").as[Boolean]
      val bombCount = (tile \ "bombCount").as[Int]
      field = field.setTile(row, col, false, bombCount, isHidden, isFlagged)
    field

  override def save(field: FieldInterface): Boolean =
    import java.io._
    val pw = new PrintWriter(new File("gameData.json"))
    val res = Try(pw.write(Json.prettyPrint(fieldToJson(field))))
    pw.close()
    res.isSuccess

  private def fieldToJson(field: FieldInterface): JsObject =
    Json.obj(
      "field" -> Json.obj(
        "size" -> JsNumber(field.rowSize),
        "tiles" -> Json.toJson(
          for
            row <- 0 until field.rowSize
            col <- 0 until field.rowSize
          yield
            val tile = field.getTile(row, col)
            Json.obj(
              "row" -> row,
              "col" -> col,
              "tile" -> Json.obj(
                "isBomb" -> tile.isBomb,
                "bombCount" -> tile.bombCount,
                "isHidden" -> tile.isHidden,
                "isFlagged" -> tile.isFlagged
              )
            )
        )
      )
    )




