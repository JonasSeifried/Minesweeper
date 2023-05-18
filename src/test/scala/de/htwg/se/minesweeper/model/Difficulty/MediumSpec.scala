package de.htwg.se.minesweeper.model.Difficulty

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MediumSpec extends AnyWordSpec {
  val easy: Difficulty = Medium
  "getBombProbability" in {
    easy.getBombProbability should be(0.2)
  }
}
