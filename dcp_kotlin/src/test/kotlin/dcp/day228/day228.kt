package dcp.day228
// MaxNumTester.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.math.min

class MaximumNumTester : StringSpec() {
    init {
        "Max number tester" {
            forAll(10, Gen.list(Gen.choose(1, 100))) { nums ->
                val limitedNums = nums.take(min(nums.size, 10))
                comparatorNumber(limitedNums) == bruteForceNumber(limitedNums)
            }
        }
    }
}