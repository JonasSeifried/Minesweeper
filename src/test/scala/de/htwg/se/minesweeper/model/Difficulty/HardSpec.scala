package de.htwg.se.minesweeper.model.Difficulty

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class HardSpec extends AnyWordSpec:
  val easy: Difficulty = Hard
  "getBombProbability" in {
    easy.getBombProbability should be(0.3)
  }
