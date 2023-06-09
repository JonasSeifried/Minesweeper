package de.htwg.se.minesweeper.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObserverSpec extends AnyWordSpec with Matchers {
  class TestObserver extends Observer {
    var updated = false

    override def update(event: Event): Unit = updated = true
  }

  class TestObservable extends Observable {
    def triggerUpdate(): Unit = notifyObservers(Event.Move)
  }

  "An Observable" should {
    "notify all subscribed observers" in {
      val observable = new TestObservable
      val observer1 = new TestObserver
      val observer2 = new TestObserver

      observable.add(observer1)
      observable.add(observer2)

      observable.triggerUpdate()

      observer1.updated should be(true)
      observer2.updated should be(true)
    }
    "not notify unsubscribed observers" in {
      val observable = new TestObservable
      val observer1 = new TestObserver
      val observer2 = new TestObserver

      observable.add(observer1)
      observable.add(observer2)
      observable.remove(observer1)

      observable.triggerUpdate()

      observer1.updated should be(false)
      observer2.updated should be(true)
    }
  }
}
