#!/usr/bin/env python3

# Three distinct ways to generate any Fibonacci number in space O(1).

import sys
from typing import Tuple
import math

from profile import profile
from tail_recursion import tail_recursive, recurse

import hypothesis.strategies as st
from hypothesis import given


def fib_generator():
    """
    Use a generator to generate all the Fibonacci numbers. Then use the auxiliary function below to iterate
    directly to the desired Fibonacci number.
    :return: a generator for the Fibonacci numbers
    """
    a, b = 0, 1
    while True:
        yield a
        a, b = b, a + b


@profile
def fib_generator_n(n: int) -> int:
    """
    An iterator over the Fibonacci number generator to access the nth.
    :param n: the index of the Fibonacci number
    :return: the nth Fibonacci number
    """
    a = fib_generator()
    value = 0
    for i in range(n+1):
        value = next(a)
    return value


@profile
def fib_direct(n: int) -> int:
    """
    Using the Golden Ratio, it is possible to calculate the nth Fibonacci number directly.
    Due to floating point arithmetic limits, we cannot calculate much higher than n=1000.
    Also, there are inaccuracies starting at 71.
    :param n: the index of the Fibonacci number
    :return: the nth Fibonacci number
    """
    sqrt5 = math.sqrt(5)
    phi = (1 + sqrt5) / 2
    #return round((phi**n - (1 - phi)**n)/sqrt5)
    return round((phi**n - (- phi)**(-n))/sqrt5)


@profile
def fib_tail_recursion(n: int) -> int:
    """
    Use tail recursion to generate the nth Fibonacci number. This avoids stack buildup.
    Use special tail recursive code to allow tail recursion in Python.
    :param n: the index of the Fibonacci number
    :return: the nth Fibonacci number
    """
    @tail_recursive
    def aux(n: int, accumulator: Tuple[int,int] = (0, 1)) -> int:
        if n == 0:
            return accumulator[0]
        recurse(n-1, accumulator=(accumulator[1], accumulator[0] + accumulator[1]))
    return aux(n)


@profile
def fib_unoptimized_tail_recursion(n: int) -> int:
    """
    Same as fib_tail_recursion, but unoptimized to allow for actual tail recursion.
    :param n: the index of the Fibonacci number
    :return: the nth Fibonacci number
    """
    def aux(n: int, a: int = 0, b: int = 1) -> int:
        if n == 0:
            return a
        return aux(n-1, b, a + b)
    return aux(n)


if __name__ == '__main__':
    # 1000 is around the limit for the floating point arithmetic in the direct calculation.
    sys.setrecursionlimit(2000)
    run1 = fib_generator_n(1000)
    run2 = fib_direct(1000)
    run3 = fib_tail_recursion(1000)
    run4 = fib_unoptimized_tail_recursion(1000)


@given(st.integers(min_value=0, max_value=70))
def test_fib(n: int):
    calc1 = fib_generator_n(n)
    calc2 = fib_direct(n)
    calc3 = fib_tail_recursion(n)
    calc4 = fib_unoptimized_tail_recursion(n)
    print(calc1, calc2, calc3, calc4)
    assert calc1 == calc2 == calc3 == calc4
