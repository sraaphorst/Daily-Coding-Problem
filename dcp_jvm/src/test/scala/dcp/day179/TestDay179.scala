// TestDay179.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day179

import org.scalacheck.Arbitrary

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class TestDay179 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  // We just transform a BST from a list for simplicitiy.
  "A postfix reading of a BST" should "be able to reconstruct the BST" in {
    forAll { lst: List[Int] =>
      val bst = lst.foldRight(Option.empty[BinarySearchTree])((i, t) => if (t.isEmpty) Some(BinarySearchTree(i)) else t.map(_.add(i)))
      BinarySearchTree.constructFromPostfix(bst.map(_.postfix()).getOrElse(Nil)) shouldEqual bst
    }
  }
}
