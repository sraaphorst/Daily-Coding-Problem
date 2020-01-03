package dcp.day269
// day269.kt
// By Sebastian Raaphorst, 2020.

import java.security.InvalidParameterException

typealias Section= Pair<Int, Int>


fun push(dominoes: String): String {

    // Locate the sections of dominoes, i.e. find the boundaries of the sections in which the dominoes might possibly
    // influence each other.
    fun locateSections(dominoes: String): List<Section> {
        tailrec
        fun aux(low: Int, high: Int, sections: List<Section>): List<Section> {
            if (high == dominoes.length)
                return sections
            when (dominoes[high]) {
                '.' -> return aux(low, high + 1, sections)
                'L' -> return aux(high, high+1, sections + listOf(Section(low, high)))
                'R' -> return aux(high, high+1, sections + listOf(Section(low, high)))
            }
            throw InvalidParameterException("Invalid string: $dominoes")
        }
        return aux(0, 1, emptyList())
    }

    val boundaries = locateSections(dominoes)
    if (boundaries.isEmpty())
        return dominoes

    var newDominoes = ""

    // This is a bit of a mess due to half-open boundaries.
    boundaries.forEach { (low, high) ->
        when {
            dominoes[low] == '.' && dominoes[high] == 'L' -> newDominoes += "L".repeat(high - low)
            dominoes[low] == '.' && dominoes[high] == 'R' -> newDominoes += ".".repeat(high - low)
            dominoes[low] == 'R' && dominoes[high] == '.' -> newDominoes += "R".repeat(high - low)
            dominoes[low] == 'L' && dominoes[high] == 'R' -> newDominoes += "L" + ".".repeat(high - low - 1)
            dominoes[low] == dominoes[high] -> newDominoes += dominoes[low].toString().repeat(high - low)
            dominoes[low] == 'R' && dominoes[high] == 'L' -> {
                val each = (high - low + 1) / 2
                val dot = (high - low + 1) % 2
                newDominoes += "R".repeat(each) + (if (dot == 1) "." else "") + "L".repeat(each - 1)
            }
        }
    }
    
    // Process the last letter.
    newDominoes += when (newDominoes.last()) {
        'R' -> if (dominoes.last() == '.') "R" else "."
        else   -> dominoes.last()
    }

    return newDominoes
}