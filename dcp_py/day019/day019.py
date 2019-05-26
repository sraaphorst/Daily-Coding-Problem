#!/usr/bin/env python3
# day028.py
# By Sebastian Raaphorst, 2019.

from typing import List


def brute_force_tree(houseNumber: int, costs: List[List[int]], prevColor: int, currentCost: int) -> int:
    # If we are done, return the cost.
    if houseNumber == len(costs):
        return currentCost

    # Try colouring this house all of the colours and pick the minimal cost.
    return min([brute_force_tree(houseNumber + 1, costs, color, currentCost + cost)
                for (color, cost) in enumerate(costs[houseNumber]) if color != prevColor])


def dynamic_approach(costs: List[List[int]]) -> int:
    """
    The dynamic approach.
    We arrange a table of size houses by colours, and pick the minimum accumulating cost at each round.
    :param costs: the costs per house by colour.
    :return: the minimal cost
    """
    num_houses = len(costs)
    num_colours = len(costs[0])

    cost_table = [[0] * colours