package dcp.day204
// TreeTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TreeTester {
    @Test
    fun treeTest() {
        val t = CBT(1, CBT(2, CBT(4), CBT(5)), CBT(3, CBT(6), CBT(7)))
        assertEquals(countNodes(t), 7)

        val t2 = CBT(1, CBT(2, CBT(4), CBT(5)), CBT(3, CBT(6)))
        assertEquals(countNodes(t2), 6)

        val t3 = CBT(1, CBT(2, CBT(4), CBT(5)), CBT(3))
        assertEquals(countNodes(t3), 5)
    }
}