// TestDay043.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day043

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class TestDay043 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  def lstToStack(lst: List[Int]): Stack[Int] = {
    val stack: Stack[Int] = Stack[Int]
    lst.foreach(stack.push)
    stack
  }

  "A stack" should "be able to pop all its values" in {
    forAll { lst: List[Int] =>
      val stack = lstToStack(lst)
      lst.reverse.foreach(Some(_) should equal(stack.pop))
      stack.pop should equal(None)
    }
  }

  it should "always return the max value" in {
    forAll { lst: List[Int] =>
      def maxpop(stk: Stack[Int]): Option[Int] = {
        val max = stk.max
        stk.pop
        max
      }

      val stack = lstToStack(lst)

      // Create a list of max values up until pos
      val maxes = for (i <- lst.indices) yield lst.reverse.drop(i).max
      maxes.foreach(Some(_) should equal(maxpop(stack)))
    }
  }
}
