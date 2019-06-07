#!/usr/bin/env python3
# day062.py
# By Sebastian Raaphorst, 2019.


from math import factorial


def num_paths(n: int, m: int) -> int:
    """
    Calculate the number of paths in a rectangle of dimensions n x m from the top left to the bottom right.
    This is incredibly easy: we have to make n - 1 moves to the right, and m - 1 moves down.
    Thus, we must make a total of n - 1 + m - 1 moves, and choose n - 1 of them to be to the right.
    The remaining ones will be down.

    :param n: one dimension of the matrix (doesn't really matter which, due to symmetry)
    :param m: the other dimension of the matrix
    :return: the number of possible paths through the matrix

    >>> num_paths(2, 2)
    2
    >>> num_paths(5, 5)
    70
    """
    return factorial(n - 1 + m - 1)//factorial(n-1)//factorial(m-1)
