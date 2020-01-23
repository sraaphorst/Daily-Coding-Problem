package dcp.day292
// day292.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class UnitTests {
    @Test
    fun example1() {
        val s = listOf(
            listOf(3),
            listOf(2),
            listOf(1, 4),
            listOf(0, 4, 5),
            listOf(2, 3),
            listOf(3),
            listOf(7),
            listOf(6)
        )
        assertNotNull(s.findBipartition())
    }

    @Test
    fun example2() {
        val s = listOf(
            listOf(3),
            listOf(2),
            listOf(1, 3, 4),
            listOf(0, 2, 4, 5),
            listOf(2, 3),
            listOf(3)
        )
        assertNull(s.findBipartition())
    }

    @Test
    fun example3() {
        val s: AdjacencyGraph = listOf(
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )
        assertNotNull(s.findBipartition())
    }
}