# Day 38 \[Hard\]

This problem was asked by Microsoft.

You have an `N x N` board. Write a function that, given `N`, returns the number of possible arrangements of the board where
`N` queens can be placed on the board without threatening each other, i.e. no two queens share the same row, column, or diagonal.

Link: [Testing code](../../test/TestDay038.cpp)


### Possible future enhancements:

1. Isomorphism checking. We are generating each arrangement multiple times, namely the size of the
symmetry group divided by the size of the automorphism ground. In this case, I believe that the
symmetry group has size eight. Here is a table that shows the difference between a full generation
and an isomorph-free generation:

| `N`    | 0 | 1 | 2 | 3 | 4 |  5 | 6 |  7 |
|-------:|--:|--:|--:|--:|--:|---:|--:|---:|
| unique | 0 | 1 | 0 | 0 | 1 |  2 | 1 |  6 |
| all    | 0 | 1 | 0 | 0 | 2 | 10 | 4 | 40 |

2. Early isomorphism pruning if possible. This will require more thought.

The earlier version of [`day038.h`](day038.h) had mechanisms for trying to capture the automorphism group
and to assign a lexicographical order to boards in an isomorphism class so that we can limit to the
canonical representative and just generate the "unique" boards instead of the "all" boards. This will
enable us to generate more efficiently, and for higher values of `n`.
