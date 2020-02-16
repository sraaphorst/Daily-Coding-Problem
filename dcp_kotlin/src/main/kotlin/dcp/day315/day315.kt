package dcp.day315



fun <T> List<List<T>>.isToeplitz(): Boolean {
    val rows = size
    val cols = get(0).size
    require(all { it.size == cols })

    // Check the diagonal containing (r,c).
    tailrec
    fun checkDiagonal(r: Int, c: Int, v: T = get(r)[c], equal: Boolean = true): Boolean =
        when {
            r == rows - 1 || c == cols - 1 -> equal
            else -> checkDiagonal(r + 1, c + 1, v, get(r + 1)[c + 1] == v && equal)
        }

    // Check all diagonals starting in the top row and all diagonals starting in left column, except first, which is
    // covered by the top row.
    return (0 until cols). all { checkDiagonal(0, it)}
            && (1 until rows).all { checkDiagonal(it, 0)}
}
