package dcp.day291
// day291.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(findBoats(200, arrayOf(100, 200, 150, 50)), 3)
    }

    @Test
    fun example2() {
        assertEquals(findBoats(200, arrayOf(30, 70, 40, 200, 100, 150, 80)), 4)
    }

    @Test
    fun example3() {
        assertEquals(findBoats(200, arrayOf(32, 40, 45, 75, 63, 101, 200, 112, 142, 168, 123, 196)), 7)
    }
}