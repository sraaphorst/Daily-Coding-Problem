#!/usr/bin/env python3
# day069.py
# By Sebastian Raaphorst, 2019.

from typing import List, Tuple
from functools import reduce
from operator import mul


def max_triple_product(nums: List[int]) -> int:
    """
    A bare-bones O(n3) method to determine the largest product of three numbers in a list

    :param nums: the list of numbers
    :return: the highest prodict
    """
    if len(nums) < 3:
        return 0
    from itertools import combinations
    return max([reduce(mul, lst, 1) for lst in combinations(nums, 3)])
