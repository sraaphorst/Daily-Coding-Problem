#!/usr/bin/env python3
# ch12_2.py
# By Sebastian Raaphorst, 2019.

from functools import lru_cache


def count_decodings(s: str) -> int:
    """
    Code the letters A-Z as 1-26 and then determine the number
    of possible decodings. Note that anything that starts with a 0
    has no decodings. This essentially uses a tree by decomposing and recursing,
    and includes dynamic programming through memoization.

    :param s: the encoded string
    :return: the number of decodings

    >>> count_decodings('0')
    0
    >>> count_decodings('111') # [11,1], [1,11], [1,1,1]
    3
    >>> count_decodings('1212') # [1,2,1,2], [1,2,12], [1,21,2], [12,1,2], [12,12]]
    5
    """
    # Memoized aux function for dynamic programming.
    @lru_cache(maxsize=None)
    def aux(sp: str) -> int:
        # Base case: starts with 0, no encoding.
        # Base case: empty string, so no message.
        if len(sp) == 0:
            return 1
        if sp[0] == '0':
            return 0

        num_encodings = 0

        # For a single character encoding 1-9, (a-i) and recurse.
        if len(sp) >= 1:
            num_encodings += 1 * aux(sp[1:])

        # For a value double character 10 - 26 (h-z):
        if len(sp) >= 2 and sp[:2] <= '26':
            num_encodings += 1 * aux(sp[2:])

        return num_encodings

    return aux(s)


a = count_decodings('11122111')
a = count_decodings('1212')
print(a)