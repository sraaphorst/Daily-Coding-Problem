package dcp.day234
// day234.kt
// By Sebastian Raaphorst, 2019.

typealias Vertex = Int
typealias Weight = Int
typealias Edge = Pair<Vertex, Vertex>
typealias WeightedEdge = Pair<Edge, Weight>

data class WeightedGraph(val edges: List<WeightedEdge>) {
    /**
     * Use Prim's algorithm to calculate the maximum spanning tree of the graph.
     */
    val minimumSpanningTree: Set<WeightedEdge> by lazy {
        val vertices = edges.map { it.first }.unzip().toList().flatten().distinct()
        val n = vertices.size

        // Keep track of the edges chosen and the vertices visited.
        val tree = mutableListOf<WeightedEdge>()
        val visited = mutableSetOf(0)

        while (visited.size != n) {
            // Find the maximum weight edge between visited and the vertices not in visited.
            var maxEdge: WeightedEdge? = null
            for (e in edges) {
                val (vs, w) = e
                val (u, v) = vs
                if ((u in visited && !(v in visited)) || (!(u in visited) && v in visited)) {
                    if (maxEdge == null || (w > maxEdge.second))
                        maxEdge = e
                }
            }
            requireNotNull(maxEdge) { "Disconnected graph" }
            tree.add(maxEdge)
            visited.add(maxEdge.first.first)
            visited.add(maxEdge.first.second)
        }

        return@lazy tree.toSet()
    }
}
