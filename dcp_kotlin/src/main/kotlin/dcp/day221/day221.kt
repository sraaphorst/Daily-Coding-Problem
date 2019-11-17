package dcp.day221
// day221.kt
// By Sebastian Raaphorst, 2019.

// Tail recursive functional program implementation of sevenish.
fun sevenish(n: Int): Int {
    require(n >= 0) { "sevenish($n) does not exist: only defined on values $n ≥ 0" }

    fun aux(cur: Int, pow: Int, remain: Int): Int {
        if (remain == 0) return cur
        return aux(cur + if (remain and 1 == 1) pow else 0, pow * 7, remain ushr 1)
    }

    return aux(0,1, n)
}

// Loop mutable functional program implementation of sevenish.
fun sevenish_loop(n: Int): Int {
    require(n >= 0) { "sevenish($n) does not exist: only defined on values $n ≥ 0" }

    // 7 ^ 0
    var pow = 1
    var sum = 0
    var rest = n

    while (rest != 0) {
        if (rest and 1 == 1)
            sum += pow
        pow *= 7
        rest = rest ushr 1
    }
    return sum
}