# Day 276 \[Hard\]

This problem was asked by Dropbox.

Implement an efficient string matching algorithm.

That is, given a string of length `N` and a pattern of length `k`, write a program that searches for the pattern in the string with less than `O(N * k)` worst-case time complexity.

If the pattern is found, return the start index of its location. If not, return `False`.

**NOTE:** This implementation is from [Apostolico-Giancarlo algorithm](https://www.sciencedirect.com/science/article/pii/S1570866703000054) as per the paper at the link.
The Apostolico-Giancarlo algorithm is based on the [Boyer-Moore algorithm](https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm), which still has a worse case of `O(mn)`;
however, applying the [Galil rule](https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_string-search_algorithm#The_Galil_rule) results in:
* a preprocessing phase of `O(k + a)` time and complexity, where `a` is the size of the alphabet; and
* a search phase `O(N)`.

Furthermore, we return a `std::vector<int>` of all match positions instead of simply the first match.

Link: [Testing code](../../test/TestDay276.cpp)