package dcp.day255
// day255.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        val adjgraph = listOf(
            listOf(0, 1, 3),
            listOf(1, 2),
            listOf(2),
            listOf(3)
        )

        val tc = listOf(
            listOf(1, 1, 1, 1),
            listOf(0, 1, 1, 0),
            listOf(0, 0, 1, 0),
            listOf(0, 0, 0, 1)
        )

        assertEquals(adjacencyGraphtoTransitiveClosure(adjgraph), tc)
    }

    @Test
    fun example2() {
        val adjgraph = listOf(
            listOf(0, 1, 1, 0),
            listOf(0, 0, 1, 0),
            listOf(1, 0, 0, 1),
            listOf()
        )

        val tc = listOf(
            listOf(1, 1, 1, 1),
            listOf(1, 1, 1, 1),
            listOf(1, 1, 1, 1),
            listOf(0, 0, 0, 1)
        )

        assertEquals(adjacencyGraphtoTransitiveClosure(adjgraph), tc)
    }
}