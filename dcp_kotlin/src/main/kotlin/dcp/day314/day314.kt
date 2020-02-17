package dcp.day314

import kotlin.math.max
import kotlin.math.min

fun findMinimalSignal(subscribers: List<Int>, towers: List<Int>): Int {
    val sSorted = subscribers.sorted()

    // Pad towers at distances at infinity.
    val tSorted = listOf(Int.MIN_VALUE) + towers.sorted() + listOf(Int.MAX_VALUE)

    var minRange = 0
    var i = 0

    // For the current subscriber, advance to the nearest tower to the left.
    for (s in sSorted) {
        while (s > tSorted[i + 1])
            ++i

        minRange = max(minRange, min(s - tSorted[i], tSorted[i + 1] - s))
    }
    return minRange
}
