package dcp.day185
// RectangleIntersectionTester.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RectangleIntersectionTester {
    @Test
    fun rectangleIntersectionTest() {
        val rect1 = Rectangle(Point(4, 1), Dimension(3, 3))
        val rect2 = Rectangle(Point(5, 0), Dimension(4, 3))
        assertEquals(rect1 intersection rect2, 6)
    }
}