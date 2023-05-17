package de.htwg.se.minesweeper.model

import scala.util.Random

sealed trait Difficulty {
  def getBombProbability: Double
}

case object Easy extends Difficulty {
  override def getBombProbability: Double = 0.1 // Niedrige Wahrscheinlichkeit für eine Bombe
}

case object Medium extends Difficulty {
  override def getBombProbability: Double = 0.2 // Mittlere Wahrscheinlichkeit für eine Bombe
}

case object Hard extends Difficulty {
  override def getBombProbability: Double = 0.3 // Hohe Wahrscheinlichkeit für eine Bombe
}