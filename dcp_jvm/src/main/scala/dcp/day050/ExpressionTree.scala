// BinaryOperationTree.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day050

sealed abstract class ExpressionTree {
  def value: Int = this match {
    case  Mult(l, r) => l.value * r.value
    case   Div(l, r) => l.value / r.value
    case  Plus(l, r) => l.value + r.value
    case Minus(l, r) => l.value - r.value
    case  Leaf(v)    => v
  }
}

sealed abstract class Operation(val left: ExpressionTree, val right: ExpressionTree) extends ExpressionTree
case class Mult(override val left: ExpressionTree, override val right: ExpressionTree) extends Operation(left, right)
case class Div(override val left: ExpressionTree, override val right: ExpressionTree) extends Operation(left, right)
case class Plus(override val left: ExpressionTree, override val right: ExpressionTree) extends Operation(left, right)
case class Minus(override val left: ExpressionTree, override val right: ExpressionTree) extends Operation(left, right)
case class Leaf(v: Int) extends ExpressionTree

object Test extends App {
  val tree = {
    val l3 = Leaf(3)
    val l2 = Leaf(2)
    val p1 = Plus(l3, l2)
    val l4 = Leaf(4)
    val l5 = Leaf(5)
    val p2 = Plus(l4, l5)
    val m1 = Mult(p1, p2)
    m1
  }
  assert(tree.value == 45)
}