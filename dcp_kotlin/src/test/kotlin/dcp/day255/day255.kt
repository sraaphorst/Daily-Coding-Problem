package dcp.day255
// day255.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        val graph: AdjacencyGraph = mapOf(
            0 to setOf(0, 1, 3),
            1 to setOf(1, 2),
            2 to setOf(2),
            3 to setOf(3)
        )

        val transitiveClosure: AdjacencyMatrix = listOf(
            listOf(1, 1, 1, 1),
            listOf(0, 1, 1, 0),
            listOf(0, 0, 1, 0),
            listOf(0, 0, 0, 1)
        )

        assertEquals(findTransitiveClosures(adjacencyGraphToMatrix(graph)), transitiveClosure)
    }
}