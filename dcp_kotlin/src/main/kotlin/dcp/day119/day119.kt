package dcp.day119
// dcp.day119.kt
// By Sebastian Raaphorst, 2019.

data class Interval(val start: Int, val end: Int) {
    fun contains(time: Int): Boolean {
        return time in start..end
    }

    override fun toString(): String {
        return "[$start,$end]"
    }
}

fun findIntervalCover(intervals: List<Interval>): Set<Int> {
    if (intervals.isEmpty())
        setOf<Int>()

    var covering: Set<Int> = setOf()
    var sortedIntervals = intervals.sortedBy { it.end }

    while (sortedIntervals.isNotEmpty()) {
        val newElem = sortedIntervals.first().end
        covering = covering.plus(newElem)
        sortedIntervals = sortedIntervals.filter { !it.contains(newElem) }
    }
    return covering
}
