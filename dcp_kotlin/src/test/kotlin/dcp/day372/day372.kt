package dcp.day372
// day372.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun testLessThan10() {
        assertTrue((0..9).all { it.numDigits() == 1 })
    }

    @Test
    fun testLessThan100() {
        assertTrue((10..99).all { it.numDigits() == 2 })
    }

    @Test
    fun testLessThan1000() {
        assertTrue((100..999).all { it.numDigits() == 3 })
    }
}