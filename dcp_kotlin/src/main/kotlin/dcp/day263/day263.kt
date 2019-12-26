package dcp.day263
// day263.kt
// By Sebastian Raaphorst, 2019.

/**
 * The rules for a "sentence" are strange:
 * 1. Must start with uppercase capital letter.
 * 2. Must then be followed by a lowercase letter or space.
 * 3. Must be a single space between words.
 * 4. The sentence must end with a terminal mark following a word.
 *
 * We model with a regular expression and a finite state automaton.
 */

/**
 * Really hideous regular expression.
 */
fun sentenceRegexChecker(txt: String): Boolean =
    "^[A-Z]((([ ][a-z,;:]+)+[.?!‽])|(([a-z]([ ]?[a-z,;:]+)+[.?!‽])|[.?!‽]))\$".toRegex().matches(txt)

/**
 * Model with a 5-state diagram.
 */
fun sentenceStateChecker(txt: String, state: Int = 0): Boolean {
    val separator = ",;:"
    val terminator = ".!?‽"
    val uppercase = 'A'..'Z'
    val lowercase = 'a'..'z'
    val space = ' '

    when (state) {
        /**
         * State 0: Must start with capital letter
         * To 1: Starts with capital letter
         * To 5: Violation
         */
        0 -> {
            val nextState = when (txt.firstOrNull()) {
                null -> 5
                in uppercase -> 1
                else -> 5
            }
            return sentenceStateChecker(txt.drop(1), nextState)
        }

        /**
         * State 1: Started with capital letter
         * To 2: Contains a lowercase letter.
         * To 3: Contains a space.
         * To 5: Violation
         */
        1 -> {
            val nextState = when (txt.firstOrNull()) {
                null -> 5
                in lowercase -> 2
                space -> 3
                else -> 5
            }
            return sentenceStateChecker(txt.drop(1), nextState)
        }

        /**
         * State 2: lowercase letter
         * To 2: lowercase letter or separator
         * To 3: space
         * To 4: terminal character
         * To 5: Violation
         */
        2 -> {
            val nextState = when (txt.firstOrNull()) {
                null -> 5
                in lowercase -> 2
                in separator -> 2
                space -> 3
                in terminator -> 4
                else -> 5
            }
            return sentenceStateChecker(txt.drop(1), nextState)
        }

        /**
         * State 3: space
         * To 2: lowercase letter or separator
         * To 5: space or terminal character.
         */
        3 -> {
            val nextState = when (txt.firstOrNull()) {
                null -> 5
                space -> 5
                in lowercase -> 2
                in separator -> 2
                in terminator -> 5
                else -> 5
            }
            return sentenceStateChecker(txt.drop(1), nextState)
        }

        /**
         * State 4: victory if empty
         * To: 5 if not empty
         */
        4 -> {
            return txt.isEmpty()
        }

        /**
         * State 5: failure
         */
        5 -> {
            return false
        }

        /**
         * We should never get here.
         */
        else -> {
            return false
        }
    }
}

fun main() {
    println(sentenceStateChecker("The goose flies at night.."))//oose flies at night."))
    println(sentenceRegexChecker("The goose flies at night.."))//oose flies at night."))
}