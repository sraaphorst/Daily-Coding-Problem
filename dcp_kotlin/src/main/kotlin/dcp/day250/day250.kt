//package dcp.day250
//
//import java.lang.NumberFormatException
//
//
//fun <T> List<T>.indexOfOrNull(t: T): Int? {
//    val idx = this.indexOf(t)
//    return if (idx == -1) null else idx
//}
//
//fun crypto_solve(word1: String, word2: String, wordSum: String): Map<Char, Int>? {
//    // Collect all the characters and make sure there are at most 10.
//    // We want to collect them in columns starting at the right.
//    val chars = (0..wordSum.length).map {
//        listOf(word1.elementAtOrNull(it), word2.elementAtOrNull(it), wordSum.elementAtOrNull(it))
//    }.flatten().filterNotNull().distinct()
//    require(chars.size <= 10) { "Crytopuzzle can have at most 10 characters" }
//
//
//    // map: We want to assign Ints to chars in the order of chars to be able to do backtracking by exploiting
//    // partial sums.
//    val map: MutableList<Int> = mutableListOf()
//    val digitsUsed: MutableList<Boolean> = MutableList(10) { false }
//
//
//    fun backtrack(extending: Boolean = true): Map<Char, Int>? {
//
//        // Check to see if the arithmetic over the last column columns is correct.
//        fun isValid(column: Int, full: Boolean = false): Boolean {
//            val w1 = if (full) word1 else word1.takeLast(column)
//            val w2 = if (full) word2 else word2.takeLast(column)
//            val wSum = if (full) wordSum else wordSum.takeLast(column)
//
//            // Now convert each into a number to the best of our ability.
//            fun wordToInt(word: String): Int? =
//                try {
//                    word.fold(word) { acc, char -> acc.replace(char.toString(), map[chars.indexOf(char)].toString()) }.toInt()
//                } catch (_: NumberFormatException) {
//                    null
//                }
//
//            val i1 = wordToInt(w1)
//            val i2 = wordToInt(w2)
//            val iSum = wordToInt(wSum)
//
//            if (i1 == null || i2 == null || iSum == null)
//                return false
//
//            // Unfortunately convert to String and use to chop off the potentially leading 1.
//            return (i1 + i2).toString().take(wSum.length) == iSum.toString()
//        }
//
//        // Sanity check: unassigned + assigned should be 10.
//        val numDigitsLeft = digitsUsed.count { !it }
//        require(numDigitsLeft + map.size == 10) { "Something went wrong" }
//
//        // If we have a complete solution, return it.
//        if (map.size == chars.size)
//            return chars.zip(map).toMap()
//
//        // If we have explored the whole backtrack tree and found no solution and are back at the root, we are done.
//        if (!extending && map.isEmpty())
//            return null
//
//        // Get the candidates, possibly above a certain number.
//        fun getCandidates(lowerBound: Int = 0): List<Int> =
//            digitsUsed.withIndex().filter { !it.value && it.index >= lowerBound }.map { it.index }
//
//
//        // If we are extending, try the first digit available.
//        if (extending) {
//            // We are storing for the character at key.
//            val key = map.size
//            val candidates = getCandidates()
//            for (candidate in candidates) {
//                map[map.size] = candidate
//                digitsUsed[candidate] = true
//                // If we are at the end of a column, check our math and backtrack if it fails.
//                if (map.size % 3 != 0 || isValid(map.size / 3, map.size == chars.size))
//                    return backtrack(true)
//            }
//            return backtrack(false)
//        }
//
//        // Otherwise, we are backtracking. We return the current character, try the next available one, and extend.
//        // If there are no more available characters, we continue to backtrack.
//        else {
//            val prevCandidate = map.last()
//            val candidates = getCandidates(prevCandidate + 1)
//            digitsUsed[prevCandidate] = false
//
//            for (candidate in candidates) {
//
//            }
//            // If value is not null, extend.
//            if (value != null) {
//                digitsUsed[value] = true
//                return backtrack(map.dropLast(1) + Pair(key, value), digitsUsed, extending = true)
//            } else {
//                return backtrack(map.dropLast(1), digitsUsed, extending = false)
//            }
//        }
//    }
//
//    return backtrack()
//}
//
//
//fun main() {
//    println("hello".toCharArray().drop(1))
//}