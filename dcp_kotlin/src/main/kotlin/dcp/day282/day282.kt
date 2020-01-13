package dcp.day282
// day282.kt
// By Sebastian Raaphorst, 2020.

typealias Triplet = Triple<Int, Int, Int>

/**
 * Find the first Pythagorean triplet (in lexicographic order) in a collection of integers.
 * This is a triple (a, b, c) such that:
 * 1. a, b, c > 0 and a < b
 * 2. a^2 + b^2 = c^2.
 */
fun Collection<Int>.findPythagoreanTriplet(): Triplet? {
    if (size < 3)
        return null

    // First square everything and sort to avoid having to repeatedly square.
    // Only positive integers can make up a Pythagorean triple by definition.
    val sortedSquares = filter{it > 0}.map{it * it to it}.sortedBy(Pair<Int,Int>::first)

    // Now iterate O(N^2) to see if we can find a triplet, and return the first one.
    for (i in 0 until (sortedSquares.size - 2))
        for (j in (i + 1) until (sortedSquares.size - 1)) {
            val hypotenuse = sortedSquares.find { it.first == sortedSquares[i].first + sortedSquares[j].first }
            if (hypotenuse != null)
                return Triplet(sortedSquares[i].second, sortedSquares[j].second, hypotenuse.second)
        }
    return null
}
