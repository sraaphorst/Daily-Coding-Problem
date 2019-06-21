// TestDay075.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day075

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class TestDay075 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {
  "The algorithm" should "be able to find the largest increasing subsequence in a list" in {
    forAll { lst: List[Int] =>
      val bf = LongestIncreasingSubsequence.longestSubsequenceBF(lst)
      val dp = LongestIncreasingSubsequence.longestSubsequenceDP(lst)
      dp shouldEqual bf
    }
  }
}
