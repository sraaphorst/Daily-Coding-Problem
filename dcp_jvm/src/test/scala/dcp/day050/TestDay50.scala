// TestDay50.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day050

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import org.scalacheck._
import org.scalacheck.Arbitrary._

class TestDay50 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  /**
    * Generator to produce arbitrary ExpressionTrees. There are no specifications I can think of on how to test
    * these yet, so just leaving this here as a placeholder.
    */
  def genLeaf: Gen[Leaf] = for {
    v <- arbitrary[Int]
  } yield Leaf(v)

  def genExpressionTree(maxDepth: Int): Gen[ExpressionTree] =
    if (maxDepth <= 1)
      for (l <- genLeaf) yield l
    else
      for {
        l <- if (maxDepth == 1) genLeaf else genExpressionTree(maxDepth - 1)
        r <- if (maxDepth == 1) genLeaf else genExpressionTree(maxDepth - 1)
        v <- arbitrary[Int]
        o <- Gen.oneOf(Mult(l, r), Div(l, r), Plus(l, r), Minus(l, r), Leaf(v))
      } yield o
}
