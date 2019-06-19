#!/usr/bin/env python3
# day074.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given, settings
from typing import List


def multiplication_table(n: int) -> List[List[int]]:
    """
    A completely unnecessary generation of a multiplication table, which is not needed to solve the problem.
    :param n: the size of the table, ranging [1,n] x [1,n].
    :return: the multiplication table, where the entry [i][j] = (i + 1) * (j + 1)

    >>> multiplication_table(4)
    [[1, 2, 3, 4], [2, 4, 6, 8], [3, 6, 9, 12], [4, 8, 12, 16]]

    >>> multiplication_table(1)
    [[1]]
    """
    return [[(r + 1) * (c + 1) for c in range(n)] for r in range(n)]


def count_table_entries(x: int, table: List[List[int]]) -> int:
    """
    Given a table, count the number of occurrences of x in it.
    :param x: the element to find
    :param table: the table to search
    :return: the number of occurrences of x

    >>> count_table_entries(1, [[1, 1, 1], [1, 1, 1], [1, 1, 1]])
    9

    >>> count_table_entries(2, [[2, 0, 0], [0, 2, 0], [0, 0, 3]])
    2

    >>> count_table_entries(0, [[1, 2], [3, 4]])
    0
    """
    return sum(row.count(x) for row in table)


def brute_force_multiplication_count(x: int, n: int) -> int:
    """
    Given a size n, create the multiplication table where entry [i][j] = (i + 1) * (j + 1) and count the number of
    occurrences of x in the table.
    :param x: the element to search for in the table
    :param n: the size of the table
    :return: the number of occurrences of x

    >>> [brute_force_multiplication_count(i, 5) for i in range(1,26)]
    [1, 2, 2, 3, 2, 2, 0, 2, 1, 2, 0, 2, 0, 0, 2, 1, 0, 0, 0, 2, 0, 0, 0, 0, 1]

    >>> [brute_force_multiplication_count(i, 6) for i in range(1,37)]
    [1, 2, 2, 3, 2, 4, 0, 2, 1, 2, 0, 4, 0, 0, 2, 1, 0, 2, 0, 2, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1]
    """
    return count_table_entries(x, multiplication_table(n))


def efficient_multiplication_count(x: int, n: int) -> int:
    """
    An efficient way to count the number of appearances of a number x in the multiplication table for [1,n] x [1,n].
    Since the table is symmetric due to commutativity of integer multiplication, we only need to consider factors a and
    b of x when a <= b and add them twice (a * b and b * a) unless x is square and a == b, in which case, we only add
    them once.
    :param x: the product to search for
    :param n: the size of the table
    :return: the number of unique products that give x

    >>> [efficient_multiplication_count(i, 5) for i in range(1, 26)]
    [1, 2, 2, 3, 2, 2, 0, 2, 1, 2, 0, 2, 0, 0, 2, 1, 0, 0, 0, 2, 0, 0, 0, 0, 1]

    >>> [efficient_multiplication_count(i, 6) for i in range(1, 37)]
    [1, 2, 2, 3, 2, 4, 0, 2, 1, 2, 0, 4, 0, 0, 2, 1, 0, 2, 0, 2, 0, 0, 0, 2, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1]
    """
    occurrences = 0

    # The lower bound for a factor is x / n or n, as for anything lower, there aren't entries high enough in the table
    # to multiply by to get n.
    a = x // n + (1 if x % n else 0)
    b = x // a + (1 if x % a else 0)
    a = min(a, b)

    # We only count entries x = a * b for a <= b.
    # This reflects the diagonal symmetry of the board due to commutativity of integers.
    while True:
        b, r = divmod(x, a)

        # Now terminate prematurely if a > b.
        if a > b:
            break

        # If this is a factor, count it.
        # If it is not on the main diagonal (i.e. x is not a square), count it twice to account for a * b and b * a.
        # If it is on the main diagonal (i.e. x is a square, and a == b), count it once to account for a * a.
        if r == 0 and b <= n:
            occurrences += 1 if a == b else 2

        a += 1

    return occurrences


@settings(deadline=1e9)
@given(st.integers(min_value=1, max_value=100))
def test_it(n: int):
    bf = [brute_force_multiplication_count(i, n) for i in range(1, n + 1)]
    ef = [efficient_multiplication_count(i, n) for i in range(1, n + 1)]
    assert (bf == ef)
