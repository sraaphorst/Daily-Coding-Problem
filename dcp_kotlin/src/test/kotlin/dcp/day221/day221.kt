package dcp.day221
// SevenishTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SevenishTester {
    @Test
    // We just make sure that we have a bijection.
    fun sevenishTest() {
        // Test consistency in calculating the first 1000 sevenish numbers.
        for (i in 1..1000)
            assertEquals(sevenish(i), sevenish_loop(i))
    }
}