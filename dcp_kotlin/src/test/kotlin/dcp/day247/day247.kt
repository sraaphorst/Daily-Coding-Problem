package dcp.day247
// day247.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Examples {
    @Test
    fun example1() {
        assertEquals(BinaryTree(1).balanced(), true)
    }

    @Test
    fun example2() {
        assertEquals(BinaryTree(1, BinaryTree(2)).balanced(), true)
    }

    @Test
    fun example3() {
        assertEquals(BinaryTree(1, BinaryTree(2), BinaryTree(3)).balanced(), true)
    }

    @Test
    fun example4() {
        assertEquals(BinaryTree(1, BinaryTree(2, BinaryTree(3))).balanced(), false)
    }
}