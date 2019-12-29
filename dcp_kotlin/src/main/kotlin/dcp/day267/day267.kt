package dcp.day267
// day267.kt
// By Sebastian Raaphorst, 2019.

import java.lang.IllegalArgumentException
import java.security.InvalidParameterException

// Represent the board as an 8x8 grid with (0,0) being in the upper left corner, the first coordinate representing
// the x-axis, and the second coordinate representing the y-axis.

enum class Direction {
    VERTICAL_N,
    VERTICAL_S,
    HORIZONTAL_E,
    HORIZONTAL_W,
    DIAGONAL_NE,
    DIAGONAL_NW,
    DIAGONAL_SE,
    DIAGONAL_SW
}


// Position of a piece
typealias Position = Pair<Int, Int>


// Generic filter to filter only legal moves.
fun List<Position>.filterLegal(): List<Position> =
    filter { it.first in 0..7 && it.second in 0..7 }


sealed class ChessPiece(val name: String, private val symbol: Char, val position: Position) {
    // Given a starting position, determine the list of valid positions to which a piece can move.
    abstract fun moves(otherPieces: List<Position>): List<Position>

    override fun toString(): String =
        "$symbol(${position.first},${position.second})"

    companion object {
        // Calculate moves for pieces that move in straight lines.
        // This takes a direction to calculate the legal moves along that direction, and the position of other pieces that
        // can block it from continuing in that direction.
        private fun lineMoves(start: Position, dx: Int, dy: Int, direction: Direction, otherPieces: List<Position>): List<Position> {
            val newPos = Position(start.first + dx, start.second + dy)

            // If we are out of bounds, stop. This voids us from having to filter legal moves.
            return if (newPos.first !in 0..7 || newPos.second !in 0..7)
                emptyList()

            // If a piece other than the black king blocks us, then stop.
            else if (newPos in otherPieces)
                emptyList()

            else listOf(newPos) + when(direction) {
                Direction.HORIZONTAL_E -> lineMoves(start,dx + 1, dy, direction, otherPieces)
                Direction.HORIZONTAL_W -> lineMoves(start, dx - 1, dy, direction, otherPieces)
                Direction.VERTICAL_N -> lineMoves(start, dx, dy + 1, direction, otherPieces)
                Direction.VERTICAL_S -> lineMoves(start, dx, dy - 1, direction, otherPieces)
                Direction.DIAGONAL_NE -> lineMoves(start, dx + 1, dy + 1, direction, otherPieces)
                Direction.DIAGONAL_NW -> lineMoves(start, dx - 1, dy + 1, direction, otherPieces)
                Direction.DIAGONAL_SE -> lineMoves(start, dx + 1, dy - 1, direction, otherPieces)
                Direction.DIAGONAL_SW -> lineMoves(start, dx - 1, dy - 1, direction, otherPieces)
            }
        }


        // Given a list of allowable directions, calculate all the line moves (i.e. moves along lines) that a piece can
        // make. The result is only allowable moves, i.e. moves that maintain position on the board and are not blocked
        // by another piece, with the exception of the black king.
        fun allLineMoves(start: Position, directions: List<Direction>, otherPieces: List<Position>): List<Position> =
            directions.flatMap { lineMoves(start, 0, 0, it, otherPieces) }

    }
}


class Pawn(position: Position): ChessPiece("pawn", 'P', position) {
    // For our intents and purposes, as we are looking to see if the black king is in check, we only allow diagonal
    // moves away from the x axis.
    override fun moves(otherPieces: List<Position>): List<Position> =
        listOf(-1, 1).map { Pair(position.first - 1, position.second + it) }.filterLegal()
}


// White king, so we use W instead of K, which is reserved for the black king.
// While in a real chess game, the kings can never place each other in check, we allow including the white king as
// he can block the movement of other pieces.
class King(position: Position): ChessPiece("king", 'W', position) {
    override fun moves(otherPieces: List<Position>): List<Position> =
        listOf(-1, 0, 1).flatMap { dx -> listOf(-1, 0, 1).filterNot { dx == 0 && it == 0 }.
            map { dy -> Pair(position.first + dx, position.second + dy) } }.filterLegal()
}


// A knight can move all 8 Ls (1x2), and can move through other pieces.
class Knight(position: Position): ChessPiece("knight", 'N', position) {
    override fun moves(otherPieces: List<Position>): List<Position> =
        listOf(Pair(1, 2), Pair(2, 1), Pair(-1, 2), Pair(-2, 1), Pair(1, -2), Pair(2, -1), Pair(-1, -2), Pair(-2, -1)).
            map { (dx, dy) -> Pair(position.first + dx, position.second + dy) }.filterLegal()
}


// With all subsequent pieces, they can be blocked by other pieces, so we have to restrict their movement based on
// the position of the other pieces.
// Rooks can move along horizontal and vertical lines.
class Rook(position: Position): ChessPiece("rook", 'R', position) {
    override fun moves(otherPieces: List<Position>): List<Position> =
        allLineMoves(position, listOf(Direction.HORIZONTAL_W, Direction.HORIZONTAL_E, Direction.VERTICAL_N, Direction.VERTICAL_S), otherPieces)
}


// Bishops can move along diagonal lines.
class Bishop(position: Position): ChessPiece("bishop", 'B', position) {
    override fun moves(otherPieces: List<Position>): List<Position> =
        allLineMoves(position, listOf(Direction.DIAGONAL_NE, Direction.DIAGONAL_NW, Direction.DIAGONAL_SE, Direction.DIAGONAL_SW), otherPieces)
}

// Queens can move along all lines.
class Queen(position: Position): ChessPiece("queen", 'Q', position) {
    override fun moves(otherPieces: List<Position>): List<Position> =
        allLineMoves(position, listOf(
            Direction.HORIZONTAL_W, Direction.HORIZONTAL_E, Direction.VERTICAL_N, Direction.VERTICAL_S,
            Direction.DIAGONAL_NE, Direction.DIAGONAL_NW, Direction.DIAGONAL_SE, Direction.DIAGONAL_SW
            ), otherPieces)
}


// The board is represented by an 8x8 matrix of rows, for example, of the form:
// ...K....
// ........
// .B......
// ......P.
// .......R
// ..N.....
// ........
// .....Q..
class Board(matrix: List<String>) {
    private var blackKing: Position
    private val whitePieces: List<ChessPiece>

    init {
        // Auxiliary function to read in the white pieces.
        fun aux(x: Int, y: Int): List<ChessPiece> {
            // Stopping condition: we reach one row past the end of the board.
            return when {
                x == 8 -> emptyList()
                y == 8 -> aux(x + 1, 0)
                else -> when(val s = matrix[x][y]) {
                    'P' -> listOf(Pawn(Position(x, y)))
                    'N' -> listOf(Knight(Position(x, y)))
                    'W' -> listOf(King(Position(x, y)))
                    'R' -> listOf(Rook(Position(x, y)))
                    'B' -> listOf(Bishop(Position(x, y)))
                    'Q' -> listOf(Queen(Position(x, y)))
                    'K' -> emptyList()
                    '.' -> emptyList()
                    else -> throw IllegalArgumentException("Board contains invalid symbol: $s")
                } + aux(x, y + 1)
            }
        }

        whitePieces = aux(0, 0)

        // Find the black king.
        val blackKingRow = matrix.withIndex().find { (_, row) -> 'K' in row }
        val blackKingX = blackKingRow?.index
        val blackKingY = blackKingRow?.value?.indexOf('K')

        blackKing = blackKingX?.let { x -> blackKingY?.let { y -> Position(x, y) }} ?:
                throw InvalidParameterException("Black king not found on board.")

    }

    // Check if the black king is in check by calculating all of the moves of the other pieces to see if they can
    // move onto the black king.
    fun isBlackKingInCheck(): Boolean {
        val positions = whitePieces.map(ChessPiece::position)
        return blackKing in whitePieces.flatMap { it.moves(positions - it.position) }
    }

    companion object {
        // Convenience function to create a board from a single string of length 64 instead of a list of eight
        // strings of length 8.
        fun stringToBoard(str: String): Board {
            require(str.length == 64)
            val matrix = (0..7).map{ idx -> str.drop(idx * 8).take(8) }
            return Board(matrix)
        }
    }
}

fun main() {
    val boardString =
        "...K...." +
        "........" +
        ".B......" +
        "......P." +
        ".......R" +
        "..N....." +
        "........" +
        ".....Q.."

    val board: Board = Board.stringToBoard(boardString)
    println(board.isBlackKingInCheck())
}