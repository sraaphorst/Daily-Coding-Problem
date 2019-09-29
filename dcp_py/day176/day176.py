#!/usr/bin/env python3
# day176.py
# By Sebastian Raaphorst, 2019.


def injective_map_exists(str1: str, str2: str) -> bool:
    """
    Determine if there is an injective (one-to-one) mapping from the characters in str1 to the characters in str2.

    :param str1: the characters in str1
    :param str2: the chracaters in str2
    :return: True if an injective map exists, and False otherwise.

    >>> injective_map_exists("abc", "bcd")
    True
    >>> injective_map_exists("foo", "bar")
    False
    """
    return len(set(str1)) >= len(set(str2))