#!/usr/bin/env python3

from typing import List, Optional, Tuple


def sieve_of_eratosthenes(n: int) -> List[int]:
    """
    Simple Sieve of Eratosthenes algorithm to return all primes up to n.
    :param n: the upper bound on the primes
    :return: the list of primes in order

    >>> sieve_of_eratosthenes(0)
    []
    >>> sieve_of_eratosthenes(1)
    []
    >>> sieve_of_eratosthenes(2)
    [2]
    >>> sieve_of_eratosthenes(3)
    [2, 3]
    >>> sieve_of_eratosthenes(60)
    [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59]
    """
    primes = []
    sieve = [False, False] + [True] * (n - 1)
    for i in range(2, n + 1):
        # Skip non-primes.
        if not sieve[i]:
            continue

        # Add new primes and then extend the sieve.
        primes.append(i)

        # Get rid of all multiples.
        idx = i
        while idx <= n:
            sieve[idx] = False
            idx += i

    return primes


def prime_sum(n: int) -> Optional[Tuple[int, int]]:
    """
    Given an even integer n >= 4, find the lexicographically smallest way to write it as a sum of two primes.
    :param n: the integer n
    :return: a pair (i, n-i) where i <= n-i and i and n-i are both primes

    >>> prime_sum(4)
    (2, 2)
    >>> prime_sum(6)
    (3, 3)
    >>> prime_sum(8)
    (3, 5)
    >>> prime_sum(10)
    (3, 7)
    >>> prime_sum(12)
    (5, 7)
    >>> prime_sum(14)
    (3, 11)
    >>> prime_sum(16)
    (3, 13)
    >>> prime_sum(18)
    (5, 13)
    >>> prime_sum(20)
    (3, 17)
    """
    assert n >= 4
    assert n % 2 == 0

    primes = sieve_of_eratosthenes(n)
    for i in primes:
        # This should never happen.
        if i > n - i:
            return None
        if n - i in primes:
            return i, n - i
