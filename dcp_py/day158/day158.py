#!/usr/bin/env python3
# day158.py
# By Sebastian Raaphorst, 2019

from typing import List, Tuple
from enum import Enum
from functools import lru_cache


class State(Enum):
    Floor = 0
    Wall = 1


Matrix = List[List[State]]
F = State.Floor
W = State.Wall


def memoized_path(matrix: Matrix) -> int:
    """
    Instead of using a brute-force approach, which might require processing the same submatrix many times,
    use a memoized approach on position using an LRU cache in order to avoid repeated work.

    :param matrix: the matrix indicating the layout of the space
    :return: the number of paths

    >>> matrix1 = [[F, F, W], [F, F, W], [W, F, F]]
    >>> memoized_path(matrix1)
    2

    >>> matrix2 = [[F, F, F, W], [F, F, F, W], [W, F, F, F], [W, W, F, F]]
    >>> memoized_path(matrix2)
    10
    """
    final_pos = len(matrix) - 1, len(matrix[0]) - 1
    final_pos_x, final_pos_y = final_pos

    @lru_cache(maxsize=None)
    def aux(pos: Tuple[int, int]) -> int:
        if pos == final_pos:
            return 1

        x, y = pos

        # Try moving down.
        down_paths = 0
        if x + 1 <= final_pos_x and matrix[x + 1][y] == F:
            down_paths = aux((x + 1, y))

        # Try moving right.
        right_paths = 0
        if y + 1 <= final_pos_y and matrix[x][y + 1] == F:
            right_paths = aux((x, y + 1))

        return down_paths + right_paths

    return aux((0, 0))
