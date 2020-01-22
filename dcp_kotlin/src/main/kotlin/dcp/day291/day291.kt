package dcp.day291

/**
 * We want to know how many boats of given capacity it requires to carry people of weights given to safety,
 * with a maximum of two people per boat.
 *
 * Strategy: sort the weights by nondecreasing order and proceed recursively.
 * If the heaviest person remaining and the lightest person remaining fit in a boat, then pair them together.
 * If they don't, send the heaviest remaining person alone.
 *
 * We do not need to do any more than pair people like this: if there was some violation of this strategy
 * that didn't work - say we had weights [x y z w] in order - our strategy would pair - if possible - w with x and
 * y with z.
 *
 * We examine the possible violations to prove that this strategy works:
 * 1. If w + x > capacity, then w + y and w + z are also > capacity, so w cannot be paired with anyone else.
 * 2. Assume w + x < capacity but y + z > capacity, i.e. maybe we could have used less boats by not pairing w
 *    with the smallest element.
 *    If we pair w with y, then since w > z and y + z > capacity, w + y > capacity, so this pairing doesn't work.
 *    Similarly, w with z, then since w > y and y + z > capacity, w + z > capacity, so this pairing doesn't work.
 *
 * Note that to achieve O(n log n), we have to use an array instead of a linked list, so that the lookups
 * are in constant time and / or we don't have to change the collection's size: using a linked list would give us
 * time O(n log n + n^3) = O(n^3), since each boat requires O(n^2) (one pass to get the current lower bound, and one
 * to get the current upper bound) and there are O(n) boats.
 */
fun findBoats(capacity: Int, weights: Array<Int>): Int {
    // Nobody can be stranded.
    require(weights.all { it <= capacity })

    val sortedWeights: Array<Int> = weights.sortedArray()
    tailrec
    fun aux(boats: Int = 0, lowerBound: Int = 0, upperBound: Int = weights.size - 1): Int = when {
        upperBound < lowerBound -> boats
        lowerBound == upperBound -> boats + 1
        sortedWeights[upperBound] + sortedWeights[lowerBound] <= capacity ->
            aux(boats + 1, lowerBound + 1, upperBound - 1)
        else ->
            aux(boats + 1, lowerBound, upperBound - 1)
    }
    return aux()
}
