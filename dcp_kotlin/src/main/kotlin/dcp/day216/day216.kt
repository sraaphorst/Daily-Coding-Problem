package dcp.day216

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
        val orderedList = mapOf(
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

        val reverseList = orderedList.toList().map { it.second to it.first }.toMap()

        private fun convStr(rest: Int): String {
            if (rest == 0)
                return ""
            val entry = reverseList.filterKeys { rest >= it }.maxBy { it.key }
            if (entry == null)
                throw IllegalArgumentException("Cannot represent as a Roman numeral: $rest")
            return entry.value + convStr(rest - entry.key)

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

            val oneChar = rest.take(1)

            val num: Int? = orderedList[oneChar]
            if (num != null)
                return num + convInt(rest.drop(1))

            throw IllegalArgumentException("Illegal sequence: $rest")
        }
    }
}

fun main() {
    for (i in 0..2000) {
        val rm = RomanNumeral(i)
        val rm2 = RomanNumeral(rm.toString)
        val rm3 = RomanNumeral(rm.toInt)
        println("${rm.toString} -> ${rm2.toInt} -> ${rm3.toString}")
    }
}