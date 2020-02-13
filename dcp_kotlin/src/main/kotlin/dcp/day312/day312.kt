package dcp.day312
// day312.kt
// By Sebastian Raaphorst, 2020.

import kotlin.math.max

/**
 * This is sequence A052980 in the Online Encyclopedia of Integer Sequences:
 *
 * https://oeis.org/A052980
 *
 * and is a deceptively difficult problem of dynamic programming (i.e. using memoization to make recurrence relations
 * feasible to solve). My initial attempt was to tabulate all unique tilings of the board up to symmetry and sub-boards
 * but this proceeds until 6:
 *       A A B B C C
 *       A D D E E C
 * which is indivisible into smaller tilings, and that solution was too complicated what with symmetries.
 *
 * Consider the case of placing the last tile. We have four distinct cases:
 *
 * 1. A vertical domino:
 *       A
 *       A
 *
 *  2. A horizontal domino:
 *       A A
 *
 *  3. A tromino extending on the top:
 *        A A
 *          A
 *
 *  4. A tromino extending on the bottom:
 *          A
 *        A A
 *
 * We now want to derive recurrence relations to describe these cases.
 * Let F(N) be the number of distinct tilings that can tile a 2xN board.
 * We also need the cases:
 *        X X    and   X
 *        X            X X
 *  Let G(N) be the number of distinct tilings that tile a 2xN board with a square jutting out.
 *  We can reduce this to the number of distinct tilings with a square jutting out the top, and multiply by 2 to get
 *  the symmetry.
 *
 *  Then we get that:
 *  F(N) = F(N-1) + F(N-2) + 2G(N-2).
 *  because we can either have one vertical domino as our previous move (option 1), one horizontal domino as our
 *  previous move (option 2 as it reduces the size of the board by 2), or one of the tromino situations (which
 *  reduces the board by 2).
 *
 *  The base cases for F are:
 *         A     or    A A
 *         A           B B
 *   so F(1) = 1 and F(2) = 2.
 *
 *   We now need a recurrence relation and the base cases for G.
 *   We have two situations in which this may happen (four through symmetry, hence the multiple of 2 in the
 *   recurrence relation for F):
 *        X A A    or    X A A
 *        X X            X A
 *
 *   In the first case, a domino was last placed, leaving us a 2XN rectangle with a jut with G(N-1) possible tilings.
 *   In the second case, a tromino was last placed, leaving us a 2xN rectangle with F(N-1) possible tilings.
 *
 *   Thus, we have that:
 *   G(N) = F(N-1) + G(N-1)
 *
 *   The base cases for G are:
 *          A A     or    A B B    or    A B B
 *          A             A A            A B
 *   so G(1) = 1 and G(2) = 2.
 *
 *   In both cases, there is 1 way to cover a 2x0 board (i.e. nothing), so that is also a base case.
 *
 *   This gives us all we need to calculate the recurrences.
 */

fun coverings(n: Int): Int {
    // We need mutable lists to store the intermediate values.
    val f = MutableList(max(3, n+1)){0}
    f[0] = 1
    f[1] = 1
    f[2] = 2

    val g = MutableList(max(3,n+1)){0}
    g[0] = 1
    g[1] = 1
    g[2] = 2

    for (i in 3 until n+1) {
        g[i] = g[i-1] + f[i-1]
        f[i] = f[i-1] + f[i-2] + 2 * g[i-2]
    }

    return f[n]
}

fun main() {
    for (i in 0 until 15)
        println("$i -> ${coverings(i)}")
}