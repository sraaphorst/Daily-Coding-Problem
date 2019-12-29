package dcp.day211
// day211.kt
// By Sebastian Raaphorst, 2019.


fun Char.hash(): Int = this.toInt() - 'a'.toInt() + 1

// Scan windows for substring.
fun findStartPoints(str: String, substr: String): Set<Int> {
    require(str.length >= substr.length) { "Main string must be longer than substring" }
    val strlen = str.length
    val substrlen = substr.length
    return (0..(strlen - substrlen)).filter {
            start ->
        str.subSequence(start, start + substrlen) == substr
    }.toSet()
}

fun findStartPointsRollingHash(str: String, substr: String): Set<Int> {
    require(str.length >= substr.length) { "Main string must be longer than substring" }
    // Convert a character into an int.

    val strLen = str.length
    val substrLen = substr.length

    // The hash of the substring.
    val substrHash = substr.map { it.hash() }.sum()

    // We start with a fully hashed string, so position 0 is the hash of the string str[0:substrLen].
    val startHash = str.take(substrLen).map { it.hash() }.sum()

    fun aux(pos: Int, hash: Int): Set<Int> {
        // Check to see if the requirements are met for this to quality as a possible candidate.
        val set = if (hash == substrHash && str.subSequence(pos, pos + substrLen) == substr) setOf (pos) else emptySet()

        // Update the hash code if possible to get the new set of values.
        val newset = if (pos + substrLen < strLen) {
            val newHash = hash - str[pos].hash() + str[pos + substrLen].hash()
            aux(pos + 1, newHash)
        } else {
            emptySet()
        }

        return set + newset
    }
    return aux(0, startHash)
}
