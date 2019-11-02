package dcp.day207
// BipartiteTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BipartiteTester {
    @Test
    fun cycle3Test() {
        val g = Graph(listOf(0, 1, 2), mapOf(0 to setOf(1, 2), 1 to setOf(0, 2), 2 to setOf(0, 1)))
        assertEquals(g.isBipartite, false)
    }

    @Test
    fun cycle4Test() {
        // 0 - 1 - 2 - 3 - 0
        val g = Graph(listOf(0, 1, 2, 3), mapOf(0 to setOf(1, 3), 1 to setOf(0, 2), 2 to setOf(1, 3), 3 to setOf(0, 2)))
        assertEquals(g.isBipartite, true)
    }

    @Test
    fun k4Test() {
        val g = Graph(listOf(0, 1, 2, 3), mapOf(0 to setOf(1, 2, 3), 1 to setOf(0, 2, 3), 2 to setOf(0, 1, 3), 3 to setOf(0, 1, 2)))
        assertEquals(g.isBipartite, false)
    }

    @Test
    fun evenOddTest() {
        val g = Graph(listOf(0, 1, 2, 3, 4, 5),
            mapOf(0 to setOf(1, 3, 5), 2 to setOf(1, 3, 5), 3 to setOf(1, 3, 5),
                  1 to setOf(0, 2, 4), 3 to setOf(0, 2, 4), 5 to setOf(0, 2, 4)))
        assertEquals(g.isBipartite, true)
    }
}