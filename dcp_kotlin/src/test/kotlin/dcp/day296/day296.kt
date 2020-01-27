package dcp.day296

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    private fun <T: Comparable<T>> N(value: T, left: BinarySearchTree<T>? = null, right: BinarySearchTree<T>? = null): BinarySearchTree<T> =
        BinarySearchTree(value, left, right)

    @Test
    fun example1() {
        assertEquals(emptyArray<Int>().createBalancedBinarySearchTree(), null)
    }

    @Test
    fun example2() {
        assertEquals(arrayOf(1).createBalancedBinarySearchTree(), N(1))

    }
    @Test
    fun example3() {
        assertEquals(arrayOf(2, 1, 3).createBalancedBinarySearchTree(), N(2, N(1), N(3)))
    }

    @Test
    fun example4() {
        assertEquals(arrayOf(7, 5, 3, 1, 2, 4, 6).createBalancedBinarySearchTree(),
            N(4, N(2, N(1), N(3)), N(6, N(5), N(7))))
    }
}