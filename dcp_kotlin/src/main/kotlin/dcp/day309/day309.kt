package dcp.day309
// day309.kt
// By Sebastian Raaphorst

// M people sitting in a row of N seats.
enum class SeatContent {
    PERSON,
    EMPTY
}

typealias SeatContents=List<SeatContent>

/**
 * Clump the people in the seats so that there are no gaps
 * between them using the fewest moves.
 */
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
