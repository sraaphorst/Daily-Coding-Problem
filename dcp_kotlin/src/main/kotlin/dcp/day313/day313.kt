package dcp.day313

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max

/**
 * We formulate the problem as a shortest path over a simple graph.
 * For every one of the 1000 permissible states, we have a vertex, with edges between vertices if they differ by
 * 1 in one place, e.g. 123 is adjacent to 122, 124, 113, 133, 023, and 223.
 *
 * We can then use BFS to find the shortest path between the single source 000 and the desired state.
 */

/**
 * A graph is just a node of adjacencies we can go to.
 * The node labeled x is simply the list at x returned from createGraph.
 */
private typealias Node = List<Int>

/**
 * Find the adjacencies of a given number.
 * This is kind of horrible, but I'm not immediately sure of a better way.
 * We make sure i is a three digit number (by prepending 0s if required) and then rotate each digit both
 * positively and negatively to get the six adjacencies.
 */
private fun calculateAdjacencies(i: Int): List<Int> {
    fun rotateDigitClockwise(str: String, i: Int): String {
        return str.take(max(0,i)) + when (str[i]) {
            '9' -> '0'
            else -> str[i] + 1
        } + str.drop(i+1)
    }

    fun rotateDigitCounterclockwise(str: String, i: Int): String = str.take(max(0,i)) + when (str[i]) {
        '0' -> '9'
        else -> str[i] - 1
    } + str.drop(i+1)

    val str = i.toString()
    val digits = (if (str.length < 3) "0".repeat(3 - str.length) else "") + str
    return (0 until 3).flatMap {
        idx -> listOf(rotateDigitClockwise(digits, idx), rotateDigitCounterclockwise(digits, idx))
    }.map { it.toInt() }
}

/**
 * Create an adjacency list graph and return the array of 1000 nodes.
 * Forbidden nodes simply have no edges.
 * An array allows us constant lookup, which is why we use it instead of a list.
 */
private fun createGraph(forbidden: Collection<Int>): Array<Node> =
    Array(1000) { i -> calculateAdjacencies(i).filter { i !in forbidden } }

/**
 * Find the minimum number of moves to unlock the lock, if it is possible at all.
 */
fun unlock(goal: Int, forbidden: Collection<Int>): Int? {
    // Conditions:
    // 1. 000 cannot be forbidden.
    // 2. All minimum forbidden elements must be in range [0,1000).
    // 3. The goal is in range.
    // 4. The goal is not forbidden.
    require(0 !in forbidden)
    require((forbidden.min() ?: 0) >= 0)
    require((forbidden.max() ?: 0) <= 999)
    require(goal in 0..999)
    require(goal !in forbidden)

    if (goal == 0)
        return 0

    val graph = createGraph(forbidden)

    // We will perform a BFS, using a queue and a list mapping the distance from 000.
    // We need mutable data structures to do this.
    val distances = MutableList(1000){-1}
    distances[0] = 0

    // Queue to for BFS to keep track of nodes to visit.
    val queue: Queue<Int> = LinkedList<Int>()
    queue.add(0)
    while (queue.isNotEmpty()) {
        val nodeIdx = queue.poll()

        if (nodeIdx == goal)
            return distances[goal]

        // Add all the unvisited nodes to the priority queue.
        graph[nodeIdx].filter { distances[it] == -1 }.forEach {
            distances[it] = distances[nodeIdx] + 1
            queue.add(it)
        }
    }

    // If we reach this point, we never found distances.
    require(distances[goal] == -1)
    return null
}
