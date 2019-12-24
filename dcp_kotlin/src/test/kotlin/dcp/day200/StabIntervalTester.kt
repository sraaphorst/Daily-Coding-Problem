package dcp.day200
// StabIntervalTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

// Based on the rules we have implemented, this should be the final results.
class StabIntervalTester {
    @Test
    fun stabIntervalTest() {
        val list1 = listOf(
            Interval.makeInterval(0.0, 3.0),
            Interval.makeInterval(2.0, 3.0),
            Interval.makeInterval(15.0, 3.0),
            Interval.makeInterval(8.0, 11.0),
            Interval.makeInterval(7.0, 10.0),
            Interval.makeInterval(4.0, 6.0)
        )
        assertEquals(smallestStabbingSet(list1).size, smallestStabSetFP(list1).size)
    }
}