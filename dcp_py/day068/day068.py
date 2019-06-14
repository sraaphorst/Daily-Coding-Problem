#!/usr/bin/env python3
# day068.py
# By Sebastian Raaphorst, 2019.

from typing import List, Tuple


def calculate_bishops(bishops: List[Tuple[int, int]], m: int) -> int:
    """
    Given a list of bishops on a board of size m x m, where the bishops are given by coordinate,
    calculate the number of possible attacking pairs at the point in time of the board.

    :param bishops: the positions of the bishops
    :param m: the size of the board (not even needed)
    :return: the number of pairs of possibly attacking bishops

    >>> bishops1 = [(0,0), (2,0), (0,2), (1,1), (2,2)]
    >>> calculate_bishops(bishops1, 3)
    6

    >>> bishops2 = [(0,0), (1,2), (2,2), (0,1)]
    >>> calculate_bishops(bishops2, 5)
    2
    """
    for (r, c) in bishops:
        assert 0 <= r < m and 0 <= c < m, f"Illegal bishop: ({r},{c})"

    def bishops_share_diagonal(b1: int, b2: int) -> bool:
        """
        Determine if two bishops share the same diagoonal.
        :param b1: the index of the first bishop
        :param b2: the index of the second bishopp
        :return: True if they are on a diagonal together
        """
        (r1, c1) = bishops[b1]
        (r2, c2) = bishops[b2]
        return abs(r1 - r2) == abs(c1 - c2)

    return len([(b1, b2) for b1 in range(len(bishops)) for b2 in range(len(bishops))
                if b1 > b2 and bishops_share_diagonal(b1, b2)])
