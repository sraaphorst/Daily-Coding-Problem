package dcp.day267
// day267.kt
// By Sebastian Raaphorst, 2019.

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// Obviously, there are so many test cases we could make, but we choose a handful of fairly illustrative ones.
class UnitTests {
    @Test
    fun example1() {
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
        assertTrue(board.isBlackKingInCheck())
    }

    @Test
    fun example2() {
        val boardString =
                    "...K...." +
                    "........" +
                    "........" +
                    "......P." +
                    ".......R" +
                    "..N....." +
                    "........" +
                    ".....Q.."

        val board: Board = Board.stringToBoard(boardString)
        assertFalse(board.isBlackKingInCheck())
    }

    @Test
    fun example3() {
        // All line pieces are blocked by pawns.
        val boardString =
                    "...K...." +
                    "........" +
                    ".P.P.P.." +
                    "B......." +
                    ".......Q" +
                    "...R...." +
                    "........" +
                    "........"

        val board: Board = Board.stringToBoard(boardString)
        assertFalse(board.isBlackKingInCheck())
    }

    @Test
    fun example4() {
        // King can be killed by a pawn.
        val boardString =
                    "........" +
                    "........" +
                    "........" +
                    ".......K" +
                    "......P." +
                    "........" +
                    "........" +
                    "........"

        val board: Board = Board.stringToBoard(boardString)
        assertTrue(board.isBlackKingInCheck())
    }

    @Test
    fun example5() {
        // King can be killed by a knight that cannot be blocked by a pawn.
        val boardString =
                    "...K...." +
                    "...P.N.." +
                    ".P.P.P.." +
                    "B......." +
                    ".......Q" +
                    "...R...." +
                    "........" +
                    "........"

        val board: Board = Board.stringToBoard(boardString)
        assertTrue(board.isBlackKingInCheck())
    }

    @Test
    fun example6() {
        // King can not be killed backwards by a pawn or by a pawn in the wrong position, or by blocked queens.
        val boardString =
                    "........" +
                    "........" +
                    "........" +
                    "........" +
                    "PPPPPPPP" +
                    "....K..." +
                    "PPP...PP" +
                    "PPPPPPPP"

        val board: Board = Board.stringToBoard(boardString)
        assertFalse(board.isBlackKingInCheck())
    }

    @Test
    fun example7() {
        // King can be killed backwards by a knight
        val boardString =
                    "........" +
                    "........" +
                    "..N....." +
                    "....K..." +
                    "........" +
                    "........" +
                    "........" +
                    "........"

        val board: Board = Board.stringToBoard(boardString)
        assertTrue(board.isBlackKingInCheck())
    }

    @Test
    fun example8() {
        // At death's door but still alive.
        val boardString =
                    "B...R..." +
                    "....P..." +
                    "R.....B." +
                    "R.W.K.N." +
                    "..Q....N" +
                    "......P." +
                    "..B....Q" +
                    ".....Q.."

        val board: Board = Board.stringToBoard(boardString)
        assertFalse(board.isBlackKingInCheck())
    }
}