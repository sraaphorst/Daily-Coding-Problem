#!/usr/bin/env python3
# day070.py
# By Sebastian Raaphorst, 2019.

from typing import Iterator, Tuple


def perfect_brute_iterator() -> Iterator[Tuple[int, int]]:
    """
    Define the ith perfect number using an iterator.
    In this case, the ith perfect number is the number where the individual digits add up to precisely 10.
    100, for example, and 1000 are not perfect numbers.
    """
    idx = 0
    elem = 1

    # Increment until we have a number whose digits sum to 1.
    while True:
        s = sum([int(i) for i in str(elem)])
        if s == 10:
            idx += 1
            yield(idx, elem)
        elem += 1
