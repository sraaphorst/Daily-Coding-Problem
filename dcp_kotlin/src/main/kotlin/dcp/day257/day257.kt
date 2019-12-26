package dcp.day257
// day257.kt
// By Sebastian Raaphorst, 2019.

fun <T: Comparable<T>> smallestWindowBruteForce(list: List<T>): Pair<Int, Int>? {
    if (list.isEmpty()) return null

    val sorted = list.sorted()

    val left = list.zip(sorted).indexOfFirst { it.first != it.second }

    val right = list.zip(sorted).indexOfLast { it.first != it.second }

    return if (left == right) null else Pair(left, right)
}


fun <T: Comparable<T>> smallestWindow(list: List<T>): Pair<Int, Int>? {
    if (list.isEmpty()) return null

    // Traverse from left to right, finding the element less than the max seen so far.
    // This has to be part of the sorting window.
    // The pair holds the max so far and the index of the element mentioned above.
    val right = list.foldIndexed(Pair(list.min(), 0)) { idx, acc, value ->
        val (maxSeen, right) = acc
        val newMaxSeen = maxOf(maxSeen!!, value)
        Pair(newMaxSeen, if (value < newMaxSeen) idx else right)
    }.second

    // Traverse from right to left, finding the element greater than the min seen so far.
    // This has to be part of the sorting window.
    // The pair holds the min so far and the index of the element mentioned above.
    val left = list.foldRightIndexed(Pair(list.max(), 0)) { idx, value, acc ->
        val (minSeen, leftN) = acc
        val newMinSeen = minOf(minSeen!!, value)
        Pair(newMinSeen, if (value > newMinSeen) idx else leftN)
    }.second

    return if (left == right) null else Pair(left, right)
}
