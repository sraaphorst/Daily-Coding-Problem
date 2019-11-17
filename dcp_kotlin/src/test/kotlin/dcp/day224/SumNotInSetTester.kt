package dcp.day224
// SumNotInSetTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SumNotInSetTester {
    @Test
    fun sumNotInSetTester1() {
        assertEquals(smallestIntegerNotSumInSet(listOf(1, 3, 4, 5)), 2)
    }

    @Test
    fun sumNotInSetTester2() {
        assertEquals(smallestIntegerNotSumInSet(listOf(1, 2, 6, 10, 11, 15)), 4)
    }

    @Test
    fun sumNotInSetTester3() {
        assertEquals(smallestIntegerNotSumInSet(listOf(1, 1, 1, 1)), 5)
    }

    @Test
    fun sumNotInSetTester5() {
        assertEquals(smallestIntegerNotSumInSet(listOf(1, 1, 3, 4, 11)), 10)
    }
}