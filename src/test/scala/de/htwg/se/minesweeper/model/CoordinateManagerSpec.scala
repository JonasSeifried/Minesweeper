import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*
import de.htwg.se.minesweeper.util.CoordinateManager

class CoordinateManagerSpec extends AnyWordSpec {
  val coordinateManager = new CoordinateManager

  "A CoordinateManager" should {
    "decrypt coordinates in the form of b12 (a-Z0-99) and flip them" should {
      "input.length == 2" in {
        coordinateManager.decrypt("a1") should be(Some(1, 0))
        coordinateManager.decrypt("z9") should be(Some(9, 25))
      }
      "input.length == 3" in {
        coordinateManager.decrypt("a01") should be(Some(1, 0))
        coordinateManager.decrypt("Z99") should be(Some(99, 51))
      }
    }

    "return None for input too short or too long" in {
      coordinateManager.decrypt("a123") should be(None)
      coordinateManager.decrypt("a") should be(None)
      coordinateManager.decrypt("a1234") should be(None)
    }

    "return None for invalid characters" in {
      coordinateManager.decrypt("1a") should be(None)
    }
  }
}
