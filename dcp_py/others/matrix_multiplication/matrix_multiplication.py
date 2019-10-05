#!/usr/bin/env python3
# matrix_multiplication
# By Sebastian Raaphorst, 2019.

from functools import lru_cache
from typing import List, Callable


def matrix_multiplication_number_of_ops(dims: List[int], fn: Callable[[int, int], int]= min) -> int:
    """
    Given a list of matrix dimensions of the form M_i = dims[i-1] x dims[i], determine the fewest / most operations
    needed to multiply matrices of those dimensions together.

    This is done by recognizing that matrix multiplication is associative, i.e. ABC = (AB)C = A(BC), so the order
    in which the matrices are multiplied are invariant in determining the final result.

    Furthermore, we use the following information: if we are multiplying two matrices, say:
    A, with m rows and n columns
    B, with n rows and k columns
    the resultant matrix AB will have mxk entries. Each entry in AB will require the dot product of a row of A with
    a column of B, which requires n multiplications and (n-1) additions to sum up the multiplications. Since they are
    negligible, we ignore the (n-1) additions and instead estimate the number of simple operations to multiply matrices
    A and B to be:
        m x n x k

    :param dims: the dimensions of the matrices
    :param fn: the function to determine if we should replace the number of operations (min or max, typically)
    :return: the smallest (or largest) number of operations

    >>> matrix_multiplication_number_of_ops([10, 30, 5, 60])
    4500
    >>> matrix_multiplication_number_of_ops([10, 30, 5, 60], max)
    27000
    >>> matrix_multiplication_number_of_ops([40, 20, 30, 10, 30])
    26000
    >>> matrix_multiplication_number_of_ops([40, 20, 30, 10, 30], max)
    69000
    >>> matrix_multiplication_number_of_ops([10, 20, 30, 40, 30])
    30000
    >>> matrix_multiplication_number_of_ops([10, 20, 30, 40, 30], max)
    60000

    # There are only two matrices, so the size op doesn't matter.
    >>> matrix_multiplication_number_of_ops([10, 20, 30])
    6000
    >>> matrix_multiplication_number_of_ops([10, 20, 30], max)
    6000

    >>> matrix_multiplication_number_of_ops([10, 10])
    0
    """
    @lru_cache(maxsize=None)
    def aux(i: int, j: int, base: int = None) -> int:
        # Base case: no matrix
        if i == j:
            return 0

        # We try parentheses at all different positions between the first and last matrix.
        for parenthesis_pos in range(i, j):
            ops_count = aux(i, parenthesis_pos) + aux(parenthesis_pos + 1, j)\
                        + dims[i - 1] * dims[parenthesis_pos] * dims[j]

            if base is None:
                base = ops_count
            else:
                base = fn(base, ops_count)

        return base

    return aux(1, len(dims) - 1)
