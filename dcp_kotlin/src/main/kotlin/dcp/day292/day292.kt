package dcp.day292
// day292.kt
// By Sebastian Raaphorst, 2020.

import java.util.*

typealias Vertex = Int
typealias AdjacencyList = List<Vertex>
typealias AdjacencyGraph = List<AdjacencyList>
typealias Partition = Set<Vertex>

data class Bipartition(val partition1: Partition, val partition2: Partition) {
    init {
        require(partition1.intersect(partition2).isEmpty())
        require(partition1.isNotEmpty())
        require(partition2.isNotEmpty())
    }
}

/**
 * The idea here is that a solution corresponds to a bipartite graph, with each partition representing a team.
 * We can find out if a graph is bipartite by doing a DFS on it to see if it has a 2-colouring, i.e. has
 * no cycles of odd length. The graph may not be connected, so we must take this into account.
 *
 * We assume for simplicity that the graph is over the vertices {0, ..., n-1}.
 *
 * This should have complexity O(V + E), but it may be higher because of our choice of use of data structures, i.e.
 * immutable sets for partitions, which requires copying.
 */
fun AdjacencyGraph.findBipartition(): Bipartition? {
    // If there are one or fewer vertices, clearly there is no bipartition.
    if (size <= 1)
        return null

    // Make sure all values in the adjacency lists are in range [0,size).
    require(all { (it.min() ?: 0) >= 0 })
    require(all { (it.max() ?: 0) < size })

    // Unfortunately, we're going to be using mutability here since graph algorithms don't lend themselves well to
    // immutability.
    val visited: MutableList<Boolean> = MutableList(size){false}
    val stack: Stack<Vertex> = Stack()
    stack.push(0)

    // If colour is true, this corresponds to partition1; else, partition2.
    tailrec
    fun dfs(partition1: Partition = emptySet(), partition2: Partition = emptySet(), colour: Boolean = true): Bipartition? {
        if (stack.isEmpty()) {
            val firstUnvisited = visited.indexOfFirst { !it }
            return when {
                firstUnvisited == -1 && partition1.isNotEmpty() && partition2.isNotEmpty() -> Bipartition(partition1, partition2)
                firstUnvisited == -1 -> null
                else -> {
                    // Make sure to add the next vertex to the smaller partition so as to avoid cases like a
                    // completely disconnected graph putting all vertices in the same partition, which would be invalid.
                    stack.push(firstUnvisited)
                    dfs(partition1, partition2, partition1.size < partition2.size)
                }
            }
        }

        // Visit the top of the stack, make sure none of its neighbours will have the same colour as it.
        val v = stack.peek()

        // Add v to the appropriate partition.
        val newPartition1 = if (!visited[v] && colour) partition1 + v else partition1
        val newPartition2 = if (!visited[v] && !colour) partition2 + v else partition2

        if (visited[v]) {
            stack.pop()
        } else {
            visited[v] = true
            if (colour && get(v).any{ it in partition1 }) return null
            if (!colour && get(v).any{ it in partition2 }) return null
        }

        // Find the first unvisited neighbour of v, if it exists, and push it on the stack.
        val uIdx = get(v).indexOfFirst { !visited[it] }
        if (uIdx != -1) {
            // Get the element from v's adjacency list.
            val u = get(v)[uIdx]
            stack.push(u)
        }
        return dfs(newPartition1, newPartition2, v in newPartition2)
    }

    return dfs()
}
