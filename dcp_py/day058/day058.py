#!/usr/bin/env python3
# day058.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given
from typing import List, Optional


def brute_force(arr: List[int], value: int) -> Optional[int]:
    """
    A brute force method that runs in O(n) and thus does not satisfy the question's requirements.
    We use it for comparison.
    :param arr: the rotated array
    :param value: the value to find in the array
    :return: the index of value, or None if it does not exist

    >>> brute_force([2, 3, 0, 1], 2)
    0
    >>> brute_force([-1, 0], 0)
    1
    >>> brute_force([0, 1, 2], 1)
    1
    >>> brute_force([13, 18, 25, 2, 8, 10], 8)
    4
    >>> brute_force([13, 18, 25, 2, 8, 10], 3) is None
    True
    """
    if arr is None or not arr or value is None:
        return None
    try:
        return arr.index(value)
    except ValueError:
        return None


def quicksort_technique(arr: List[int], value: int) -> Optional[int]:
    """
    Solves the problem by assuming that this is a step in a quicksort with max on left, min on right.
    The rotation of the sorted array generates a pivot, which is an element such that the next element
    is loweer. In the example provided, [13, 18, 25, 2, 8, 10], the pivot is 25 as the next element is
    lower than 25, namely 2.

    Once we have the pivot, we know the arrays on either side are sorted in increasing order, so we can
    use binary search to determine if / where value is in the appropriate half.
    :param arr: the rotated array
    :param value: the value to find in the array
    :return: the index of value, or None if it does not exist

    >>> quicksort_technique([1, 2, 3, 4, 0], 0)
    4
    >>> quicksort_technique([2, 3, 0, 1], 2)
    0
    >>> quicksort_technique([-1, 0], 0)
    1
    >>> quicksort_technique([0, 1, 2], 1)
    1
    >>> quicksort_technique([13, 18, 25, 2, 8, 10], 8)
    4
    >>> quicksort_technique([13, 18, 25, 2, 8, 10], 3) is None
    True
    """

    if arr is None or not arr or value is None:
        return None

    # Finding the pivot point.
    def find_pivot(low: int = 0, high: int = len(arr)-1) -> Optional[int]:
        """
        Finds the pivot (if any) in the rotated sequence for indices low through high inclusive.

        :param low: the starting point
        :param high: the ending point
        :return: the index of the pivot, or None if the array is ordered
        """

        # There is no pivot: the array is sorted.
        if low >= high:
            return None
        if low == high:
            return low

        mid = (low + high) // 2
        if mid < high and arr[mid] > arr[mid + 1]:
            return mid
        elif mid > low and arr[mid] < arr[mid - 1]:
            return mid-1
        elif arr[low] > arr[mid]:
            return find_pivot(low, mid - 1)
        return find_pivot(mid + 1, high)

    def binary_search(low: int, high: int) -> Optional[int]:
        """
        Perform a standard binary search on a sorted portion of array from indices low to high inclusive.
        :param low: the starting point
        :param high: the ending point
        :return: the index of value, or None if it does not appear
        """
        if low > high:
            return None

        mid = (low + high) // 2
        if arr[mid] == value:
            return mid
        elif arr[mid] > value:
            # Search the lower part.
            return binary_search(low, mid-1)
        else:
            # Search the higher part.
            return binary_search(mid+1, high)

    pivot = find_pivot()

    # If there was no pivot to find, the array was not sorted.
    if pivot is None:
        return binary_search(0, len(arr)-1)

    # Otherwise, determine which half of the pivot to search.
    if arr[pivot] == value:
        return pivot
    elif arr[0] <= value:
        return binary_search(0, pivot-1)
    else:
        return binary_search(pivot+1, len(arr)-1)


@st.composite
def gen_input(draw) -> (List[int], int):
    """
    Generate a sorted array and then rotate it by a specified amount.
    Generate a value and see if it is in the rotated array
    :param draw: the hypothesis object needed to draw from the strategies
    :return: a pair representing the array of text and the line length
    """
    # Unsure why sometimes we get a newline at the end of the elements in text.
    arr = draw(st.lists(st.integers(min_value=0, max_value=50), min_size=2, max_size=50, unique=True).map(sorted))
    rot = draw(st.integers().map(lambda x: x % len(arr)))
    value = draw(st.integers(min_value=-50, max_value=50))
    return arr[rot:] + arr[:rot], value


@given(gen_input())
def test_searches(input: (List[int], int)):
    (arr, value) = input
    bf = brute_force(arr, value)
    qs = quicksort_technique(arr, value)
    assert(bf == qs)
