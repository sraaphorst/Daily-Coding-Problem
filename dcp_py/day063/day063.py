#!/usr/bin/env python3
# day063.py
# By Sebastian Raaphorst, 2019.

from typing import List


def contains_word(grid: List[List[chr]], word: str) -> bool:
    """
    Determine if a square matrix of chr contains a given word.
    We could do this perhaps slightly more efficiently by finding the rows and columns that start with the first
    letter, and then the second, etc, but this solution is far more Pythonic.

    :param grid: the grid of letters
    :param word: the word to find
    :return: True if the word appears, and False otherwise

    >>> grid = [['F', 'A', 'C', 'I'], ['O', 'B', 'Q', 'P'], ['A', 'N', 'O', 'B'], ['M', 'A', 'S', 'S']]
    >>> contains_word(grid, 'FOAM')
    True
    >>> contains_word(grid, 'MASS')
    True
    >>> contains_word(grid, 'FOAD')
    False
    """
    for row in grid:
        if ''.join(row) == word:
            return True
    for col in zip(*grid):
        if ''.join(col) == word:
            return True
    return False
