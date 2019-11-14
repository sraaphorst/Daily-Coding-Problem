#!/usr/bin/env python3
# day222.py
# By Sebastian Raaphorst, 2019


def simplify_pathname(path_name: str) -> str:
    """
    Simplify an absolute pathname.
    :param path_name: the pathname, which may contain . or ..
    :return: the simplified pathname

    >>> simplify_pathname('/usr/bin/../bin/./scripts/../')
    '/usr/bin/'
    >>> simplify_pathname('/')
    '/'
    """
    pieces = path_name.split('/')
    stack = []

    for piece in pieces:
        if piece == '..':
            stack.pop()
        elif piece != '.':
            stack.append(piece)

    return '/'.join(stack)
