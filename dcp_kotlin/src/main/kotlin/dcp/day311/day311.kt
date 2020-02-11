package dcp.day311
// day311.kt
// By Sebastian Raaphorst.

/**
 * I am not convinced that, by the given definition of a "peak", the question can be done in O(log n).
 * If a peak specifically requires the left and right elements to be lower, then this is not possible:
 * For example, a plateau:
 *
 * 0 1 1 1 1 1 1 1 1 1 1 0
 *
 * would require traversal of the entire array in order to ascertain that there are no peaks.
 * Thus, we use the definition of a peak found elsewhere: an element at index P such that P-1 <= P <= P+1, i.e.
 * equality is tolerated. Then we can solve using binary search.
 */

fun <T: Comparable<T>> List<T>.findPeak(): Int? {
    if (isEmpty())
        return null
    if (size == 1)
        return 0

    tailrec
    fun aux(left: Int = 0, right: Int = size - 1): Int? {
        val pos = (left + right) / 2
        return when {
            pos < 0 || pos >= size -> null
            pos == 0 && get(0) > get(1) -> 0
            pos == size - 1 && get(size - 1) > get(size - 2) -> size - 1
            get(pos) >= get(pos - 1) && get(pos) >= get(pos + 1) -> pos

            get(pos + 1) > get(pos) -> aux(pos + 1, right)
            else -> aux(left, pos - 1)
        }
    }

    return aux()
}