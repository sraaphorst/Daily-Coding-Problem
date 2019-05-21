#!/usr/bin/env python3
# day045.py
# By Sebastian Raaphorst, 2019.

import random

rand5 = lambda: random.randrange(1, 6)


def rand7() -> int:
    """
    Given a function that returns a random uniform value in [1,5], write a function that returns a uniform value
    in [1,7].

    We do this by recognizing that 555555 % 7 == 0, so five calls to rand5() will give us a call to rand(7).
    :return: a uniform random number in [1,7]

    # Check that in a large number of trials, each number in [1,7] appears approximately uniformly.
    >>> numTrials = 100000
    >>> trials = [rand7() for _ in range(numTrials)]
    >>> counts = dict([(c, trials.count(c)) for c in range(1,8)])
    >>> expected = numTrials / 7
    >>> error = 1e-2
    >>> successes = [abs((c - expected) / numTrials) < error for c in counts.values()]
    >>> all(successes)
    True
    """

    # I tested these with 10 ** i, (5 ** i) << i, pow, and using a loop to accumulate powers of 10,
    # and none of them seem to make a substantial difference in performance.
    val = sum([rand5() * 10 ** i for i in range(6)])
    return val % 7 + 1
