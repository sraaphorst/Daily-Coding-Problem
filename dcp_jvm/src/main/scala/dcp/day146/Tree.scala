// Tree.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day146

import dcp.day146.NodeState.NodeState

object NodeState extends Enumeration {
  type NodeState = Value
  val Zero, One = Value
}

sealed trait Tree {
  def value: NodeState
  def left: Option[Tree]
  def right: Option[Tree]
}

case class Node(override val value: NodeState,
                override val left: Option[Tree],
                override val right: Option[Tree]) extends Tree

object Tree {
  def process(tree: Option[Tree]): Option[Tree] = tree match {
    case None                                   => None
    case Some(Node(NodeState.Zero, None, None)) => None
    case Some(Node(ns, left, right))            =>
      val pLeft  = process(left)
      val pRight = process(right)
      if (ns == NodeState.One || pLeft.isDefined || pRight.isDefined)
        Some(Node(ns, pLeft, pRight))
      else
        None
  }

  def main(args: Array[String]): Unit = {
    val t = {
      val n1 = Node(NodeState.Zero, None, None)
      val n2 = Node(NodeState.Zero, None, None)
      val n3 = Node(NodeState.One, Some(n1), Some(n2))

      val n4 = Node(NodeState.Zero, None, None)
      val n5 = Node(NodeState.Zero, Some(n3), Some(n4))

      val n6 = Node(NodeState.One, None, None)
      Node(NodeState.Zero, Some(n6), Some(n5))
    }

    val pt = {
      val n1 = Node(NodeState.One, None, None)
      val n2 = Node(NodeState.Zero, Some(n1), None)
      val n3 = Node(NodeState.One, None, None)
      Node(NodeState.Zero, Some(n3), Some(n2))
    }
    assert(process(Some(t)).contains(pt))
  }
}