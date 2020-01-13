package dcp.day144
// day144.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun example1() {
        val l = listOf(4, 1, 3, 5, 6)
        assertEquals(l.findNextHighestBF(0), 3)
        assertTrue(l.findNextHighestBF(1) in setOf(0, 2))
        assertEquals(l.findNextHighestBF(2), 3)
        assertEquals(l.findNextHighestBF(3), 4)
        assertNull(l.findNextHighestBF(4))
    }

    @Test
    fun example2() {
        val ppl = preprocess(listOf(4, 1, 3, 5, 6))
        assertEquals(ppl[0], 3)
        assertTrue(ppl[1] in setOf(0, 2))
        assertEquals(ppl[2], 3)
        assertEquals(ppl[3], 4)
        assertNull(ppl[4])
    }
}