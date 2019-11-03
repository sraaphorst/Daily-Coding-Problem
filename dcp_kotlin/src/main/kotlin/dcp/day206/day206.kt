package dcp.day206
// day206.kt
// By Sebastian Raaphorst, 2019.

fun <T> permute(seq: List<T>, permutation: List<Int>): List<T> {
    return permutation.map { seq[it] }
}
