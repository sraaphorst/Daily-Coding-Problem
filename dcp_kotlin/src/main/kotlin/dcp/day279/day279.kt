package dcp.day279
// day279.kt
// By Sebastian Raaphorst, 2020.

typealias Vertex = Int
typealias AdjacencyList = Set<Vertex>
typealias AdjacencyGraph = Map<Vertex, AdjacencyList>
typealias TransitiveClosure = Set<Vertex>
typealias TransitiveClosures = Set<TransitiveClosure>

/**
 * Calculate the transitive closure groups of a directed graph represented by adjacency lists.
 * @param graph the graph represented as adjacency lists
 */
fun findTransitiveClosures(graph: AdjacencyGraph): TransitiveClosures {
    if (graph.isEmpty())
        return emptySet()

    // All vertices should appear as keys in the adjacency graph, but in case there are vertices with no outbound
    // edges that are not listed, make sure they appear in the set of vertices all the same.
    val vertices: Set<Vertex> = graph.keys + graph.values.flatten()

    /**
     * Given a partial transitive closure, recursively completes it by looking for adjacency lists that intersect
     * it and adding them to the partial transitive closure. When stability is maintained, i.e. there are no new
     * vertices added to the partial transitive closure, the transitive closure is complete and returned.
     */
    tailrec
    fun findTransitiveClosure(partialClosure: TransitiveClosure, remainingVertices: Set<Vertex>): TransitiveClosure {
        val partialClosureAddition = partialClosure.flatMap { graph[it]?.intersect(remainingVertices) ?: emptySet() }
        return if (partialClosureAddition.isEmpty())
            partialClosure
        else
            findTransitiveClosure(partialClosure + partialClosureAddition, remainingVertices - partialClosureAddition)
    }

    /**
     * Auxiliary tail recursive function to find the transitive closures.
     * It processes the set of remaining vertices, taking one at a time, calculating its transitive closure, and then
     * recursing by removing the transitive closure from the remaining vertices and adding it to the list of
     * transitive closures.
     */
    tailrec
    fun aux(remainingVertices: Set<Vertex>, transitiveClosures: TransitiveClosures = emptySet()): TransitiveClosures =
        if (remainingVertices.isEmpty())
            transitiveClosures
        else {
            // Peel off the first remaining vertex and find its transitive closure.
            val vertexSet = setOf(remainingVertices.first())
            val newTransitiveClosure = findTransitiveClosure(vertexSet, remainingVertices - vertexSet)
            aux(remainingVertices - newTransitiveClosure, transitiveClosures + setOf(newTransitiveClosure))
        }

    return aux(vertices)
}
