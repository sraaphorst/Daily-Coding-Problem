#!/usr/bin/env python3
# day065.py
# By Sebastian Raaphorst, 2019.

from typing import List


def spiral(grid: List[List[int]]) -> List[int]:
    """
    Given a grid, read off the entries in a clockwise spiral starting at the top-left corner.

    :param grid: the grid to traverse
    :return: the list representing the unwinding of the matrix

    >>> grid45 = [[0, 1, 2, 3, 4], [13, 14, 15, 16, 5], [12, 19, 18, 17, 6], [11, 10, 9, 8, 7]]
    >>> spiral(grid45)
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]

    >>> grid54 = [[0, 1, 2, 3], [13, 14, 15, 4], [12, 19, 16, 5], [11, 18, 17, 6], [10, 9, 8, 7]]
    >>> spiral(grid54)
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]

    >>> grid55 = [[0, 1, 2, 3, 4], [15, 16, 17, 18, 5], [14, 23, 24, 19, 6], [13, 22, 21, 20, 7], [12, 11, 10, 9, 8]]
    >>> spiral(grid55)
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]

    >>> grid44 = [[0, 1, 2, 3], [11, 12, 13, 4], [10, 15, 14, 5], [9, 8, 7, 6]]
    >>> spiral(grid44)
    [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]

    >>> grid24 = [[0, 1, 2, 3], [7, 6, 5, 4]]
    >>> spiral(grid24)
    [0, 1, 2, 3, 4, 5, 6, 7]

    >>> grid14 = [[0, 1, 2, 3]]
    >>> spiral(grid14)
    [0, 1, 2, 3]

    >>> grid41 = [[0], [1], [2], [3]]
    >>> spiral(grid41)
    [0, 1, 2, 3]

    >>> grid11 = [[0]]
    >>> spiral(grid11)
    [0]
    """
    xsize = len(grid[0])
    ysize = len(grid)
    maxc = min(xsize // 2 + xsize % 2, ysize // 2 + ysize % 2)

    # Define an auxiliary method to start at a specified upper left corner (c, c) and do one square loop (if possible).
    def spiral_aux(c: int, lst: List[int]) -> List[int]:
        if c >= maxc:
            return lst

        lxbound = c
        uxbound = xsize - c
        lybound = c
        uybound = ysize - c

        # Go from left to right.
        for i in range(lxbound, uxbound):
            lst.append(grid[c][i])

        # Go from top to bottom. Skip the first entry since we already added it in the left-to-right traversal.
        for i in range(lybound + 1, uybound):
            lst.append(grid[i][uxbound - 1])

        # If the right-to-left row is the same as the left-to-right row, we stop.
        if uybound - 1 == c:
            return lst

        # Go from right to left. Skip the first entry since we already added it in the top-to-bottom traversal.
        for i in range(uxbound - 2, lxbound - 1, -1):
            lst.append(grid[uybound - 1][i])

        # If the bottom-to-top column is the same as the top-to-bottom column, we stop.
        if uxbound - 1 == lxbound:
            return lst

        # Go from bottom to top. Skip the first entry since we already added it in the right-to-left traversal.
        # Skip the last entry since we covered it in the first left-to-right traversal.
        for i in range(uybound - 2, lybound, -1):
            lst.append(grid[i][lxbound])

        return spiral_aux(c + 1, lst)

    return spiral_aux(0, [])
