package dcp.day194
// day194.kt
// By Sebastian Raaphorst, 2019.

typealias PointType = Int
typealias Segment = Pair<PointType, PointType>
typealias Points = List<PointType>

/**
 * Given a set of 2n points of the form (p_i, 0) and (q_i, 1) for i in [0,n), determine the number of line segments
 * of the form (p_i, 0) to (q_i, 0) that intersect.
 *
 * @param ps The x coordinates of the points of the form (p_i, 0): must have same size as qs
 * @param qs The x coordinates of the points of the form (q_i, 0): must have same size as ps
 * @return the number of intersections
 *
 * @sample countIntersections(listOf(0, 2, 5, 6), listOf(1, 3, 4, 5))
 */
fun countIntersections(ps: Points, qs: Points): Int {
    val n = ps.size
    assert(ps.size == qs.size) {"ps and qs must have same size (ps.size =${ps.size}, qs.size=${qs.size}"}

    infix fun Segment.intersect(other: Segment): Boolean {
        val (p1, q1) = this
        val (p2, q2) = other
        return if (p1 <= p2 && q1 >= q2) true
        else p1 >= p2 && q1 <= q2
    }

    val points = sequence {
        for (i in 0 until n)
            yield (ps[i] to qs[i])
    }.toList()

    val intervals = sequence {
        for (i1 in 0 until n)
            for (i2 in (i1 + 1) until n)
                yield(points[i1] to points[i2])
    }

    return intervals.count{ (i1, i2) -> i1 intersect i2}
}
