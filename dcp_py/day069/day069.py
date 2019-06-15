#!/usr/bin/env python3
# day069.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given
from typing import List, Optional
from functools import reduce
from operator import mul


def max_triple_product_bare_bones(nums: List[int]) -> int:
    """
    A bare-bones O(n3) method to determine the largest product of three numbers in a list

    :param nums: the list of numbers
    :return: the highest prodict
    """
    from itertools import combinations
    return max([reduce(mul, lst, 1) for lst in combinations(nums, 3)])


def max_triple_product(nums: List[int]) -> int:
    """
    A bare-bones O(n3) method to determine the largest product of three numbers in a list

    :param nums: the list of numbers
    :return: the highest prodict
    """

    def lte(a: int, b: Optional[int]) -> bool:
        if b is None or a <= b:
            return True

    def gte(a: int, b: Optional[int]) -> bool:
        if b is None or a >= b:
            return True

    min1, min2 = None, None
    max1, max2, max3 = None, None, None

    # Iterate over the numbers.
    for n in nums:
        if lte(n, min1):
            min2, min1 = min1, n
        elif lte(n, min2):
            min2 = n

        if gte(n, max1):
            max3, max2, max1 = max2, max1, n
        elif gte(n, max2):
            max3, max2 = max2, n
        elif gte(n, max3):
            max3 = n
    return max(min1 * min2 * max1, max1 * max2 * max3)


@given(st.lists(st.integers(min_value=-100, max_value=100), min_size=3, max_size=100))
def test_it(nums: List[int]):
    assert (max_triple_product_bare_bones(nums) == max_triple_product(nums))

