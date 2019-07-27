#!/usr/bin/env python3


def rotate_strings(a: str, b: str) -> bool:
    """
    Determine if you can rotate a string to get another string.
    :param a: first string
    :param b: second string
    :return: True if the rotation is possible, and False otherwise.

    >>> rotate_strings('abcde', 'cdeab')
    True
    >>> rotate_strings('abc', 'acb')
    False
    """
    if len(a) != len(b):
        return False
    if set(a) != set(b):
        return False
    for i in range(len(b)):
        if a == b[i:] + b[:i]:
            return True
    return False
