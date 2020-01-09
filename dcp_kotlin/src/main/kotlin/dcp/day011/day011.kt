package dcp.day011
// day011.kt
// By Sebastian Raaphorst, 2020.

typealias Word = String

// Use a trie to represent the dictionary.
class Trie private constructor(val char: Char, private val isWord: Boolean, val children: Map<Char, Trie>) {

    constructor(words: List<String>): this('/', false, createTrieWords(words))

    companion object {
        private fun createTrieWords(words: List<Word>): Map<Char, Trie> {
            if (words.isEmpty()) return emptyMap()

            // This can be used at the first node or recursively at intermediate nodes.
            // Group the words by first character. Filter out any empty entries as they have already been used to
            // mark this node's isWord to true.
            val wordsByString = words.filter(String::isNotEmpty).groupBy { it.first() }
            return wordsByString.keys.map { ch ->
                // If ch is the last word in a string, then the child node created has the isWord flag set.
                ch to Trie(ch, wordsByString[ch]?.contains(ch.toString()) ?: false,
                    createTrieWords(wordsByString[ch]?.map { it.drop(1) } ?: emptyList()))
            }.toMap()
        }
    }

    // A trie can contain a word.
    operator fun contains(word: String): Boolean =
        isWord(word)

    // Determine if the specified text is a word of this Trie.
    fun isWord(word: Word): Boolean =
        getNode(word)?.isWord ?: false

    // Get the trie node associated with the partial or complete word, if it exists (null otherwise).
    private fun getNode(word: Word): Trie? {
        if (word.isEmpty())
            return null

        tailrec
        fun aux(remainingWord: Word, node: Trie? = this): Trie? =
            when {
                node == null -> null
                remainingWord.isEmpty() -> node
                else -> aux(remainingWord.drop(1), node.children[remainingWord.first()])
            }

        // This seems redundantly repetitive, but since we're dealing with two data structures,
        return aux(word)
    }

    fun autocomplete(partialWord: Word): List<Word> {
        val baseNode = getNode(partialWord) ?: return emptyList()

        // Now do a DFS of the tree rooted at node.
        fun aux(wordSoFar: Word, words: List<Word>, nodeStack: List<Trie>): List<Word> {
            if (nodeStack.isEmpty())
                return words

            val node: Trie = nodeStack.last()
            val extensions = node.children.values.flatMap { trie -> aux(wordSoFar + trie.char, emptyList(), nodeStack + listOf(trie)) }
            return if (node.isWord) listOf(wordSoFar) + extensions else extensions
        }

        return aux(partialWord, emptyList(), listOf(baseNode))
    }

    override fun toString(): String {
        return "Trie(char=$char, isWord=$isWord, children=$children)"
    }
}
