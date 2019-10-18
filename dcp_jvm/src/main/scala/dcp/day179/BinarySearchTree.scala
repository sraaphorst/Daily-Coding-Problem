package dcp.day179

case class BinarySearchTree(value: Int, left: Option[BinarySearchTree] = None, right: Option[BinarySearchTree] = None) {
  def postfix(): List[Int] = {
    left.map(_.postfix()).getOrElse(Nil) ++ right.map(_.postfix()).getOrElse(Nil) ++ List(value)
  }

  def add(v: Int): BinarySearchTree =
    if (v < value)
      BinarySearchTree(value, Some(left.map(_.add(v)).getOrElse(BinarySearchTree(v))), right)
    else if (v > value)
      BinarySearchTree(value, left, Some(right.map(_.add(v)).getOrElse(BinarySearchTree(v))))
    else
      this
}

object BinarySearchTree {
  // O(n^2) solution.
  // Given a postfix reading of a BST, reconstruct the BST.
  def constructFromPostfix(lst: List[Int]): Option[BinarySearchTree] = {
    if (lst.isEmpty)
      None
    else {
      // The end point is always the root. Split into the left and right and recurse.
      val root = lst.last
      val (left, right) = lst.dropRight(1).partition(_ < root)
      Some(BinarySearchTree(root, constructFromPostfix(left), constructFromPostfix(right)))
    }
  }
}
