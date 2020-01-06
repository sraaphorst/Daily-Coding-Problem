package dcp.day272
// day272.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.min

typealias Ways = Int

// Brute force: stack overflows way too quickly.
fun throwDiceBF(number: Int, faces: Int, total: Int): Ways {
    fun aux(number: Int, totalLeft: Int, rolls: Int): Ways {
        if (number == 1)
            if (total <= faces) return 1 else 0

        return (1 until min(totalLeft, faces + 1)).map { aux(number - 1, faces, totalLeft - faces) }.sum()
    }
    return aux(number, faces, total)
}

// Dynamic programming.
fun throwDiceDP(number: Int, faces: Int, total: Int): Ways {
    // Create a matrix number by total for dynamic programming.
    val ways = MutableList(number){ MutableList(total+1) {0} }

    (1 until total + 1).forEach { ways[0][it] = if (it <= faces) 1 else 0 }
    (1 until number).forEach { dice ->
        (1 until total + 1).forEach { t ->
            (1 until min(t, faces + 1)).forEach { face ->
                ways[dice][t] += ways[dice - 1 ][t - face]
            }
        }
    }

    return ways.last().last()
}
