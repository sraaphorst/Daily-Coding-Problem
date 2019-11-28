#!/usr/bin/env python3
# day 235.py
# By Sebastian Raaphorst, 2019.

from typing import List, Tuple
from hypothesis import strategies as st
from hypothesis import given


def find_max_min(list: List[int]) -> Tuple[int, int, int]:
    """
    Find the maximum and minimum of a list of n elements in at most 2(n-2) comparisons.
    We do this by first running a tournament, i.e. pairing up every element of the list to get n/2 pairs, with one
    element possibly left over in the case of a list of odd length.

    We can then run max over each of these n/2 pairs to partition the list into two lists of n/2, with:
    * one being the candidates for maximum element
    * one being the candidates for minimum element
    If the list was odd, append the odd element to each of these partitions.

    Then iterate over the lists in the traditional way to find the maximum / minimum, which requires (n/2) - 1
    comparisons for each list.

    This strategy results in less than 2(n-2) comparisons for any list of length >= 4.

    :param list: the list of elements
    :return: the maximum element, the minimum element, and the number of comparisons made.
    """

    # Divide the list in pairs as best we can and find the max / min of each pair, i.e. perform a tournament.
    maxs = []
    mins = []
    comparisons = 0
    for i in range(0, len(list)-1, 2):
        if list[i] > list[i+1]:
            maxs.append(list[i])
            mins.append(list[i+1])
        else:
            maxs.append(list[i+1])
            mins.append(list[i])
        comparisons += 1

    if len(list) % 2 == 1:
        maxs.append(list[-1])
        mins.append(list[-1])

    # Now we can process each sublist individually in the usual way done to find max / min.
    max_elem, min_elem = maxs[0], mins[0]
    for elem in maxs[1:]:
        if elem > max_elem:
            max_elem = elem
        comparisons += 1
    for elem in mins[1:]:
        if elem < min_elem:
            min_elem = elem
        comparisons += 1

    return max_elem, min_elem, comparisons


@given(st.lists(st.integers(), min_size=4))
def test_comparisons(list: List[int]):
    max_comparisons = 2 * (len(list) - 2)
    max_elem, min_elem, comparisons = find_max_min(list)
    assert max_elem == max(list)
    assert min_elem == min(list)
    assert comparisons <= max_comparisons

