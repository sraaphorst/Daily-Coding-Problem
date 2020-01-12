package dcp.day281
// day281.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val matrix: Matrix = listOf(
            listOf(3, 5, 1, 1),
            listOf(2, 3, 3, 2),
            listOf(5, 5),
            listOf(4, 4, 2),
            listOf(1, 3, 3, 3),
            listOf(1, 1, 6, 1, 1)
        )

        assertEquals(findVerticalLine(matrix), 8)
    }
}