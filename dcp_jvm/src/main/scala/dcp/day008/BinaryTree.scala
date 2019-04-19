package dcp.day008

final case class BinaryTree[T](value: T,
                               left:  Option[BinaryTree[T]],
                               right: Option[BinaryTree[T]]) {
  // Returns:
  // 1. (Some(val), count) if this tree is unival; or
  // 2. (None, count) if this tree is not unival;
  // where count is the number of unival trees under and including this one.
  def unival_count: Int =
    BinaryTree.calculate_unival(Some(this))._2
}

object BinaryTree {
  private type Unival[T] = (Option[T], Int)

  def calculate_unival[T](tOpt: Option[BinaryTree[T]]): Unival[T] = tOpt match {
    case None    => (None, 0)

    case Some(t) =>
      if (t.left.isEmpty && t.right.isEmpty) {
        (Some(t.value), 1)
      } else {
        val (leftVal,  leftCt)  = calculate_unival(t.left)
        val (rightVal, rightCt) = calculate_unival(t.right)

        // Determine if this node is unival, i.e. the leftVal and the rightVal are both defined and the same.
        val v = leftVal.filter(rightVal.contains)
        val ct = leftCt + rightCt + {if (v.isDefined) 1 else 0}
        (v, ct)
      }
  }
}