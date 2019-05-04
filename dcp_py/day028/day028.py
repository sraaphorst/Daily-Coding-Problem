#!/usr/bin/env python3


def partition_words(words, k):
    """
    Given a list of words, maximally split them into lists such that the length with space separators is at most k.
    :param words: the list of words
    :param k: the maximum line length
    :return: a list of lists of words

    >>> partition_words(['the', 'quick', 'brown', 'fox', 'jumps', 'over', 'the', 'lazy', 'dog'], 16)
    [['the', 'quick', 'brown'], ['fox', 'jumps', 'over'], ['the', 'lazy', 'dog']]
    >>> partition_words([], 100)
    []
    >>> partition_words(['one'], 5)
    [['one']]
    """
    # I don't know if there's a better way to do this using a list partitioning utility.
    # For complexity, reverse the list.
    reversed_words = list(reversed(words))

    lines = []
    line = []
    currlength = 0

    while reversed_words:
        word = reversed_words.pop()
        if currlength + len(word) <= k:
            line.append(word)
            currlength += len(word) + 1
        else:
            lines.append(line)
            line = [word]
            currlength = len(word) + 1

    if line:
        lines.append(line)

    return lines


def pad_line(line, k):
    """
    Pad a line (list of words) to length k by dividing spaces as evenly as possible, with preference given to left
    words.
    :param line: list of words
    :param k: length
    :return: a string representing the line

    >>> pad_line(['the', 'quick', 'brown'], 16)
    'the  quick brown'
    >>> pad_line(['fox', 'jumps', 'over'], 16)
    'fox  jumps  over'
    >>> pad_line(['the', 'lazy', 'dog'], 16)
    'the   lazy   dog'
    >>> pad_line(['one'], 5)
    'one  '
    """

    num_words = len(line)
    line_length = sum([len(word) for word in line])

    # Divide as evenly as possible amongst the num_words - 1 words.
    if num_words > 1:
        padding, extra_padding = divmod(k - line_length, num_words - 1)
    else:
        padding, extra_padding = k - line_length, 0

    # Now pad the line.
    padded_line = ""
    pad = ' ' * padding
    for idx, word in enumerate(line):
        # Add the word, and then the padding. If we only have one word, all padding goes to end.
        padded_line += word
        if idx < num_words - 1 or num_words == 1:
            padded_line += pad + (' ' if idx < extra_padding else '')

    return padded_line


if __name__ == '__main__':
    import sys
    if len(sys.argv) < 3:
        sys.stderr.write(f'Usage: {sys.argv[0]} line-size word-list')
        sys.exit(0)

    k = int(sys.argv[1])
    words = sys.argv[2:]

    for line in partition_words(words, k):
        print(pad_line(line, k))
