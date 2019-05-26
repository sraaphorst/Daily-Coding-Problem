#!/usr/bin/env python3
# day045.py
# By Sebastian Raaphorst, 2019.

# We test this using the Python hypothesis API and doctests.

import hypothesis.strategies as st
from hypothesis import given


def is_palindrome(p: str) -> bool:
    """
    Determine if a string is a palindrome.
    :param p: the string
    :return: True if a palindrome, and false otherwise

    >>> is_palindrome("can")
    False
    >>> is_palindrome("cac")
    True
    >>> is_palindrome('')
    True
    >>> is_palindrome('a')
    True
    """
    return p == p[::-1]


def brute_force_palindrome(s: str) -> str:
    """
    Brute force method to find palindromes.
    :param s: the string containing the palindrome
    :return: the first longest palindrome

    >>> brute_force_palindrome('')
    ''
    >>> brute_force_palindrome('a')
    'a'
    >>> brute_force_palindrome('dealapoopadoop')
    'apoopa'
    """
    if not s:
        return ''
    n = len(s)
    # All substrings
    substrings = [s[i:j] for i in range(n) for j in range(i, n+1)]
    palindromes = [p for p in substrings if is_palindrome(p)]
    return max(palindromes, key=lambda x: len(x))


def dynamic_palindrome(s: str) -> str:
    """
    Assume N = len(s).
    We make an NxN table T that we fill in bottom-up, where:
    1. table[i][j] = the substring is a palindrome
    :param s: the longest palindrome
    :return: the first longest palindrome

    >>> dynamic_palindrome('a')
    'a'
    >>> dynamic_palindrome('dealapoopadoop')
    'apoopa'
    """
    n = len(s)

    # Keep track of the maximum length.
    max_length = 1

    # All substrings of length 1 are palindromes.
    table = [[i == j for j in range(n)] for i in range(n)]

    # Strings of length 2 are palindromes if their character is equal to the first.
    start = 0
    for i in range(n - 1):
        if s[i] == s[i + 1]:
            table[i][i + 1] = True
            start = i
            max_length = 2

    # Process for lengths greater than 2.
    for k in range(3, n + 1):
        for i in range(n - k + 1):
            j = i + k - 1

            if table[i + 1][j - 1] and s[i] == s[j]:
                table[i][j] = True

                if k > max_length:
                    start = i
                    max_length = k

    return s[start:start + max_length]


# To configure this, add a configuration for pytest.
# We can Limit the characters so that the likelihood of palindromes is higher, and they will be longer:
# @given(st.text('abcdefg'))
@given(st.text())
def test_palindromes(s):
    """
    Test the two palindrome functions. They may not return the same palindromes
    if they contain multiple palindromes of maximal length so we ensure that both are palindromes,
    the lengths are the same, and the string contains both.
    """
    bfp = brute_force_palindrome(s)
    assert(is_palindrome(bfp))
    dp = dynamic_palindrome(s)
    assert(is_palindrome(dp))
    assert(len(bfp) == len(dp))
    assert(bfp in s)
    assert(dp in s)

