package dcp.day076

import scala.math.Ordering.Implicits._

object LexifyMatrix extends App {
  type Grid[T] = List[List[T]]

  /**
    * Sanity check to see if the rows specified are in lexicographical order.
    * We do this by looking at each pair of adjacent rows by zipping the list with its tail.
    */
  def check_lex_rows[T : Ordering](grid: Grid[T]): Boolean =
    grid.isEmpty || grid.zip(grid.tail).forall { case (r1, r2) => r1 < r2}

  /**
    * "Brute force" method to try to eliminate columns from the matrix that are not in lexicographical
    * order so that only lexigraphical columns - and hence rows - remain.
    */
  def lexifyMatrixBF[T : Ordering](grid: Grid[T]): (Grid[T], Int) = {
    // Convert to columns through transpose.
    val cols = grid.transpose

    // Rows that are in lexicographical order are sorted. This could be an expensive operation and is where
    // we will improve in the other implementation. This is O(n * n log n) and must be done O(m) times, so
    // O(m n n log n) times. Eeeep.
    val lexCols = cols.filter(col => col == col.sorted)

    // Return back to regular matrix format along with the number of removed columns.
    val rows = lexCols.transpose
    (rows, cols.size - lexCols.size)
  }

  /**
    * More efficient method to try to eliminate colunmns from the matrix that are not in lex mode by iterating
    * over them, which only takes O(n) instead of O(n n log n).
    */
  def lexifyMatrixEF[T : Ordering](grid: Grid[T]): (Grid[T], Int) = {
    // This lexicographical checked makes sure that the column appears in O(n).
    def is_lex(col: List[T]): Boolean =
      col.zip(col.tail).forall { case (e1, e2) => e1 < e2 }

    val cols = grid.transpose
    val lexCols = cols.filter(is_lex)

    (lexCols.transpose, cols.size - lexCols.size)
  }

  // Examples from the problem description.
  val testArray1 = List(List('c', 'b', 'a'), List('d', 'a', 'f'), List('g', 'h', 'i'))
  val lmbf11 = lexifyMatrixBF(testArray1)
  assert(check_lex_rows(lmbf11._1))
  assert(lmbf11._2 == 1)
  val lmbf12 = lexifyMatrixEF(testArray1)
  assert(check_lex_rows(lmbf12._1))
  assert(lmbf12._2 == 1)
  assert(lmbf11 == lmbf12)

  val testArray2 = List("abcdef".toList)
  val lmbf21 = lexifyMatrixBF(testArray2)
  assert(check_lex_rows(lmbf21._1))
  assert(lmbf21._2 == 0)
  val lmbf22 = lexifyMatrixEF(testArray2)
  assert(check_lex_rows(lmbf22._1))
  assert(lmbf22._2 == 0)
  assert(lmbf21 == lmbf22)

  val testArray3 = List(List('z', 'y', 'x'), List('w', 'v', 'u'), List('t', 's', 'r'))
  val lmbf31 = lexifyMatrixBF(testArray3)
  assert(check_lex_rows(lmbf31._1))
  assert(lmbf31._2 == 3)
  val lmbf32 = lexifyMatrixEF(testArray3)
  assert(check_lex_rows(lmbf32._1))
  assert(lmbf32._2 == 3)
  assert(lmbf31 == lmbf32)

}
