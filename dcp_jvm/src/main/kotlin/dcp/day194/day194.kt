package dcp.day194
// day194.kt
// By Sebastian Raaphorst, 2019.

import kotlin.test.assertEquals

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

    infix fun Segment.`∩`(other: Segment): Boolean {
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

    return intervals.count{ (i1, i2) -> i1 `∩` i2}
}

fun main() {
    // Test 1: simple test.
    val p1 = listOf(0, 2, 5, 6)
    val q1 = listOf(1, 5, 3, 4)
    val i1 = countIntersections(p1, q1)
    assertEquals(i1, 2, "Test 1 failed: $i1 =!= 2")

    // Test 2: straight line segments, interleaving.
    val p2 = (0..10 step 2).toList()
    val q2 = (1..11 step 2).toList()
    val i2 = countIntersections(p2, q2)
    assertEquals(i2, 0, "Test 1 failed: $i2 =!= 0")

    // Test 3: straight line segments, overlapping: these don't count.
    val p3 = (0..10 step 2).toList()
    val q3 = (0..10 step 2).toList()
    val i3 = countIntersections(p3, q3)
    assertEquals(i3, 0, "Test 1 failed: $i3 =!= 0")
    assert(countIntersections(p3, q3) == 0)

    // Test 4: Xs.
    //
    //  0  12  35  4 <- p
    //   \/  \/  \/
    //   /\  /\  /\
    //  1  03  24  5
    //  ^
    //  |
    //  q
    //
    // Segments (six of them, labelled across the diagonals, e.g.:
    //
    //  0   12  35  4
    //    \        /
    //     \      /
    //  1   03  24  5
    // Here we have the #i=0 segment and the #i=4 segments shown.
    // The #i=4 segment connects p_4 = 3 and q_4 = 2.
    //
    // #i=0 -> p_0 = 0, q_0 = 1 -> (0,1)
    // #1=1 -> p_1 = 1, q_1 = 0 -> (1,0)

    // #i=2 -> p_2 = 1, q_2 = 2 -> (1,2)
    // #i=3 -> p_3 = 2, q_3 = 1 -> (2,1)

    // #i=4 -> p_4 = 3, q_4 = 2 -> (3,2)
    // #i=5 -> p_5 = 2, q_5 = 3 -> (2,3)

    // The intersecting segments happen at the middle points and at the top and bottom points.

    val p4 = listOf(0, 1, 1, 2, 3, 2)
    val q4 = listOf(1, 0, 2, 1, 2, 3)
    val i4 = countIntersections(p4, q4)
    assertEquals(i4, 7, "Test 1 failed: $i4 =!= 7")
}