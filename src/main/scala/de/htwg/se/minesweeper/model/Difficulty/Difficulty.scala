package de.htwg.se.minesweeper.model.Difficulty

import de.htwg.se.minesweeper.model.Difficulty

sealed trait Difficulty {
  def getBombProbability: Double
}

case object Easy extends Difficulty {
  override def getBombProbability: Double = 0.1
}

case object Medium extends Difficulty {
  override def getBombProbability: Double = 0.2
}

case object Hard extends Difficulty {
  override def getBombProbability: Double = 0.3
}
