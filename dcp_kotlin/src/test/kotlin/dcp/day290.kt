package dcp
// day290.kt
// By Sebastian Raaphorst, 2020.

import dcp.day290.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        assertEquals(quxes().reduce(), quxes())
    }

    @Test
    fun example2() {
        assertEquals(quxes(R).reduce(), quxes(R))
    }

    @Test
    fun example3() {
        assertEquals(quxes(R, G, B, G, B).reduce(), quxes(R))
    }

    @Test
    fun example4() {
        assertEquals(quxes(R, R, R, R, G, G, G).reduce(), quxes(G, G, G))
    }

    @Test
    fun example5() {
        assertEquals(quxes(R, R, R, R, G, G, G, R).reduce(), quxes(B))
    }
}