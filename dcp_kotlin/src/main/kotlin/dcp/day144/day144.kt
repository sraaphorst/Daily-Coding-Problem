package dcp.day144
// day144.kt
// By Sebastian Raaphorst, 2019.


import kotlin.random.Random.Default

fun findNextHighestBruteForce(elems: List<Int>, elemIdx: Int): Int? {
    val elem = elems[elemIdx]
    var idx: Int? = null
    for (i in elems.indices) {
        val currElem = elems[i]
        if (currElem > elem && (idx == null || currElem < elems[idx]))
            idx = i
    }
    return idx
}

fun preprocess(elems: List<Int>): Pair<List<Int>, List<Int>> {
    // Create a map that indices where the numbers in elems appear when sorted.
    // For example:
    // elems:   4 1 3 5 6
    // sorted:  1 3 4 5 6
    // indices: 2 0 1 3 4
    // i.e. elems[0] = 4 is in sorted position 2, elems[1] = 1 is in sorted position 0, etc.
    val index = elems.withIndex()
        .sortedBy{ x -> x.value }
        .map{ x -> x.index }
        .withIndex()
        .sortedBy{ x -> x.value }
        .map{ x -> x.index }

    // Create a map that gives the reverse position of the above.
    // For example:
    // elems:    4 1 3 5 6
    // sorted:   1 3 4 5 6
    // original: 1 2 0 3 4
    // i.e. sorted[0] = 1 was originally in position 0, sorted[1] = 3 was originally in position 2, etc.
    val unindex = elems.withIndex()
        .sortedBy { x -> x.value }
        .map { x -> x.index }
    return Pair(index, unindex)
}

fun findNextHighestConstant(data: Pair<List<Int>, List<Int>>, elemIdx: Int): Int? {
    val index   = data.first
    val unindex = data.second

    // Get the sorted position of the element at elemIdx.
    // Then, if there is a next position in the sorted list, find its index in the original list.
    val position = index[elemIdx]

    return if (position < unindex.size - 1) unindex[position + 1] else null
}

fun main() {
    val lst = listOf(4, 1, 3, 5, 6)
    val processedLst = preprocess(lst)
    for (i in lst.indices)
        assert(findNextHighestBruteForce(lst, i) == findNextHighestConstant(processedLst, i))

    // Do a random one as well.
    val length = Default.nextInt(0, 100)
    val randomLst = List(length) {
        Default.nextInt(0, 100)
    }
    val processedRandomLst = preprocess(randomLst)
    for (i in randomLst.indices)
        assert(findNextHighestBruteForce(randomLst, i) == findNextHighestConstant(processedRandomLst, i))
}