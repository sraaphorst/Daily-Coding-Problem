// TestDay044.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day044

import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class TestDay044 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  "The mergesort inversion counter" should "match the brute force inversion counter" in {
    forAll { lst: List[Int] =>
      Inversions.countInversionsBruteForce(lst) should equal(Inversions.countInversionsMergeSort(lst))}
  }
}
