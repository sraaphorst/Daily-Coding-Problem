package dcp.day211
// abracadabratest.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AbracadabraTest {
    @Test
    fun abracadabraTest() {
        assertEquals(findStartPoints("abracadabra", "abr"), setOf(0, 7))
    }

    @Test
    fun abracadabraTest2() {
        assertEquals(findStartPoints("abracadabra", "a"), setOf(0, 3, 5, 7, 10))
    }
}
