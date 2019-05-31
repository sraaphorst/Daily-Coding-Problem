#!/usr/bin/env python3
# day049.py
# By Sebastian Raaphorst, 2019.

import hypothesis.strategies as st
from hypothesis import given
from typing import List


def brute_force_max_sum_subarray(array: List[int]) -> int:
    """
    A brute force O(n2) algorithm to solve the maximum subarray problem.
    :param array: the array to process
    :return: the maximum value amongst all consecutive subarrays

    >>> brute_force_max_sum_subarray([34, -50, 42, 14, -5, 86])
    137
    >>> brute_force_max_sum_subarray([-5, -1, -8, -9])
    0
    """
    if not array:
        return 0
    return max([sum(array[i1:i2]) for i1 in range(len(array)) for i2 in range(i1, len(array)+1)])


def kadane_algorithm(array: List[int]) -> int:
    """
    Use Kadane's algorithm to solve the max sum subarray problem.
    :param array: the array to process
    :return: the maximum value amongst all consecutive subarrays.

    >>> kadane_algorithm([34, -50, 42, 14, -5, 86])
    137
    >>> kadane_algorithm([-5, -1, -8, -9])
    0
    """
    current_max_ending = 0
    max_ending = 0
    for v in array:
        current_max_ending += v
        if max_ending < current_max_ending:
            max_ending = current_max_ending

        if current_max_ending < 0:
            current_max_ending = 0

    return max_ending


@given(st.lists(st.integers()))
def test_algs(arr: List[int]):
    """
    Test the subarray maximization algorithms.
    """
    bfa = brute_force_max_sum_subarray(arr)
    kaa = kadane_algorithm(arr)
    assert (bfa == kaa)
