# Day 2 \[Hard\]

This problem was asked by Uber.

Given an array of integers, return a new array such that each element at index `i`
of the new array is the product of all the numbers in the original array except the one at `i`.

**Example 1:** If our input was:

```
[1, 2, 3, 4, 5]
```

the expected output would be

```
[120, 60, 40, 30, 24]
```

**Example 2:** If our input was:

```
[3, 2, 1]
```

the expected output would be

```
[2, 3, 6]
```

**Follow-up:** what if you can't use division?

## Notes

Assume we have a list `L` of length `n`.

If you can use division, the problem is easy: find the product `p` of all elements in `L`, and
then to our new list `L'`, assign:

```
L'[i] = p / L[i]
```

If you cannot use division, this becomes more difficult. The idea we use is to then make two arrays,
`left` and `right`, where:

1. `left[i]` is the product of `L[0] * L[1] * ... * L[i-1]`.

2. `right[i]` is the product of `L[i+1] * L[1+2] * ... * L[n-1]`.

Then to our new list `L'`, assign:

```
L'[i] = left[i] * right[i]
```