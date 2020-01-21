package dcp.day288
// day288.kt
// By Sebastian Raaphorst, 2020.

// Make sure we have four digits by prepending.
private fun Int.to4digits(): Int =
    (toString().toList() + List(4 - toString().length){"0"}).joinToString("").toInt()

// At least two digits. Must treat as a string to avoid removing leading 0s.
private fun Int.atLeastTwo(): Boolean =
    !(toString().toList() + List(4 - toString().length){"0"}).groupBy { it }.values.any{ it.size == 4 }

// Size of digits must be between 0 and 4 inclusive, and not have triply repeated digits.
fun Int.legal(): Boolean =
    toString().length <= 4 && atLeastTwo()

// Sorts
private fun Int.sortDecreasing(): Int =
    to4digits().toString().toList().sorted().joinToString("").toInt()

private fun Int.sortIncreasing(): Int =
    to4digits().toString().toList().sorted().joinToString("").reversed().toInt()

fun Int.checkKaprekar(): Int? {
    tailrec
    fun aux(value: Int = this, steps: Int = 0): Int? =
        when {
            !legal() -> null
            value == 6174 -> steps
            else -> {
                val higher = value.sortIncreasing()
                val lower = value.sortDecreasing()
                aux(higher - lower, steps + 1)
            }
        }

    return aux()
}

fun main() {
    for (i in 1 until 10000) {
        if (i.legal())
            println("$i -> ${i.checkKaprekar()}")
    }
}