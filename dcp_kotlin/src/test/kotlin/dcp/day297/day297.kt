package dcp.day297
// day297.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UnitTests {
    @Test
    fun example1() {
        val preferences = listOf(
            setOf(0, 1, 3, 6),
            setOf(1, 4, 7),
            setOf(2, 4, 7, 5),
            setOf(3, 2, 5),
            setOf(5, 8))
        assertEquals(drinksToMemorize(preferences), setOf(1, 5))
    }

    @Test
    fun example2() {
        val preferences = listOf(
            setOf(1),
            setOf(0, 1)
        )
        assertEquals(drinksToMemorize(preferences), setOf(1))
    }

    @Test
    fun example3() {
        val preferences = listOf(
            setOf(5),
            setOf(4),
            setOf(3),
            setOf(2),
            setOf(1),
            setOf(0)
        )
        assertEquals(drinksToMemorize(preferences), setOf(0, 1, 2, 3, 4, 5))
    }

    @Test
    fun example4() {
        val preferences = listOf(
            setOf(1, 2, 3, 4, 5, 6, 7),
            emptySet()
        )
        assertNull(drinksToMemorize(preferences), null)
    }
}