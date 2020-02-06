package dcp.day305
// day305.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(emptyList<Int>().removeZeroSumSublists(), emptyList())
    }

    @Test
    fun example2() {
        assertEquals(listOf(3, 4, 0, -7, 0, 5, 0, -6, 6, 0).removeZeroSumSublists(), listOf(5))
    }

    @Test
    fun example3() {
        assertEquals(listOf(1, 2, 3, -5, 0, 8, 1, -4, -5, 5, 3, -4, 1).removeZeroSumSublists(), listOf(1, 5))
    }
}