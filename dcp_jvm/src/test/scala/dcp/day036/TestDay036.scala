package dcp.day036

import org.scalacheck.Arbitrary.arbitrary
import org.scalatest.{FlatSpec, Matchers}
import org.scalacheck.Prop.BooleanOperators
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import BinarySearchTreeUtils._

class TestDay036 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  /**
    * Generator to produce arbitrary binary search trees.
    * We need the type T to be able to produce arbitrary instances, and to be ordered so we can insert.
    */
  implicit def binarySearchTreeArb[T : Ordering : Arbitrary]: Arbitrary[BinarySearchTree[T]] = Arbitrary {for {
    vals <- Gen.nonEmptyListOf(arbitrary[T])
  } yield vals.tail.foldLeft(BinarySearchTree(vals.head))((t, v) => t.insert(v))}

  "A BST" should "be valid" in {
    forAll { bst: BinarySearchTree[Int] =>
      bst.isValid should equal(true)
    }
  }

  it should "be convertible into a sorted list" in {
    forAll { bst: BinarySearchTree[Int] =>
      val lst = bst.toList
      lst should equal(lst.sorted)
    }
  }

  it should "be able to identify its largest element" in {
    forAll { bst: BinarySearchTree[Int] =>
      bst.largest should equal(bst.toList.max)
    }
  }

  it should "not contain an element not inserted into it" in {
    forAll { (bst: BinarySearchTree[Int], other: Int) =>
      (!bst.toList.contains(other)) ==> !bst.contains(other)
    }
  }

  it should "contain all elements inserted into it" in {
    forAll { bst: BinarySearchTree[Int] =>
      bst.toList.forall(bst.contains) should equal(true)
    }
  }

  it should "not contain any element more than once" in {
    forAll { bst: BinarySearchTree[Int] =>
      bst.toList.toSet.size should equal(bst.toList.size)
    }
  }

  /**
    * The TRUE test for this exercise.
    */
  it should "be able to find its second highest element" in {
    forAll { bst: BinarySearchTree[Int] =>
      val lst = bst.toList
      if (lst.size == 1)
        bst.secondLargestNode should equal(None)
      else {
        val idx = lst.size - 2
        lst.size should be >= 2
        bst.secondLargestNode.map(_.value) should equal(Some(lst(idx)))
      }
    }
  }
}
