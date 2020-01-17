package dcp.day286
// day286.kt
// By Sebastian Raaphorst, 2020.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UnitTests {
    @Test
    fun example1() {
        val buildings = listOf(
            Building(0, 15, 3),
            Building(4, 11, 5),
            Building(19, 23, 4))

        val points = listOf(
            Point(0, 3),
            Point(4, 5),
            Point(11, 3),
            Point(15, 0),
            Point(19, 4),
            Point(23, 0))

        assertEquals(findSkyline(buildings), points)
    }

    @Test
    fun example2() {
        val buildings = listOf(
            Building(1, 8, 1),
            Building(2, 3, 2),
            Building(5, 7, 5),
            Building(8, 12, 3),
            Building(10, 11, 4),
            Building(13, 17, 2),
            Building(14, 16, 1),
            Building(18, 20, 4),
            Building(18, 23, 2),
            Building(22, 23, 4))

        val points = listOf(
            Point(1, 1),
            Point(2, 2),
            Point(3, 1),
            Point(5, 5),
            Point(7, 1),
            Point(8, 3),
            Point(10, 4),
            Point(11, 3),
            Point(12, 0),
            Point(13, 2),
            Point(17, 0),
            Point(18, 4),
            Point(20, 2),
            Point(22, 4),
            Point(23, 0)
        )

        assertEquals(findSkyline(buildings), points)
    }
}