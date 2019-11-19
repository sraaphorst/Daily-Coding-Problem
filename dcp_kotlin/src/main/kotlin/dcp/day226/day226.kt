package dcp.day226

/**
 * Given a list of "words" sorted alphabetically (according to an arbitrary alphabet that does not necessarily
 * correspond to a normal alphabet), find the underlying order of the alphabet.
 *
 * General algorithm and notes:
 * 1. Assume we have n symbols.
 * 2. Create an nxn table where each entry is <, =, or >, indicating the ordering of the two symbols.
 * 3. The main diagonal is initialized to =.
 * 4. Rule: compare two words. We can remove the leftmost shared symbols to derive a new inequality. For example:
 *          bafdu < baftr -> du < tr.
 *    This implies that d < t, which we can insert into the table.
 * 5. Once the table no longer changes, we should have a chain that we can parse out to give the order of the alphabet.
 * 6. We cannot assume the first letter of the first word appearing is first in the alphabet. There may, for example,
 *    be no examples of words with the first letter in the first position (say we start with baby, but a beats b).
 */

enum class Comparison {
    // A PROCESSED entry is an entry that has been processed and is no longer necessary.
    LESS, EQUAL, GREATER, UNKNOWN, PROCESSED
}

typealias SymbolList = List<Char>
typealias Ordering = MutableList<MutableList<Comparison>>
typealias Lookup = Map<Char, Int>

// Information about the comparison
fun findAlphabet(words: List<String>): SymbolList {
    // We want a set of all characters.
    val symbols: SymbolList = words.flatMap { it.toList() }.toSet().toList()
    val n = symbols.size

    // Create a lookup table for the symbols.
    val lookup: Lookup = (0 until n).map { symbols[it] to it }.toMap()

    // Now create the mutable table that represents
    val ordering: Ordering = MutableList(n) { i -> MutableList(n) { j -> if (i == j) Comparison.EQUAL else Comparison.UNKNOWN } }

    /**
     * The result of comparing two distinct words: we left-eliminate all same characters and then return the first
     * pair - providing it exists - of different characters, with the relationship between those characters based on
     * the relationship between the initial two words.
     */
    class Result(word1: String, word2: String) {
        val char1: Char?
        val char2: Char?

        init {
            val (remain1, remain2) = word1.zip(word2).dropWhile { it.first == it.second }.unzip()
            char1 = remain1.firstOrNull()
            char2 = remain2.firstOrNull()
        }
    }

    // While there are change and / or empty entriess...
    var unknowns = n * n - n
    var changed = true
    outerloop@ while (changed && unknowns > 0) {
        changed = false
        // Get the first word.
        for (w1 in 0 until (words.size - 1)) {
            for (w2 in (w1 + 1) until words.size) {
                if (w1 != w2) {
                    // w1 < w2 by virtue that it appears first in the dictionary.
                    val result = Result(words[w1], words[w2])
                    if (result.char1 != null && result.char2 != null) {
                        val c1 = lookup[result.char1]
                        val c2 = lookup[result.char2]
                        require(c1 != c2 && ordering[c1!!][c2!!] != Comparison.EQUAL)
                        if (c1 != null && c2 != null) {
                            if (ordering[c1][c2] == Comparison.UNKNOWN) {
                                ordering[c1][c2] = Comparison.LESS
                                ordering[c2][c1] = Comparison.GREATER
                                changed = true
                                unknowns -= 2

                                // If we have established all relationships, terminate early.
                                if (unknowns == 0)
                                    break@outerloop
                            }
                        }
                    }
                }
            }
        }
    }
    return emptyList()
}

//fun makeChain(ordering: Ordering), lookup: Lookup: List<Char> {
//    print(lookup)
//    println(ordering)
//    //   x w y z
//    // x = < < <
//    // w > = < ?
//    // y > > = >
//    // z > ? < =
//    // Keep picking rows with only less or greater, and eliminate columns as processed?
//    // y and z are both greater than rows. z and y are unknown? Cannot process.
//    // x is a less than row, so x is first.
//    // Leaves us with:
//    //   w y z
//    // w = < ?
//    // y > = >
//    // z ? < =
//    // w is a less than row, so it is next.
//    // w z w y
//    // Create the chain. vcxds                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     n                                                                                                                                                                                                                                                                                                                                                                       cx                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
//    val alphabet = mutableListOf<Char>()
//    var beginningPos = 0
//    var endPos = n - 1
//    val processedRows = MutableList(n){false}
//    var currentRow = 0
//    while (beginningPos <= endPos) {
//        /**
//         * Iterate over the rows, trying to collapse them strictly to rows consisting of:
//         * 1. {LESS, EQUAL, UNKNOWN, PROCESSED}
//         * 2. {GREATER, EQUAL, UNKNOWN, PROCESSED}
//         * Then see if we can break the UNKNOWN entries in a consistent way.
//         */
//        // Try the low rows.
//        for (currentRow in 0 until n) {
//            // If the row has been makred skipped, it is already in the alphabet, so skip/
//            if (processedRows[currentRow] ?: true)
//                continue
//
//            // Try to see if it goes at the next front position of the alphabet. Fi;lter out all but GREATER.
//            val rowEntries = ordering.map { row -> row.filter { it != Comparison.GREATER } }
//            val unknowns = rowEntries. != Comparison.UNKNOWN}
//            val unknowns = rowEntries.withIndex().filter{it != Comparison.UNKNOWN}
//
//
//    }
//    return listOf()
//}


fun main() {
    val words = listOf("xww", "wxyz", "wxyw", "ywx", "ywz")
    findAlphabet(words)
    val (v1, v2) = "abcde".zip("abfghji").dropWhile { it.first == it.second }.unzip()
    print(v1.toList())
    print(v2.toList())
    println("Done")
}