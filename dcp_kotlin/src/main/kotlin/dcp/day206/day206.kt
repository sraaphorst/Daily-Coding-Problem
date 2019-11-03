package dcp.day206
// day206.kt
// By Sebastian Raaphorst, 2019.

// Easy functional technique.
fun <T> permute(seq: List<T>, permutation: List<Int>): List<T> {
    return permutation.map { seq[it] }
}

// Permute in-place by cycles.
fun <T> permuteInPlace(seq: MutableList<T>, permutation: MutableList<Int>): MutableList<T> {
    for (i in 0..(seq.size)) {
        var elem = seq[i]
        var p = permutation[i]

        // Process the next cycle, if there is one, by cycling.
        while (p != i) {
            val tmp = elem
            elem = seq[p]
            seq[p] = tmp

            val tmpInt = permutation[p]
            permutation[p] = p
            p = tmpInt
        }

        seq[i] = elem
        permutation[i] = p
    }

    return seq
}
