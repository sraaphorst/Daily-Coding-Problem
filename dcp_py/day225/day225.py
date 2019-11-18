#!/usr/bin/env python3
# day225.py
# By Sebastian Raaphorst, 2019.


# These are variants of the Josephus problem J(n, k), where a group of n soldiers are lined up and every kth soldier is
# killed until there is only one remaining soldier, the survivor: the goal is to pick the position of the survivor.
# There is a constant, easy way to calculate the survivor for n = 2: we eliminate half the soldiers on each pass and
# arrive back at the first soldier.

# Thus, it is easy to see that for J(2^a, 2), 1 is always the winner.

# Consider 2^a + d soldiers where d < 2^a. After killing d soldiers, we end up in the looping technique where we
# eliminate half of the remaining soldiers each round and end up back at where the powers of 2 started. Thus,
# the dth noneliminated soldier is the survivor. This soldier is in position 2d + 1 (as we eliminate a soldier and
# then skip to the next with each elimination).

from functools import lru_cache
import hypothesis.strategies as st
from hypothesis import given


# k = 2 Josephus case
def josephus2(n: int) -> int:
    # Base cases
    if n <= 1:
        return 1

    h = int.bit_length(n) - 1
    remove_highest = (~(1 << h)) & n
    survivor = remove_highest << 1 | 1
    return survivor


# The general case, which uses recursion.
@lru_cache(maxsize=None)
def josephus(n: int, k: int) -> int:
    if n == 1:
        return 1

    else:
        # When we kill someone, we have n-1 people left, so this can be framed recursively.
        # However, the starting position has changed, which must be taken into account.
        # We now begin recursively at the person after the person we just killed with our new "first person",
        # which is k % n + 1.
        # Example: J(9,3)
        # 1 2 3 4 5 6 7 8 9
        # Eliminate 3:
        # 1 2 - 4 5 6 7 8 9
        # Recursive call:
        # (J(8,3) + 2) % 9 + 1 -> move ahead two squares (our next killer), mod 9 (number victims left), and then
        # move 1 square forward to our next victim.
        return (josephus(n - 1, k) + k - 1) % n + 1


@given(st.integers(min_value=1))
def test_j2(n: int):
    assert josephus2(n) == josephus(n, 2)
