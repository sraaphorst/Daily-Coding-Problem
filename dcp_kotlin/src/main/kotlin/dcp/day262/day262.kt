package dcp.day262
// day262.kt
// By Sebastian Raaphorst, 2019.

typealias Vertex = Int
typealias Edge = Pair<Vertex, Vertex>


data class UndirectedSimpleGraph(val nodes: Vertex, val adjacencies: Map<Vertex, Set<Vertex>>) {
    init {
        // The graph is simple because it uses a set.
        // Make sure all adjacencies are legal.
        require(adjacencies.keys.all((0 until nodes)::contains)){"Adjacency list contains non-vertices."}

        // Make sure undirected.
        require(adjacencies.all { (u,vs) -> vs.all { v -> (adjacencies[v] ?: error("$v missing adjacency list.")).contains(u) } })

        // Make sure no loops.
        require(adjacencies.none { (u, vs) -> u in vs }){"Adjacency list contains loops."}
    }

    fun dfs(start: Vertex): Set<Vertex> {
        val visited = mutableSetOf<Vertex>()

        fun aux(v: Vertex): Unit {
            if (v in visited)
                return
            visited.add(v)
            adjacencies[v]?.forEach { aux(it) }
        }

        aux(start)
        return visited
    }

    fun removeEdge(u: Vertex, v: Vertex): UndirectedSimpleGraph =
        UndirectedSimpleGraph(nodes, adjacencies.filterNot { it.key == u || it.key == v } +
                Pair(u, (adjacencies[u]?.filterNot { it == v } ?: emptyList()).toSet()) +
                Pair(v, (adjacencies[v]?.filterNot { it == u } ?: emptyList()).toSet()))

    fun removeEdge(e: Edge) =
        removeEdge(e.first, e.second)

    // The filter here is to only consider lexicographic edges (u,v) where u < v.
    // This avoids duplicating edges and having (u,v) and (v,u).
    fun edges(): Set<Edge> =
        adjacencies.flatMap { (u, vs) -> vs.filter{u < it}.map{ Pair(u, it)} }.toSet()

    /**
     * This is a brute force approach that takes O((V+E)E), as it tries to remove an edge and then do a DFS to make
     * sure the graph is still connected. There is an O(V+E) algorithm as well.
     */
    fun findBridgesBF(): Set<Edge> =
        edges().filter { e -> removeEdge(e).dfs(e.first).size < nodes }.toSet()
}