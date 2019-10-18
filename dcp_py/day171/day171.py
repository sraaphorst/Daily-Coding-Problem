#!/usr/bin/env python3
# day171.py
# By Sebastian Raaphorst


from typing import List, Union
from itertools import permutations


def find_starting_instances(string: str, words: List[str]) -> Union[None, List[int]]:
    """
    Given a list of words of the same length, find all the permutations of the concatenations of the words.
    For example: cat dog pie -> catdogpie, catpiedog, dogcatpie, dogpiecat, piecatdog, piedogcat
    Then given a string, find the indices of the concatenated words.

    Note that all characters must be lowercase strings.

    :param string: the string to search for concatenated words
    :param words: the list of words
    :return: the indices of the concatenated words, or None if none

    >>> find_starting_instances('dogcatcatcodecatdog', ['cat', 'dog'])
    [0, 13]
    >>> find_starting_instances('barfoobazitbyte', ['cat', 'dog']) is None
    True
    """

    # We must have words.
    if not words:
        return None

    # They must all be the same length (> 0).
    if len(set(len(w) for w in words)) != 1:
        return None

    # They must all be lowercase.
    if any(ch.isupper() for word in words for ch in word):
        return None

    # Now create all of the concatenations of words.
    perms = list(permutations(words))

    # We don't want lists: we want tuples for comparisons.
    tuples = [x + y for (x, y) in perms]
    indices = [string.find(x) for x in tuples]
    indices = [x for x in indices if x >= 0]

    if len(indices) == 0:
        return None
    return sorted(indices)
