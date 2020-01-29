package dcp.day297
// day297.kt
// By Sebastian Raaphorst, 2020.

private typealias Drink = Int

/**
 * This is just a specific instance of a set covering problem in disguise.
 * We want to cover the customers with the fewest number of drinks.
 * We use the power set and iterate by size to find a minimal set of drinks required to satisfy all customers.
 */

/**
 * Whittle down to nothing by repeatedly calling drop(1).powerset.
 * Then add the current sets built up followed by the first element that we had dropped
 *
 * [] - whittled down to nothing
 *
 * - [] + [[]].map(_ + first()), where first is now the last element dropped, i.e. 3.
 * [3]
 *
 * - [[],[3]] + [[],[3]]].map(_ + first()), where first is now the second-last element dropped, i.e. 2.
 * [], [3] filtered out
 * [2]
 * [3, 2]
 *
 * - [[2],[3,2]]] + [[2],[3,2]].map(_ + first()), where first is now the third-last element ignored, i.e. 1
 * [], [3], [2], [3,2] filtered out
 * [1]
 * [3, 1]
 * [2, 1]
 * [3, 2, 1]
 *
 * etc.
 * [0]
 * [3, 0]
 * [2, 0]
 * [3, 2, 0]
 * [1, 0]
 * [3, 1, 0]
 * [2, 1, 0]
 * [3, 2, 1, 0]
 */
private fun <T> Collection<T>.powerset(): Set<Set<T>> = when {
    isEmpty() -> setOf(setOf())
    else -> drop(1).powerset().let { setOfSets -> setOfSets + setOfSets.map { set -> set + first() } }
}

fun drinksToMemorize(customerPreferences: List<Set<Drink>>): Set<Drink>? {
    // Get an entire list of the drinks.
    val drinks = customerPreferences.flatten()

    // Determine if a set of drinks satisfies all the customers.
    fun satisfy(drinkSet: Set<Drink>): Boolean =
        customerPreferences.all { preferences -> preferences.any { it in drinkSet } }

    // Sort the power set by size to obtain a minimum set of drinks that the bartender should know.
    return drinks.powerset().sortedBy { it.size }.find { satisfy(it) }
}
