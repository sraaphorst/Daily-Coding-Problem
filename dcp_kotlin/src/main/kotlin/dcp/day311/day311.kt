package dcp.day311
// day311.kt
// By Sebastian Raaphorst.

/**
 * Given that all the elements are distinct, we can simply use a binary search to find a peak.
 */

fun <T: Comparable<T>> List<T>.findPeak(): Int {
    if (size == 1)
        return 0

    tailrec
    fun aux(left: Int = 0, right: Int = size - 1): Int {
        val pos = left + (right - left) / 2
        return when {
            get(pos) >= get(pos - 1) && get(pos) >= get(pos + 1) -> pos
            get(pos + 1) > get(pos) -> aux(pos + 1, right)
            else -> aux(left, pos - 1)
        }
    }

    return aux()
}