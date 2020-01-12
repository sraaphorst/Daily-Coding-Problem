package dcp.day281
// day281.kt
// By Sebastian Raaphorst, 2020.

typealias Row = List<Int>
typealias Matrix = List<Row>
typealias CollectiveRow = List<Int>
typealias CollectiveMatrix = List<CollectiveRow>
typealias RowIndex = Int

fun findVerticalLine(matrix: Matrix): RowIndex {
    if (matrix.isEmpty())
        return 0

    fun matrixToCollectiveMatrix(matrix: Matrix): CollectiveMatrix {
        fun rowToCollectiveRow(row: Row): CollectiveRow =
            row.fold(emptyList()) { acc, i -> if (acc.isEmpty()) listOf(i) else acc + listOf(acc.last() + i) }
        return matrix.map(::rowToCollectiveRow)
    }

    // We convert the row representations (e.g. 2, 3, 3, 2) to collective representations (e.g. 2, 5, 8, 10).
    val collectiveMatrix = matrixToCollectiveMatrix(matrix)

    // Assert first that each collective matrix has the same length.
    val rowLength = collectiveMatrix.first().last()
    require(collectiveMatrix.all { it.last() == rowLength }){"Rows must all have the same length."}

    // Then we find which number appears the most times in the collective representations. This makes sense because
    // each number can only appear once in each collective row, so choosing the number that appears the most means
    // having to chop through the least number of rows that don't have a seam at that number. We ignore the last
    // number because that would be cheating and the obvious solution, since it appears in every row.
    val allValues = collectiveMatrix.flatten()
    return (1 until rowLength).map { i -> i to allValues.count{it == i}}.maxBy { it.second }?.first ?:
            throw RuntimeException("Something went disastrously wrong.")
}