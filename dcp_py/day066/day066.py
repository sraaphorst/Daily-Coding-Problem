#!/usr/bin/env python3
# day066.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given, settings
from typing import Iterator
from random import random


def unbiased_toss(toss_coin: Iterator[int]) -> Iterator[int]:
    """
    Given an biased coin with unknown bias (although neither result probability 1), find a way to generate an unbiased
    result from it, i.e. a simulated coin where the probability of both results is 0.5.

    :param toss_coin: the iterator that produces the value of the coin toss
    :return: the iterator that produces an unbiased coin
    """
    while True:
        (a, b) = (0, 0)
        while a == b:
            (a, b) = next(toss_coin), next(toss_coin)
        if a == 1 and b == 0:
            yield 1
        if a == 0 and b == 1:
            yield 0


def biased_toss(probability1: float) -> Iterator[int]:
    """
    A biased coin.

    :param probability1: the probability of 1 being flipped; the probability of 0 is 1 - probability1
    :return: the iterator that produces the biased coin
    """
    assert 0 < probability1 < 1, "Probability of 1 must be in (0,1)."
    while True:
        yield 1 if random() < probability1 else 0


@given(st.floats(min_value=0.001, max_value=0.999))
@settings(deadline=1e9)
def test_coin(probability1: float):
    """
    Given a random probability in (0.001, 0.999), verify that unbiased_toss creates an unbiased simulated coin
    from a biased coin created from biased_toss.

    We change the deadline to a large number so that we don't time out in 200 ms.

    It is theoretically possible that this will fail, but unlikely.

    :param probability1: the probability of 1 being flipped in the biased coin
    """
    num_trials = 50000
    num_ones = 0
    gen = unbiased_toss(biased_toss(probability1))
    for _ in range(num_trials):
        if next(gen) == 1:
            num_ones += 1
    probability1 = num_ones / num_trials

    # Generous wiggle room.
    assert(abs(probability1 - 0.5) < 1e-2)

