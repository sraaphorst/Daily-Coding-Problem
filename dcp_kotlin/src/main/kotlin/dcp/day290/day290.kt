package dcp.day290
// day290.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.max

sealed class Qux(private val char: Char) {
    override fun toString() = char.toString()
}
object R: Qux('R')
object G: Qux('G')
object B: Qux('B')

fun quxes(vararg quxLine: Qux) = quxLine.toList()

private val transform: Map<Pair<Qux, Qux>, Qux> = mapOf(
        Pair(R, G) to B,
        Pair(G, R) to B,
        Pair(R, B) to G,
        Pair(B, R) to G,
        Pair(B, G) to R,
        Pair(G, B) to R)

fun List<Qux>.reduce(): List<Qux> {
    tailrec
    fun aux(lst: List<Qux> = this, idx: Int = 0): List<Qux> =
        // We have reached the end of the line of Quxes: there are 0 or 1 left.
        if (idx >= lst.size - 1)
            lst
        else {
            // Check to see if the two adjacent Quxes are different colours.
            val newQux: Qux? = transform[Pair(lst[idx], lst[idx + 1])]
            when {
                newQux != null -> aux(lst.take(idx) + newQux + lst.drop(idx + 2), max(idx - 1, 0))
                else -> aux(lst, idx + 1)
            }
        }

    return aux()
}
