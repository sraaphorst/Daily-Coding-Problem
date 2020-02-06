package dcp.day306
// day306.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(listOf(6, 5, 3, 2, 8, 10, 9).sortPartiallySorted(3), listOf(2, 3, 5, 6, 8, 9, 10))
    }

    @Test
    fun example2() {
        assertEquals(listOf(10, 9, 8, 7, 4, 70, 60, 50).sortPartiallySorted(4), listOf(4, 7, 8, 9, 10, 50, 60, 70))
    }

    @Test
    fun example3() {
        assertEquals(listOf(3, 1, 2).sortPartiallySorted(5), listOf(1, 2, 3))
    }
}