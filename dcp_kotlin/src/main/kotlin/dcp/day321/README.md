# Day 321 \[Easy\]

This problem was asked by PagerDuty.

Given a positive integer `N`, find the smallest number of steps it will take to reach `1`.

There are two kinds of permitted steps:

* You may decrement `N` to `N - 1`.
* If `a * b = N`, you may decrement `N` to the larger of `a` and `b`.

For example, given `100`, you can reach `1` in five steps with the following route: `100 -> 10 -> 9 -> 3 -> 2 -> 1`.

***Note:*** This solves Day 321 but has variations to explore integer sequences.

Link: [Testing code](../../../../test/kotlin/dcp/day321/day321.kt)