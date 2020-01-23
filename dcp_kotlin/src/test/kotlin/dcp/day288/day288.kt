package dcp.day288
// day288.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class UnitTests {
    // Just check all numbers over four digits that are legal, i.e.
    // do not contain any digit repeated three times.
    @Test
    fun testAll() {
        for (i in 1 until 10000) {
            if (i.legal())
                assertNotNull(i.checkKaprekar())
        }
    }
}