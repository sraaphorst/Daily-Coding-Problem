package dcp.day226
// day226.kt
// By Sebastian Raaphorst, 2019.

import java.util.*

/**
 * Given a list of "words" sorted alphabetically (according to an arbitrary alphabet that does not necessarily
 * correspond to a normal alphabet), find the underlying order of the alphabet.
 *
 * We want to find the rules governing the alphabet symbols. This can be done through the operation of leftwise
 * elimination, which is, given two words, eliminating the leftmost portion of the two words that is identical to
 * reduce to a relationship between two letters. For example:
 *      babies < babylon -> eliminate bab -> ies < ylon -> i < y in the alphabet
 * We can do this pairwise through the list of words to get a sufficient set of rules to determine the alphabet.
 * We represent this by a directed graph where a tree's children are the symbols that appear after it.
 * In the example above, y will be a child of i.
 */

typealias SymbolList = List<Char>

// Information about the comparison
fun findAlphabet(words: List<String>): SymbolList {
    // We want a set of all characters.
    val symbols: SymbolList = words.flatMap { it.toList() }.toSet().toList()

    // The topological graph.
    val graph = mutableMapOf<Char,MutableSet<Char>>()
    symbols.forEach { graph[it] = mutableSetOf() }

    /**
     * The result of comparing two distinct words: we left-eliminate all same characters and then return the first
     * pair - providing it exists - of different characters, with the relationship between those characters based on
     * the relationship between the initial two words.
     */
    class Rule(word1: String, word2: String) {
        val char1: Char?
        val char2: Char?

        init {
            val (remain1, remain2) = word1.zip(word2).dropWhile { it.first == it.second }.unzip()
            char1 = remain1.firstOrNull()
            char2 = remain2.firstOrNull()
        }
    }

    /**
     * Compare words pairwise.
     */
    words.zipWithNext { w1, w2 ->
        /**
         * w1 < w2 by virtue that it appears first in the dictionary, so Rule.char1 < Rule.char2 after left elimination
         * if there is both a char1 and a char2 left.
         */

        val result = Rule(w1, w2)
        if (result.char2 != null)
        graph[result.char1]?.add(result.char2)
    }

    /**
     * Perform a topological sort to get the alphabet ordering.
     * This depends on visiting the symbols in a coherent order and then adding them at depths that make sense.
     */
    val alphabet = LinkedList<Char>()
    val visited = mutableSetOf<Char>()

    fun visit(char: Char) {
        visited.add(char)
        graph[char]?.forEach{
            if (it !in visited)
                visit(it)
        }
        alphabet.addFirst(char)
    }

    graph.keys.forEach {
        if (it !in visited)
            visit(it)
    }

    return alphabet
}