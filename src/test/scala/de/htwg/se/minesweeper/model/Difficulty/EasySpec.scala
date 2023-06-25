package de.htwg.se.minesweeper.model.Difficulty

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class EasySpec extends AnyWordSpec:
  val easy: Difficulty = Easy
  "getBombProbability" in {
    easy.getBombProbability should be(0.1)
  }
