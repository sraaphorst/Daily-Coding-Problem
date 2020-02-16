package dcp.day315

import org.junit.jupiter.api.Test
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class UnitTests {
    @Test
    fun example1() {
        val m = listOf(
            listOf(1, 2, 3, 4, 8),
            listOf(5, 1, 2, 3, 4),
            listOf(4, 5, 1, 2, 3),
            listOf(7, 4, 5, 1, 2),
            listOf(1, 7, 4, 5, 1)
        )
        assertTrue(m.isToeplitz())
    }

    @Test
    fun example2() {
        val m = listOf(
            listOf(1, 2, 3, 4, 8),
            listOf(5, 1, 2, 3, 5)
        )
        assertFalse(m.isToeplitz())
    }
}