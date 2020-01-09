package dcp.day004
// day004.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.abs

// Unfortunately, to make this code as efficient as possible and have it run in linear time and constant space,
// we must use an imperative approach and mutability, along with an array to avoid linked list access times.
fun findFirstPositiveMissingInteger(array: IntArray): Int {
    if (array.isEmpty())
        return 1
    // We don't want to deal with 0 and it has no relevance, so change it to -1.
    // We will use the signs of the elements to indicate their presence, starting at 1 for position 0.
    // Since there can be negative numbers in the array, we have to be careful about how we use this approach.
    // We first partition the list into negative numbers and positive numbers by moving all the negative numbers
    // to the left.
    var pivot: Int = 0
    for (i in array.indices) {
        if (array[i] == 0)
            array[i] = -1
        if (array[i] <= 0) {
            val tmp: Int = array[i]
            array[i] = array[pivot]
            array[pivot] = tmp
            ++pivot
        }
    }

    // Now everything to the left of pivot should be negative. Everything to the right of pivot should be positive.
    // We only need to process elements to the right of pivot. Take their absolute value and mark their index value
    // as swapped.
    for (i in pivot until array.size) {
        // We have to detect duplicate (or more) entries of a number or it will flip its sign back and seem like it
        // never appeared in the array.
        if (abs(array[i]) <= array.size) {
            if ((abs(array[i]) - 1 < pivot && array[abs(array[i]) - 1] > 0) ||
                (abs(array[i]) - 1 >= pivot && array[abs(array[i]) - 1] < 0))
                continue
            
            // Reverse the sign of abs(array[i]) - 1 to indicate that array[i], a positive integer, is in the array.
            array[abs(array[i]) - 1] = -array[abs(array[i]) - 1]
        }
    }

    // Check to see for missing elements. Everything before the pivot should be positive if 1..pivot appear in the
    // array and everything after the pivot should be negative if (pivot+1)..(array.size) appears in the array.
    // If everything appears in the array, then the first missing number is array.size + 1.
    for (i in 0 until pivot)
        if (array[i] < 0)
            return i + 1
    for (i in pivot until array.size)
        if (array[i] > 0)
            return i + 1
    return array.size + 1
}

/**
 * Functional method.
 */
fun findFirstPositiveMissingIntegerFM(array: IntArray): Int =
    if (array.isEmpty()) 1
    else (1..array.size).find { it !in array } ?: (array.size + 1)
