// TestDay073.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day073

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import scala.collection.JavaConverters._

class TestDay073 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  "A list" should "append" in {
    forAll { lst: List[Int] =>
      val sll = new SingleLinkedList[Int]
      lst.foreach(sll.append)
      sll.asScala.toList shouldEqual lst
    }
  }

  it should "prepend" in {
    forAll { lst: List[Int] =>
      val sll = new SingleLinkedList[Int]
      lst.reverse.foreach(sll.prepend)
      sll.asScala.toList shouldEqual lst
    }
  }

  it should "reverse after prepend" in {
    forAll { lst: List[Int] =>
      val sll = new SingleLinkedList[Int]
      lst.foreach(sll.prepend)
      sll.reverse()
      sll.asScala.toList shouldEqual lst
    }
  }

  it should "reverse after append" in {
    forAll { lst: List[Int] =>
      val sll = new SingleLinkedList[Int]
      lst.foreach(sll.append)
      sll.reverse()
      sll.asScala.toList shouldEqual lst.reverse
    }
  }
}
