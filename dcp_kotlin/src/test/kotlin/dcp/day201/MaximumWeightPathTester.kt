package dcp.day201
// MaximumPathWeightTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaximumWeightPathTester {
    @Test
    fun nextHighestConstantTest() {
        val t1 = listOf(mutableListOf(1), mutableListOf(2, 3), mutableListOf(1, 5, 1))
        assertEquals(maximumWeightPath(t1), 9)

        val t2 = listOf(mutableListOf(3))
        assertEquals(maximumWeightPath(t2), 3)

        val t3 = listOf<MutableList<Int>>()
        assertEquals(maximumWeightPath(t3), 0)
    }
}