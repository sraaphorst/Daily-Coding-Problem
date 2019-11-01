package dcp.day198
// LongestDivisorTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LongestDivisorTester {
    @Test
    fun longestDivisorTest() {
        assertEquals(findLongestDivisors(listOf(3, 5, 10, 20, 21)), setOf(5, 10, 20))
        assertEquals(findLongestDivisors(listOf(1, 3, 6, 24)), setOf(1, 3, 6, 24))
        assertEquals(findLongestDivisors(listOf(5, 10, 20, 15, 35, 40, 50, 3, 9)), setOf(5, 10, 20, 40))
        assertEquals(findLongestDivisors(listOf()), setOf())
        assertEquals(findLongestDivisors(listOf(0, 2, 4)), setOf(0, 2, 4))
        assertEquals(findLongestDivisors(listOf(1, 3, 5)), setOf(1, 3, 5))
    }
}