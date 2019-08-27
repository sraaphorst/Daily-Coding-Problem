#!/usr/bin/env python3

from typing import List


def max_coins(coins: List[List[int]]) -> int:
    """
    Given an n by m board, with each label (i,j) labeled with the number of coins that that square contains,
    determine the maximum number of squares that can be collected starting at the top left corner and moving
    to the bottom right corner
    >>> max_coins([[0, 3, 1, 1], [2, 0, 0, 4], [1, 5, 3, 1]])
    12
    """
    n = len(coins)
    m = len(coins[0])
    aux = [[0] * m for _ in range(n)]

    for i in range(n):
        for j in range(m):
            aux[i][j] = coins[i][j] if i == 0 or j == 0 else 0

    # Now calculate the max values from (1,n) and (1,m).
    for i in range(1,n):
        for j in range(1,m):
            aux[i][j] = coins[i][j] + max(aux[i-1][j], aux[i][j-1])

    # The bottom right corner is always the biggest answer.
    return aux[n-1][m-1]
