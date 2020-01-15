package dcp.day206
// day206.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun permTest1() {
        assertEquals(permute(listOf("a", "b", "c"), listOf(2, 1, 0)), listOf("c", "b", "a"))
        assertEquals(permute(mutableListOf("a", "b", "c"), mutableListOf(2, 1, 0)), mutableListOf("c", "b", "a"))
    }

    @Test
    fun permTestID() {
        assertEquals(permute(listOf("a", "b", "c", "d", "e"), listOf(0, 1, 2, 3, 4)), listOf("a", "b", "c", "d", "e"))
        assertEquals(permute(mutableListOf("a", "b", "c", "d", "e"), mutableListOf(0, 1, 2, 3, 4)), mutableListOf("a", "b", "c", "d", "e"))
    }

    @Test
    fun invertPermutation() {
        val elems = (0..4).toList()
        val perm = listOf(1, 0, 3, 2, 4)
        val permInverse = listOf(1, 0, 3, 2, 4)
        assertEquals(permute(permute(elems, perm), permInverse), elems)
    }
}
