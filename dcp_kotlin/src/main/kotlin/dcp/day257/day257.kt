package dcp.day257
// day257.kt
// By Sebastian Raaphorst, 2019.

typealias Advancer = (Int) -> Int
typealias MinMax<T> = (T, T) -> T

// Say I want to find the smallest item in a generic list.
fun <T: Comparable<T>> smallest(list: List<T>): Boolean =
    list[0] > list[1]

fun <T: Comparable<T>> smallest_window(list: List<T>): Pair<Int, Int> {
    if (list.isEmpty()) return Pair(0, 0)

    // Traverse from left to right, finding the element less than the max seen so far.
    // This has to be part of the sorting window.
    val right = list.foldIndexed(Pair(list.min(), 0)) { idx, acc, value ->
        val (maxSeen, right) = acc
        val newMaxSeen = maxOf(maxSeen!!, value)
        Pair(newMaxSeen, if (value < newMaxSeen) idx else right)
    }.second

    // Traverse from right to left, finding the element greater than the min seen so far.
    // This has to be part of the sorting window.
    val left = list.foldRightIndexed(Pair(list.max(), 0)) { idx, value, acc ->
        val (minSeen, leftN) = acc
        val newMinSeen = minOf(minSeen!!, value)
        Pair(newMinSeen, if (value > newMinSeen) idx else leftN)
    }.second

    return Pair(left, right)
}