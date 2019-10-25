package dcp.day200
// day200.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.max
import kotlin.math.min

data class Interval private constructor(val start: Double, val end: Double): Comparable<Interval> {
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

//operator Double.contains(interval: Interval): Boolean {  in interval.start..interval.end }

fun findStabbingSet(intervals: List<Interval?>): Set<Double> {
    // Filter out the nulls, sort by increasing order on the last element, and then take
    // the largest number in the current interval.
    // Consume intervals until we reach an interval that requires a new stabbing number.
    // This runs in O(n log n) and O(n) space.
    // If we disallow null intervals, we could reduce this to constant space.
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

fun main() {
    val list1 = listOf(
            Interval.makeInterval(0.0, 3.0),
            Interval.makeInterval(2.0, 3.0),
            Interval.makeInterval(15.0, 3.0),
            Interval.makeInterval(8.0, 11.0),
            Interval.makeInterval(7.0, 10.0),
            Interval.makeInterval(4.0, 6.0)
    )
    println(findStabbingSet(list1))
}