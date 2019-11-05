package dcp.day211

// Scan windows for substring.
fun findStartPoints(str: String, substr: String): Set<Int> {
    val strlen = str.length
    val substrlen = substr.length
    return (0..(strlen - substrlen)).filter {
            start ->
        str.subSequence(start, start + substrlen) == substr
    }.toSet()
}
