package dcp.day076

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

import org.scalacheck._

class TestDay076 extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks {

  type Grid[T] = List[List[T]]

  implicit def gridArb: Arbitrary[Grid[Int]] = Arbitrary {
    for {
      n <- Gen.choose(0, 100)
      m <- Gen.choose(0, 100)
      rows <- Gen.listOfN(n, Gen.listOfN(m, Gen.choose(0, 1000000)))
    } yield rows
  }

  "The algorithm" should "be able to find the maximum lexicographic submatrix" in {
    forAll { grid: Grid[Int] =>
      val bf = LexifyMatrix.lexifyMatrixBF(grid)
      val dp = LexifyMatrix.lexifyMatrixEF(grid)
      dp shouldEqual bf
    }
  }
}
