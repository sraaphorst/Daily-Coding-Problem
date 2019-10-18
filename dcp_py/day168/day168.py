#!/usr/bin/env python3
# day169.py
# By Sebastian Raaphorst, 2019
# I think that this does indeed use new memory. We should use mutable programming.

import numpy as np
from typing import List


def numpy_rot90(a: List[List[int]]) -> List[List[int]]:
    """
    Using numpy, rotate a matrix by 90 degrees. For example:
    [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]

    should rotate to:

    [[7, 4, 1],
     [8, 5, 2],
     [9, 6, 3]]

    :param a: the array to rotate by 90 degrees
    :return: the rotated list

    >>> numpy_rot90([[]])
    []
    >>> numpy_rot90([[1, 2], [3, 4]])
    [[3, 1], [4, 2]]
    >>> numpy_rot90([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    >>> numpy_rot90([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]])
    [[13, 9, 5, 1], [14, 10, 6, 2], [15, 11, 7, 3], [16, 12, 8, 4]]
    >>> numpy_rot90([[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]])
    [[21, 16, 11, 6, 1], [22, 17, 12, 7, 2], [23, 18, 13, 8, 3], [24, 19, 14, 9, 4], [25, 20, 15, 10, 5]]
    """
    # Note that numpy's rot90 rotates COUNTERCLOCKWISE and we want CLOCKWISE, so we set k=-1 to rotate 90 degrees
    # clockwise. These list class seem extraneous but they make the typing match: otherwise iterable is returned.
    return list(np.rot90(a, k=-1).tolist())


def create_standard_layers(layers: int) -> List[List[int]]:
    """
    Create a standard set of layers, i.e. a set of rows and lengths of columns layers labeled from the top left
    to the bottom right right corner.

    :param layers: the number of layers
    :return: the matrix representing the layers

    >>> create_standard_layers(0)
    []
    >>> create_standard_layers(1)
    [[0]]
    >>> create_standard_layers(2)
    [[0, 1], [2, 3]]
    >>> create_standard_layers(3)
    [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
    >>> create_standard_layers(4)
    [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11], [12, 13, 14, 15]]
    """
    return [list(range(layers * i, layers * (i + 1))) for i in range(layers)]


def inplace_90(matrix: List[List[int]]) -> List[List[int]]:
    """
    Rotate a square matrix 90 degrees clockwise.
    This is supposed to be using no additional memory but is by creating the lists, so we should have used mutability.
    :param matrix: the matrix to rotate
    :return: the rotated matrix

    >>> inplace_90([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    >>> inplace_90([[]])
    []
    >>> inplace_90([[1, 2], [3, 4]])
    [[3, 1], [4, 2]]

    >>> inplace_90([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]])
    [[13, 9, 5, 1], [14, 10, 6, 2], [15, 11, 7, 3], [16, 12, 8, 4]]
    >>> inplace_90([[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]])
    [[21, 16, 11, 6, 1], [22, 17, 12, 7, 2], [23, 18, 13, 8, 3], [24, 19, 14, 9, 4], [25, 20, 15, 10, 5]]


    >>> inplace_90([[1, 2], [3, 4]])
    [[3, 1], [4, 2]]
    >>> X = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    >>> inplace_90(X)
    [[13, 9, 5, 1], [14, 10, 6, 2], [15, 11, 7, 3], [16, 12, 8, 4]]
    """
    def transpose(m: List[List[int]]) -> List[List[int]]:
        return [[m[j][i] for j in range(len(m))] for i in range(len(m[0]))]

    # To rotate by 90, transpose and then shift the columns.
    def select_columns(m: List[List[int]]) -> List[List[int]]:
        dim = len(m)
        # We turn the rows into columns so that we can select them.
        trans = transpose(matrix)
        cols = [*zip(*trans)]
        return [*zip(*[list(cols[dim-i-1]) for i in range(dim)])]

    # Convert from tuples to lists.
    return [list(x) for x in select_columns(transpose(matrix))]


def inplace_902(matrix: List[List[int]]) -> List[List[int]]:
    """
    Rotate a square matrix 90 degrees clockwise.
    This is supposed to be using no additional memory but is by creating the lists, so we should have used mutability.
    :param matrix: the matrix to rotate
    :return: the rotated matrix

    >>> inplace_902([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
    [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
    >>> inplace_902([[]])
    []
    >>> inplace_902([[1, 2], [3, 4]])
    [[3, 1], [4, 2]]

    >>> inplace_902([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]])
    [[13, 9, 5, 1], [14, 10, 6, 2], [15, 11, 7, 3], [16, 12, 8, 4]]
    >>> inplace_902([[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]])
    [[21, 16, 11, 6, 1], [22, 17, 12, 7, 2], [23, 18, 13, 8, 3], [24, 19, 14, 9, 4], [25, 20, 15, 10, 5]]


    >>> inplace_902([[1, 2], [3, 4]])
    [[3, 1], [4, 2]]
    >>> X = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]]
    >>> inplace_902(X)
    [[13, 9, 5, 1], [14, 10, 6, 2], [15, 11, 7, 3], [16, 12, 8, 4]]
    """
    def transpose(m: List[List[int]]) -> List[List[int]]:
        return [[m[j][i] for j in range(len(m))] for i in range(len(m[0]))]

    # To rotate by 90, transpose and then shift the columns.
    def select_columns(m: List[List[int]]) -> List[List[int]]:
        dim = len(m)
        # We turn the rows into columns so that we can select them.
        trans = transpose(matrix)
        cols = [*zip(*trans)]
        return [*zip(*[list(cols[dim-i-1]) for i in range(dim)])]

    # Convert from tuples to lists.
    return [list(x) for x in select_columns(transpose(matrix))]
