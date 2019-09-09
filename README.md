# Daily Coding Problem

These are some practice problems solved in a variety of different languages (depending on the question specification and on my whims) from the Daily Coding Problem challenge:

https://www.dailycodingproblem.com

The languages I used to solve the problem depend on what seems to be the best tool for the job, coupled with my whims
for the day. So far, the programming languages I use are:
* C++17
* Java 10
* Python 3.6
* Scala 2.12
* Kotlin 1.3.40 (in the process of learning)

The C++ testing is done using:
* Unit testing with [Catch2](https://github.com/catchorg/Catch2).

The Java and Scala testing is done using:
* Unit testing with [ScalaTest](https://github.com/scalatest/scalatest).
* Specification testing with ScalaTest and [ScalaCheck](https://github.com/rickynils/scalacheck).

The Python testing is done using:
* Unit testing with [doctests](https://docs.python.org/2/library/doctest.html).
* Specification testing with [Hypothesis](https://github.com/HypothesisWorks/hypothesis).


Here is a brief description of each problem solved so far and a link to the problem in the repo.

## Day 1 \[Easy\]

Given a list and a goal sum, find if two elements in the list sum up to the goal.

Implemented in C++: [Day 1](dcp_cpp/src/day001)

## Day 2 \[Hard\]

Given a list `L`, create a new list `L'` where `L'[i]` is the product of all elements of `L` except `L[i]`.

Implemented in C++: [Day 2](dcp_cpp/src/day002)

## Day 3 \[Medium\]

Create a binary tree `Node` in Python that is serializable and deserializable.

Implemented in Python: [Day 3](dcp_py/day003)

## Day 5 \[Medium\]

Working with `cons`, `car`, and `cdr`.

Implemented in Python: [Day 5](dcp_py/day005)

## Day  7 \[Medium\]

Determining the number of ways to decode numeric strings.

Implemented in Python: [Day 7](dcp_py/day007)

## Day 8 \[Easy\]

Counting unival subtrees.

Implemented in Scala: [Day 8](dcp_jvm/src/main/scala/dcp/day008)

## Day 10 \[Medium\]

Delay a function call by a specified number of ms.

Implemented in Java: [Day 10](dcp_jvm/src/main/java/dcp/day010)

## Day 12 \[Hard\]

Given a staircase of `N` steps and a list `L` of possible step jumps, find the number of ways to ascend the steps.

Implemented in C++: [Day 12](dcp_cpp/src/day012)

## Day 14 \[Medium\]

Use a Monte-Carlo method to estimate pi to three decimal places.

Implemented in C++: [Day 14](dcp_cpp/src/day014)

## Day 19 \[Medium\]

Colour houses according to cost using dynamic programming.

Implemented in Python (and property tested with hyptothesis):
[Day 19](dcp_py/day019)

## Day 20 \[Easy\]

Detect the intersection point in two singly linked lists, if one
exists.

Implemented in Java: [Day 20](dcp_jvm/src/main/java/dcp/day020)

## Day 27 \[Easy\]

Given a string made of parentheses `{`, `}`, `[`, `]`, `(`, and `)`,
determine if it is balanced.

Implemented in C++: [Day 27](dcp_cpp/src/day027)

## Day 28 \[Medium\]

Given a list of words `L` and a line length `k` (with each word at
most length `k`), break the words into full-padded lines of length `k`
with excess padding given to left words.

Implemented in Python: [Day 28](dcp_py/day028)

## Day 29 \[Easy\]

Run-length encoding and decoding of strings.

Implemented in Scala: [Day 29](dcp_jvm/src/main/scala/dcp/day029)

## Day 30 \[Medium\]

Elevation water pooling.

Implemented in C++: [Day 30](dcp_cpp/src/day30)

## Day 31 \[Easy\]

Computing word edit distance

Implemented in Python: [Day 31](dcp_py/day031)

## Day 35 \[Hard\]

Sort a string of `R`s, `G`s, and `B`s  so that all `R`s are on the
left, followed by all `G`s, and then by all `B`s.

Implemented in C++: [Day 35](dcp_cpp/src/day035)

## Day 36 \[Medium\]

Find the second largest element in a binary search tree.

Implemented in Scala (and property tested with ScalaCheck):
[Day 36](dcp_jvm/src/main/scala/dcp/day036)

## Day 38 \[Hard\]

Count the number of solutions to the n-queen problem.

Implemented in C++: [Day 38](dcp_cpp/src/day038)

Future enhancements planned.

## Day 39 \[Medium\]

Visual implementation of Conway's Game of Life.

Implemented in Java: [Day 39](dcp_jvm/src/main/java/dcp/day039)

## Day 43 \[Easy\]

Implement a mutable stack that keeps track of its largest element in
constant time.

Implemented in Scala (and property tested with ScalaCheck):
[Day 43](dcp_jvm/src/main/scala/dcp/day043)

## Day 44 \[Medium\]

Count the number of inversions in an array. Both brute force and
mergesort are implemented.

mplemented in Scala (and property tested with ScalaCheck):
[Day 44](dcp_jvm/src/main/scala/dcp/day044)

## Day 45 \[Easy\]

Given `rand5` that generates `[1,5]` uniformly, derive `rand7`that
generates `[1,7]` uniformly.

Implemented in Python: [Day 45](dcp_py/day045)

## Day 46 \[Hard\]

Given a string, find the longest palindrome that it contains.

Implemented in Python (and property tested with hypothesis):
[Day 46](dcp_py/day046)

## Day 47 \[Easy\]

Given a list of stock price over time, determine the highest profit
per stock you can make.

Implemented in Python (and property tested with hypothesis):
[Day 47](dcp_py/day047)

## Day 48 \[Medium\]

Given the prefix and infix reading of a binary tree, recreate the
tree.

Implemented in Scala (and property tested with Scalacheck):
[Day 48](dcp_jvm/src/main/scala/dcp/day048)

## Day 49 \[Medium\]

Given an array, find the maximum sum amongst its consecutive
subarrays.

Implemented in Python (and property tested with hyptothesis):
[Day 49](dcp_py/day049)

## Day 50 \[Easy\]

Create and evaluate an expression tree.

Implemented in Scala (and property tested with Scalacheck):
[Day 50](dcp_jvm/src/main/scala/dcp/day050)

## Day 51 \[Medium\]

Shuffle a deck of cards uniformly given a uniform random number
generator.

Implemented in Python:
[Day 51](dcp_py/day051)

## Day 53 \[Medium\]

Implement a queue with two stacks.

Implemented in Java:
[Day 53](dcp_jvm/src/main/java/dcp/day053)

## Day 54 \[Hard\]

Implement an efficient sudoku solver.
I have already done this many times:

* [Brute force backtracking in C++ with `constexpr`](https://github.com/sraaphorst/sudoku_constexpr)
* [Using constraint programming](https://github.com/sraaphorst/sudoku_cp)
* [Using Knuth's DLX algorithm in C++ with `constexpr`](https://github.com/sraaphorst/dlx_constexpr)
* [Using Knuth's DLX algorithm in Python](https://github.com/sraaphorst/dlxpy)
* [Using stochastic methods](https://github.com/sraaphorst/sudoku_stochastic)

## Day 55 \[Easy\]

Impleent a base-62 URL shortening algorithm.

Implemented in Python (and property tested with hypothesis)
[Day 55](dcp_py/day055)

## Day 56 \[Medium\]

Write a graph colouring algorithm.

Implemented as brute force backtracking (with basic isomorphic
pruning) in `constexpr` C++: [Day 56](dcp_cpp/src/day056)

## Day 57 \[Medium\]

Split text into lines of maximum length.

Implemented in Python (and property tested with hypothesis):
[Day 57](dcp_py/day057)

## Day 58 \[Medium\]

Given a sorted array that has been rotated and an element, determine
if and where the element is in the array in time less than `O(n)`.

Implemented in Python (and property tested with hypothesis):
[Day 58](dcp_py/day058)

## Day 59 \[Hard\]

Use a Merkle tree to determine the differences between two file
systems.

Implemented in Java, and currently untested:
[Day59](dcp_jvm/src/main/java/dcp/day059)

## Day 62 \[Medium\]

Given an n x m matrix, determine the number of paths from the top left
to the bottom right.

Implemented in Python:
[Day 62](dcp_day062)

## Day 63 \[Easy\]

Given a square matrix of letters, determine if a word appears amongst
the rows or columns.

Implemented in Python:
[Day 63](dcp_day063)

## Day 65 \[Easy\]

Given an `n` by `m` matrix, unwind it as a spiral clockwise from the
top left corner.

Implemented in Python:
[Day 65](dcp_day065)

## Day 66 \[Medium\]

Given a biased coin, find a way to use it to produce an unbiased coin.

Implemented in Python (and property tested with hypothesis):
[Day 66](dcp_py/day066)

## Day 68 \[Medium\]

Given a board of bishops, determine the number of attacking pairs.

Implemented in Python:
[Day 68](dcp_py/day068)

## Day 69 \[Easy\]

Given a list of integers, return the largest product that can be made by multiplying any three integers.

Implemented in Python (and property tested with hypothesis):
[Day 69](dcp_py/day069)

## Day 70 \[Easy\]

Generate "perfect" numbers. Very unclear.

Implemented in Python.
[Day 70](dcp_py/day070)

## Day 73 \[Easy\]

Reverse a singly linked list in place.

Implemented in Java (and property tested with ScalaCheck):
[Day 73](dcp_jvm/src/main/scala/dcp/day073)

## Day 74 \[Medium\]

Find the number of occurrences of a product in a muliplication table.

Implemented in Python (and property tested with hypothesis):
[Day 74](dcp_py/day074)

## Day 75 \[Hard\]

Find the length of the largest increasing subarray within an array.

Implemented in Scala (and property tested with ScalaCheck):
[Day 75](dcp_jvm/src/main/scala/dcp/day075)

## Day 76 \[Medium\]

Given a grid, find the mnimum number of columns that can be removed to
ensure each row is ordered from top to borrom lexicographically.

Implemented in Scala (and property checked with ScalaCheck):
[Day 76](dcp_jvm/src/main/scala/dcp/day076)

## Day 77 \[Easy\]

Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals have been merged.

Implemented in Python (and property tested with hypothesis):
[Day 77](dcp_py/day077)

## Day 91 \[Easy\]

What does the below code snippet print out? How can we fix the anonymous functions to behave as we'd expect?

```
functions = []
for i in range(10):
    functions.append(lambda : i)

for f in functions:
    print(f())
```

Implemented in Python:
[Day 91](dcp_py/day091)


## Day 102 \[Medium\]

Given a sum `k` and a list of elements `L`, find a contiguous sublist
of `L` that sums to `k`.

Implemented in Python (and property tested with hypothesis):
[Day 102](dcp_py/day102)

## Day 104 \[Easy\]

Check if the contents of a doubly linked list are palindromic.

How about a singly linked list?

Implemented in C++: [Day 104](dcp_cpp/src/day104)

## Day 106 \[Medium\]

Given an array of hops, see if you can make it to the end.

Implemented in Python (and property tested with hypothesis):
[Day 106](dcp_py/day106)

## Day 107 \[Easy\]

Given a binary tree, print it row-by-row.

Implemented in Scala: [Day 107](dcp_jvm/src/main/scala/dcp/day107)

## Day 108 \[Easy\]

Determine if one string can be rotated to match antoher.

Implemented in Python: [Day 108](dcp_py/day108)

## Day 109 \[Medium\]

Given a binary string of length 8, interleave the bits, i.e.:

```
a7 a6 a5 a4 a3 a2 a1 a0
```

becomes:

```
a6 a7 a4 a5 a2 a3 a0 a1.
```

Implemented in C++: [Day109](dcp_cpp/src/day109)

## Day 110 \Medium\]

Given a binary tree, return all paths from the roots to the leaves.

Implemented in Scala (and property checked with ScalaCheck):
[Day 110](dcp_jvm/src/main/scala/dcp/day110)

## Day 112 \[Hard\]

Given two nodes in a binary tree, find their least common ancestor.

Implemented in Java:
[Day 112](dcp_jvm/src/main/java/dcp/day112)

## Day 113 \[Medium\]

For a mutable string, reverse the order of its words.

Implemented in C++: [Day 113](dcp_cpp/src/day113)

## Day 114 \[Hard\]

For a mutable string, reverse the order of its words while maintaining
the order of its delimiters. WIP.

Implemented in C++: [Day 114](dcp_cpp/src/day114)

## Day 118 \[Easy\]

Given a sorted array, square the contents of the array and provide the
output in sorted order.

Implemented in `constexpr` C++: [Day 118](dcp_cpp/src/day118)

## Day 119 \[Medium\]

Given a set of intervals, determine a smallest collection of intervals
that cover the intervals.

Implemented in Kotlin: [Day 119](dcp_jvm/src/main/kotlin/dcp/day119)

## Day 120 \[Medium\]

Instead of a singleton, implement an alternating pair of singletons.

Implemented in Java: 
[Day 120](dcp_jvm/src/main/java/dcp/day120)

## Day 121 \[Hard\]

Write a means of identifying k-palindromes.

Implemented in Python:
[Day 121](dcp_py/day121)

## Day 122 \[Medium\]

Given an `n` by `m` matrix, starting at the top left and moving only
right and down, find the largest sum.

Implemeneted in Python: [Day 122](dcp_py/src/day122)

## Day 140 \[Medium\]

Find the two non-repeated elements in a list of integral elements.

Implemented in `constexpr` C++: [Day 140](dcp_cpp/src/day140)

## Day 142 \[Hard\]

Given a string made out of `(`, `)`, and `*`, where `*` can be a
parenthesis or an empty string, determine if it is balanced.

Implemented in Python (and property tested with hypothesis):
[Day 142](dcp_py/day142)

## Day 143 \[Medium\]

Partition a list around a pivot element.

Implemented in Python (and property tested with hypothesis):
[Day 143](dcp_py/day143)

## Day 144 \[Medium\]

Given an array and an index to an element in an array, find the
nearest larger number. (Interpreted incorrectly.)

Implemented in Kotlin: [Day 144](dcp_jvm/src/main/kotlin/dcp/day144)

## Day 145 \[Easy\]

Given a singly linked list, reverse the first and second nodes, the
third and fourth nodes, etc.

Implemented in C++: [Day145](dcp_cpp/src/day145)

## Day 146 \[Medium\]

Given a zero-one tree, process the tree.

Implemented in Scala: [Day 146](dcp_jvm/src/main/scala/dcp/day146)

## Day 151 \[Medium\]

Perform flood-fill on an image.

Implemened in Python: [Day151](dcp_py/day151)


## Day 154 \[Easy\]

Implement a stack via a heap or priority queue.

Impemented in C++: [Day154](dcp_cpp/src/day154)

## Outstanding problems

Some problems are outstanding, and others have been repeated, so they
were not attempted again.
