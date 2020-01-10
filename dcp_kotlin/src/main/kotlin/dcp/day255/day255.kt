package dcp.day255
// day255.kt
// By Sebastian Raaphorst, 2020.

typealias Vertex = Int
typealias AdjacencyList = Set<Vertex>
typealias AdjacencyGraph = Map<Vertex, AdjacencyList>
typealias AdjacencyMatrix = List<List<Vertex>>

/**
 * Convert an adjacency graph into an adjacency matrix.
 */
fun adjacencyGraphToMatrix(adjacencyGraph: AdjacencyGraph): AdjacencyMatrix {
    if (adjacencyGraph.isEmpty())
        return emptyList()

    // We want the vertices to be {0, ..., n-1}.
    val vertices = (adjacencyGraph.keys + adjacencyGraph.values.flatten()).toList().sorted()
    require(vertices == vertices.indices.toList())

    fun row(vertex: Vertex): List<Vertex> {
        val adjacencyList: AdjacencyList = adjacencyGraph[vertex] ?: emptySet()
        return vertices.map { if (it in adjacencyList) 1 else 0 }
    }

    return vertices.map(::row)
}

/**
 * Given an adjacency matrix, find its transitive closure, represented again by an adjacency matrix.
 */
fun findTransitiveClosures(adjacencyMatrix: AdjacencyMatrix): AdjacencyMatrix {
    if (adjacencyMatrix.isEmpty())
        return emptyList()

    // We'll use the Floyd Warshall Algorithm for this, which lends itself poorly to functional programming and
    // immutable data structures, unfortunately, so start by making a mutable copy of adjacencyMatrix.
    val matrix: MutableList<MutableList<Vertex>> = adjacencyMatrix.map{ it.toMutableList() }.toMutableList()
    val n: Int = matrix.size

    (0 until n).forEach { k ->
        (0 until n).forEach { i ->
            (0 until n).forEach { j ->
                if (matrix[i][k] == 1 && matrix[k][j] == 1) matrix[i][j] = 1
            }
        }
    }

    return matrix
}