package dcp.day231
// day231.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.ceil

/**
 * Interleave letters of s if possible in a way that no two adjacent letters are the same.
 * Returns null if cannot be done, and otherwise one method of doing so.
 */
fun interleave(s: String): String? {
    // Check if feasible.
    if (s.isEmpty())
        return ""

    // Sort letters by frequency, and get the letter with the highest count.
    // If the letter with the highest count appears too many times, we cannot perform any rearranging and fail.
    val lettersByFrequency = s.toSet().map { c -> c to s.count { c == it }}.toList().sortedBy { it.second }.reversed()
    val maxSymbols = lettersByFrequency.firstOrNull()?.second
    if (maxSymbols == null || maxSymbols > ceil(s.length.toDouble() / 2).toInt())
        return null

    // Now form the string. We want to interleave characters from the first half with the chracters from the second
    // half using zip, so we have to work with character arrays instead of strings.
    val preprocessed = lettersByFrequency.map { (c, num) -> c.toString().repeat(num) }.joinToString(separator = ""){it}.toList()
    val splitPoint = s.length / 2 + s.length % 2
    val firstHalf = preprocessed.take(splitPoint)
    val secondHalf = preprocessed.drop(splitPoint)

    // If there is an odd number of characters, we have to append the last character from firstHalf or it will get
    // devoured by zip due to the discrepancy of sizes of firstHalf and secondHalf, so we have a special case for it.
    return firstHalf.zip(secondHalf).joinToString(separator = "") { "${it.first}${it.second}" } +
            if (firstHalf.size > secondHalf.size) firstHalf.last().toString() else ""

}

/**
 * Check if a string produced by interleave is valid, i.e. the string passed into this function has no two
 * adjacent letters that are the same.
 */
fun checkIfValid(s: String): Boolean =
    s.zipWithNext().none { it.first == it.second }
