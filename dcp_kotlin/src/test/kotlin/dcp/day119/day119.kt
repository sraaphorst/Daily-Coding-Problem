package dcp.day119

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.math.max
import kotlin.math.min

class StringSpecExample: StringSpec() {
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