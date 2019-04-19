package dcp.day008

import org.scalatest.FunSuite

class TestDay008 extends FunSuite {
  test("BinaryTree.univalCount") {
    val t =
      BinaryTree(0,
        Some(BinaryTree(1, None, None)),
        Some(BinaryTree(0,
          Some(BinaryTree(1,
            Some(BinaryTree(1, None, None)),
            Some(BinaryTree(1, None, None)))),
          Some(BinaryTree(0, None, None)))))
    assert(t.unival_count == 5)
  }
}

