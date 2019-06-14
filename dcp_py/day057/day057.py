#!/usr/bin/env python3
# day057.py
# By Sebastian Raaphorst, 2019.

from hypothesis import strategies as st
from hypothesis import given
from typing import List, Optional


def to_lines(text: str, k: int) -> Optional[List[str]]:
    """
    Given a block of text and a maximum line length k, split the text into lines of length at most k.
    If this cannot be done, i.e. a word is longer than k, return None.
    :param text: the block of text to process
    :param k: the maximum length of each line
    :return: the list of lines

    >>> text = 'the quick brown fox jumps over the lazy dog'
    >>> to_lines(text, 4) is None
    True
    >>> to_lines(text, 5)
    ['the', 'quick', 'brown', 'fox', 'jumps', 'over', 'the', 'lazy', 'dog']
    >>> to_lines(text, 9)
    ['the quick', 'brown fox', 'jumps', 'over the', 'lazy dog']
    >>> to_lines(text, 10)
    ['the quick', 'brown fox', 'jumps over', 'the lazy', 'dog']
    >>> to_lines(text, 12)
    ['the quick', 'brown fox', 'jumps over', 'the lazy dog']
    >>> to_lines('AAAAA', 5)
    ['AAAAA']
    """
    def line_to_str(l: List[str]) -> str:
        return ' '.join(l)

    # If there is no text or the line length is 0, we can't do anything.
    if not text or not k:
        return None

    # If any word is longer then k, we can't do anything.
    words = text.split()
    if max(len(word) for word in words) > k:
        return None

    # Now split the word into lines.
    lines = []
    line = []
    len_so_far = 0
    for word in words:
        len_word = len(word)
        if len_word + len_so_far <= k:
            # We add the word to the line plus a blank space afterwards.
            # If this is the last word in the line, the blank space will not occur; hence why we check the
            # condition <= k rather than < k.
            line.append(word)
            len_so_far += len_word + 1
        else:
            # Make the line into a string, add it to lines, and reset everything.
            lines.append(line_to_str(line))
            line = [word]
            len_so_far = len_word + 1

    # Last case: if we have a partial line, add it.
    if line:
        lines.append(line_to_str(line))

    # Assert that none of the lines went over the length.
    for line in lines:
        assert(len(line) <= k)

    return lines


# Not sure how much good spec testing will do here, but we'll try it anyway.
for _ in range(20):
    print(f'\"{st.from_regex("^[a-zA-Z]{3,20}$").example().strip()}\"')


@st.composite
def gen_input(draw) -> (List[str], int):
    """
    Generate an array of "words" (alphabetic text) representing a block of text with words ranging in length
    [3, 20], and also a line length k in [1, 20].
    :param draw: the hypothesis object needed to draw from the strategies
    :return: a pair representing the array of text and the line length
    """
    # Unsure why sometimes we get a newline at the end of the elements in text.
    text = draw(st.lists(st.from_regex("^[a-zA-Z]{3,20}$").map(lambda x: x.strip())))
    length = draw(st.integers(min_value=1, max_value=20))
    return text, length


@given(gen_input())
def test_algorithm(input: (List[str], int)):
    """
    Test the algorithm with a generated block of text and line length.
    :param input: a tuple containing a list of the words in the text and the line length
    """
    (words, k) = input
    text = ' '.join(words)

    # If we have no words or the maximum word size is greater than k, we should get None.
    if not words or max(len(word) for word in words) > k:
        assert(to_lines(text, k) is None)
    else:
        # Otherwise:
        # 1. Every line should be length at most k; and
        # 2. Joining the lines together should give us our original block of text.
        lines = to_lines(text, k)
        for line in lines:
            assert(len(line) <= k)
        assert(' '.join(lines) == text)
