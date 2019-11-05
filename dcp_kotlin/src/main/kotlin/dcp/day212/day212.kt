package dcp.day212

fun columnNumToId(colNum: Int): String {
    require(colNum >= 1) { "Illegal column number: \"$colNum\"" }

    // Work with mod 26 to simplify.
    fun aux(id: Int): String {
        val chr = (id % 26 + 'A'.toInt()).toChar().toString()
        val rem = id / 26
        return (if (rem > 0) aux(rem - 1) else "") + chr
    }
    return aux(colNum - 1)
}

fun columnIdToNum(colId: String): Int {
    require(colId.isNotEmpty() && colId.all { it.isLetter() }) { "Illegal column identifier: \"$colId\"" }
    fun aux(colId: String): Int {
        // If we are empty, done.
        return if (colId.isEmpty()) 0
        else {
            // Process right to left.
            val curr = (colId.last().toInt() - 'A'.toInt() + 1)
            val rem = colId.dropLast(1)
            if (rem.isEmpty()) curr else curr + 26 * aux(rem)
        }
    }
    return aux(colId)
}
