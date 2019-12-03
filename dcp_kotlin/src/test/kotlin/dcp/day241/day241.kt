package dcp.day241

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        assertEquals(calculateHIndex(listOf(4, 3, 0, 1, 5)), 3)
    }

    @Test
    fun example2() {
        assertEquals(calculateHIndex(emptyList()), 0)
    }

    @Test
    fun example3() {
        assertEquals(calculateHIndex(listOf(3, 3, 1, 1, 0)), 2)
    }

    @Test
    fun example4() {
        assertEquals(calculateHIndex(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0)), 5)
    }
}