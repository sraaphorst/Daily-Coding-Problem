package dcp.day262

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

    private fun dfs(start: Vertex): List<Vertex> {
        val visited = mutableSetOf<Vertex>()

        fun aux(v: Vertex, list: List<Int> = emptyList()): List<Int> {
            if (v in list)
                return list
            return adjacencies[v]?.fold(list){ acc, u -> aux(u, list + listOf(u))} ?: emptyList()
        }

        return aux(start)
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

//    fun findBridgesBF(): Set<Edge> =
//        edges().filter { e -> removeEdge(e).dfs(e.first).size <= nodes }.toSet()
}

fun main() {
    val g = UndirectedSimpleGraph(3, mapOf(
        Pair(0, setOf(1)),
        Pair(1, setOf(0, 2)),
        Pair(2, setOf(1))
    ))

    for (e in g.edges()) {
        println("Removing $e")
        println("${g.removeEdge(e)}")
    }
}