#!/usr/bin/env python
# day143.py
# By Sebastian Raaphorst, 2019.

from hypothesis import given
from hypothesis import strategies as st
from typing import List, Tuple


def check_pivot(lst: List[int], pivot: int) -> bool:
    first_pivot = lst.index(pivot)
    last_pivot = len(lst) - list(reversed(lst)).index(pivot)
    for elem in lst[:first_pivot]:
        if elem >= pivot:
            return False
    for elem in lst[first_pivot:last_pivot]:
        if elem != pivot:
            return False
    for elem in lst[last_pivot:]:
        if elem < pivot:
            return False
    return True


def brute_force(num_lst: List[int], pivot: int) -> List[int]:
    """
    Given a list and a pivot, sort so that all elements to the left of the pivot(s) are less than the pivot
    and all elements to the right of the pivot(s) are greater than the pivot. Runs in O(3n) but not constant storage.

    :param num_lst: the list of numbers
    :param pivot: the pivot element
    :return: a list as described above

    >>> brute_force([10, 5, 3, 11, 10, 2, 4, 12], 10)
    [5, 3, 2, 4, 10, 10, 11, 12]
    """
    return [x for x in num_lst if x < pivot] + [x for x in num_lst if x == pivot] + [x for x in num_lst if x > pivot]


def pivot(lst: List[int], pivot: int) -> List[int]:
    """
    Given a list and a pivot, sort so that all elements to the left of the pivot(s) are less than the pivot
    and all elements to the right of the pivot(s) are greater than the pivot. This is not a stable partitioning
    like in the brute force technique, but should run in O(n) instead of O(3n).

    :param lst: the list of numbers
    :param pivot: the pivot element
    :return: a list as described above

    >>> check_pivot(pivot([1, 0], 0), 0)
    True
    >>> check_pivot(pivot([10, 5, 3, 11, 10, 2, 10, 4, 12], 10), 10)
    True
    """
    curr = 0
    right = len(lst) - 1
    mid = 0
    step = True

    while curr <= right - mid:
        if mid > 0 and step:
            lst[curr] = lst[curr + mid]
        step = True
        if lst[curr] > pivot:
            if lst[right] == pivot:
                lst[right] = lst[curr]
                right -= 1
                mid += 1
                lst[curr] = lst[curr + mid]
            else:
                lst[curr], lst[right] = lst[right], lst[curr]
                right -= 1
                step = False
        elif lst[curr] == pivot:
            mid += 1
        else:
            curr += 1

    while mid > 0:
        mid -= 1
        lst[curr] = pivot
        curr += 1

    return lst


@st.composite
def list_and_elem(draw, elements=st.integers(min_value=0, max_value=20)) -> (List[int], int):
    xs = draw(st.lists(elements, min_size=1, max_size=100))
    elem = draw(st.sampled_from(xs))
    return xs, elem


@given(list_and_elem())
def test(items: Tuple[List[int], int]):
    lst, elem = items
    assert check_pivot(pivot(lst, elem), elem)