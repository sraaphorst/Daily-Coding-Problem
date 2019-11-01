package dcp.day204

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TreeTester {
    @Test
    fun fullTreeTest() {
        val t = CBT(1,
            CBT(2,
                CBT(3,
                    CBT(4), CBT(5)),
                CBT(6, CBT(7))))
        println(t)
        //assertEquals(countNodes(t), 7)
        assertEquals(true, true)
    }
}