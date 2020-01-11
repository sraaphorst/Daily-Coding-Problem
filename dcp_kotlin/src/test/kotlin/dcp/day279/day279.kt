package dcp.day279
// day279.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val graph: AdjacencyGraph = mapOf(
            0 to setOf(1, 2),
            1 to setOf(0, 5),
            2 to setOf(0),
            3 to setOf(6),
            4 to emptySet(),
            5 to setOf(1),
            6 to setOf(3)
        )

        val transitiveClosures = findTransitiveClosures(graph)

        assertEquals(transitiveClosures, setOf(setOf(0, 1, 2, 5), setOf(3, 6), setOf(4)))
    }
}