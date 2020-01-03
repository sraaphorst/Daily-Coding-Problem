package dcp.day269

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// day 269.kt
// By Sebastian Raaphorst, 2020.

class UnitTests {
    @Test
    fun example1() {
        assertEquals(push("R.L"), "R.L")
    }

    @Test
    fun example2() {
        assertEquals(push("L.R"), "L.R")
    }

    @Test
    fun example3() {
        assertEquals(push("..."), "...")
    }

    @Test
    fun example4() {
        assertEquals(push(".L.R....L"), "LL.RRRLLL")
    }

    @Test
    fun example5() {
        assertEquals(push("..R...L.L"), "..RR.LLLL")
    }

    @Test
    fun example6() {
        assertEquals(push("LR..RL..L.R.L.R"), "LRRRRLLLL.R.L.R")
    }
}


