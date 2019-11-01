package dcp.day200
// day200.kt
// By Sebastian Raaphorst, 2019.

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

// Not sure how to do this.
//class StringSpecExample: StringSpec() {
//    init {
//        "Set detection" {
//            forAll(Gen.list(Gen.pair(Gen.numericDoubles(0, 200), Gen.numericDoubles(0, 200)))) { list ->
//                // Convert the lists into intervals.
//                val intervals = list
//                    .filter { it.second >= it.first }
//                    .map { (s, e) -> Interval.makeInterval(min(s, e), max(s, e)) }
//                    .filterNotNull()
//
//                val ss1 = smallestStabbingSet(intervals)
//                val ss2 = smallestStabSetFP(intervals)
//                assertEquals(ss1.size, ss2.size)
//            }
//        }
//    }
//}