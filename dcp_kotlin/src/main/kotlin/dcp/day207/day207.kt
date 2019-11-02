package dcp.day207
// day207.kt
// By Sebastian Raaphorst, 2019.

/**
 * A graph is bipartite if it is 2-colourable, and to determine if a graph is 2-colourable can be accomplished by
 * a greedy algorithm. This is due to detecting odd cycles, and a graph is bipartite iff it has no odd cycles. */

enum class Colour {
    BLACK, WHITE, UNVISITED
}

typealias Vertex = Int

// We represent an immutable graph using an adjacency list and then just do a DFS.
data class Graph(val vertices: List<Vertex>, val adjacencyList: Map<Vertex, Set<Vertex>>) {
    // We do a lazy calculation of whether or not the immutable graph is bipartite.
    val isBipartite: Boolean by lazy {
        calculateBipartite()
    }

    private fun calculateBipartite(): Boolean {
        val colours = mutableMapOf<Int, Colour>()
        vertices.forEach{ colours[it] = Colour.UNVISITED }

        while (true) {
            val v = vertices.find { colours[it] == Colour.UNVISITED }
            return if (v == null)
                true
            else
                colourVertexAndNeighbours(v, Colour.BLACK, colours)
        }
    }

    // Given a vertex and a colour, try to colour that vertex the specified colour.
    // Return true if the colouring is consistent, i.e. bipartite, and false if inconsistent, i.e. not bipartite.
    private fun colourVertexAndNeighbours(v: Vertex, c: Colour, colours: MutableMap<Vertex, Colour>): Boolean {
        // If the colour is already set, make sure it is consistent.
        if (colours[v] != Colour.UNVISITED) {
            if (colours[v] != c)
                return false
            if (colours[v] == c)
                return true
        }
        colours[v] = c

        // Set the colour of all the neighbours to the opposite.
        val neighbours = adjacencyList[v]
        if (neighbours != null) {
            val otherColour = if (c == Colour.BLACK) Colour.WHITE else Colour.BLACK
            for (n in neighbours.toList())
                if (!colourVertexAndNeighbours(n, otherColour, colours))
                    return false
        }
        return true
    }
}