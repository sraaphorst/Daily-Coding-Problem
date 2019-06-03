#!/usr/bin/env python3
# day047.py
# By Sebastian Raaphorst, 2019.


import hypothesis.strategies as st
from hypothesis import given
from typing import List


def brute_force_profit(arr: List[int]) -> int:
    """
    Brute force O(n^2) approach to find the largest jump in a list.
    Note that there must be at least two entries, and a possible profit.
    :param arr: the array of stock values
    :return: the maximum profit that could be made per stock

    >>> brute_force_profit([])
    0
    >>> brute_force_profit([0])
    0
    >>> brute_force_profit([1, 4 ,2, 6, 3])
    5
    >>> brute_force_profit([9, 11, 8, 5, 7, 10])
    5
    """
    if len(arr) < 2:
        return 0

    val = max([arr[y] - arr[x] for x in range(len(arr)) for y in range(x+1, len(arr)) if x <= y])
    return max(0, val)


def efficient_profit(arr: List[int]) -> int:
    """
    More efficient O(n) approach to finding the largest jump in a list.
    :param arr: the array of stock values
    :return: the maximum profit that could be made per stock

    >>> efficient_profit([])
    0
    >>> efficient_profit([0])
    0
    >>> efficient_profit([1, 4 ,2, 6, 3])
    5
    >>> efficient_profit([9, 11, 8, 5, 7, 10])
    5
    """
    # Keep track of the smallest element seen so far.
    if len(arr) < 2:
        return 0

    smallest_elem = arr[0]
    biggest_diff = 0
    for candidate in arr[1:]:
        diff = candidate - smallest_elem
        if diff > biggest_diff:
            biggest_diff = diff

        if candidate < smallest_elem:
            smallest_elem = candidate

    return biggest_diff


@given(st.lists(st.integers()))
def test_stocks(arr):
    """
    Test the stock maximization functions.
    """
    bfp = brute_force_profit(arr)
    ep = efficient_profit(arr)
    assert(bfp == ep)
