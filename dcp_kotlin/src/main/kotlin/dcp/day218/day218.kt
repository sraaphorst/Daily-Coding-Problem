package dcp.day218
// day218.kt
// By Sebastian Raaphorst, 2019.


// We use an edge list because it makes it the easiest to swap.
data class ReversibleDirectedGraph<T>(val vertices: Set<T>, val edges: Set<Pair<T, T>>) {
    // This should be a self-inverting map.
    fun reverse(): ReversibleDirectedGraph<T> {
        // Make sure all vertices in edgeList are in vertices.
        val edgeVertices = edges.flatMap { it.toList() }
        require(edgeVertices.all { it in vertices }) { "Illegal graph specification: $this" }

        // Swap the edges.
        val newEdges = edges.map { it.second to it.first }.toSet()
        return ReversibleDirectedGraph(vertices, newEdges)
    }
}

fun main() {
    val g = ReversibleDirectedGraph(setOf(0, 1), emptySet())
    println(g.reverse().reverse())
    println(g)
}
//
//fun main() {
//    val g = ReversibleDirectedGraph(setOf(0, 1, 2, 3),
//        listOf(0 to 1, 2 to 1, 3 to 2, 3 to 0, 1 to 3))
//    println(g)
//    val gr = g.reverse()
//    println(gr)
//    val grr = gr.reverse()
//    println(grr)
//    println(grr == g)
//}
