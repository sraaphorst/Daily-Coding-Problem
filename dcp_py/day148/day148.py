#!/usr/bin/env python3
# day148.py
# By Sebastian Raaphorst, 2019.

from hypothesis import given
from hypothesis import strategies as st
from typing import List, Optional


def gray_code(n: int) -> List[List[int]]:
    """
    Recursively generate the Gray code of order n.
    :param n: the length of the Gray code
    :return: a list of 2^n binary strings of length n, who pairwise differ from each other (cyclically) by distance 1.

    >>> gray_code(0)
    [[]]
    >>> gray_code(1)
    [[0], [1]]
    >>> gray_code(2)
    [[0, 0], [0, 1], [1, 1], [1, 0]]
    >>> gray_code(3)
    [[0, 0, 0], [0, 0, 1], [0, 1, 1], [0, 1, 0], [1, 1, 0], [1, 1, 1], [1, 0, 1], [1, 0, 0]]
    """
    if n < 0:
        raise ValueError(f"gray_code({n}) does not exist")

    if n == 0:
        return [[]]
    prev = gray_code(n - 1)
    return [[0] + c for c in prev] + [[1] + c for c in reversed(prev)]


def distance(v1: List[int], v2: List[int]) -> Optional[int]:
    """
    Determine the distance between two vectors, i.e. the number of positions in which their value differs.
    :param v1: first vector
    :param v2: second vector
    :return: distance as described above

    >>> distance([], [])
    0
    >>> distance([0, 1, 1], [1, 0, 1])
    2
    """
    if len(v1) != len(v2):
        return None
    return sum(1 if v1_n != v2_n else 0 for v1_n, v2_n in zip(v1, v2))


def check_gray_code(gc: List[List[int]], n: int) -> bool:
    """
    Check if the list gc is a Gray code of length n.
    :param gc: the Gray code of length n candidate
    :param n: the length of the Gray code
    :return: True if it represents a Gray code of length n, else False

    >>> check_gray_code(gray_code(0), 0)
    True
    >>> check_gray_code(gray_code(5), 5)
    True
    """
    s = set(tuple(c) for c in gc)
    if len(s) != (1 << n):
        return False
    if n == 0:
        return True

    for curr in range(len(gc)):
        v1, v2 = gc[curr], gc[(curr + 1) % len(gc)]
        if len(v1) != n or len(v2) != n:
            return False
        if distance(v1, v2) != 1:
            return False
    return True


@given(st.integers(min_value=0, max_value=10))
def test_gray_code(n):
    assert check_gray_code(gray_code(n), n)
