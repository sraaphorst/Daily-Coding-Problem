#!/usr/bin/env python3
# day102.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given
from typing import List, Optional


def brute_force_search(elements: List[int], k: int) -> Optional[List[int]]:
    """
    This is a brute-force search of a contiguous sequence of subelements of a list that sum to k.
    :param elements: the elements of the list
    :param k: the desired sum
    :return: the sublist

    >>> brute_force_search([1, 2, 3, 4, 5], 9)
    [2, 3, 4]
    >>> brute_force_search([1, 3, 4], 5) is None
    True
    """
    for start in range(len(elements)):
        for end in range(start, len(elements)):
            if sum(elements[start:end]) == k:
                return elements[start:end]

    # Failure to find.
    return None


def linear_search(elements: List[int], k: int) -> Optional[List[int]]:
    """
    Thiis is a linear search to find a contiguous sequence of subelements of a list that sum to k.
    :param elements: the elements of the list
    :param k: the desired sum
    :return: the sublist

    >>> linear_search([1, 2, 3, 4, 5], 9)
    [2, 3, 4]
    >>> linear_search([1, 3, 4], 5) is None
    True
    """
    start = 0
    current_sum = 0
    for end in range(len(elements)):
        if current_sum == k:
            return elements[start:end]

        # Extend the list by another element. If this puts us over k, remove elements until we are no longer above k.
        current_sum += elements[end]
        while current_sum > k:
            current_sum -= elements[start]
            start += 1

    # Failure to find.
    return None


@st.composite
def elements_and_sum(draw):
    elements = draw(st.lists(st.integers(min_value=0, max_value=20), min_size=0, max_size=100))
    k = draw(st.integers(min_value=1, max_value=100))
    return elements, k


@given(elements_and_sum())
def test_it(input: (List[str], int)):
    elements, k = input
    assert brute_force_search(elements, k) == linear_search(elements, k)
