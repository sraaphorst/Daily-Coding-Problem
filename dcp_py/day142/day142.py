#!/usr/bin/env python3
# day142.py
# By Sebastian Raaphorst, 2019.

from hypothesis import given
from hypothesis import strategies as st


def evaluate_parens(paren_string: str) -> bool:
    """
    Evaluate an expression consisting of ( and ), and ensure that they are balanced.
    :param paren_string: A string representing the expression
    :return: True if the ( and ) parentheses are balanced, and False otherwise.

    >>> evaluate_parens('')
    True
    >>> evaluate_parens('()')
    True
    >>> evaluate_parens('(()(()))')
    True
    >>> evaluate_parens(')')
    False
    >>> evaluate_parens('(()')
    False
    >>> evaluate_parens('())(')
    False
    """
    lefts = 0
    for ch in paren_string:
        if ch == '(':
            lefts += 1
        elif ch == ')':
            if lefts <= 0:
                return False
            lefts -= 1
    return lefts == 0


def brute_force_evaluation(paren_str: str) -> bool:
    """
    Given a string containing (, ), and *, where * can be either (, ), or the empty string, check to see if
    the parentheses are balanced using a brute force approach. This has order O(nk^3) where k is the number of *.

    :param paren_str: A string representing the expression.
    :return: True if the string is balanced, and false otherwise.

    >>> brute_force_evaluation('')
    True
    >>> brute_force_evaluation('*')
    True
    >>> brute_force_evaluation('(()*')
    True
    >>> brute_force_evaluation('(*)')
    True
    >>> brute_force_evaluation('***))(**')
    True
    >>> brute_force_evaluation(')*(')
    False
    """
    paren_strs = ['']
    for ch in paren_str:
        if ch == '(' or ch == ')':
            paren_strs = [s + ch for s in paren_strs]
        elif ch == '*':
            # Add three strings for every string s, namely s(, s, and s) to represent the possibilities for *.
            paren_strs = [item for sublist in [[s + '(', s, s + ')'] for s in paren_strs] for item in sublist]
    for s in paren_strs:
        if evaluate_parens(s):
            return True
    return False


def paren_intervals(paren_str: str) -> bool:
    """
    Keep track of the possible balance of parentheses via a continuous interval over integers.
    For example:
    1. '' has a balance of [0, 0]
    2. '(' has a balance of (1, 1)
    3. '(*' has a balance of (0, 2): if * is ), the balance is 0, if it is empty, it is 1, and if (, it is 2.
    4. ')' has a balance of (-1,-1)

    We stop as soon as the balance high point is negative, and if this never happens, we return True if 0 is in the
    interval.

    This should run in O(n).

    :param paren_str: A string representing the expression.
    :return: True if the string is balanced, and false otherwise.

    >>> paren_intervals('*)(')
    False
    >>> paren_intervals('')
    True
    >>> paren_intervals('*')
    True
    >>> paren_intervals('(()*')
    True
    >>> paren_intervals('(*)')
    True
    >>> paren_intervals('***))(**')
    True
    >>> paren_intervals(')*(')
    False
    """
    balance_low, balance_high = 0, 0

    for ch in paren_str:
        if ch == '(':
            balance_low += 1
            balance_high += 1
        elif ch == ')':
            balance_low -= 1
            balance_high -= 1
        elif ch == '*':
            balance_low -= 1
            balance_high += 1
        if balance_high < 0:
            break
        balance_low = max(balance_low, 0)
    return balance_low <= 0 <= balance_high


@given(st.text(alphabet=['(', ')', '*'], min_size=0, max_size=10))
def test_it(paren_str: str):
    print(paren_str + ': ' + str(paren_intervals(paren_str)))
    assert brute_force_evaluation(paren_str) == paren_intervals(paren_str)
