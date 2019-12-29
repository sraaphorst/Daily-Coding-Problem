package dcp.day119
// day119.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun coverTest() {
        val intervals = listOf(
            Interval(0, 4),
            Interval(5, 7),
            Interval(1, 2),
            Interval(8, 10),
            Interval(6, 9),
            Interval(6, 7))

        val covering = findIntervalCover(intervals)
        val isCovered = intervals.all { interval ->
            covering.any { interval.contains(it) }
        }
        assertEquals(isCovered, true)
    }
}

class PropertyTests: StringSpec() {
    init {
        "Set detection" {
            forAll(Gen.list(Gen.pair(Gen.choose(0, 200), Gen.choose(0, 200)))) { list ->
                // Convert the lists into intervals.
                val intervals = list.filter { it.second >= it.first }.map { (s, e) -> Interval(min(s, e), max(s, e)) }
                val covering = findIntervalCover(intervals)
                intervals.all { interval ->
                    covering.any { interval.contains(it) }
                }
            }
        }
    }
}