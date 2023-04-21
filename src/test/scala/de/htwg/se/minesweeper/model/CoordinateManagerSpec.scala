package de.htwg.se.minesweeper.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class CoordinateManagerSpec extends AnyWordSpec {
  val coordinateManager = new CoordinateManager
  "A CoordinateManager" should {
    "decrypt coordinates in for of b12 (a-Z0-99) and flip them" should {
      "input.length == 2" in {
        coordinateManager.decrypt("a1") should be(1, 0)
      }
      "input.length == 3" in {
          coordinateManager.decrypt("a01") should be(1, 0)
      }
    }
    "input to short or too long" in {
      coordinateManager.decrypt("a123") should be(-1,-1)
    }
  }
}
