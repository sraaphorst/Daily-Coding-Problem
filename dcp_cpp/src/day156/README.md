# Day 156 \[Medium\]

This problem was asked by Facebook.

Given a positive integer `n`, find the smallest number of squared integers which sum to `n`.

For example, given `n = 13`, return `2` since `13 = 3^2 + 2^2 = 9 + 4`.

Given `n = 27`, return `3` since `27 = 3^2 + 3^2 + 3^2 = 9 + 9 + 9`.

Note:
* By Lagrange's four-square theorem, the answer is always at most 4:
https://en.wikipedia.org/wiki/Lagrange%27s_four-square_theorem
* We use Legende's three-square theorem to identify cases when the answer is 4:
https://en.wikipedia.org/wiki/Legendre%27s_three-square_theorem
This happens precisely when `n = 4^k(8m+7)` for integers `k` and `m`.

Link: [Testing code](../../test/TestDay156.cpp)