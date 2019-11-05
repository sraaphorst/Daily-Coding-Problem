package dcp.day211

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

// Use a simple rolling hash to scan the windows for pattern.
// This uses fewer operations: for each window, we simply add a value and then subtract a value instead of looping
// over len(substr) items.
//fun findStartPointsRollingHash(str: String, substr: String): Set<Int> {
//    // Convert a character into an int.
//    fun aux(s: Char): Int = s.toInt() - 'A'.toInt() + 1
//
//    val strLen = str.length
//    val substrLen = substr.length
//
//    // The hash of the substring.
//    val subStrHash = substr.map { aux(it) }.sum()
//
//    // The current position in the string and the current hash value.
//    var hash = 0
//    for (pos in 0..strLen) {
//        if (pos > substrLen)
//        // Remove the first character, if applicable.
//            hash -= aux(substr[pos - substrLen])
//        // Add the next character.
//        hash += aux(str[pos])
//
//        // Check the hash.
//        if (hash == subStrHash) {
//            // If they match, do a more comprehensive scan.
//        }
//    }
//
//}

fun findStartPointsRollingHash(str: String, substr: String): Set<Int> {
    require(str.length >= substr.length) { "Main string must be longer than substring" }
    // Convert a character into an int.
    fun Char.hash(): Int = this.toInt() - 'a'.toInt() + 1

    val strLen = str.length
    val substrLen = substr.length

    // The hash of the substring.
    val substrHash = substr.map { it.hash() }.sum()

    // We start with a fully hashed string, so position 0 is the hash of the string str[0:substrLen].
    val startHash = str.take(substrLen).map { it.hash() }.sum()

    fun aux(pos: Int, hash: Int): Set<Int> {
        // Check to see if the requirements are met.
        println("Checking hash values: substr=$substrHash, current=$hash")
        val set: Set<Int> = {
            if (hash == substrHash) {
                // Check to see if the strings are actually equal.
                val hashStr = str.subSequence(pos, pos + substrLen)
                println("\tCheck to see if strings are equal: $substr, $hashStr")
                if (substr == hashStr) {
                    println("\t\t*** MATCH: pos=$pos")
                    setOf(pos)
                } else emptySet()
            } else emptySet()
        }()

        // Update the hash if possible.
        println("Trying to update the hash with ${hash + 1}. Max val is ${strLen - substrLen}.")
        val newset = if (pos + substrLen < strLen) {
            println("Removing the bottom position ${str[pos]} = ${str[pos].hash()}.")
            println("Adding top position ${str[pos + substrLen]} = ${str[pos + substrLen].hash()}")
            val newHash = hash - str[pos].hash() + str[pos + substrLen].hash()
            aux(pos + 1, newHash)
        } else {
            emptySet()
        }

        return set + newset
    }
    return aux(0, startHash)
}

fun main() {
    println(findStartPointsRollingHash("abcdbcebccbc", "bc"))
    println(findStartPointsRollingHash("abracadabra", "abr"))
}
