package dcp.day310
// day310.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        for (n in 1 until 10000) {
            val n1 = binaryCount(n)
            val n2 = binaryCount2(n)
            val n3 = binaryCountViaStrings(n)
            assertEquals(n1, n2)
            assertEquals(n2, n3)
        }
    }
}