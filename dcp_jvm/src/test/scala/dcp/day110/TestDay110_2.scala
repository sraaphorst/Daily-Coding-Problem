// TestDay110_2.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day110

import org.scalatest.FunSuite

class TestDay110_2 extends FunSuite {
  test("Day110: Example tree") {
    val bt: BinaryTree[Int] = {
      val leaf2 = BinaryTree(2, None, None)
      val leaf4 = BinaryTree(4, None, None)
      val leaf5 = BinaryTree(5, None, None)
      val node3 = BinaryTree(3, Some(leaf4), Some(leaf5))
      val root  = BinaryTree(1, Some(leaf2), Some(node3))
      root
    }
    assert(bt.allLongestPaths.toSet == Set(List(1, 2), List(1, 3, 4), List(1, 3, 5)))
  }

  test("Day110: More complex tree") {
    val bt: BinaryTree[Int] = {
      val leaf11 = BinaryTree(11, None, None)
      val node10 = BinaryTree(10, None, Some(leaf11))
      val leaf9  = BinaryTree(9, None, None)
      val node8  = BinaryTree(8, Some(leaf9), Some(node10))
      val leaf7  = BinaryTree(7, None, None)
      val node6  = BinaryTree(6, Some(node8), None)
      val leaf5  = BinaryTree(5, None, None)
      val node4  = BinaryTree(4, None, Some(leaf7))
      val node3  = BinaryTree(3, Some(node6), None)
      val node2  = BinaryTree(2, Some(node4), Some(leaf5))
      val root   = BinaryTree(1, Some(node2), Some(node3))
      root
    }
    assert(bt.allLongestPaths.toSet ==
      Set(List(1, 2, 4, 7), List(1, 2, 5), List(1, 3, 6, 8, 9), List(1, 3, 6, 8, 10, 11)))
  }
}
