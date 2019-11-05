#!/usr/bin/env python3
# day213.py
# By Sebastian Raaphorst

from typing import List
from itertools import combinations


def is_valid_ip_segment(s: str) -> bool:
    if not s.isnumeric():
        return False
    if len(s) == 0:
        return False
    if len(s) > 1 and s[0] == '0':
        return False
    if int(s) > 255:
        return False
    return True


def find_all_perms_brute_force(s: str) -> List[str]:
    """
    A brute-force technique to generate all IPs from a list of characters represented as a string.
    We simply pick three places to insert the periods and then check for validity.
    This prouces many invalid IPs, but given the small number of IPs (11C3 = 165), time is not really a concern
    and there is no reason to engage in a technique like backtracking.
    :param s: the string of digits to analyze for IPs
    :return: the list of valid IPs

    >>> find_all_perms_brute_force('2542540123')
    ['254.25.40.123', '254.254.0.123']
    >>> find_all_perms_brute_force('0000')
    ['0.0.0.0']
    >>> find_all_perms_brute_force('255255255255')
    ['255.255.255.255']
    >>> find_all_perms_brute_force('300000')
    []
    """
    if not s.isnumeric() or len(s) < 4 or len(s) > 12:
        return []

    ips = []

    # Pick the three potential positions for the dots.
    for (i1, i2, i3) in combinations(range(1, len(s)), 3):
        # Divide into four slices:
        slices = [s[0:i1], s[i1:i2], s[i2:i3], s[i3:]]
        if False not in [is_valid_ip_segment(slice) for slice in slices]:
            ips.append('.'.join(slices))
    return ips
