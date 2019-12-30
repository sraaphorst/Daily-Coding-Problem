package dcp.day266
// day266.kt
// By Sebastian Raaphorst, 2019.

typealias Word = String
typealias Dictionary = List<Word>

/**
 * Find all step words, i.e. words where one letter is added and are anagrams of the result.
 * Note we work under the assumption of being case-insensitive.
 */
fun findStepWords(word: Word, dictionary: Dictionary): Dictionary {
    // Generate all candidate sorted sets of letters: if they correspond to a word in the dictionary, they will
    // be able to be anagrammed to that word.
    val candidates = ('a'..'z').map { (word + it).toUpperCase().toList().sorted() }

    // Turn the dictionary into similarly sorted sets of letters: then filter to see which of the words correspond to
    // the candidates.
    return dictionary.filter { it.toUpperCase().toList().sorted() in candidates }
}
