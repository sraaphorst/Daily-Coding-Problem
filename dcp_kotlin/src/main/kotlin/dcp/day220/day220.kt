package dcp.day220
// day220.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.max
import kotlin.math.min

// Two solutions: backtracking and dynamic programming.

// Backtracking: O(2^N)
fun coin_backtrack(coins: List<Int>): Int {
    if (coins.isEmpty()) return 0
    fun aux(curr_coins: List<Int>, value: Int): Int {
        if (curr_coins.size == 1)
            return curr_coins[0] + value
        else if (curr_coins.size == 2)
            return max(curr_coins[0], curr_coins[1]) + value
        else {
            val minCandidate = aux(curr_coins.drop(2), value)
            val midCandidate = aux(curr_coins.drop(1).dropLast(1), value)
            val maxCandidate = aux(curr_coins.dropLast(2), value)
            return max(maxCandidate, midCandidate) + min(minCandidate, midCandidate) + value

        }
    }

    return aux(coins, 0)
}

// Dynamic programming approach. Upper triangular, where coins[i]..coins[j] represents the highest possible
// value for coins v_i through v_j.
fun coin_dynamic(coins: List<Int>): Int {
    if (coins.isEmpty()) return 0
    val n = coins.size
    val value = MutableList(n) {MutableList(n) {0} }

    // Strategy to fill:
    // 1. If there is just one coin, then that is the highest value.
    (0 until n).forEach { value[it][it] = coins[it] }

    // 2. The maximum value of two coins (stored in value[i][i]) is the maximum of the two choices.
    (0 until n-1).forEach { value[it][it+1] = max(value[it][it], value[it+1][it+1]) }

    // 3. Now we consider longer rows of coins.
    // We choose a coin that maximizes our value and the minimal value of the opponent.
    (2 until n)
        .forEach{ gap -> (0 until (n-gap))
            .forEach {
                val j = it + gap
                val left = value[it][j - 2]
                val diagonal = value[it + 1][j - 1]
                val bottom = value[it + 2][j]
                value[it][it + gap] = max(
                    coins[it] + min(diagonal, bottom),
                    coins[j] + min(left, diagonal)
                )}
    }
    return value[0][n-1]
}