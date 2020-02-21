package dcp.day321

import kotlin.math.max
import kotlin.math.sqrt

// Memoization speeds things up considerably.
@kotlin.ExperimentalUnsignedTypes
private val cache = mutableMapOf(1UL to 0UL)

@kotlin.ExperimentalUnsignedTypes
fun ULong.stepToZero(): ULong {
    if (this in cache)
        return cache[this]!!

    val upperBound = sqrt(this.toDouble()).toULong()

    // If N = a * b, we can move to max(a, b). Either a >= sqrt(this) or b >= sqrt(this), so this
    // covers all of the possibilities.
    val divisors = (2UL..upperBound.toULong()).filter { this % it == 0UL }.map { max(this / it, it) }

    cache[this] = (divisors.map { 1UL + it.stepToZero() } + (1UL + (this - 1UL).stepToZero())).min() ?: 0UL
    return cache[this]!!
}

@kotlin.ExperimentalUnsignedTypes
fun main() {
    var maxVal = 0UL
    println(ULong.MAX_VALUE)
    for (i in (1UL until ULong.MAX_VALUE)) {
        val value = i.stepToZero()
        if (value > maxVal) {
            maxVal = value
            println("$i $value")
        }
    }
}