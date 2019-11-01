package dcp.day200
// day200.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.max
import kotlin.math.min

data class Interval(val start: Double, val end: Double): Comparable<Interval> {
    operator fun contains(that: Interval): Boolean = start <= that.start && end >= that.end

    operator fun contains(value: Double): Boolean = value in start..end

    override fun compareTo(other: Interval): Int {
        return COMPARATOR.compare(this, other)
    }

    fun intersection(other: Interval): Interval? {
        // If there is a gap, then there is no intersection.
        if (end < other.start || other.end < start)
            return null
        return Interval(max(start, other.start), min(end, other.end))
    }

    companion object {
        fun makeInterval(start: Double, end: Double): Interval? {
            return if (start > end) null else Interval(start, end)
        }

        private val COMPARATOR = Comparator.comparingDouble<Interval>(Interval::end)
    }
}

// This is the non-functional, efficient implementation of findStabbingSet.
// We sort the intervals and then only have to traverse them. Since we allow for nullability, this takes
// O(n log n) complexity and O(n) space: if we eliminate nullibility, we could do it in constant space.
fun smallestStabbingSet(intervals: List<Interval?>): Set<Double> {
    // Filter out the nulls, sort by increasing order on the last element, and then take
    // the largest number in the current interval.
    // Consume intervals until we reach an interval that requires a new stabbing number.
    val sortedIntervals = intervals.filterNotNull().sorted()
    val stabbingSet = mutableListOf<Double>()

    var lastStab: Double? = null
    for (interval in sortedIntervals) {
        if (lastStab == null || (lastStab !in interval)) {
            lastStab = interval.end
            stabbingSet.add(lastStab)
        }
    }

    return stabbingSet.toSet()
}

// A functional programming approach: higher complexity and space required, i.e. O(n^2) in each case.
// This will not return the same stabbing set, but the sizes should be the same and they should both be solutions.
fun smallestStabSetFP(intervals: List<Interval?>): Set<Double> {
    // We want a set of all intersections of the intervals, and then filter to the minimal intervals, i.e.
    // we want the intersection for every pair of intervals (including every interval with itself so that the set
    // contains the original intervals) and then we filter out intervals that contain other intervals.
    // What is left behind is a set of intervals that is disjoint but has points that cover all the initial
    // intervals, so we can just pick one point from each one to get the stab set.
    val definedIntervals = intervals.filterNotNull()
    val allIntersections = definedIntervals.flatMap { i1 ->definedIntervals.map { i2 -> i1.intersection(i2) }  }.filterNotNull().distinct()
    val maximalIntervals = allIntersections.filter { i1-> allIntersections.none { i2 -> i1 != i2 && i2 in i1 } }

    // Any point from each of the maximalIntervals would do.
    return maximalIntervals.map { it.start }.toSet()
}
