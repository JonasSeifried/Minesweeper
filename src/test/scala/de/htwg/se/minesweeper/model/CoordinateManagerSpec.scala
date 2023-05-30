import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.util.CoordinateManager

class CoordinateManagerSpec extends AnyWordSpec {
  val coordinateManager = new CoordinateManager
  "A CoordinateManager" should {
    "decrypt coordinates in the form of b12 (a-Z0-99) and flip them" should {
      "input.length == 2" in {
        coordinateManager.decrypt("a1") should be(Some(1, 0))
      }
      "input.length == 3" in {
        coordinateManager.decrypt("a01") should be(Some(1, 0))
      }
    }
    "input too short or too long" in {
      coordinateManager.decrypt("a123") should be(None)
      coordinateManager.decrypt("a") should be(None)
      coordinateManager.decrypt("a1234") should be(None)
    }
  }
}
