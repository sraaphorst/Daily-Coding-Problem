// TestDay120.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day120

import org.scalatest.FunSuite

class TestDay120 extends FunSuite {
  test("Day120: Singleton pair") {
    for (idx <- 0 to 20) {
      val sidx = idx % 2;
      assert(SingletonPair.getInstance().getID == sidx);
    }
  }
}