// TestDay053.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day053

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

import scala.collection.mutable

// Admittedly, these tests mix a lot of things together that should be tested separately and miss some corner cases,
// but as I'm feeling sick today, this is good enough for now.
class TestDay053 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  "A double-stacked queue" should "behave like a normal queue" in {
    forAll {lst: List[Int] =>
      val sq: StackQueue[Int] = new StackQueue[Int]
      val qq: mutable.Queue[Int] = new mutable.Queue[Int]

      for (i <- lst) {
        sq.enqueue(i)
        qq.enqueue(i)
        sq.size should equal(qq.size)
        sq.empty should equal(qq.isEmpty)
      }

      for (i <- lst.reverse) {
        val i1 = sq.dequeue()
        val i2 = qq.dequeue()
        i1 should equal(i2)
        sq.size should equal(qq.size)
        sq.empty should equal(qq.isEmpty)
      }
    }
  }

  it should "be able to remove elements partway through" in {
    forAll {lst: List[Int] =>
      val sq: StackQueue[Int] = new StackQueue[Int]
      val qq: mutable.Queue[Int] = new mutable.Queue[Int]

      // dequeue when an even number is seen, if possible
      for (i <- lst) {
        if (i % 2 == 0) {
          sq.size should equal(qq.size)
          sq.empty should equal(qq.isEmpty)
          if (!sq.empty()) {
            val i1 = sq.dequeue()
            val i2 = qq.dequeue()
            i1 should equal(i2)
          }
        } else {
          sq.enqueue(i)
          qq.enqueue(i)
        }

        sq.size should equal(qq.size)
        sq.empty should equal(qq.isEmpty)
      }
    }
  }
}
