package dcp.day194
// day194.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PairLineSegmentIntersectTester {
    @Test
    fun pairLineSegmentIntersectTest() {
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
}