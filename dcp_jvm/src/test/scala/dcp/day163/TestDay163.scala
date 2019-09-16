// TestDay163.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day163

import org.scalatest.FunSuite

class TestDay163 extends FunSuite {
  test("Day163: Evaluate simple RPN example expression") {
    val expression = List("5", "3", "+")
    val evaludated = RPNEvaluator.evaluate(expression)
    assert(evaludated.contains(8))
  }

  test("Day163: Evaluate complex RPN example expression") {
    val expression = List("15", "7", "1", "1", "+", "-", "/", "3", "*", "2", "1", "1", "+", "+", "-")
    val evaluated = RPNEvaluator.evaluate(expression)
    assert(evaluated.contains(5))
  }

  test("Day163: Evaluate complex RPN example expression from string") {
    val expression = "15 7 1 1 + - / 3 * 2 1 1 + + -"
    val evaluated = RPNEvaluator.evaluateFromString(expression)
    assert(evaluated.contains(5))
  }

  test("Day163: Evaluate (3 + 4) * 5") {
    assert(RPNEvaluator.evaluateFromString("5 3 4 + *").contains(35))
  }
}
