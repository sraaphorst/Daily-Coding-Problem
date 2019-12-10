package dcp.day245
// day245.kt
// By Sebastian Raaphorst, 2019.

import dcp.day247.BinaryTree
import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.math.min
import kotlin.test.assertEquals

class ArrayHopper : StringSpec() {
    init {
        "Array hopper tester" {
            forAll(20, Gen.list(Gen.choose(1, 12))) { nums ->
                val limitedNums = nums.take(min(nums.size, 20))
                brute_force(limitedNums) == dynamic_programming(limitedNums)
            }
        }
    }
}

class Examples {
    @Test
    fun example1() {
        val list = listOf(6, 2, 4, 0, 5, 1, 1, 4, 2, 9)
        assertEquals(brute_force(list), dynamic_programming(list))
    }
}