#!/usr/bin/env python3
# day069.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given
from typing import List, Tuple
from functools import reduce
from operator import mul


def max_triple_product_brute_force(nums: List[int]) -> int:
    """
    A bare-bones O(n3) method to determine the largest product of three numbers in a list

    :param nums: the list of numbers
    :return: the highest prodict
    """
    if len(nums) < 3:
        return 0
    from itertools import combinations
    return max([reduce(mul, lst, 1) for lst in combinations(nums, 3)])


def max_triple(nums: List[int]) -> int:
    """
    There are all sorts of ways to speed this up and reduce memory footprint,
    but they are quite complicated and there are nany boundary cases
    (e.g. [100, 100, -1] < [-1, -1, -1].
    """
    # Not enough numbers.
    if len(nums) < 3:
        return 0

    # Split into pos, neg, and determine if zero.
    # I'm going to sort to make this easier; it's not necessary but more finicky.
    pos_nums = list(reversed(sorted([i for i in nums if i > 0]))) # 0 is highest
    has_zero = 0 in nums
    neg_nums = list(sorted([i for i in nums if i < 0])) #  0 is highest

    candidates = []
    if has_zero:
        candidates.append(0)

    # Best candidate amongst the all-positives, if possible.
    if len(pos_nums) >= 3:
        candidates.append(pos_nums[0] * pos_nums[1] * pos_nums[2])

    # Best candidate from amongst the mixed.
    if len(pos_nums) >= 1 and len(neg_nums) >= 2:
        candidates.append(pos_nums[0] * neg_nums[0] * neg_nums[1])

    if len(pos_nums) >= 2 and len(neg_nums) >= 1:
        candidates.append(pos_nums[0] * pos_nums[1] * neg_nums[0])

    if len(neg_nums) >= 3:
        candidates.append(neg_nums[0] * neg_nums[1] * neg_nums[3])

    # Best candidate if only negative numbers.
    if not pos_nums:
        if has_zero:
            candidates.append(0)
        elif len(neg_nums) > 3:
            candidates.append(neg_nums[0] * neg_nums[1] * neg_nums[2])

    val1 = max_triple_product_brute_force(nums)
    val2 = max(candidates) if candidates else 0
    if val1 != val2:
        print("Uhoh")
    return max(candidates) if candidates else 0

@given(st.lists(st.integers(min_value=-100,max_value=100),min_size=0,max_size=10))
def test_list(nums: List[int]):
    assert(max_triple_product_brute_force(nums) == max_triple(nums))

# max_triple([95, -99, -82])
# from random import Random
# r = Random()
# while (True):
#     example = st.lists(st.integers(min_value=-100, max_value=100), min_size=0, max_size= 10).example(r)
#     print("**************")ddd
#     print(example)
#     print(max_triple_product_brute_force(example))
#     print(max_triple(example))
#     assert(max_triple_product_brute_force(example) == max_triple(example))