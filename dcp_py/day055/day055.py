#!/usr/bin/env python3
# day055.py
# By Sebastian Raaphorst, 2019.


# First we need to take a base-7 (non-extended ASCII) string and convert it to a number.
def base7_to_10(s: str) -> int:
    num = 0
    for c in s:
        num = (num << 7) | ord(c)
    return num


# Next take a base-7 string and reverse it to non-extended ASCII
def base10_to_7(num: int) -> str:
    s = ""
    while num:
        s += chr(num & 0x7F)
        num = num >> 7
    return s[::-1]


BASE_62 = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'


# Convert from base 10 to base arb.
def base_10_to_arb(n: int, alphabet: str = BASE_62) -> str:
    if not n:
        return alphabet[0]

    base = len(alphabet)
    rep = []

    while n:
        n, rm = divmod(n, base)
        rep.append(alphabet[rm])
    rep.reverse()
    return ''.join(rep)


# Convert from base arb to base 10.
def base_arb_to_10(n: str, alphabet: str = BASE_62) -> int:
    if not n:
        return 0

    base = len(alphabet)
    slen = len(n)
    num = 0

    for idx, c in enumerate(n):
        num += alphabet.index(c) * (base ** (slen - (idx + 1)))
    return num


def encode(url: str) -> str:
    v1 = base7_to_10(url)
    print(f"v1 = {v1}")
    v2 = base_10_to_arb(v1)
    print(f"v2 = {v2}")
    return v2


def decode(n: str) -> str:
    u1 = base_arb_to_10(n)
    print(f'u1 = {u1}')
    u2 = base10_to_7(u1)
    print(f'u2 = {u2}')
    return u2

#
# a = 1234567890
# print(a)
# b = base_10_to_arb(a)
# print(b)
# c = base_arb_to_10(b)
# print(c)


# Base 7 to base 10 conversion correct.
bin = 0b1101100110111111101101100101110110011110011101100110000111001001110011011000101100100110011
print(f"in base 10: {int(bin)}")
a = "lovelylads123"
b = encode(a)
print(b)
c = decode(b)
print(c)

