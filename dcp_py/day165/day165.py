#!/usr/bin/env python3
# day165.py
# By Sebastian Raaphorst, 2019


from typing import List


def right_cones(array: List[int]) -> List[int]:
    """
    We have an array, say 3 4 9 6 1.
    We want to return a new array where 3 is replaced by the number of number of smaller elements of 3 (1).
    We want to return a new array where 4 is replaced by the number of number of smaller elements of 4 (1).
    We want to return a new array where 9 is replaced by the number of number of smaller elements of 9 (6 1).
    We want to return a new array where 9 is replaced by the number of number of smaller elements of 6 (1).
    We want to return a new array where 9 is replaced by the number of number of smaller elements of 1 (0).

    Result: 1 1 2 1 0

    :param array: The initial array.
    :return: the array of smaller elements
    """
    return []