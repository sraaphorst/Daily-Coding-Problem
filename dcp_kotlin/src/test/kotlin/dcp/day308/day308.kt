package dcp.day308

/**
 * The problem consists c of alternating boolean truth symbols from T and F and operands from |, &, and ^.
 * Determine the number of parenthetizations that make it true. */
//sealed class Expression {
//    abstract fun apply(): Boolean
//}
//
//sealed class Value: Expression()
//object T: Value() {
//    override fun apply(): Boolean = true
//}
//object F: Value() {
//    override fun apply(): Boolean = false
//}
//
//sealed class Conjunction(val lhs: Expression, val rhs: Expression): Expression()
//class AND(lhs: Expression, rhs: Expression): Conjunction() {
//    override fun apply(): Boolean = lhs.apply() && rhs.apply()
//}
//class OR(lhs: Expression, rhs: Expression): Conjunction(lhs, rhs) {
//    override fun apply(): Boolean = lhs.apply() || rhs.apply()
//}
//class XOR(lhs: Expression, rhs: Expression): Conjunction(lhs, rhs) {
//    override fun apply(): Boolean = lhs.apply() xor rhs.apply()
//}

//fun List<Char>.trueParentheses(): List<String> {
//    require(indices.all {
//        when {
//            it % 2 == 0 -> get(it) in "TF"
//            else -> it in "&|^"
//        }
//    })
//
//    // Let's do some memoization.
//    val memoize = mutableMapOf<List<Char>, Boolean>()
//
//    fun aux(startIdx, endIdx): Int = when {
//        startIdx == endIdx && idx in
//    }
//}

fun List<Char>.countParentheses(): List<String> {
    fun aux(start: Int = 0, end: Int = 0, exprSoFar: String = ""): List<String> {
        if (start == end)
            return listOf(exprSoFar)
        else {
            return aux(start + 1, end, exprSoFar + "${get(start)}(") +
            if (start - end >= 2)
                return aux(start + 2, end, exprSoFar + "(${get(start)}${get(start+1)}")
            else
                emptyList<String>()
        }
    }

    return aux()
}


fun main() {
    println(listOf('A', 'B').countParentheses())
}