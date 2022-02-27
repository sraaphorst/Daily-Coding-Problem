package dcp.day311
// day311.kt
// By Sebastian Raaphorst.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun example2() {
        assertEquals(listOf(0).findPeak(), 0)
    }

    @Test
    fun example3() {
        assertTrue{ listOf(0, 2, 3, 1, 5, 4).findPeak() in setOf(2, 4)}
    }
}