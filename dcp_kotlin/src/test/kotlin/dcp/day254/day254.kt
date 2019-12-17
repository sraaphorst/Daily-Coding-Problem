package dcp.day254

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> l(value: T, left: BinaryTree<T>? = null): BinaryTree<T> =
    BinaryTree(value, left, null)
fun <T> r(value: T, right: BinaryTree<T>? = null): BinaryTree<T> =
    BinaryTree(value, null, right)
fun <T> lr(value: T, left: BinaryTree<T>? = null, right: BinaryTree<T>? = null): BinaryTree<T> =
    BinaryTree(value, left, right)
fun <T> n(value: T): BinaryTree<T> =
    BinaryTree(value, null, null)

class Examples {
    @Test
    fun example1() {
        val t = lr(0, l(1, r(3, n(5))), r(2, lr(4, n(6), n(7))))
        val soln = lr(0, n(5), lr(4, n(6), n(7)))
        assertEquals(t.toFullTree(), soln)
    }
}