package dcp.day198

import kotlin.test.assertEquals

fun find_longest_divisors(elems: Collection<Int>): Set<Int> {
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
    var divs = Array(numElems){1}
    var idxs = Array(numElems){-1}

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

fun main() {
    assertEquals(find_longest_divisors(listOf(3, 5, 10, 20, 21)), setOf(5, 10, 20))
    assertEquals(find_longest_divisors(listOf(1, 3, 6, 24)), setOf(1, 3, 6, 24))
    assertEquals(find_longest_divisors(listOf(5, 10, 20, 15, 35, 40, 50, 3, 9)), setOf(5, 10, 20, 40))
    assertEquals(find_longest_divisors(listOf()), setOf())
    assertEquals(find_longest_divisors(listOf(0, 2, 4)), setOf(0, 2, 4))
    assertEquals(find_longest_divisors(listOf(1, 3, 5)), setOf(1, 3, 5))
}