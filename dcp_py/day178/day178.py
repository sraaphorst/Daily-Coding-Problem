#!/usr/bin/env python3
# day 178.py
# By Sebastian Raaphorst, 2019.


from typing import Tuple

from random import randint


def roll() -> int:
    """
    Roll a d6.
    :return: [1,6]
    """
    return randint(1, 6)


def simulation(prev: int, curr: int) -> int:
    """
    Perform a simulation of the expected number of rolls Alice needs to win one of the two variants of the game
    described in the problem. Alice wins when her previous roll is prev and her current roll is curr.
    :param prev: the previous roll necessary to win
    :param curr: the current roll necessary to win
    :return: the number of rolls that were required to win
    """
    rolls = 0
    previous = 0

    while True:
        current = roll()
        rolls += 1
        if previous == prev and current == curr:
            return rolls
        previous = current


def expected_rolls(simulations: int = 100000) -> Tuple[float, float]:
    exp56 = 0
    for i in range(simulations):
        exp56 += simulation(5, 6)

    exp55 = 0
    for i in range(simulations):
        exp55 += simulation(5, 5)

    return exp56 / simulations, exp55 / simulations


five_six, five_five = expected_rolls()
print(f"5-6 expected cost: {five_six}")
print(f"5-5 expected cost: {five_five}")
