package dcp.day036

import Ordering.Implicits._

/**
  * A simple binary search tree, with no repeated values allowed.
  * @param value the value for this node
  * @param left the left subtree, if it exists (must contain only elements < value)
  * @param right the right subtree, if it exists (must contain only elements > value)
  * @tparam T the type of element contained in the tree
  */
case class BinarySearchTree[T : Ordering](value: T,
                                          left: Option[BinarySearchTree[T]] = None,
                                          right: Option[BinarySearchTree[T]] = None) {

  /**
    * Insert an element into a BinarySearchTree if it does not already exist.
    * @param newval the element to insert
    * @return the new binary search tree containing newval
    */
  def insert(newval: T): BinarySearchTree[T] =
    if (newval == value) this
    else if (newval < value)
      BinarySearchTree(value, left.map(_.insert(newval)).orElse(Some(BinarySearchTree(newval))), right)
    else
      BinarySearchTree(value, left, right.map(_.insert(newval)).orElse(Some(BinarySearchTree(newval))))

  /**
    * Find the node containing v, if it exists.
    * @param v the value to find
    * @return the node containing v
    */
  def findNode(v: T): Option[BinarySearchTree[T]] =
    if (v == value) Some(this)
    else if (v < value)
      left.flatMap(_.findNode(v))
    else
      right.flatMap(_.findNode(v))

  /**
    * Determine if a value v is in the binary search tree.
    * @param v the value to find
    * @return true if it appears, and false otherwise
    */
  def contains(v: T): Boolean =
    findNode(v).isDefined

  def isValid: Boolean =
    left.forall(_.value < value) && right.forall(_.value > value && left.forall(_.isValid)) && right.forall(_.isValid)
}


object BinarySearchTreeUtils {

  /**
    * Some specialized BinarySearchTree pimping ops that I'd rather keep outside of the main class.
    */
  implicit class BinaryTreeOps[T](val t: BinarySearchTree[T]) extends AnyVal {
    /**
      * Return the node containing the largest value in the tree.
      */
    def largestNode: BinarySearchTree[T] =
      t.right.map(_.largestNode).getOrElse(t)

    /**
      * Return the largest value in the tree.
      */
    def largest: T =
      largestNode.value

    /**
      * Find the node containing the second largest value in the tree, if it exists.
      */
    def secondLargestNode: Option[BinarySearchTree[T]] = {
      type NodeWithParent[S] = (BinarySearchTree[S], Option[BinarySearchTree[S]])

      // Modified method to get the largest node with its (optional) parent.
      def largestWithParent(node: BinarySearchTree[T], parent: Option[BinarySearchTree[T]]): NodeWithParent[T] =
        node.right.map(r => largestWithParent(r, Some(node))).getOrElse((node, parent))

      /**
        * To do this, our strategy is as follows:
        * Find the largest node N in the tree.
        * 1. If it has no left subtree, then the parent (if any) is the second-largest node.
        * 2. If it has a left subtree, the largest node in that subtree is the second-largest node.
        * Thus, we must find the largest node AND its parent node.
        */
      var (n, p) = largestWithParent(t, None)
      n.left.map(_.largestNode).orElse(p)
//      largestWithParent(t, None) match {
//        case (_, None) => None
//        case (n, p) => n.left.map(_.largestNode).orElse(p)
//      }
    }

    def toSeq: Seq[T] = {
      // Perform an infix traversal of the binary search tree, which should return a sorted list if the tree is legal.
      def aux(n: Option[BinarySearchTree[T]]): Seq[T] = n match {
        case None => Nil
        case Some(BinarySearchTree(value, left, right)) =>
          aux(left) ++ Seq(value) ++ aux(right)
      }

      aux(Some(t))
    }

    def toList: List[T] =
      toSeq.toList
  }
}

object Test extends App {
  import BinarySearchTreeUtils._

//  val bst = BinarySearchTree[Int](4).insert(1).insert(3)
//  println(s"$bst\n\n")
//  val node = bst.secondLargestNode
//  println(node)
val bst = BinarySearchTree[Int](5).insert(3).insert(4).insert(2).insert(0).insert(1)
  println(s"$bst\n\n")
  val node = bst.secondLargestNode
  println(node.map(_.value))
}