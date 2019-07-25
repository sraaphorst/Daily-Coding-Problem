// BinaryTree.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day107

import scala.collection.immutable.Queue

final case class BinaryTree[T](value: T, left: Option[BinaryTree[T]], right: Option[BinaryTree[T]]) {
  // Turn into a list, read row-by-row.
  def toList: List[T] = {
    def aux(queue: Queue[BinaryTree[T]]): List[T] = queue.dequeueOption match {
      case Some((n, q)) => n.value :: aux(q.enqueue(List(n.left, n.right).flatten))
      case None         => Nil
    }
    aux(Queue(this))
  }
}
