package dcp.day075

import math.max
import scala.math.Ordering.Implicits._

object LongestIncreasingSubsequence extends App {
  /**
    * Brute force method of finding the longest subsequence. We try all possibilities, so if there are n elements
    * in the list, the algorithm takes O(n**2).
    * @param lst
    * @tparam T
    * @return
    */
  def longestSubsequenceBF[T : Ordering](lst: List[T]): Int = {

    def aux(prev: Option[T], remaining: List[T]): Int = remaining match {
      case Nil => 0

      case r :: rs =>
        // Find the longest subsequence not including the head of remaining.
        val without_current = aux(prev, rs)

        // If the head of remain is greater than the highest element so far, find the longest subsequence including
        // the head of remain.
        if (prev.forall(_ < r)) {
          val with_current = 1 + aux(Some(r), rs)
          max(without_current, with_current)
        } else
          without_current
    }

    aux(None, lst)
  }


  def longestSubsequenceDP[T : Ordering](lst: List[T]): Int = {
    /**
      * Find the maximum crossover list between a and b
      * @param a list where a(0) < ... < a(n-1)
      * @param b list where b(0) < ... < b(m-1)
      * @return the longest list a(0) < ... < a(i) < b(j) < ... b(m-1).
      */
    def findMaxCrossover(a: List[T], b :List[T]): List[T] = {
      val n = a.size
      val m = b.size

      // For each possible ai, find where we can switch over to b.
      // We also want a and b
      val candidates = for (i <- Range(0, n)) yield {
        val bs = b.dropWhile(_ < a(i))
        a.take(i) ++ bs
      }

      println(s"a=$a, b=$b, candidates=$candidates")

      // Return the longest of said lists.
      candidates.maxBy(_.size)
    }

    /**
      * A collection of adjacent sublists. Find the maximum crossover between each adjacent pair.
      * @param lst the collection of lists
      * @return a collection of maximum crossovers between adjacent pairs, resulting in a list of one shorter
      */
    def aux(lsts: List[List[T]]): List[T] = lsts match {
      case Nil      => Nil
      case a :: Nil => a
      case _        =>
        // Find the maximum crossovers of all the lists and recurse.
        aux(lsts.zip(lsts.tail).map {
          case (l1, l2) => findMaxCrossover(l1, l2)
        })
    }

    val singletons = lst.map(List(_))
    val longest_increasing_subsequence = aux(singletons)
    longest_increasing_subsequence.size
  }

  val testarray: List[Int] = List(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)
  val xyz = longestSubsequenceBF(testarray)
  assert(xyz == 6)
  val xyzs = longestSubsequenceDP(testarray)
  assert(xyzs == 6)
}
