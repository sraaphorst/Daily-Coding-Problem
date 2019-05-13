#!/usr/bin/env python3
# day007.py
# By Sebastian Raaphorst, 2019.


# We memoize the auxiliary internal function in calculate_decodings, because the recursion explodes into
# already-solved sub-problems.
# For the last test, without memoization, it takes
class Memoize:
    def __init__(self, f):
        self.f = f
        self.memo = {}

    def __call__(self, *args):
        if not args in self.memo:
            self.memo[args] = self.f(*args)
        return self.memo[args]


def calculate_decodings(enc: str) -> int:
    """
    Given an encoded  string consisting of numbers, using the decoding:
      a -> 1, b -> 2, ..., z -> 26,
    calculate the number of ways in which the string can be decoded.
    :param enc: the string to decode
    :return: the number of possible decodings

    >>> calculate_decodings('111')
    3
    >>> calculate_decodings('121')
    3
    >>> calculate_decodings('131')
    2
    >>> calculate_decodings('1234')
    3
    >>> calculate_decodings('2563')
    2
    >>> calculate_decodings('4123')
    3
    >>> calculate_decodings('1101')
    1
    >>> calculate_decodings('11101')
    2
    >>> calculate_decodings('1001')
    0

    # 17  is 1/7   or 17             2 choices
    # 224 is 2/2/4 or 22/4 or 2/24   3 choices
    # 3   is 3                       1 choice
    # 15  is 1/5   or 15             2 choices
    # 9   is 9                       1 choice
    # 20  is 20                      1 choice
    # 22  is 2/2   or 22             2 choices
    # Total = 2^3 * 3 = 24 choices
    >>> calculate_decodings('1722431592022')
    24

    # On my MacBook pro:
    # cProfile.run("calculate_decodings('111111111111111111111111111111111111')")
    # * without memoization, this takes 76.355 seconds.
    # * with memoization, this takes less than 0.01 seconds.
    # Profiled using:
    # import cProfile
    # cProfile.run(...)
    # Note that using a string entirely of 1s and / or 2s leads to the Fibonacci numbers, as we must break
    # the string into bits of lengths 1 and 2, which is equivalent to the coding of a 1xn board with
    # squares and dominoes.

    >>> calculate_decodings('111111111111111111111111111111111111')
    24157817
    """

    # General strategy: we break this down into a case-by-case basis and recursively ascertain the number of possible
    # decoding. To begin with, the numbers:
    # (A) 3 - 6 are the same and will be treated as such; and
    # (B) 7 - 9 are the same.
    # This, we begin by converting these characters to tokens A and B respectively to simplify the processing
    # and to increase the chances at memoization.
    enc = enc.translate(enc.maketrans({'3': 'A', '4': 'A', '5': 'A', '6': 'A', '7': 'B', '8': 'B', '9': 'B'}))

    # Now we use an auxiliary method to consider all possible cases.
    # There could be some optimization here: slicing + comparing is slightly faster than startswith, and
    # >>> timeit.timeit('"1234".startswith("12")', number=10000000)
    #     1.5383420000434853
    # >>> timeit.timeit('"1234"[:2] == "12"', number=10000000)
    #     1.2081479519838467
    # and furthermore, the long if statement might be faster if using sets, e.g.
    #     if len(2) > 1 and ((s[0] == '1' and s[1] in S1) or (s[0] == '2' and s[2] in S2)
    # but I think it is clearer what is happening with the list of startswith.
    @Memoize
    def aux(s: str) -> int:
        # Base case 1: if s is empty, there is a unique decoding.
        if len(s) <= 1:
            return 1

        # Base case 2: if the first character of s is 0, we have made a mistake.
        # This could occur in, e.g., 110 when we consider 110 to be 11/0 instead of 1/10.
        if s[0] == '0':
            return 0

        # Base case 2: if s contains a single character, we only have one decoding.
        if len(s) == 1:
            assert s[0] != '0', f"{s} contains 0 in illegal position"
            return 1

        # If s starts with either A or B, then we can only decode to the symbol that was changed to A or B
        # so this part of the encoding is fixed. Advance and recurse.
        if s.startswith('A') or s.startswith('B'):
            return aux(s[1:])

        # If s starts with either 10 or 20, then we can only decode to j or t. Advance and recurse.
        if s.startswith('10') or s.startswith('20'):
            return aux(s[2:])

        # If s starts with 2B, then we can only decode to b for 2 and the symbol represented by B.
        # Advance and recurse.
        if s.startswith('2B'):
            return aux(s[2:])

        # Now the mixed case:
        # If s starts with 11, 12, 1A, or 1B, then it can be decoded into two possibilities.
        # If s starts with 21, 22, or 2A, then it too can be decoded into two possibilities.
        if s.startswith('11') or s.startswith('12') or s.startswith('1A') or s.startswith('1B') or \
                s.startswith('21') or s.startswith('22') or s.startswith('2A'):
            return aux(s[1:]) + aux(s[2:])

        # If we reached this point, something went wrong: an illegal character?
        assert False, f"{s} is badly formed"

    return aux(enc)

