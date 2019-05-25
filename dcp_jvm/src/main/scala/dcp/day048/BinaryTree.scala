// BinaryTree.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day048

case class BinaryTree[T](value: T, left: Option[BinaryTree[T]], right: Option[BinaryTree[T]]) {
  def inorder: List[T] =
    left.map(_.inorder).getOrElse(Nil) ++ List(value) ++ right.map(_.inorder).getOrElse(Nil)
  def preorder: List[T] =
    value :: left.map(_.preorder).getOrElse(Nil) ++ right.map(_.preorder).getOrElse(Nil)
  def postorder: List[T] =
    left.map(_.postorder).getOrElse(Nil) ++ right.map(_.postorder).getOrElse(Nil) ++ List(value)
}


object BinaryTree {
  def apply[T](preorder: List[T], inorder: List[T]): Option[BinaryTree[T]] = {
    // If postorder head is current node, then it is None to the left and pop it.
    // If postorder head is current node and left is none, then we go to right.
    // If both lists are done, then we are done, so this case should never come up.

    // Always create a node if preorder is nonempty.

    // If postorder head is preorder head, left is None.
    // Otherwise it is BinaryTree(preorder.tail, postorder)

    // drop while not node in postorder.
    // Then that is right.
    preorder.headOption.map { value =>
      // Divide into left preOrder, right preOrder, left inOrgder, right inOrder.
      // a gives 3, splitAt gives bde, cfg which is what we want.
      // Now we need the inorder. We want dbe and fcg
      val inOrderIdx = inorder.indexOf(value)
      val (leftPreorder, rightPreorder) = preorder.tail.splitAt(inOrderIdx)
      val leftInorder = inorder.take(inOrderIdx)
      val rightInorder = inorder.takeRight(inOrderIdx)

      BinaryTree(value, apply(leftPreorder, leftInorder), apply(rightPreorder, rightInorder))
    }
  }
}

object Test extends App {
  val preorder  = List("a", "b", "d", "e", "c", "f", "g")
  val inorder = List("d", "b", "e", "a", "f", "c", "g")
  val postorder = List("d", "e", "b", "f", "g", "c", "a")

  val tree = BinaryTree("a",
    Some(BinaryTree("b", Some(BinaryTree("d", None, None)), Some(BinaryTree("e", None, None)))),
    Some(BinaryTree("c", Some(BinaryTree("f", None, None)), Some(BinaryTree("g", None, None)))))

  assert(preorder == tree.preorder)
  assert(inorder  == tree.inorder)
  assert(postorder == tree.postorder)
  assert(BinaryTree(preorder, inorder).contains(tree))
}