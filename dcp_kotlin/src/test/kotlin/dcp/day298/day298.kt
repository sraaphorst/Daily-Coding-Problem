package dcp.day298
// day298.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class UnitTests {
    @Test
    fun Example1() {
        val path = listOf(2, 1, 2, 3, 3, 1, 3, 5)
        assertEquals(applePicking(path), 4)
    }

    @Test
    fun Example2() {
        val path = listOf(1, 1, 2, 1, 2, 3, 3, 3, 2, 2, 3, 1, 1, 1, 3, 2)
        assertEquals(applePicking(path), 7)
    }

    @Test
    fun Example3() {
        val path = listOf(1, 1, 1, 1, 1)
        assertEquals(applePicking(path), 5)
    }

    @Test
    fun Example4() {
        val path = listOf(1, 2, 2, 1, 2, 2, 3, 3, 3, 2, 3, 2, 3, 1, 3, 2, 3, 2, 1, 3)
        assertEquals(applePicking(path), 9)
    }

    @Test
    fun Example5() {
        val path = emptyList<Int>()
        assertEquals(applePicking(path), 0)
    }
}
