#!/usr/bin/env python3
# day028.py
# By Sebastian Raaphorst, 2019.

import hypothesis.strategies as st
from hypothesis import assume, given
from hypothesis.strategies import integers as ints
from typing import List


def brute_force_tree(costs: List[List[int]]) -> int:
    """
    Brute force approach to picking the house colours.
    :param costs: the costs per house by colour
    :return: the minimum cost
    """
    return brute_force_tree_aux(0, costs, -1, 0)


def brute_force_tree_aux(houseNumber: int, costs: List[List[int]], prevColor: int, currentCost: int) -> int:
    """
    Brute force approach to picking the house colours.
    This creates the entire search tree and picks the smallest value. The search space is huge.
    :param houseNumber: the number of house to pick
    :param costs: the costs per house by colour
    :param prevColor: the previous colour used
    :param currentCost: the current cost so far
    :return: the minimum cost in this subtree
    """
    # If we are done, return the cost.
    if houseNumber == len(costs):
        return currentCost

    # Try colouring this house all of the colours and pick the minimal cost.
    return min([brute_force_tree_aux(houseNumber + 1, costs, color, currentCost + cost)
                for (color, cost) in enumerate(costs[houseNumber]) if color != prevColor])


def dynamic_approach(costs: List[List[int]]) -> int:
    """
    The dynamic approach.
    We arrange a table of size houses by colours, and pick the minimum accumulating cost at each round.
    :param costs: the costs per house by colour.
    :return: the minimum cost
    """
    num_houses = len(costs)
    num_colours = len(costs[0])

    cost_table = [[0] * num_colours for _ in range(num_houses)]

    # Initialize the first row.
    for c in range(num_colours):
        cost_table[0][c] = costs[0][c]

    # Dynamically proceed.
    for h in range(1, num_houses):
        for c in range(num_colours):
            cost_table[h][c] = min([cost_table[h-1][cp] + costs[h][c] for cp in range(num_colours) if cp != c])

    for i in cost_table:
        print(i)
    return min(cost_table[num_houses-1])


# Generate a table of 3 to 7 rows of 3 to 6 columns.
# Represents cost of 3 to 7 houses using 3 to 6 colours.
# Deeper than this cannot be handled efficiently by the brute force approach.
same_len_lists = ints(min_value=3, max_value=6).flatmap(lambda n: st.lists(st.lists(ints(), min_size=n, max_size=n), min_size=3, max_size=7))


@given(same_len_lists)
def test_my_func(lists):
    assert(brute_force_tree(lists) == dynamic_approach(lists))
