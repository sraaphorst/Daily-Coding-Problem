// BinaryTree.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day110

final case class BinaryTree[T](value: T, left: Option[BinaryTree[T]], right: Option[BinaryTree[T]]) {
  type Path = List[T]

  def isLeaf: Boolean = left.isEmpty && right.isEmpty

  private def allLongestPathsAux(prevPath: Path): List[Path] = {
    val currPath = value :: prevPath

    // If we are at a leaf, we have a longest path.
    // The paths are built backwards for efficiency, so reverse them.
    if (isLeaf)
      List(currPath.reverse)

    else {
      // Extend by the subtrees.
      val leftPaths = left.map(_.allLongestPathsAux(currPath)).getOrElse(Nil)
      val rightPaths = right.map(_.allLongestPathsAux(currPath)).getOrElse(Nil)
      leftPaths ++ rightPaths
    }
  }

  // Return a set of all the longest paths in the tree.
  def allLongestPaths: List[Path] = {
    allLongestPathsAux(Nil)
  }

  // The number of longest paths should be the number of leaves, given that the path from the root to a leaf is unique.
  def numLongestPaths: Int =
    if (isLeaf)
      1
    else
      left.map(_.numLongestPaths).getOrElse(0) + right.map(_.numLongestPaths).getOrElse(0)

  private def nodeDepthsAux(currDepth: Int): List[Int] = {
    if (isLeaf)
      List(currDepth)
    else
      left.map(_.nodeDepthsAux(currDepth + 1)).getOrElse(Nil) ++ right.map(_.nodeDepthsAux(currDepth + 1)).getOrElse(Nil)
  }

  // Determine the number of different depths from the root to the leaves of the tree.
  def nodeDepths: List[Int] =
    nodeDepthsAux(0)
}

object BinaryTree extends App {
  val bt1: BinaryTree[Int] = {
    val leaf5 = BinaryTree(5, None, None)
    val leaf4 = BinaryTree(4, None, None)
    val node3 = BinaryTree(3, Some(leaf4), Some(leaf5))
    val leaf2 = BinaryTree(2, None, None)
    val root  = BinaryTree(1, Some(leaf2), Some(node3))
    root
  }

  println(bt1.allLongestPaths)
}