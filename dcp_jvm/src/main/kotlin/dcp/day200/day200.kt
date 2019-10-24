package dcp.day200

import kotlin.math.max
import kotlin.math.min

// day200.kt
// By Sebastian Raaphorst, 2019.

data class Interval private constructor(val start: Double, val end: Double) {
    fun contains(that: Interval): Boolean = start <= that.start && end >= that.end

    fun intersection(that: Interval): Interval? {
        // If there is a gap, then there is no intersection.
        if (end < that.start || that.end < start)
            return null
        return Interval(max(start, that.start), min(end, that.end))
    }

    companion object {
        fun makeInterval(start: Double, end: Double): Interval? {
            return if (start > end) null else Interval(start, end)
        }
    }
}

fun findStabbingSet(intervals: List<Interval>): List<Double> {
    // We want a minimal set of Intervals, i,e. only the non-intersecting intervals.
    // We get this by taking the intersections of all pairs of intervals (including themselves to
    // contain them in the final result), and then we keep only the Intervals that are not contained
    // in any other intervals.
    val allIntersections = intervals.flatMap { i1 -> intervals.map { it.intersection(i1) } }
    return listOf(0.0)
}

fun main() {
}