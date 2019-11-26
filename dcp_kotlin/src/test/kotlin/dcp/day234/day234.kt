package dcp.day234

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    fun e(u: Vertex, v: Vertex, w: Weight): WeightedEdge = ((u to v) to w)

    @Test
    fun example1() {
        val wg = WeightedGraph(
            listOf(
                e(0,1,4),
                e(0,2,4),
                e(1,2,2),
                e(2,3,1),
                e(2,4,4),
                e(2,5,2),
                e(3,4,3),
                e(4,5,3)
            )
        )

        assertEquals(wg.minimumSpanningTree,
            setOf(
                e(0,1,4),
                e(0,2,4),
                e(2,4,4),
                e(3,4,3),
                e(4,5,3)
            )
        )
    }

    @Test
    fun example2() {
        val wg = WeightedGraph(
            listOf(
                e(0,1,2),
                e(0,2,2),
                e(0,4,4),
                e(0,5,2),
                e(1,2,1),
                e(1,3,3),
                e(2,3,5),
                e(2,4,3),
                e(3,4,2),
                e(3,5,5),
                e(4,5,1)
            )
        )

        assertEquals(wg.minimumSpanningTree,
            setOf(
                e(0, 4, 4),
                e(2, 4, 3),
                e(2, 3, 5),
                e(3, 5, 5),
                e(1, 3, 3)
            )
        )
    }
}