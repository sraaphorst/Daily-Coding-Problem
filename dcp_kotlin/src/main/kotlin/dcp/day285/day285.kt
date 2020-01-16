package dcp.day285

import kotlin.math.max

/**
 * List represents buildings from east to west (reverse order).
 * Determine which have an unobstructed view of the sunset (every increase in max height).
 */
fun List<Int>.unobstructedSunset(): Int {
    tailrec
    fun aux(maxHeight: Int = 0, buildings: Int = 0, position: Int = size - 1): Int {
        if (position == -1)
            return buildings

        val newMaxHeight = max(maxHeight, this[position])
        val newBuildings = if (newMaxHeight > maxHeight) buildings + 1 else buildings
        return aux(newMaxHeight, newBuildings, position - 1)

    }

    return aux()
}
