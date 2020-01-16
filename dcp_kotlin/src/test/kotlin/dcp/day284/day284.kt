package dcp.day284
// day284.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val t = BinaryTree(1,
            BinaryTree(2,
                BinaryTree(4, null, null),
                BinaryTree(5, null, null)),
            BinaryTree(3,
                null,
                BinaryTree(6, null, null)))

        assertEquals(t.findAllCousins(), setOf(4 to 6, 5 to 6))
    }

    @Test
    fun example2() {
        val t = BinaryTree(1,
            BinaryTree(2,
                BinaryTree(3,
                    BinaryTree(7, null,
                        BinaryTree(8, null, null)),
                    null),
                BinaryTree(4,
                    BinaryTree(5, null, null),
                    BinaryTree(6,
                        BinaryTree(9, null, null),
                        BinaryTree(10, null, null)))), null)

        assertEquals(t.findAllCousins(), setOf(5 to 7, 6 to 7, 8 to 9, 8 to 10))
    }
}