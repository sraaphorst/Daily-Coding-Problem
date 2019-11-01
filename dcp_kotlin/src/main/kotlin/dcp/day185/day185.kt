package dcp.day185
// day185.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.max
import kotlin.math.min

data class Point(val top: Int, val left: Int)
data class Dimension(val width: Int, val height: Int)
data class Rectangle(val point: Point, val dimension: Dimension)

infix fun Rectangle.intersection(other: Rectangle): Int {
    val left = max(point.left, other.point.left)
    val right = min(point.left + dimension.width, other.point.left + other.dimension.width)
    val bottom = min(point.top + dimension.height, other.point.top + other.dimension.height)
    val top = max(point.top, other.point.top)

    // Check if the intersection is not empty.
    return if (left < right && top < bottom)
        (right - left) * (bottom - top)
    else
        0
}

fun main() {
    val rect1 = Rectangle(Point(4, 1), Dimension(3, 3))
    val rect2 = Rectangle(Point(5, 0), Dimension(4, 3))
    println("The intersection of $rect1 and $rect2 is ${rect1 intersection rect2}.")
}
