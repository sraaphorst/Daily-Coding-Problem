package dcp.day295
// day295.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(pascalRow(0), listOf(1))
    }

    @Test
    fun example2() {
        assertEquals(pascalRow(1), listOf(1, 1))
    }

    @Test
    fun example3() {
        assertEquals(pascalRow(2), listOf(1, 2, 1))
    }

    @Test
    fun example4() {
        assertEquals(pascalRow(3), listOf(1, 3, 3, 1))
    }

    @Test
    fun example5() {
        assertEquals(pascalRow(4), listOf(1, 4, 6, 4, 1))
    }

    @Test
    fun example6() {
        assertEquals(pascalRow(5), listOf(1, 5, 10, 10, 5, 1))
    }
}