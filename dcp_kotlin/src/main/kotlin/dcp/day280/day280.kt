package dcp.day280
// day280.kt
// By Sebastian Raaphorst, 2020.

typealias Vertex = Int
typealias AdjacencyList = Set<Vertex>
typealias AdjacencyGraph = Map<Vertex, AdjacencyList>

/**
 * Perform a DFS to search for cycles. If we find, in our DFS, a vertex that is a neighbour of the top vertex of the
 * stack that is not its parent that is already in the stack, we have found a cycle.
 */
fun AdjacencyGraph.containsCycles(): Boolean {
    if (isEmpty())
        return false

    val vertices = this.keys + this.values.flatten()

    fun AdjacencyGraph.isSimpleGraph(): Boolean {
        // No loops.
        val noLoops = vertices.none{ v -> v in (this[v] ?: emptySet()) }
        require(noLoops){"Graph contains loops."}

        // Undirected.
        val undirected = vertices.none{ v -> vertices.any { u -> v in (this[u] ?: emptySet()) && u !in (this[v] ?: emptySet()) }}
        require(undirected){"Graph is directed."}

        return true
    }

    require(this.isSimpleGraph())

    // Unfortunately, use mutability so that we don't lose the visited information and then perform unnecessary work.
    val unvisited: MutableSet<Vertex> = vertices.toMutableSet()
    fun aux(vertexStack: List<Vertex> = emptyList()): Boolean {
        // If we have visited all the vertices, we are done.
        if (unvisited.isEmpty())
            return false

        // If there is a top vertex, grab it. Otherwise, get the first unvisited vertex.
        val v = if (vertexStack.isEmpty()) unvisited.first() else vertexStack.first()
        unvisited -= v

        // Get the neighbours of the vertex.
        val nbrs = (get(v) ?: emptySet())

        // If any of the nbrs are in the list and not the parent of v, we have a cycle.
        if (nbrs.any { it in vertexStack.drop(2) }) {
            return true
        }

        // Extend the list by every unvisited vertex. We check unvisited in the lambda instead of intersecting before
        // to account for vertices that will be visited by intermediary extensions.
        return nbrs.any{ u -> u in unvisited && aux(listOf(u) + vertexStack) }
    }

    return aux()
}
