package dcp.day229

import java.util.*
import kotlin.math.min

typealias ID = Int
typealias Moves = Int
typealias Edges = Map<ID, Moves>
// Snakes and ladders are both just different forms of transporters.
typealias Transporters = Map<ID, ID>

data class Node(val id: ID, val edges: Edges)

// Snakes and ladders are equivalent: they just take you in different directions towards the end of the board.
fun snakesAndLadders(size: Int, transporters: Transporters): Int {
    // Create the board.
    val numNodes = size * size

    val board = (0 until numNodes).map {id ->
        // First determine the edges. If there is a snake here, there is only one edge.
        val edges: Edges

        val teleporter = transporters[id]
        edges = if (teleporter != null) {
            mapOf(teleporter to 0)
        } else {
            (1..6).filter { id + it < numNodes }.map { id + it to 1 }.toMap()
        }

        Node(id, edges)
    }

    // Now we do a BFS on the board.
    val visited = (0 until numNodes).map{false}.toMutableList()
    val shortestDistance = (0 until numNodes).map{-1}.toMutableList()
    shortestDistance[0] = 0
    val queue = LinkedList<ID>()
    queue.push(0)

    while (queue.isNotEmpty()) {
        val nodeID = queue.pop()
        val node = board[nodeID]
        //println("* Visiting $nodeID: $node, ${shortestDistance[nodeID]}")
        node.edges.forEach{ (dID, dist) ->
            // We either have a distance or we don't
            // If we don't, the distance is shortestDistance[node] + dist
            // Otherwise, it is min(that, existing)
            val newDistance = shortestDistance[nodeID] + dist
            shortestDistance[dID] = if (shortestDistance[dID] == -1) newDistance else min(newDistance, shortestDistance[dID])
            if (!visited[dID])
                queue.push(dID)
            visited[nodeID] = true
        }
    }

    return shortestDistance.last()
}

fun main() {
    val snakes = mapOf(16 to 6, 48 to 26, 49 to 11, 56 to 53, 62 to 19, 64 to 60, 87 to 24, 93 to 73, 95 to 75, 98 to 78)
    val ladders = mapOf(1 to 38, 4 to 14, 9 to 31, 21 to 42, 28 to 84, 36 to 44, 51 to 67, 71 to 91, 80 to 100)

    // We play from 0 to 99 instead of 1 to 100, so drop all the transporters by 1.
    val transporters = (snakes + ladders).map { it.key - 1 to it.value - 1}.toMap()
    println("Number of moves required: ${snakesAndLadders(10, transporters)}.")
}