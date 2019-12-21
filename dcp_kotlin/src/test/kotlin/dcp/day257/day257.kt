package dcp.day257
// day257.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        assertEquals(smallest_window(listOf(3, 7, 5, 6, 9)), Pair(1, 3))
    }

    @Test
    fun example2() {
        assertEquals(smallest_window(listOf(2, 3, 4, 9, 7, 6, 5, 11, 10, 12)), Pair(3, 8))
    }
}