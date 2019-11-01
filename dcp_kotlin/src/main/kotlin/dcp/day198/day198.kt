package dcp.day198
// day198.kt
// By Sebastian Raaphorst, 2019.

fun findLongestDivisors(elems: Collection<Int>): Set<Int> {
    // If there are no elems or 0 or 1 are in elems, then just return the whole sequence.
    // If 0 is in: 0 % m == 0 for all m.
    // If 1 is in: m % 1 == 0 for all n.
    if (elems.isEmpty() || elems.contains(0) || elems.contains(1))
        return elems.toSet()

    // Let divs be the number of divisors of x_i for x_i in elems.
    // We have the property that if x_i > x_j and x_j | x_i, then if x_k | x_j, x_k | x_i.
    // In this case, divs(x_i) >= divs(x_j) + 1.
    // We calculate divs and keep track of the indices.
    val numElems = elems.size
    val sortedElems = elems.sorted()

    // We use arrays due to fixed size, which are inherently mutable.
    val divs = Array(numElems){1}
    val idxs = Array(numElems){-1}

    var maxVal = 0
    var maxIdx = numElems - 1

    for ((idx, elem) in sortedElems.withIndex()) {
        for (j in idx-1 downTo 0) {
            if (elem % sortedElems[j] == 0 && divs[j] + 1 > divs[idx]) {
                divs[idx] = divs[j] + 1
                idxs[idx] = j
            }
        }

        if (maxVal < divs[idx]) {
            maxVal = divs[idx]
            maxIdx = idx
        }
    }

    // Retrieve the indices.
    val result = mutableSetOf<Int>()
    while (maxIdx >= 0) {
        result.add(sortedElems[maxIdx])
        maxIdx = idxs[maxIdx]
    }
    return result
}
