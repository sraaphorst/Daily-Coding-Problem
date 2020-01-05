package dcp.day270
// day270.kt
// By Sebastian Raaphorst, 2020.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun example1() {
        assertTrue { listOf(4, 7, 11, 16, 27, 45, 55, 65, 80, 100).fibContains(45) }
    }
}

class PropertyTests: StringSpec() {
    init {
        "Find first fixed point correctly" {
            forAll(100_000, Gen.list(Gen.choose(0, 50))) { lst ->
                lst.sorted().all { lst.fibContains(it) }
            }
        }
    }
}