package dcp.day309
// day309.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(listOf(E, P, P, E, P, E, E, E, P).group(), 5)
    }

    @Test
    fun example2() {
        assertEquals(listOf(P, E, E, E, E, E, E, P).group(), 6)
    }

    @Test
    fun example3() {
        assertEquals(listOf(P, E, E, P, E, P, P, P, E, P,P, E, E, P, E).group(), 9)
    }
}