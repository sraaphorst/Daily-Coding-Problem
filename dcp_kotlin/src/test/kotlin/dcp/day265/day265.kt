package dcp.day265
// day265.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    private val example = listOf(10, 40, 200, 1000, 60, 30)
    private val bonuses = listOf(1, 2, 3, 4, 2, 1)

    @Test
    fun test1() {
        assertEquals(calculateBonus(example), bonuses)
    }

    @Test
    fun test2() {
        assertEquals(calculateBonusMutable(example), bonuses)
    }
}

class PropertyTests: StringSpec() {
    init {
        "Calculate bonuses correctly" {
            forAll(Gen.list(Gen.choose(0, 100000))) { locs ->
                calculateBonus(locs) == calculateBonusMutable(locs)
            }
        }
    }
}