package dcp.day119
// CoverTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CoverTester {
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
