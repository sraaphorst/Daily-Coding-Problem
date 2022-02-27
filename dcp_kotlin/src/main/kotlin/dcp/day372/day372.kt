package dcp.day372
// day372.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.log10

fun Int.numDigits(): Int = when (this) {
    0 -> 1
    else -> log10(this.toDouble()).toInt() + 1
}
