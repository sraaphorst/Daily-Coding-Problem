#!bin/env python3
# By Sebastian Raaphorst, 2019.

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

    @param s: the string to check
    @param k: the number of distinct allowable colours in the string.
    @return: the first lexical maximum substring of s containing at most k distinct colours

    >>> longestSubstring('abcba', 2)
    'bcb'
    >>> longestSubstring('abcabcdzabcdeabcdefyabcdefgab', 5)
    'abcabcdzabcd'
    """

    n = len(s)

    # Nothing to sort.
    if not s:
        return ""

    # We can sort it all.
    elif len(s) <= k:
        return s

    # First thiing out of place.
    elif k <= 1:
        candidates = [i for i in range(n) for j in range(i, n) if s[j] > s[i]]

        # All sorted.
        if len(candidates) == 0:
            return None
        else:
            return s[candidates[0]]

    # Now that the sanity checks are done, proceed with the algorithm.
    def aux(left: int = 0, best_left: int = None, best_right: int = None) -> str:
        """
        We start at position left and try to build a sorting frame as far right as possible until we reach the point
        where we cannot continue due to k.

        During the process, we determine the next starting position, if there is one, and keep track of the max / min.

        @param left: the starting position for this iteration.
        @param best_left: the best left position seen so far.
        @param best_right: the best right position so far.
        @return: the longest sorting window.
        """
        if left is None:
            return s[best_left:best_right]

        first = s[left]
        chars_seen = set()
        chars_seen.add(first)
        next_try = None

        # Advance past all s[0] to get to the next position to try.
        right = left
        while right < n and s[right] == first:
            right += 1
        next_try = right

        # We are at the end, so proceed.
        if right == n:
            if best_right is None or best_left is None or right - left > best_right - best_left:
                best_left, best_right = left, right
            return s[best_left:best_right]

        # Continue to add elements until:
        # 1. We reach the k restriction
        # 2. We reach the n boundary
        # at this point, we take the max with the value calculated from the next starting position and return.
        while right < n and (len(chars_seen) < k or (len(chars_seen) == k and s[right] in chars_seen)):
            chars_seen.add(s[right])
            right += 1

        if best_right is None or best_left is None or right - left > best_right - best_left:
            best_left, best_right = left, right

        # Calculate the candidate for next (which causes a recursion of cascading computations, of which the best is
        # kept), and compare to our current best and return the best.
        candidate = aux(next_try, best_left, best_right)
        if len(candidate) > best_right - best_left:
            return candidate
        else:
            return s[best_left:best_right]

    return aux()


@given(st.tuples(st.from_regex("[a-eA-E0-9]{5,}", fullmatch=True), st.integers(min_value=2, max_value=10)))
def testwindow(inp):
    s, k = inp
    assert bruteForceLongestSubstring(s, k) == longestSubstring(s, k)

