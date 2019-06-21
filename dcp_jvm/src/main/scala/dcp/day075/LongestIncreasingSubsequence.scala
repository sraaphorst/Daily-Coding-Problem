package dcp.day075

import math.max
import scala.collection.mutable
import scala.math.Ordering.Implicits._

object LongestIncreasingSubsequence extends App {
  /**
    * Brute force method of finding the longest subsequence. We try all possibilities, so if there are n elements
    * in the list, the algorithm takes O(n**2).
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


  /**
    * My initial idea was to do a refinement across the list.
    * This works in most cases, but there are a small number of exceptions.
    */
  def longestSubsequenceDP_NotWorking[T : Ordering](lst: List[T]): Int = {
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
      val candidates = b :: (for (i <- Range(0, n)) yield {
        val bs = b.dropWhile(_ <= a(i))
        a.take(i+1) ++ bs
      }).toList

      // We want the lexicographically smallest candidate of the candidates amongst the largest size.
      val max_size = candidates.map(_.size).max
      val winner = candidates.filter(_.size == max_size).min
      //println(s"a=$a, b=$b, candidates=$candidates, winner=$winner")
      winner
    }

    /**
      * A collection of adjacent sublists. Find the maximum crossover between each adjacent pair.
      * @param lst the collection of lists
      * @return a collection of maximum crossovers between adjacent pairs, resulting in a list of one shorter
      *         (or more due to the call to distinct)
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

  /**
    * We memoize the function since it has repeated calls.
    */
  //def Memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() { key: I => getOrElseUpdate(key, f(key)) }
  def Memoize[I, O](f: I => O): I => O = new mutable.HashMap[I, O]() {
    override def apply(key: I) = getOrElseUpdate(key, f(key))
  }

  /**
    * Another dynamic programming approach, but this one works in all cases.
    * length(i) is the length of the longest increasing subarray ending at i and containing lst(i).
    *
    * Thus, we have the recursion:
    * 1. length(0) = 1
    * 2. length(i) = 1 + max({length(j) | j < i and lst(j) < lst(i)}), or 1 if no such j exists.
    */
  def longestSubsequenceDP[T : Ordering](lst: List[T]): Int = {
    if (lst.isEmpty) 0
    else {
      lazy val aux: Int => Int = Memoize {
        case 0 => 1
        case i =>
          // Include 0 here to account for the case where no such j exists; thus, max never is called on an empty list.
          1 + (0 :: Range(0, i).filter(lst(_) < lst(i)).map(aux).toList).max
      }
      lst.indices.map(aux).max
    }
  }

  val testarray: List[Int] = List(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15)
  assert(longestSubsequenceBF(testarray) == 6)
  assert(longestSubsequenceDP(testarray) == 6)

  // Counterexample: generates 3 (-1, 1, 3) when should generate four (-1, 0, 1, 3).
//  val a = List(-1, 1, 3, 0, -1, 1, 3)
//  println(s"${longestSubsequenceDP_NotWorking(a)}")
}
