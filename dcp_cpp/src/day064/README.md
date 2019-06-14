# Day 64 \[Hard\

This problem was asked by Google.

A __knight's tour__ is a sequence of moves by a knight on a chessboard such that all squares are visited once.

Given `N`, write a function to return the number of knight's tours on an `N` by `N` chessboard.

Link: [Testing code](../../test/TestDay064.cpp)

### Possible enhancements:

1. For even sided boards, limit the starting position to the top left 1/4 board. Then we can exploit the symmetry
group of the board to determine the current number of equivalent boards across the starting positions of the rest
of the board. This could also be used to reduce the number of starting positions in the upper-left board: for
example, starting at `(0,1)` should be the same as starting at `(1,0)` for any `N > 1`.
