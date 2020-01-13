package dcp.day144
// day144.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.abs

/**
 * Given a list of elements elems and an index idx of one of the elements, find the index of the nearest element whose
 * value is greater than elems[idx]. Ties may be broken arbitrarily.
 *
 * This algorithm runs in time O(n log n) due to the call to sortedBy.
 */
fun List<Int>.findNextHighestBF(idx: Int): Int? =
    withIndex().filter { (_, value) -> value > get(idx) }.sortedBy { abs(it.index - idx) }.firstOrNull()?.index


/**
 * Preprocess the array so that we can retrieve the answer in constant time for any idx.
 * This is very confusing, so lots of comments. It could probably be done more cleanly.
 * At the end of this, the returned array should give a value equivalent to that given by findNextHighestBF,
 * although since there may not be a unique answer, it may not be the same.
 */
fun preprocess(elems: List<Int>): List<Int?> {
    val idxed = elems.withIndex().sortedBy { it.value }
    // Now that we have sorted by value, for idx, we only need to find the idx of the value to the right that is
    // smallest away in distance. We want to drop everything to the left, so index again. This is undoubtedly confusing
    // now, but the format of each entry is:
    // (idx in idxed, (idx in original list, value in original list)).
    val nearest = idxed.withIndex().map { (idx2, p) ->
        val (idx, _) = p
        idxed.drop(idx2 + 1).minBy { abs(idx - it.index) }}

    // Now convert back to the original ordering. To start doing this, create a map such that map[idx] is the answer
    // for idx.
    val mappedAnswer = idxed.indices.map { idxed[it].index to nearest[it]?.index }.toMap()

    // Now put everything back in order.
    return idxed.indices.map { mappedAnswer[it] }
}
