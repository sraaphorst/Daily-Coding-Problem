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
    preorder.headOption.map { value =>
      // Divide into left preOrder, right preOrder, left inOrgder, right inOrder.
      // In example, a gives 3, splitAt gives bde, cfg which is what we want.
      // Now we need the inorder. We want dbe and fcg.
      val inOrderIdx = inorder.indexOf(value)
      val (leftPreorder, rightPreorder) = preorder.tail.splitAt(inOrderIdx)
      val leftInorder = inorder.take(inOrderIdx)
      // We need a slice from inOrderIdx + 1 to the end:
      val rightInorder = inorder.takeRight(inorder.length - inOrderIdx - 1)

//      Debugging:
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
  }
}

// A random sampling of test cases. Specification testing is also done in TestDay048.
object Test extends App {
  // Tree 1: Given with problem
  val preorder1  = List("a", "b", "d", "e", "c", "f", "g")
  val inorder1 = List("d", "b", "e", "a", "f", "c", "g")
  val postorder1 = List("d", "e", "b", "f", "g", "c", "a")

  val tree1 = {
    val g = BinaryTree("g", None, None)
    val f = BinaryTree("f", None, None)
    val c = BinaryTree("c", Some(f), Some(g))
    val e = BinaryTree("e", None, None)
    val d = BinaryTree("d", None, None)
    val b = BinaryTree("b", Some(d), Some(e))
    val a = BinaryTree("a", Some(b), Some(c))
    a
  }
  assert(preorder1  == tree1.preorder)
  assert(inorder1   == tree1.inorder)
  assert(postorder1 == tree1.postorder)
  assert(BinaryTree(preorder1, inorder1).contains(tree1))

  // A random smattering of problems that were used to debug an issue in the original implementation.
  val preorder2 = List(1, 2, 3)
  val inorder2  = List(2, 3, 1)

  val tree2 = {
    val n3 = BinaryTree(3, None, None)
    val n2 = BinaryTree(2, None, Some(n3))
    val n1 = BinaryTree(1, Some(n2), None)
    n1
  }
  assert(tree2.preorder == preorder2)
  assert(tree2.inorder == inorder2)
  assert(BinaryTree(preorder2, inorder2).contains(tree2))

  val preorder3 = List(1, 2, 3, 4)
  val inorder3  = List(2, 1, 4, 3)
  val tree3 = {
    val n4 = BinaryTree(4, None, None)
    val n3 = BinaryTree(3, Some(n4), None)
    val n2 = BinaryTree(2, None, None)
    val n1 = BinaryTree(1, Some(n2), Some(n3))
    n1
  }
  assert(tree3.preorder == preorder3)
  assert(tree3.inorder  == inorder3)
  assert(BinaryTree(preorder3, inorder3).contains(tree3))

  val preorder4 = List(1, 2)
  val inorder4  = List(1, 2)
  val tree4 = {
    val n2 = BinaryTree(2, None, None)
    val n1 = BinaryTree(1, None, Some(n2))
    n1
  }
  assert(BinaryTree(preorder4, inorder4).contains(tree4))

  val preorder5 = List(1, 2, 3)
  val inorder5  = List(2, 1, 3)
  val tree5 = {
    val n3 = BinaryTree(3, None, None)
    val n2 = BinaryTree(2, None, None)
    val n1 = BinaryTree(1, Some(n2), Some(n3))
    n1
  }
  assert(BinaryTree(preorder5, inorder5).contains(tree5))
}