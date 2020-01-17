package dcp.day286
// day286.kt
// By Sebastian Raaphorst, 2020.

typealias Point = Pair<Int, Int>
data class Building(val startx: Int, val endx: Int, val height: Int) {
    // Omit the last point in a building as we want that to represent a drop to the next building.
    operator fun contains(xPos: Int): Boolean =
        xPos in startx until endx
}

fun findSkyline(buildings: List<Building>): List<Point> {
    // First, we map the maximum heights at each x-position.
    val xrangeStart = buildings.map(Building::startx).min()
    val xrangeEnd = buildings.map(Building::endx).max()
    require(xrangeStart != null && xrangeStart >= 0)
    require(xrangeEnd != null && xrangeEnd > xrangeStart)

    // Append a 0 because we are going to zipNext and we check if there is a change between this position and the next,
    // and thus we want a leading 0.
    val heights = listOf(0) + (xrangeStart..xrangeEnd).map { xPos -> buildings.filter { xPos in it }.maxBy { it.height }?.height ?: 0 }

    // We only want to compare to the previous point for a change. Zip with the index, which will serve as the x-coord,
    // and if there is a change, the second height will serve as the y-coord.
    return heights.zipWithNext().withIndex().filter { (_, hts) ->
        val (ht1, ht2) = hts
        ht1 != ht2 }. map { (xpos, hts) ->
        val (_, ht2) = hts
        Point(xpos + xrangeStart, ht2)
    }
}
