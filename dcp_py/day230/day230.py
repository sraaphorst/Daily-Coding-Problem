#!/usr/bin/env python3
# day230.py
# By Sebastian Raaphorst, 2019.

from functools import lru_cache
from typing import List, Mapping


@lru_cache(maxsize=None)
def egg_drop(n: int, k: int) -> int:
    """
    What is the minimum number of trials we need to drop eggs to determine which floors of a building are safe for
    dropping eggs, given n eggs and k floors?
    :param n: number of eggs
    :param k: number of floors
    :return: the minimum number of trials

    >>> egg_drop(1, 5)
    5
    >>> egg_drop(2,36)
    8
    """
    # Base cases.
    # If we have one egg, we need to try each floor.
    if n == 1:
        return k
    # If we have one floor, we need to try it.
    if k == 1 or k == 0:
        return k

    # Drop an egg from floor x:
    # 1. If it breaks, then we know the floor is <= x, and we have n-1 eggs left to do it, so E(n-1, x).
    # 2. If it doesn't break, then we know the floor is > x (which means k-x floors to try( and we have n eggs left
    #    to do it, so E(n, k-x).
    return 1 + min([max(egg_drop(n-1, x-1), egg_drop(n, k-x)) for x in range(1,k+1)])


def floor_sequence(k: int) -> List[int]:
    """
    Given a set number of floors, how many trials do we need for x eggs.
    Note that this reaches a constant very quickly. In the doctests example below, having more than five eggs offers
    no benefits for 300 floors.
    :param k: the number of floors
    :return: a list where position i represents the number of trials we need for i+1 eggs.

    >>> floor_sequence(300)
    [300, 24, 13, 10, 9]
    """
    trials = []
    prev = -1
    n = 1
    while True:
        curr = egg_drop(n, k)
        if curr == prev:
            break
        trials.append(curr)
        n += 1
        prev = curr
    return trials


def egg_sequence(n: int) -> Mapping[int, int]:
    """
    Given a set number of eggs, for a given number of floors, how many trials do we need?
    Note that this function grows very slowly so we return a map m where, for an entry m[k] = t, t is the minimum
    number of floors where n eggs will suffice for k floors.
    :param n: the number of eggs
    :return: a map indicating the number of floors and the number of trials needed using n eggs

    >>> egg_sequence(4)
    {1: 1, 2: 2, 4: 3, 8: 4, 16: 5, 31: 6, 57: 7, 99: 8, 163: 9, 256: 10}
    """
    trials = {}
    prev = -1
    k = 1
    while k <= 300:
        curr = egg_drop(n, k)
        if curr != prev:
            trials[k] = curr
        k += 1
        prev = curr
    return trials
