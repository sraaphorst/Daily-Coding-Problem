package dcp.day314

import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

fun findMinimaSignalBF(subscribers: List<Int>, towers: List<Int>): Int {
    // Sort the subscribers and towers.
    val sSort = subscribers.sorted()
    val tSort = towers.sorted()
    println( sSort.map { s -> tSort.map { abs(s - it) }.min() ?: 0 }.max() ?: 9999)
    return 3
}

fun findMinimalSignal(subscribers: List<Int>, towers: List<Int>): Int {
    // Sort the subscribers and towers.
    val sSort = subscribers.sorted()
    val tSort = towers.sorted()

    // Mutable list of subscriber to nearest tower.
    val distances = MutableList(subscribers.size){Int.MAX_VALUE}

    // Traverse the list in one pass for O(N+S).
    var sIdx = 0
    var tIdx = 0

    // Stopping condition: at end of both lists.
    while (sIdx != subscribers.size && tIdx != towers.size) {
        // If we have a T T T S situation, skip the towers.
        if (tSort[tIdx] < sSort[sIdx]) {
            while (tIdx < towers.size && tSort[tIdx] < sSort[sIdx]) {
                distances[sIdx] = min(distances[sIdx], sSort[sIdx] - tSort[tIdx])
                ++tIdx
            }
        }

        // If we have an S S S S T situation, skip the subscribers but hold on to the
        // first one so we can calculate the distances to the next tower.
        val sOrig = sIdx
        if (sSort[sIdx] < tSort[tIdx]) {
            while (tIdx < towers.size && sIdx < subscribers.size && (sSort[sIdx] < tSort[tIdx])) {
                distances[sIdx] = min(distances[sIdx], tSort[tIdx] - sSort[sIdx])
                ++sIdx
            }
            if (tIdx < towers.size)
                for (i in sOrig until sIdx)
                    distances[i] = min(distances[i], tSort[tIdx] - sSort[i])
        }
    }
    print(distances)
    return 3
}


fun main() {
    findMinimalSignal(listOf(2, 3, 5, 6, 7), listOf(0, 1, 4, 8))
    findMinimaSignalBF(listOf(1, 5, 11, 20), listOf(4, 8, 15))
}