package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {

  class TestObserver extends Observer {
    var notified = false

    override def update(): Unit = notified = true
  }

  "An Observable" when {
    val observable = new Observable {}

    "new" should {
      "have no subscribers" in {
        observable.subscribers should be(Vector())
      }

      "be able to add subscribers" in {
        val observer1 = new TestObserver
        val observer2 = new TestObserver

        observable.add(observer1)
        observable.add(observer2)

        observable.subscribers should be(Vector(observer1, observer2))
      }

      "be able to remove subscribers" in {
        val observer1 = new TestObserver
        val observer2 = new TestObserver

        observable.add(observer1)
        observable.add(observer2)

        observable.remove(observer1)

        observable.subscribers should be(Vector(observer2))
      }

      "be able to notify subscribers" in {
        val observer1 = new TestObserver
        val observer2 = new TestObserver

        observable.add(observer1)
        observable.add(observer2)

        observable.notifyObservers()

        observer1.notified should be(true)
        observer2.notified should be(true)
      }
    }
  }
}
