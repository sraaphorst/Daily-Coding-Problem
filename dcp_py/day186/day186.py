#!/bin/env python3

from typing import List, Tuple, Set, Generator
from itertools import combinations


def minimal_pairs(elems: List[int]) -> int:
    """
    Given an array, divide the array into two so subarrays so that the the difference betweeen the sum of the subarrays
    is as small as possible.
    :param elems: the arrray
    :return: the smallest smallest sum of the subarrays

    >>> minimal_pairs([5, 10, 15, 20, 25])
    5
    """
    n = len(elems)

    minimal_pairs = None

    # Generate all combinatorial lists from 0 to n.
    for r in range(n+1):
        for indices in combinations(range(n), r):

            # Generate the first set.
            set1 = [elems[i] for i in indices]
            set2 = [e for e in elems if e not in set1]
            diff = abs(sum(set1) - sum(set2))
            if minimal_pairs is None or minimal_pairs > diff:
                minimal_pairs = diff

    return minimal_pairs

