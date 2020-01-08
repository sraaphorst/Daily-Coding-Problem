package dcp.day015
// day015.kt
// By Sebastian Raaphorst, 2020.

import kotlin.random.Random

/**
 * Uniform distribution from a sequence, for cases where the data is too large to fit into memory.
 * Based on reservoir sampling.
 */
fun <T> Sequence<T>.pickRandom(): T {
    // Must initialize it to something.
    // Count represents the number of things we've seen, so it is one more than idx.
    var result: T = first()
    withIndex().forEach { (idx, x) ->
        val count = idx + 1

        // If we have only seen one element, then we have no choice and must make our random choice that element.
        // Otherwise, we select a random number and see if it is the idx. If it is, select the idx.
        if (count == 1 || Random.nextInt(0, count) == idx)
            result = x
    }
    return result
}

// No real way to test this concretely as there can always be wild deviations.
// The only way to do so is to verify that all the numbers are approximately the same the majority of the time.
fun main() {
    val lst = MutableList(10){0}
    (0..1000000).forEach{ lst[(0 until 10).asSequence().pickRandom()] += 1}
    println(lst)
}