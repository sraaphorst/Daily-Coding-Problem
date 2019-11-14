#!/usr/bin/env python3
# day222.py
# By Sebastian Raaphorst, 2019


def simplify_pathname(path_name: str) -> str:
    """
    Simplify an absolute pathname.
    :param path_name: the pathname, which may contain . or ..
    :return: the simplified pathname

    >>> simplify_pathname('/')
    '/'
    >>> simplify_pathname('//////usr/bin/')
    '/usr/bin/'
    >>> simplify_pathname('/../../../../usr/bin')
    '/usr/bin'
    >>> simplify_pathname('/usr/bin/../bin/./scripts/../')
    '/usr/bin/'
    >>> simplify_pathname('/home/')
    '/home/'
    """
    # Collapse the /////... strings since they do nothing.
    while '//' in path_name:
        path_name = path_name.replace('//', '/')

    pieces = path_name.split('/')
    stack = []

    for piece in pieces:
        if piece == '..':
            if len(stack) > 0:
                stack.pop()
        elif piece != '.':
            stack.append(piece)

    # We need a leading '' to get the initial /.
    if stack[0] != '':
        stack.insert(0, '')
    return '/'.join(stack)
