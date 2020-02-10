package dcp.day309

import org.eclipse.jgit.lib.PersonIdent

// M people sitting in a row of N seats.
enum class SeatContent {
    PERSON,
    EMPTY
}

typealias SeatContents=List<SeatContent>

/**
 * Clump the people in the seats so that there are no gaps
 * between them using the fewest moves.
 * */
fun SeatContents.group2(): Int? {
    if (isEmpty())
        return 0

    // We want the median of the seat contents: we see that as the point of gravity and move everyone there.
    val seatsUsed = indices.filter { get(it) == SeatContent.PERSON }
    val seatsUsedCount = seatsUsed.count()

    var ans = 0
    var ans1 = 1

    // Process everyone to the left of the median.
    val median = seatsUsedCount / 2
    for (i in 0 until median) {
        val distanceToMedian = seatsUsed[median] - seatsUsed[i]
        ans = ans + distanceToMedian - ans1
        ++ans1
    }

    ans1 = 1
    for (i in (median + 1 until seatsUsedCount)) {
        val distanceToMedian = seatsUsed[i] - seatsUsed[median]
        ans = ans + distanceToMedian - ans1
        ++ans1
    }

    return ans
}

fun SeatContents.group(): Int {
    if (isEmpty())
        return 0

    // We want the median of the seat contents: we see that as the point of gravity and move everyone there.
    val seatsUsed = indices.filter { get(it) == SeatContent.PERSON }

    // Process everyone to the left of the median, and then to the right of the median.
    val median = seatsUsed.count() / 2

    // Move seats points towards median:
    val leftSeats = seatsUsed.take(median)
    // We adjust by the index because each seat must be 1 further away from the median.
    val leftDistancesToMedian = leftSeats.withIndex().map { (idx, seat) -> seatsUsed[median] - seat - (idx + 1) }.sum()


    // Move right seats towards median:
    val rightSeats = seatsUsed.drop(median + 1)
    // We adjust by the index because each seat must be 1 further away from the median.
    val rightDistancesToMedian = rightSeats.withIndex().map { (idx, seat) -> seat - seatsUsed[median] - (idx + 1)}.sum()

    return leftDistancesToMedian + rightDistancesToMedian
}


val P = SeatContent.PERSON
val E = SeatContent.EMPTY

fun main() {
    val row = listOf(E, P, P, E, P, E, E, E, P)
    println(row.group2())
    val row2 = listOf(P, E, E, E, E, E, E, P)
    println(row2.group2())
    val row3 = listOf(P, E, E, P, E, P, P, P, E, P, P, E, E, P, E)
    println(row3.group2())

}