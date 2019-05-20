// Inversions.scala
//
// By Sebastian Raaphorst, 2019.

package dcp.day044

import Ordering.Implicits._

object Inversions {
  /**
    * * An O(n2) way of counting the number of inversions in an array using simple iteration.
    */
  def countInversionsBruteForce[T : Ordering](lst: List[T]): Int = {
    val lstWithIdx = lst.zipWithIndex
    lstWithIdx.map { case (e1, i1) => lstWithIdx.count { case (e2, i2) => e1 > e2 && i1 < i2}}.sum
  }

  /**
    * An O(n log n) way of counting the number of inversions in an array using a merge sort.
    */
  def countInversionsMergeSort[T : Ordering](lst: List[T]): Int = {
    // Sort and merge elements using mutable buffer.
    val sortedList = lst.toBuffer
    val lookupList = lst.toBuffer

    // Recursive method to sort the input array [left,right] and return the number of inversions.
    def mergeSort(left: Int, right: Int): Int = {
      if (right > left) {
        val mid = (right + left) / 2

        // The number of inversions is calculated by the number in the left half plus the number in the right half
        // plus the number resulting from merging.
        mergeSort(left, mid) + mergeSort(mid + 1, right) + merge(left, mid + 1, right)
      } else 0
    }

    // Merge arrays [left, mid-1] and [mid, right].
    def merge(left: Int, mid: Int, right: Int): Int = {
      // Indices into the left array, the right array, and the total array.
      var (lIdx, rIdx, mergedIdx) = (left, mid, left)
      var inversions = 0

      // While we are still merging...
      while ((lIdx <= mid - 1) && (rIdx <= right)) {
        if (lookupList(lIdx) <= lookupList(rIdx)) {
          // Grab an element from the left.
          sortedList(mergedIdx) = lookupList(lIdx)
          mergedIdx += 1
          lIdx += 1
        } else {
          sortedList(mergedIdx) = lookupList(rIdx)
          // Grab an elemenet from the right.
          mergedIdx += 1
          rIdx += 1

          // This increases the number of inversions
          inversions += mid - lIdx
        }
      }

      // Copy the remaining elements
      while (lIdx <= mid - 1) {
        sortedList(mergedIdx) = lookupList(lIdx)
        mergedIdx += 1
        lIdx += 1
      }
      while (rIdx <= right) {
        sortedList(mergedIdx) = lookupList(rIdx)
        mergedIdx += 1
        rIdx += 1
      }

      // Copy the sorted list to the lookup list.
      for (i <- 0 to right)
        lookupList(i) = sortedList(i)

      inversions
    }

    val inversions = mergeSort(0, lst.length - 1)
    inversions
  }
}
