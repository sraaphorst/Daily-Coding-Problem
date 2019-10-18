// RPNEvaluator.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day163

case object RPNEvaluator {
  // Implement an immutable RPN calculator, i.e. one with no mutable stack.
  // Instead of traversing an expression and modifying a stack, we use a fold expression on a list of double
  // which acts as the stack of numbers.
  def evaluate(tokens: List[String]): Option[Double] = tokens.foldLeft(List[Double]()) {
      // (v1 :: v2 :: v2) is the existing fold contents, and token is the new symbol we have encountered.
      case (v1 :: v2 :: vs, "*") => (v1 * v2) :: vs
      case (v1 :: v2 :: vs, "/") => (v1 / v2) :: vs
      case (v1 :: v2 :: vs, "+") => (v1 + v2) :: vs
      case (v1 :: v2 :: vs, "-") => (v1 - v2) :: vs

      // If we don't have sufficient doubles to match a pair above to exercise an operand,, then we need to parse a
      // double, which should be doable if this is a valid expression.
      case (list, numString) => numString.toDouble :: list
  }.headOption

  def evaluateFromString(expression: String): Option[Double] =
    evaluate(expression.split(' ').toList)
}
