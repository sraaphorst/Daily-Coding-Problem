# Day 1 Problem \[Easy\]

This problem was recently asked by Google.

Given a list of numbers and a number `k`, return whether any two numbers from the list add up to `k`.

For example, given `[10, 15, 3, 7]` and `k = 17`, return `true` since `10 + 7 = 17`.

Bonus: Can you do this in one pass?

## Notes

We will work with `std::unordered_map`, since it has average constant complexity lookup.

We begin by storing the count of each elements in the list. This will prevent list elements from being used
twice unless they actually appear twice, e.g.:

1. List `[1, 1]` and `k = 2` is `true` since `1 + 1 = 2`.

2. List `[1]` and `k = 2` is `false` because we need two `1`s.

Then, we iterate over the elements `x` and determine if `k - x` is in the `unordered_map`,
which takes average constant time: thus, the whole algorithm takes average constant time.

Link: [Testing code](../../test/TestDay001.cpp)