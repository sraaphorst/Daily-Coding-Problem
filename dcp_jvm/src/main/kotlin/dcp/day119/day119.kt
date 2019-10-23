package dcp.day119
// day119.kt
// By Sebastian Raaphorst, 2019.

import kotlin.random.Random.Default

data class Interval(val start: Int, val end: Int) {
    fun contains(time: Int): Boolean {
        return time in start..end
    }

    override fun toString(): String {
        return "[$start,$end]"
    }
}

fun findIntervalCover1(intervals: List<Interval>): Set<Int> {
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

fun checkCovering(intervals: List<Interval>, covering: Set<Int>): Boolean {
    return intervals.all { interval -> covering.any{interval.contains(it)} }
}

fun randomIntervals(from: Int, to: Int): List<Interval> {
    val numIntervals = Default.nextInt(0, 200)
    return List(numIntervals) {
        val v1 = Default.nextInt(from, to)
        val v2 = Default.nextInt(from, to)
        Interval(Integer.min(v1, v2), Integer.max(v1, v2))
    }
}

fun execute(intervals: List<Interval>) {
    println("Intervals: ${intervals.joinToString()}")
    val covering = findIntervalCover1(intervals)
    println("Covering: ${covering.joinToString(prefix = "{", separator = ", ", postfix = "}")}")
    println("Covering check: ${checkCovering(intervals, covering)}\n")
}

fun main() {
    val intervals = listOf(Interval(0, 4), Interval(5, 7), Interval(1, 2), Interval(8,10), Interval(6, 9), Interval(6, 7))
    execute(intervals)
    val randomIntervals = randomIntervals(0, 200)
    execute(randomIntervals)
}