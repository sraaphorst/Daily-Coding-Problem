package dcp.day199
// day199.kt
// By Sebastian Raaphorst.

fun closestString(elems: List<Char>): String {
    /**
     * Goal: given a string comprising parentheses, find a string with balanaced parentheses to the original string.
     * We can add or remove parentheses: each operation is considered a move, and a string s1 is closer to s2 than s3
     * if the number of moves you need to transform s1 to s2 is less than the number needed to transfer from s2 to s3.
     *
     * Note that since adding and removing parentheses have the same value, it doesn't matter which one we do: we can
     * focus on one and ignore the other. Here, we focus on adding ) to balance strings. We could remove ( and have the
     * same effect, albeit with shorter strings.
     *
     * This results in a greedy, functional solution with complexity and space O(n).
     */

    // We only care about parentheses.
    // Our zero value is a pair of the number of open parentheses and the closest string thus far.
    val (openParens, closestString) = elems
            .filter { it in setOf('(', ')')}
            .fold(0 to ""){ (openParens, closestString), p ->
                when (p) {
                    /*** (: just devour the ( and move on ***/
                    '(' -> (openParens + 1 to "$closestString(")

                    /*** ) ***/
                    else ->
                        when (openParens) {
                            0 -> (openParens to "$closestString()")
                            else -> (openParens -1 to "$closestString)")
                        }
                }
            }

    // Now we have to close all the remaining open parentheses.
    return closestString + ")".repeat(openParens)
}

fun main() {
    println("(()          -> ${closestString("(()".toList())}")
    println("))()(        -> ${closestString("))()(".toList())}")
    println("             -> ${closestString("".toList())}")
    println(")            -> ${closestString(")".toList())}")
    println("(            -> ${closestString("(".toList())}")
    println("()           -> ${closestString("()".toList())}")
    println(")(           -> ${closestString(")(".toList())}")
    println(")))))()((((( -> ${closestString(")))))()(((((".toList())}")
}