package de.htwg.se.minesweeper
package util

import controller.Controller
import model.Field
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObserverSpec extends AnyWordSpec with Matchers {

  class TestObserver extends Observer {
    var notified = false
    override def update(): Unit = notified = true
  }

  "An Observable" when {
    val controller = Controller(new Field(5,5))
    val observer1 = new TestObserver
    val observer2 = new TestObserver
    "new" should {
      "have no subscribers" in {
        controller.subscribers should be(Vector())
      }

      "be able to add subscribers" in {


        controller.add(observer1)
        controller.add(observer2)

        controller.subscribers should be(Vector(observer1, observer2))
      }

      "be able to remove subscribers" in {
        controller.remove(observer1)

        controller.subscribers should be(Vector(observer2))
      }

      "be able to notify subscribers" in {
        controller.add(observer1)

        controller.notifyObservers()

        observer1.notified should be(true)
        observer2.notified should be(true)
      }
    }
  }
}
