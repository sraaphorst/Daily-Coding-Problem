#!/usr/bin/env python3
# day031.py
# By Sebastian Raaphorst, 2019.


def compute_edit_distance(w1: str, w2: str) -> int:
    """
    Determine the edit distances between w1 and w2, trying to turn w1 into w2.
    This is a very brute force approach, as shown by the length required for the last case.

    >>> compute_edit_distance('a', 'b')
    1
    >>> compute_edit_distance('aa', 'b')
    2
    >>> compute_edit_distance('kitten', 'sitting')
    3
    >>> compute_edit_distance('speeddemon', 'tpddmo')
    5
    >>> compute_edit_distance("disenfranchise", "inheritance")
    9
    """
    def compute_edit_distance_aux(w1: str, p1: int, w2: str, p2: int) -> int:
        # If first string is empty, only option is to add all w2.
        if p1 == 0:
            return p2

        # If second string is empty, only option is to delete all w1.
        if p2 == 0:
            return p1

        # If the last letter is the same, the edit distance is on the substrings.
        if w1[p1 - 1] == w2[p2 - 1]:
            return compute_edit_distance_aux(w1, p1 - 1, w2, p2 - 1)

        # Otherwise we try all operations on w1[p1] to turn it into w2[p2].
        return 1 + min(
            # Insert w2[p2]
            compute_edit_distance_aux(w1, p1, w2, p2 - 1),
            # Delete w2[p2]
            compute_edit_distance_aux(w1, p1 - 1, w2, p2),
            # Transform to w2[p2]
            compute_edit_distance_aux(w1, p1 - 1, w2, p2 - 1)
        )
    return compute_edit_distance_aux(w1, len(w1), w2, len(w2))


if __name__ == '__main__':
    import sys
    if len(sys.argv) < 3:
        sys.stderr.write(f'Usage: {sys.argv[0]} word1 word2')
        sys.exit(0)

    w1, w2 = sys.argv[1], sys.argv[2]
    print(f'Editing distance: {compute_edit_distance(w1, w2)}')