package dcp.day280
// day280.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun example1() {
        val graph: AdjacencyGraph = mapOf(
            0 to setOf(1),
            1 to setOf(0, 2, 3, 7),
            2 to setOf(1, 6),
            3 to setOf(1, 4, 5),
            4 to setOf(3),
            5 to setOf(3),
            6 to setOf(2),
            7 to setOf(1)
        )

        assertFalse(graph.containsCycles())
    }

    @Test
    fun example2() {
        // Small 3-cycle: {1, 2, 3}
        val graph: AdjacencyGraph = mapOf(
            0 to setOf(1),
            1 to setOf(0, 2, 3, 7),
            2 to setOf(1, 3, 6),
            3 to setOf(1, 2, 4, 5),
            4 to setOf(3),
            5 to setOf(3),
            6 to setOf(2),
            7 to setOf(1)
        )

        assertTrue(graph.containsCycles())
    }

    @Test
    fun example3() {
        // Large 5-cycle: {1, 2, 6, 5, 3}
        val graph: AdjacencyGraph = mapOf(
            0 to setOf(1),
            1 to setOf(0, 2, 3, 7),
            2 to setOf(1, 6),
            3 to setOf(1, 4, 5),
            4 to setOf(3),
            5 to setOf(3, 6),
            6 to setOf(2, 5),
            7 to setOf(1)
        )

        assertTrue(graph.containsCycles())
    }
}