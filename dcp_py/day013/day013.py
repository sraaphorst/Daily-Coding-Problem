#!bin/env python3
# By Sebastian Raaphorst, 2019.

from typing import List, Mapping, Tuple
from hypothesis import given, strategies as st


def bruteForceLongestSubstring(s: str, k: int) -> str:
    """
    Check every substring to see if it is a substring of at most k distinct characters.
    As there are n(n+1)/2 substrings. Since we use Python set - which is in essence a hash map =
    to check the number of characters in a string in checkCharacters, all insert and size operations
    are O(1), and this the entire complexity of this method is O(n^2).
    @param s: the string to check
    @param k: the number of distinct allowable colours in the string.
    @return: the first lexical maximum substring of s containing at most k distinct colours

    >>> bruteForceLongestSubstring('abcba', 2)
    'bcb'
    >>> bruteForceLongestSubstring('abcabcdzabcdeabcdefyabcdefgab', 5)
    'abcabcdzabcd'
    """

    def checkCharacters(t: str) -> int:
        """
        Given a string, check how many distinct characters it has.
        @param t: the string to check
        @return: the number of unique characters.
        """
        return len(set(t))

    # Weed out some obnoxious or corner cases.
    if s is None or len(s) < 2 or k < 1:
        return None

    n = len(s)
    candidates = [s[i:j+1] for i in range(n) for j in range(i, n) if checkCharacters(s[i:j+1]) <= k]
    return max(candidates, key=len)


def longestSubstring(s: str, k: int) -> str:
    """
    We maintain a shifting window that moves from the left to the right, trying to hold
    the maximum number of characters that need to be sorted.

    When we reach the limit k, we move the left forward to make room for more expansion
    to the right.
    @param s: the string to check
    @param k: the number of distinct allowable colours in the string.
    @return: the first lexical maximum substring of s containing at most k distinct colours

    >>> bruteForceLongestSubstring('abcba', 2)
    'bcb'
    >>> bruteForceLongestSubstring('abcabcdzabcdeabcdefyabcdefgab', 5)
    'abcabcdzabcd'
    """
    # Weed out some obnoxious or corner cases.
    if s is None or len(s) < 2 or k < 1:
        return None

    n = len(s)
    left, right = 0, 0
    characters = set()
    bestLeft, bestRight = 0, -1

    for curr in range(n):
        charToAdd = s[curr]
        right = curr
        characters.add(charToAdd)

        # Otherwise, we have reached or are approaching a new maximal.
        # We check if it is the best height so far.
        if len(characters) <= k and right - left > bestRight - bestLeft:
            bestLeft, bestRight = left, right

        # If we have too many elements, shrink the window from the left.
        if len(characters) > k:
            # Drop the left-most character.
            # We must drop ALL of them, so start at right and then move left until we
            # reach the first one, which there is guaranteed to be there.
            charToDrop = s[left]
            left = right
            while s[left] != charToDrop:
                left -= 1
            left += 1
            characters.remove(charToDrop)

        # We may have moved out of bounds if right was at the end. If so, break.
        # Otherwise, continue to expand.
        if left >= n:
            break

    # This is inclusive on the right-most character.
    return s[bestLeft:bestRight+1]


if __name__ == '__main__':
    # My idea fails for this solution.
    ls = longestSubstring('0102300', 3)
    print(f"LS: {len(ls)}: **{ls}**")
    bf = bruteForceLongestSubstring('0102300', 3)
    print(f"BF: {len(bf)}: **{bf}**")


@given(st.tuples(st.from_regex("[a-eA-E0-9]{5,}", fullmatch=True), st.integers(min_value=2, max_value=10)))
def testwindow(inp):
    s, k = inp
    print("***********")
    print(s)
    print(k)
    assert bruteForceLongestSubstring(s, k) == longestSubstring(s, k)

