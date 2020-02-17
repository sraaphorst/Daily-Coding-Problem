package dcp.day314

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test


class UnitTests {
    @Test
    fun example1() {
        val s1 = listOf(2, 3, 5, 6, 7)
        val t1 = listOf(0, 1, 4, 8)
        assertEquals(findMinimalSignal(s1, t1), 2)
    }

    @Test
    fun example2() {
        val s2 = listOf(1, 5, 11, 20)
        val t2 = listOf(4, 8, 5)
        assertEquals(findMinimalSignal(s2, t2),12)
    }

    @Test
    fun example3() {
        val s3 = listOf(4, 9, 13, 14)
        val t3 = listOf(0, 6, 12, 16)
        assertEquals(findMinimalSignal(s3, t3), 3)
    }

    @Test
    fun example4() {
        val t4 = listOf(10)
        val s4 = listOf(0)
        assertEquals(findMinimalSignal(s4, t4), 0)
    }
}