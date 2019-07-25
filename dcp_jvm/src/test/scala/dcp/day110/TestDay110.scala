// TestDay110.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day110

import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class TestDay110 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  // Same as that of TestDay048.
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

  "The binary tree path finder" should "return the correct number of paths" in {
    forAll (genTree[Int](20)) { bt =>
      bt.allLongestPaths.size == bt.numLongestPaths
    }
  }

  it should "return paths of the appropriate size" in {
    forAll (genTree[Int](20)) { bt =>
      bt.nodeDepths.sorted == bt.allLongestPaths.map(_.size).sorted
    }
  }
}

