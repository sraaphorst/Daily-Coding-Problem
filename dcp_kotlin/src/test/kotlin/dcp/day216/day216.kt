package dcp.day216
// day216.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    // We just make sure that we have a bijection.
    fun romanNumeralTester() {
        // This goes from I to MMMM / 1 to 2000
        for (i in 0..2000) {
            val rm  = RomanNumeral(i)
            val rms = RomanNumeral(rm.toString)
            assertEquals(i, rms.toInt)
        }
    }
}