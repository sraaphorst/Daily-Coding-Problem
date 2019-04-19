#!/usr/bin/env python3
# day005.py
# By Sebastian Raaphorst, 2019.


def cons(a, b):
    """
    Construct a pair.
    :param a: first element
    :param b: second element
    :return: a function that takes a function and activates it on the pair
    """
    def pair(f):
        return f(a, b)
    return pair


def car(cns):
    """
    Given an object cns created by cons, retrieve the first element.
    :param cns: the function created by cons
    :return: the first element

    >>> car(cons(1, 2))
    1
    >>> car(cons(3, cons(4, 5)))
    3
    >>> car(car(cons(cons(6, 7), 8)))
    6
    """
    return cns(lambda a, b: a)

def cdr(cns):
    """
    Given an object cns created by cons, retrieve the second element.
    :param cns: the function created by cons
    :return: the second element

    >>> cdr(cons(1, 2))
    2
    >>> cdr(cons(cons(3, 4), 5))
    5
    >>> cdr(cdr(cons(6, cons(7, 8))))
    8
    """
    return cns(lambda a, b: b)
