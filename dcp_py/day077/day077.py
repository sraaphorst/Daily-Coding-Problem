#!/usr/bin/env python3

from hypothesis import strategies as st
from hypothesis import given
from typing import List
from typing import Tuple


def merge_intervals(intervals: List[Tuple[int, int]]) -> List[Tuple[int, int]]:
    """
    Given a set of intervals, eliminate any overlaps and find the minimal set of intervals represented.
    :param intervals: a list of intervals, represented as tuples (a,b)
    :return: the reduced list of intervals

    >>> merge_intervals([(1, 3), (5, 8), (4, 10), (20, 25)])
    [(1, 3), (4, 10), (20, 25)]
    """
    reduced_intervals = []

    for interval in sorted(intervals):
        if len(reduced_intervals) == 0:
            reduced_intervals.append(interval)
        else:
            (interval_a, interval_b) = interval
            (prev_interval_a, prev_interval_b) = reduced_intervals[-1]

            # We have overlapping intervals, or possibly interval is contained in prev_interval
            if interval_a <= prev_interval_b:
                reduced_intervals.pop()
                reduced_intervals.append((prev_interval_a, max(interval_b, prev_interval_b)))
            else:
                reduced_intervals.append(interval)
    return reduced_intervals


def check_intervals(intervals: List[Tuple[int, int]]) -> bool:
    """
    Check intervals to make sure that they are sorted and non-intersecting.
    :param intervals: a list of intervals, represented as tuples (a,b)
    :return: True if the list is non-overlapping, and false otherwise.
    """
    for (interval1, interval2) in zip(intervals, intervals[1:]):
        if interval1[1] >= interval2[0]:
            return False
    return True


@given(st.lists(st.tuples(st.integers(min_value=-100, max_value=100), st.integers(min_value=-100, max_value=100)),
                min_size=1, max_size=100))
def test_it(intervals: List[Tuple[int, int]]):
    assert check_intervals(merge_intervals(intervals))
