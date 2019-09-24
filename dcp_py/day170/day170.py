#!/usr/bin/env python3
# day170.py
# By Sebastian Raaphorst, 2019.

from typing import List, Union

Word = str
Dictionary = List[Word]
Transformation = List[Word]


def find_shortest_transformation(start: Word, end: Word, dictionary: Dictionary) -> Transformation:
    """
    Use a DFS to find the shortest transformation - if one exists - from start to end.
    :param start: the start word
    :param end: the end word
    :param dictionary: the dictionary
    :return: the transformation path if one exists, and None if there is no such path

    >>> find_shortest_transformation('dog', 'cat', ['dot', 'dop', 'dat', 'cat'])
    ['dog', 'dot', 'dat', 'cat']

    >>> find_shortest_transformation('linen', 'given', ['puppy', 'ashen', 'given', 'lives', 'lines', 'kitty', 'liven'])
    ['linen', 'lines', 'lives', 'liven', 'given']

    >>> find_shortest_transformation('dog', 'cat', ['dot', 'tod', 'dat', 'dar']) is None
    True
    """

    # Auxiliary method to determine the distance between two words.
    # Two words have distance in the number of positions they deliver.
    def word_distance(word1: Word, word2: Word) -> int:
        # Words must be the same size.
        if len(word1) != len(word2):
            return -1
        # Example: dog, dat: d is the same, o/a different, and g/t different, so distance 2.
        return sum(w1 != w2 for w1, w2 in zip(word1, word2))

    # Now run the BFS.
    # We keep track of the words already used so that we do not use them again.
    def bfs(transformation: Transformation) -> Union[None, Transformation]:
        # There is no path.
        if len(transformation) == 0:
            return None

        # Previous word.
        previous_word = transformation[-1]

        # We have reached the end word.
        if previous_word == end:
            return transformation

        # Get words at distance 1 than have not been used yet.
        candidates = [word for word in dictionary if word_distance(word, previous_word) == 1
                      and word not in transformation]

        for word in candidates:
            result = bfs(transformation + [word])
            if result:
                return result

        return None

    return bfs([start])
