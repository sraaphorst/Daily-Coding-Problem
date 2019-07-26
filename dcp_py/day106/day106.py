#!/usr/bin/env python3

from hypothesis import strategies as st
from hypothesis import given
from typing import List


def brute_force_run(steps: List[int]) -> bool:
    """
    This is a brute-force naive approach: starting at the first position, recursively try all reachable positions
    and see if we can ultimately make it to the end.
    :param steps: the list of hops we can make from each position
    :return: True if we can reach the end, and False otherwise.

    >>> brute_force_run([])
    False
    >>> brute_force_run([1])
    True
    >>> brute_force_run([0])
    True
    >>> brute_force_run([2, 0, 1, 0])
    True
    >>> brute_force_run([1, 1, 0, 1])
    False
    """
    # No steps means no movement: fail.
    if not steps:
        return False

    end_pos = len(steps) - 1

    def from_position(pos: int) -> bool:
        """
        Determine if we can reach the end from position pos. This is where the recursion happens.
        :param pos: the current position
        :return: True if we can reach the end, and False otherwise
        """
        if pos >= end_pos:
            return True
        elif steps[pos] == 0:
            return False
        else:
            for i in range(1, steps[pos] + 1):
                if from_position(pos + i):
                    return True
        return False

    return from_position(0)


def dynamic_programming_run(steps: List[int]) -> bool:
    """
    Use dynamic programming to build an array to determine the minimum number of hops (hops[i]) to get from position
    0 to position i.
    :param steps: the list of hops we can make from each position
    :return: True if we can reach the end, and False otherwise.

    >>> dynamic_programming_run([])
    False
    >>> dynamic_programming_run([1])
    True
    >>> dynamic_programming_run([0])
    True
    >>> dynamic_programming_run([2, 0, 1, 0])
    True
    >>> dynamic_programming_run([1, 1, 0, 1])
    False
    """
    # No steps means no movement: fail.
    if not steps:
        return False

    def accessible(n):
        """
        Determine if position n is accessible from position 0.
        :param n: the position
        :return: True if accessible, and false otherwise.
        """

        if n == 0:
            return True
        if steps[0] == 0:
            return False

        hops = [False] * (len(steps) + 1)
        hops[0] = True

        # Find for all positions [1,n] if they can be accessed.
        for i in range(1, n + 1):
            # Check position j < i to see if it can reach position i.
            for j in range(i):
                if i <= j + steps[j] and hops[j]:
                    hops[i] = True
                    break
        return hops[n]

    return accessible(len(steps)-1)


@given(st.lists(st.integers(min_value=0, max_value=5), max_size=100))
def test_it(steps: List[int]):
    assert brute_force_run(steps) == dynamic_programming_run(steps)
