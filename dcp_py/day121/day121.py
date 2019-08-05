#!/usr/bin/python3

def is_k_palindrome_rec(str1: str, str2: str, m: int, n: int):
    """"
    Determine if a string is a k-palindrome:
    A k-palindrome transforms into a palindrom by removing at most k ccharacters from it.

    To avoid exponential time, we use dynamic programming.
    """

    # Create a table to store the results of the subproblems.
    dp = [[0] * (n+1) for _ in range(n+1)]

    # Fill in the table in a bottom up manner.
    for i in range(m+1):
        for j in range(n+1):
            # If str1 is empty, our only valid option is to return all characters from str2 to get the palindrome.
            if not i:
                dp[i][j] = j
            # Same in reverse
            elif not j:
                dp[i][j] = i

            # If the characters are the same, ignore the last character and recurse for remaining string.
            elif  str1[i-1] == str2[j-1]:
                dp[i][j] = dp[i-1][j-1]

            # If the characters are different, remove and find the minimum.
            else:
                # First: remove from str1, second: remove from s2.
                dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1])

    return dp[m][n]


def is_k_palindrome(s, k):
    """
    Returns true if str is k-palindrome.
    """
    return is_k_palindrome_rec(s, s[::-1], len(s), len(s)) <= k * 2


if __name__ == '__main__':
    assert(is_k_palindrome('waterrfetawx', 2))
    assert(is_k_palindrome('abcdecba', 1))
    assert(not is_k_palindrome('abcdecab', 1))
