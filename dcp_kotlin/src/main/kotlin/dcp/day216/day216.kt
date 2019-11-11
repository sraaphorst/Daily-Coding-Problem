package dcp.day216
// day216.kt
// By Sebastian Raaphorst, 2019.

class RomanNumeral {
    val toString: String
    val toInt: Int

    constructor(string: String) {
        this.toString = string
        this.toInt = convInt(string)
    }
    constructor(int: Int) {
        this.toInt = int
        this.toString = convStr(int)
    }

    companion object {
        // These are the important combinations, in order, to generate a Roman numeral from a natural number.
        private val orderedList = mapOf(
            "M"  to 1000,
            "D"  to 500,
            "C"  to 100,
            "XC" to 90,
            "XL" to 40,
            "L"  to 50,
            "IX" to 9,
            "X"  to 10,
            "IV" to 4,
            "V"  to 5,
            "I"  to 1
            )

        // We reverse the important combinations, which allow us to reverse the process to get a natural number
        // from a Roman numeral. We need them to be ordered: hence a list and not a map.
        //private val reverseList = orderedList.toList().map { it.second to it.first }.toMap()
        private val reverseList = orderedList.map { it.value to it.key }
 }
        private fun convStr(rest: Int): String {
            if (rest == 0)
                return ""
            val entry = reverseList.filter { it.first <= rest }.maxBy { it.first }
                ?: throw IllegalArgumentException("Cannot represent as a Roman numeral: $rest")
            return entry.second + convStr(rest - entry.first)
        }

        private fun convInt(rest: String): Int {
            if (rest.isEmpty())
                return 0;

            // Try to extract two characters from the string.
            if (rest.length > 1) {
                val twoChars = rest.take(2)
                val num: Int? = orderedList[twoChars]
                if (num != null)
                    return num + convInt(rest.drop(2))
            }

            // Fallback to extracting one character from the string.
            val oneChar = rest.take(1)

            val num: Int? = orderedList[oneChar]
            if (num != null)
                return num + convInt(rest.drop(1))

            throw IllegalArgumentException("Illegal sequence: $rest")
        }
    }