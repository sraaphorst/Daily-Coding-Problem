#!/usr/bin/env python3
# day055_v1.py
# By Sebastian Raaphorst, 2019.

# My initial thought was to:
# 1. Take the URL, which would be in simple ASCII (i.e. base 7)
# 2. Encode it into base 10.
# 3. Encode that into base 62 and output the result.
#
# Then, to undo:
# 1. Take the base 62 string.
# 2. Decode it into base 10.
# 3. Decode that into base 7 ASCII and output the result.
#
# The problem with this approach is that the encodings needed for even short URLs and much longer than 6 digits,
# so this is not a feasible way to do this.

from hypothesis import strategies as st
from hypothesis import given


# First we need to take a base-7 (non-extended ASCII) string and convert it to a number.
def base7_to_10(s: str) -> int:
    """
    Take an ASCII string and convert it to its compact representation in base-10.
    :param s: the ASCII string
    :return: the number representing the ASCII string
    """
    num = 0
    for c in s:
        num = (num << 7) | ord(c)
    return num


# Next take a base-7 string and reverse it to non-extended ASCII
def base10_to_7(num: int) -> str:
    """
    Take a base 10 number and convert it to an ASCII string.
    :param num: the base 10 number
    :return: the ASCII string
    """
    s = ""
    while num:
        s += chr(num & 0x7F)
        num = num >> 7
    return s[::-1]


BASE_62 = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'


def base_10_to_arb(n: int, alphabet: str = BASE_62) -> str:
    """
    Given a number in base 10, convert it to an arbitrary base as specified by an alphabet.
    :param n: the base 10 number
    :param alphabet: the alphabet
    :return: the representation of the base 10 string in the alphabet
    """
    if not n:
        return alphabet[0]

    base = len(alphabet)
    rep = []

    while n:
        n, rm = divmod(n, base)
        rep.append(alphabet[rm])
    rep.reverse()
    return ''.join(rep)


def base_arb_to_10(n: str, alphabet: str = BASE_62) -> int:
    """
    Given a string representing a number in an arbitrary base as specified by an alphabet, convert it to base 10.
    :param n: the number in the alphabet
    :param alphabet: the alphabet
    :return: the representation of the string as base 10
    """
    if not n:
        return 0

    base = len(alphabet)
    slen = len(n)
    num = 0

    for idx, c in enumerate(n):
        num += alphabet.index(c) * (base ** (slen - (idx + 1)))
    return num


def encode(url: str) -> str:
    """
    Given a URL, encode it by converting it from ASCII to base 62.
    We should, by the requirements, cut it at 6 places, but it turns out this strategy is not sufficient.
    :param url: the URL to encode
    :return: the encoded URL

    >>> a1 = "the_goose_flies_at_night"
    >>> a2 = decode(encode(a1))
    >>> a1 == a2
    True
    """
    return base_10_to_arb(base7_to_10(url))


def decode(n: str) -> str:
    """
    Given an encoded URL, decode it by converting it from base 62 into ASCII.
    We should, by the requirements, cut it at 6 places, but it turns out this strategy is not sufficient.
    :param n: the encoded URL
    :return: the original URL
    """
    return base10_to_7(base_arb_to_10(n))


@given(st.lists(st.integers(min_value=32, max_value=126).map(chr)).map(' '.join))
def test_conversions(original: str):
    """
    Test the conversion from an ASCII string to a base-62 encoding and back.
    """
    enc = encode(original)
    dec = decode(enc)
    assert(dec == original)
