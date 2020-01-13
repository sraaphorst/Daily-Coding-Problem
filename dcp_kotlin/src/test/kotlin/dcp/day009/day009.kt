package dcp.day009
// day009.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(listOf(5, 5, 10, 100, 10, 5).findMaxSumOfNonAdjacentElements(), 110)
    }

    @Test
    fun example2() {
        assertEquals(listOf(1, 20, 3).findMaxSumOfNonAdjacentElements(), 20)
    }

    @Test
    fun example3() {
        assertEquals(listOf(1, 2, 3).findMaxSumOfNonAdjacentElements(), 4)
    }

    @Test
    fun example4() {
        assertEquals(listOf(2, 4, 6, 2, 5).findMaxSumOfNonAdjacentElements(), 13)
    }

    @Test
    fun example5() {
        assertEquals(listOf(5, 1, 1, 5).findMaxSumOfNonAdjacentElements(), 10)
    }

    @Test
    fun example6() {
        assertEquals(listOf(5, 1, 1, -5).findMaxSumOfNonAdjacentElements(), 6)
    }

    @Test
    fun example7() {
        assertEquals(listOf(-1, -2, -3, -4, -5).findMaxSumOfNonAdjacentElements(), 0)
    }

    @Test
    fun example8() {
        assertEquals(listOf(-1, -1, 1, -1, -1).findMaxSumOfNonAdjacentElements(), 1)
    }
}