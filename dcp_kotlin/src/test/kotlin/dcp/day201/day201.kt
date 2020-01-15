package dcp.day201
// day201.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun nextHighestConstantTest() {
        val t1 = listOf(mutableListOf(1), mutableListOf(2, 3), mutableListOf(1, 5, 1))
        assertEquals(maximumWeightPath(t1), 9)
    }

    @Test
    fun example2() {
        val t2 = listOf(mutableListOf(3))
        assertEquals(maximumWeightPath(t2), 3)
    }

    @Test
    fun example3() {
        val t3 = listOf<MutableList<Int>>()
        assertEquals(maximumWeightPath(t3), 0)
    }
}