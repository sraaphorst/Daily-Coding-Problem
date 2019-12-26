package dcp.day264
// day264.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UnitTests {
    @Test
    fun test1() {
        assertTrue(covered(2, 3, deBruijn(2, 3)))
    }

    @Test
    fun test2() {
        assertTrue(covered(4, 2, deBruijn(4, 2)))
    }
}

class PropertyTests: StringSpec() {
    init {
        "Generate de Bruijn sequences" {
            forAll( Gen.pair(Gen.choose(2, 6), Gen.choose(1, 6))) { (k, n) ->
                covered(k, n, deBruijn(k, n))
            }
        }
    }
}