package dcp.day272
// day272.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(throwDiceDP(3, 6, 7), 15)
    }
}