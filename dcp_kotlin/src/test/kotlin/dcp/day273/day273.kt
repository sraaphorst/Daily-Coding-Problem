package dcp.day273
// day273.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example0() {
        assertEquals(listOf(-6, 0, 2, 40).findFixedPoint(), 2)
    }

    @Test
    fun example1() {
        assertEquals(listOf(1, 5, 7, 8).findFixedPoint(), null)
    }
}

class PropertyTests: StringSpec() {
    init {
        "Find first fixed point correctly" {
            forAll(100_000, Gen.list(Gen.choose(0, 50))) { locs ->
                val fixed: Int? = locs.findFixedPoint()
                if (fixed != null) locs[fixed] == fixed else true
            }
        }
    }
}
