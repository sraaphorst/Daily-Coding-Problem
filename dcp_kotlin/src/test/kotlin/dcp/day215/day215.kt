package dcp.day215
// day215.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val t = BinaryTree(5,
            BinaryTree(3,
                BinaryTree(1,
                    BinaryTree(0, null, null), null),
                BinaryTree(4, null, null)),
            BinaryTree(7,
                BinaryTree(6, null, null),
                BinaryTree(9,
                    BinaryTree(8, null, null), null)))

        assertEquals(t.findHorizontalDistanceView(), listOf(0, 1, 3, 6, 8, 9))
    }
}
