#!/usr/bin/env python3
# day159.py
# By Sebastian Raaphorst

# Try out the Optional package.
from optional import Optional


def duplicated_search(string: str) -> Optional:
    """
    Search for the first duplicated character in a string and return it if one exists.
    Uses dict / hash map, so should run in O(n).

    :param string: the string which to search
    :return: the first duplicated character if one exists, and None otherwise

    >>> duplicated_search('acbbac')
    Optional.of('b')

    >>> duplicated_search('abcdef')
    Optional.empty()
    """
    chr_dct = {}
    for s in string:
        if s in chr_dct:
            return Optional.of(s)
        chr_dct[s] = 1
    return Optional.empty()

