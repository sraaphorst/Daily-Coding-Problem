package dcp.day262
// day262.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun test1() {
        val g = UndirectedSimpleGraph(10, mapOf(
            Pair(0, setOf(1, 2)),
            Pair(1, setOf(0, 2)),
            Pair(2, setOf(0, 1, 6)),
            Pair(3, setOf(4, 5)),
            Pair(4, setOf(3, 5)),
            Pair(5, setOf(3, 4, 6)),
            Pair(6, setOf(2, 5, 7)),
            Pair(7, setOf(6, 8, 9)),
            Pair(8, setOf(7)),
            Pair(9, setOf(7))
        ))
        assertEquals(g.findBridgesBF(), setOf(Edge(2, 6), Edge(5, 6), Edge(6, 7), Edge(7, 8), Edge(7, 9)))
    }
}