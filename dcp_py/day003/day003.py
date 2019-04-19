#!/usr/bin/env python3
# day003.py
# By Sebastian Raaphorst, 2019.


class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __repr__(self):
        return f"Node({repr(self.val)}, {repr(self.left)}, {repr(self.right)})"


# Now serialize and deserialize are just repr and eval, respectively.
serialize = repr
deserialize = eval


node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left'
