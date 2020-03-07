package dcp.day334
// day334.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class UnitTests {
    @Test
    fun example1() {
        assertTrue(play24(listOf(5, 2, 7, 8)))
    }

    @Test
    fun example2() {
        assertTrue(play24(listOf(1, 2, 3, 4)))
    }

    @Test
    fun example3() {
        assertTrue(play24(listOf(18, 20, 3, 10)))
    }

    @Test
    fun example4() {
        assertFalse(play24(listOf(1, 1, 1, 1)))
    }

    @Test
    fun example5() {
        assertTrue(play24(listOf(2, 2, 2, 3)))
    }

    @Test
    fun example6() {
        assertTrue(play24(listOf(8, 4, 6, 18)))
    }

    @Test
    fun example7() {
        assertFalse(play24(listOf(3, 2, 7, 9)))
    }

    @Test
    fun example8() {
        assertTrue(play24(listOf(23, 1, 2, 1)))
    }
}