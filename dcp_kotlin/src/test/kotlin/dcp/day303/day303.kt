package dcp.day303
// day303.kt
// By Sebastian Raaphorst, 2002.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(minClockAngle(0,0), 0)
    }

    @Test
    fun example2() {
        assertEquals(minClockAngle(2, 0), 60)
    }

    @Test
    fun example3() {
        assertEquals(minClockAngle(4, 0), 120)
    }

    @Test
    fun example5() {
        assertEquals(minClockAngle(8, 15), 158)
    }

    @Test
    fun example6() {
        assertEquals(minClockAngle(10, 10), 115)
    }

    @Test
    fun example7() {
        assertEquals(minClockAngle(5, 0), 150)
    }


    @Test
    fun example8() {
        assertEquals(minClockAngle(3, 10), 35)
    }

    @Test
    fun example9() {
        assertEquals(minClockAngle(4, 0), 120)
    }
}