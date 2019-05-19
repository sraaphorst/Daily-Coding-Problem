// Stack.scala
//
// By Sebastian Raaphorst, 2019.
package dcp.day043

import Ordering.Implicits._
import scala.util.Random


// Unfortunately, the problem demands mutability by using pop instead of top / pop.

final class Stack[T: Ordering](var topNode: Option[StackNode[T]]) {
  def push(newValue: T): Unit =
    topNode = Some(StackNode[T](newValue, topNode, topNode.map(_.maxValue).filter(_ > newValue).getOrElse(newValue)))

  def pop: Option[T] = {
    val topValue = topNode.map(_.value)
    topNode = topNode.flatMap(_.prev)
    topValue
  }

  def max: Option[T] = {
    topNode.map(_.maxValue)
  }

  override def toString: String = {
    "[" + str(topNode) + "]"
  }

  // Builder to make nodes into strings
  private def str(n: Option[StackNode[T]]): String = n match {
    case Some(StackNode(v, n, maxValue)) if n.isDefined => s"$v($maxValue), ${str(n)}"
    case Some(StackNode(v, None, maxValue))             => s"$v($maxValue)"
    case None                                    => ""
  }
}

final private[day043] case class StackNode[T : Ordering](value: T, prev: Option[StackNode[T]], maxValue: T)

object Stack {
  def apply[T: Ordering]: Stack[T] =
    new Stack[T](None)
}
