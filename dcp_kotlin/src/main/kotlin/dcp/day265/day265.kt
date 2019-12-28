package dcp.day265
// day265.kt
// By Sebastian Raaphorst, 2019.

import kotlin.math.max

typealias LOC = Int
typealias Bonus = Int
typealias LOCWithBonus = Pair<LOC, Bonus>

/**
 * Calculate the bonus based on LOC of your neighbours.
 * If you have written more LOC than your neighbours, you should receive the minimal amount more bonus than they do.
 * This is a messy functional solution: the solution is much easier allowing for mutability and loops.
 */
fun calculateBonus(locs: List<LOC>): List<Bonus> {
    if (locs.isEmpty()) return emptyList()

    // Make a left-to-right pass, with the first bonus set to 1, and subsequent bonuses set to prev + 1 if their
    // loc is greater. If it is equal, maintain it. If it is less, leave it at 1.
    val withBonus: List<LOCWithBonus> = locs.fold(emptyList()){ acc, loc2 ->
        if (acc.isEmpty()) listOf(LOCWithBonus(loc2, 1))
        else {
            val (loc1, bonus) = acc.last()
            acc + LOCWithBonus(
                loc2, when {
                    loc2 > loc1 -> bonus + 1
                    loc2 == loc1 -> bonus
                    else -> 1
                }
            )
        }
    }

    // Now make a right-to-left pass, but limit the bonus to the maximum of the current value and the right
    // neighbour + 1 so that we don't allow any reductions.
    return withBonus.foldRight(emptyList<LOCWithBonus>()){ locWithBonus1, acc ->
        if (acc.isEmpty()) listOf(locWithBonus1)
        else {
            val (loc1, bonus1) = locWithBonus1
            val (loc2, bonus2) = acc.first()
            listOf(LOCWithBonus(
                loc1, when {
                    loc1 > loc2 -> max(bonus1, bonus2 + 1)
                    loc2 == loc1 -> bonus1
                    else -> bonus1
                }
            )) + acc
        }
    }.unzip().second
}

fun calculateBonusMutable(locs: List<LOC>): List<Bonus> {
    if (locs.isEmpty()) return emptyList()

    // Initialize all bonuses to 1.
    val bonuses = MutableList(locs.size){1}

    // Iterate left-to-right, making sure the bonus of an employee is larger than its neighbour if the employee
    // has written more LOC.
    for (i in (1 until locs.size)) {
        bonuses[i] = when {
            locs[i] > locs[i-1] -> bonuses[i-1] + 1
            locs[i] == locs[i-1] -> bonuses[i-1]
            else -> 1
        }
    }

    // Now iterate from right-to-left, making sure the same condition holds.
    // We must take the max with the existing value to not violate what we calculated on the left-to-right pass.
    for (i in (locs.size - 2 downTo 0)) {
        bonuses[i] = when {
            locs[i] > locs[i+1] -> max(bonuses[i], bonuses[i+1] + 1)
            locs[i] == locs[i+1] -> bonuses[i]
            else -> bonuses[i]
        }
    }

    return bonuses
}

fun main() {
    val l = listOf(38037, 42821, 48913, 82651, 22010)
    println(calculateBonus(l))
    println(calculateBonusMutable(l))
}