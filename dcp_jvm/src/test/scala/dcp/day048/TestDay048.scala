// TestDay48.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day048

import org.scalacheck.Arbitrary
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}
import org.scalacheck.Prop.BooleanOperators

import org.scalacheck._
import org.scalacheck.Arbitrary._

class TestDay048 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  /**
    * Generator to produce arbitrary BinaryTrees.
    * This is an embarrassingly hot mess of code because:
    * 1. We need to prevent stack overflows and limit the depth of the trees; and
    * 2. The expectation is that entries in the tree will be unique.
    * We do a bit of hackery to try to limit the cases where the numbers will not be unique, but since the calls
    * to generate subtrees are independent, used is only so useful, so we need to add an implication to the spec.
    */
  def genTree[T: Arbitrary](maxDepth: Int, used: Set[T] = Set.empty[T]): Gen[BinaryTree[T]] =
    if (maxDepth <= 0) {
      for (value <- arbitrary[T]) yield BinaryTree[T](value, None, None)
    } else for {
      value <- arbitrary[T]
      l <- Gen.lzy(Gen.option(genTree[T](maxDepth-1, used + value)))
      r <- Gen.lzy(Gen.option(genTree[T](maxDepth-1, used + value)))
    } yield {
      BinaryTree[T](value, l, r)
    }

  "The binary tree construction" should "decompose and recompose" in {
    forAll (genTree[Int](20)) { bt =>
      (bt.size != bt.preorder.size) ==>
        BinaryTree(bt.preorder, bt.inorder).contains(bt)
    }
  }
}
