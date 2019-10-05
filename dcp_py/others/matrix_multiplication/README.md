# Matrix multiplication

Given a list of matrix dimensions of the form `M_i = dims[i-1] x dims[i]`, determine the fewest / most operations
needed to multiply matrices of those dimensions together.

This is done by recognizing that matrix multiplication is associative, i.e. `ABC = (AB)C = A(BC)`, so the order
in which the matrices are multiplied are invariant in determining the final result.

Furthermore, we use the following information: if we are multiplying two matrices, say:
* `A`, with `m` rows and `n` columns
* `B`, with `n` rows and `k` columns

The resultant matrix `AB` will have `m x k` entries. Each entry in AB will require the dot product of a row of A with
a column of B, which requires `n` multiplications and `(n-1)` additions to sum up the multiplications. Since they are
negligible, we ignore the `(n-1)` additions and instead estimate the number of simple operations to multiply matrices
A and B to be:
    
```
m x n x k.
```