package dcp.day257
// day257.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun test1() {
        assertEquals(smallestWindowBruteForce(listOf(3, 7, 5, 6, 9)), Pair(1, 3))
    }

    @Test
    fun test2() {
        assertEquals(smallestWindow(listOf(3, 7, 5, 6, 9)), Pair(1, 3))
    }

    @Test
    fun test3() {
        assertEquals(smallestWindowBruteForce(listOf(2, 3, 4, 9, 7, 6, 5, 11, 10, 12)), Pair(3, 8))
    }

    @Test
    fun test4() {
        assertEquals(smallestWindow(listOf(2, 3, 4, 9, 7, 6, 5, 11, 10, 12)), Pair(3, 8))
    }
}

class PropertyTests : StringSpec() {
    init {
        "Array window" {
            forAll(Gen.list(Gen.choose(0, 1000))) { lst ->
                smallestWindowBruteForce(lst) == smallestWindow(lst)
            }
        }
    }
}