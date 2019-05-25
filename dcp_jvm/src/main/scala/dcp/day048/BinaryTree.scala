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
  def size: Int =
    1 + left.map(_.size).getOrElse(0) + right.map(_.size).getOrElse(0)
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
      // We need a slice from inOrderIdx + 1 to the end:
      val rightInorder = inorder.takeRight(inorder.length - inOrderIdx - 1)
//      println(s"VALUE=$value")
//      println(s"\tpreorder=$preorder")
//      println(s"\tinorder=$inorder")
//      println(s"\tidx=$inOrderIdx")
//      println(s"\tLeft Preorder=$leftPreorder")
//      println(s"\tRight Preorder=$rightPreorder")
//      println(s"\tLeft Inorder=$leftInorder")
//      println(s"\tRight Inorder=$rightInorder")

      BinaryTree(value, apply(leftPreorder, leftInorder), apply(rightPreorder, rightInorder))
    }

//    preorder.headOption.filter { inorder.contains}.map {value =>
//      // Divide into left preOrder, right preOrder, left inOrgder, right inOrder.
//      // a gives 3, splitAt gives bde, cfg which is what we want.
//      // Now we need the inorder. We want dbe and fcg
//      val inOrderIdx = inorder.indexOf(value)
//      val (leftPreorder, rightPreorder) = preorder.tail.splitAt(inOrderIdx)
//      val leftInorder = inorder.take(inOrderIdx)
//      val rightInorder = inorder.takeRight(inOrderIdx)
//      println(s"VALUE=$value")
//      println(s"\tidx=$inOrderIdx")
//      println(s"\tLeft Preorder=$leftPreorder")
//      println(s"\tRight Preorder=$rightPreorder")
//      println(s"\tLeft Inorder=$leftInorder")
//      println(s"\tRight Inorder=$rightInorder")
//
//      BinaryTree(value, apply(leftPreorder, leftInorder), apply(rightPreorder, rightInorder))
//    }
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

  val preorder2 = List(1, 2, 3)
  val inorder2  = List(2, 3, 1)
  val tree2 = BinaryTree(1, Some(BinaryTree(2, None, Some(BinaryTree(3, None, None)))), None)
  assert(tree2.preorder == preorder2)
  assert(tree2.inorder == inorder2)
  assert(BinaryTree(preorder2, inorder2).contains(tree2))

  val tree3 = {
    val n4 = BinaryTree(4, None, None)
    val n3 = BinaryTree(3, Some(n4), None)
    val n2 = BinaryTree(2, None, None)
    val n1 = BinaryTree(1, Some(n2), Some(n3))
    n1
  }
  val preorder3 = List(1, 2, 3, 4)
  val inorder3  = List(2, 1, 4, 3)
  assert(tree3.preorder == preorder3)
  assert(tree3.inorder  == inorder3)
  assert(BinaryTree(preorder3, inorder3).contains(tree3))

  val tree4 = {
    val n2 = BinaryTree(2, None, None)
    val n1 = BinaryTree(1, None, Some(n2))
    n1
  }
  val preorder4 = List(1, 2)
  val inorder4  = List(1, 2)
  assert(BinaryTree(preorder4, inorder4).contains(tree4))

  val tree5 = BinaryTree(1, Some(BinaryTree(2, None, None)), Some(BinaryTree(3, None, None)))
  val preorder5 = List(1, 2, 3)
  val inorder5  = List(2, 1, 3)
  assert(BinaryTree(preorder5, inorder5).contains(tree5))
}