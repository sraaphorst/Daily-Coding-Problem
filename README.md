# Daily Coding Problem

***NOTE***
This is in the process of being reorganized and cleaned up.

These are some practice problems solved in a variety of different languages (depending on the question specification and on my whims) from the Daily Coding Problem challenge:

https://www.dailycodingproblem.com

The languages I used to solve the problem depend on what seems to be the best tool for the job, coupled with my whims
for the day. So far, the programming languages I use are:
* C++17
* Java 10
* Python 3.6
* Scala 2.12
* Kotlin 1.3.50 (in the process of learning)

The C++ testing is done using:
* Unit testing with [Catch2](https://github.com/catchorg/Catch2).

The Java and Scala testing are done using:
* Unit testing with [JUnit](https://junit.org/junit5/docs/current/user-guide).
* Specification testing with ScalaTest and [ScalaCheck](https://github.com/rickynils/scalacheck).

The Python testing are done using:
* Unit testing with [doctests](https://docs.python.org/2/library/doctest.html).
* Specification testing with [Hypothesis](https://github.com/HypothesisWorks/hypothesis).
Note that Python testing is included in the source files.

The Kotlin testing is done using:
* Unit testing with [JUnit](https://junit.org/junit5/docs/current/user-guide).
* Specification testing with [KotlinTest](https://github.com/kotlintest/kotlintest).


Here are the descriptions of the problems solved (not all of them have
been attempted but will progress as time goes on.)


---

### Problem #1 \[Easy\]

This problem was recently asked by Google.

Given a list of numbers and a number `k`, return whether any two numbers from the list add up to `k`.

For example, given `[10, 15, 3, 7]` and `k` of `17`, return true since `10 + 7` is `17`.

Bonus: Can you do this in one pass?

* [C++ implementation](dcp_cpp/src/day001)
* [Unit testing](dcp_cpp/test/TestDay001.cpp)


---

### Problem #2 \[Hard\]

This problem was asked by Uber.

Given an array of integers, return a new array such that each element at index `i` of the new array is the product of all the numbers in the original array except the one at `i`.

For example, if our input was `[1, 2, 3, 4, 5]`, the expected output would be `[120, 60, 40, 30, 24]`. If our input was `[3, 2, 1]`, the expected output would be `[2, 3, 6]`.

Follow-up: what if you can't use division?

* [C++ implementation](dcp_cpp/src/day002)
* [Unit testing](dcp_cpp/test/TestDay002.cpp)


---

### Problem #3 \[Medium\]

This problem was asked by Google.

Given the root to a binary tree, implement `serialize(root)`, which serializes the tree into a string, and `deserialize(s)`, which deserializes the string back into the tree.

For example, given the following `Node` class:

```
class Node:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
```
		
The following test should pass:

```
node = Node('root', Node('left', Node('left.left')), Node('right'))
assert deserialize(serialize(node)).left.left.val == 'left.left'
```

* [Python implementation](dcp_py/day003)


---

### Problem 4 \[Hard\]

This problem was asked by Stripe.

Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

For example, the input `[3, 4, -1, 1]` should give `2`. The input `[1, 2, 0]` should give `3`.

You can modify the input array in-place.

* NOT YET IMPLEMENTED.


---

### Problem 5 \[Medium\]

This problem was asked by Jane Street.

`cons(a, b)` constructs a pair, and `car(pair)` and `cdr(pair)` returns the first and last element of that pair. For example, `car(cons(3, 4))` returns `3`, and `cdr(cons(3, 4))` returns `4`.

Given this implementation of cons:

```
def cons(a, b):
    def pair(f):
        return f(a, b)
	return pair
```		
		
Implement car and cdr.

* [Python implementation](dcp_py/day005)


---

### Problem 6 \[Hard\]

This problem was asked by Google.

An XOR linked list is a more memory efficient doubly linked list. Instead of each node holding next and prev fields, it holds a field named both, which is an XOR of the next node and the previous node. Implement an XOR linked list; it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.

If using a language that has no pointers (such as Python), you can assume you have access to `get_pointer` and dereference_pointer functions that converts between nodes and memory addresses.

* NOT YET IMPLEMENTED


---

### Problem 7 \[Medium\]

This problem was asked by Facebook.

Given the mapping:
```
a = 1, b = 2, ... z = 26
```
and an encoded message, count the number of ways it can be decoded.

For example, the message `111` would give `3`, since it could be decoded as:
* `aaa`,
* `a`, and
* `ak`.

You can assume that the messages are decodable.
For example, `001` is not allowed.

* [Python implementation](dcp_py/day007)


---

### Problem 8 \[Easy\]

This problem was asked by Google.

A *unival tree* (which stands for "universal value") is a tree where all nodes under it have the same value.

Given the root to a binary tree, count the number of unival subtrees.

For example, the following tree has 5 unival subtrees:

```
   0
  / \
 1   0
    / \
   1   0
  / \
 1   1
 ```

* [Scala implementation](dcp_jvm/src/main/scala/dcp/day008)
* [Unit testing](dcp_jvm/src/test/scala/dcp/day008/TestDay008.scala)


---

### Problem 9 \[Hard\]

This problem was asked by Airbnb.

Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, `[2, 4, 6, 2, 5]` should return `13`, since we pick `2`, `6`, and `5`. `[5, 1, 1, 5]` should return `10`, since we pick `5` and `5`.

Follow-up: Can you do this in `O(N)` time and constant space?

* NOT YET IMPLEMENTED


---

### Problem 10 \[Medium\]

This problem was asked by Apple.

Implement a job scheduler which takes in a function `f` and an integer `n`, and calls `f` after `n` milliseconds.

* [Java implementation](dcp_jvm/src/main/java/dcp/day010)
* [Unit testing](dcp_jvm/src/test/scala/dcp/day010/TestDay010.scala)


---

### Problem 11 \[Medium\]

This problem was asked by Twitter.

Implement an autocomplete system. That is, given a query string `s` and a set of all possible query strings, return all strings in the set that have `s` as a prefix.

For example, given the query string `de` and the set of strings `[dog, deer, deal]`, return `[deer, deal]`.

Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.

* NOT YET IMPLEMENTED


---

### Problem 12 \[Hard\]

This problem was asked by Amazon.

There exists a staircase with `N` steps, and you can climb up either 1 or 2 steps at a time. Given `N`, write a function
that returns the number of unique ways you can climb the staircase. The order of the steps matters.

What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive
integers `X`? For example, if `X = {1, 3, 5}`, you could climb 1, 3, or 5 steps at a time.

* [C++ implementation](dcp_cpp/src/day012)
* [Unit testing](dcp_cpp/test/TestDay012.cpp)


---

### Problem 13 \[Hard\]

This problem was asked by Amazon.

Given an integer `k` and a string `s`, find the length of the longest substring that contains at most `k` distinct characters.

For example, given `s = "abcba"` and `k = 2`, the longest substring with `k` distinct characters is `"bcb"`.

* NOT YET IMPLEMENTED


---

### Problem 14 \[Medium\]

This problem was asked by Google.

The area of a circle is defined as `πr^2`.

Estimate `π` to 3 decimal places using a Monte Carlo method.

Hint: The basic equation of a circle is `x^2 + y^2 = r^2`.

* [C++ implementation](dcp_cpp/src/day014)
* [Unit testing](dcp_cpp/test/TestDay014.cpp)


---

### Problem 15 \[Medium\]

This problem was asked by Facebook.

Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.

* NOT YET IMPLEMENTED


---

### Problem 16 \[Easy\]

This problem was asked by Twitter.

You run an e-commerce website and want to record the last `N` order ids in a log. Implement a data structure to accomplish this, with the following API:

`record(order_id)`: adds the `order_id` to the log
`get_last(i)`: gets the `i`th last element from the log. `i` is guaranteed to be smaller than or equal to `N`.
You should be as efficient with time and space as possible.

* NOT YET IMPLEMENTED


---

### Problem 17 

This problem was asked by Google.

Suppose we represent our file system by a string in the following manner:

The string `"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"` represents:

```
dir
    subdir1
    subdir2
        file.ext
```
The directory dir contains an empty sub-directory `subdir1` and a sub-directory `subdir2` containing a file `file.ext`.

The string `"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"` represents:

```
dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
```
The directory `dir` contains two sub-directories `subdir1` and `subdir2`. `subdir1` contains a file `file1.ext` and an empty second-level sub-directory `subsubdir1`. `subdir2` contains a second-level sub-directory `subsubdir2` containing a file `file2.ext`.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is `"dir/subdir2/subsubdir2/file2.ext"`, and its length is `32` (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return `0`.

Note:

The name of a file contains at least a period and an extension.

The name of a directory or sub-directory will not contain a period.

* NOT YET IMPLEMENTED


---

### Problem 18 \[Hard\]

This problem was asked by Google.

Given an array of integers and a number `k`, where `1 <= k <= length` of the array, compute the maximum values of each subarray of length `k`.

For example, given `array = [10, 5, 2, 7, 8, 7]` and `k = 3`, we should get: `[10, 7, 8, 8]`, since:
```
10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)
```
Do this in `O(n)` time and `O(k)` space. You can modify the input array in-place and you do not need to store the results. You can simply print them out as you compute them.

* NOT YET IMPLEMENTED



---

### Day 19 \[Medium\]

This problem was asked by Facebook.

A builder is looking to build a row of `N` houses that can be of `K` different colors.

He has a goal of minimizing cost while ensuring that no two neighboring houses are of the same color.


Given an `N x K` matrix where the `n`th row and `k`th column represents the cost to build the nth house with kth color, return the minimum cost which achieves this goal.

[Implemented in Python](dcp_py/day019)


---

### Problem 20 \[Easy\]

This problem was asked by Google.

Given two singly linked lists that intersect at some point, find the intersecting node. The lists are non-cyclical.

For example, given:
```
A = 3 -> 7 -> 8 -> 10
```
and
```
B = 99 -> 1 -> 8 -> 10
```
return the node with value `8`.

In this example, assume nodes with the same value are the exact same node objects.

Do this in `O(M + N)` time (where `M` and `N` are the lengths of the lists) and constant space.

* [Java implementation](dcp_jvm/src/main/java/dcp/day020)
* [Unit testing](dcp_jvm/src/test/scala/dcp/day020/TestDay020.scala)


---

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


## Day 64 \[Hard\

This problem was asked by Google.

A knight's tour is a sequence of moves by a knight on a chessboard such that all squares are visited once.

Given `N`, write a function to return the number of knight's tours on an `N` by `N` chessboard.

Implemented as brute force backtracking in `constexpr` C++: [Day 64](dcp_cpp/src/day064). The implementation takes `N` and a a starting position and calculates the number of paths. Isomorphism is ignored.

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

Implemented in C++: [Day 109](dcp_cpp/src/day109)

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

Implemented in Kotlin: [Day 119](dcp_kotlin/src/main/kotlin/dcp/day119)

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

Implemeneted in Python: [Day 122](dcp_py/day122)

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

Implemented in Kotlin: [Day 144](dcp_kotlin/src/main/kotlin/dcp/day144)

## Day 145 \[Easy\]

Given a singly linked list, reverse the first and second nodes, the
third and fourth nodes, etc.

Implemented in C++: [Day 145](dcp_cpp/src/day145)

## Day 146 \[Medium\]

Given a zero-one tree, process the tree.

Implemented in Scala: [Day 146](dcp_jvm/src/main/scala/dcp/day146)

## Day 151 \[Medium\]

Perform flood-fill on an image.

Implemened in Python: [Day 151](dcp_py/day151)

## Day 154 \[Easy\]

Implement a stack via a heap or priority queue.

Impemented in C++: [Day 154](dcp_cpp/src/day154)

## Day 155 \[Medium\]

Given a list of elements, find the majority element, which appears
more than half the time.

Implemented in C++: [Day 155](dcp_cpp/src/day155)

## Day 156 \[Medium\]

For a given positive integer `n`, determine the smallest numnber of
squared integers which sums up to `n`.

Implemented in C++: [Day 156](dcp_cpp/src/day156)

## Day 157 \[Easy\]

Determine if a string is a permutation of a palindrome.

Implemented in C++: [Day 157](dcp_cpp/src/day157)

## Day 158 \[Medium\]

Given a matrix indicating a floor plan, determine the number of paths
through a matrix.

Implemented in Python: [Day 158](dcp_py/day158)

## Day 159 \[Medium\]

For a given string, return the first duplicated character in it if one
exists.

Implemented in Python: [Day 159](dcp_py/day159)

## Day 161 \[Easy\]

Flip a 32 bit integer.

Implemented in C++: [Day 161](dcp_cpp/src/day161)

## Day 162 \[Medium\]

Given a set of words, determine its unique set of minimal prefixes.

Implemented in C++: [Day 162](dcp_cpp/src/day162)

## Day 163 \[Hard\]

Implement a Reverse Polish Notation calculator evaluator.

Implemented in Scala: [Day 163](dcp_jvm/src/main/scala/dcp/day163)

## Day 164 \[Medium\]

Implement a pigeon hole detector in `O(n)` time and `O(n)` space.

Implemented in C++: [Day 164](dcp_cpp/src/day164)


## Day 170 \[Medium\]

This problem was asked by Facebook.

Given a start word, an end word, and a dictionary of valid words, find the shortest transformation sequence from start to end such that only one letter is changed at each step of the sequence, and each transformed word exists in the dictionary. If there is no possible transformation, return null. Each word in the dictionary have the same length as start and end and is lowercase.

For example, given `start = "dog"`, `end = "cat"`, and `dictionary = {"dot", "dop", "dat", "cat"}`, return `["dog", "dot", "dat", "cat"]`.

Given `start = "dog"`, `end = "cat"`, and `dictionary = {"dot", "tod", "dat", "dar"}`, return `null` as there is no possible transformation from `dog` to `cat`.

Implemented in Python: [Day 170](dcp_py/day170)

## Day 171 \[Medium\]

This problem was asked by Dropbox.

Given a string `s` and a `list` of words `words`, where each word is the same length, find all starting indices of substrings in `s` that is a concatenation of every word in `words` exactly once.

For example:

1. `s = dogcatcatcodecatdog`
2. `words = [cat", "dog]`
3. `return [0, 13]`, since `dogcat` starts at `0` and `catdog` starts at index `13`.

For example:

1. `s = barfoobazbitbyte"`
2. `words = ["dog", "cat"]`
3. `return []` since there are no substrings composed of `dog` and `cat` in `s`.

The order of the indices does not matter.

Implemented in Python: [Day 171](dcp_py/day171)


## Day 173 \[Easy\]

This problem was asked by Stripe.

Write a function to flatten a nested dictionary. Namespace the keys with a period.

For example, given the following dictionary:

```
{
    "key": 3,
    "foo": {
        "a": 5,
        "bar": {
            "baz": 8
        }
    }
}
```

it should become:

```
{
    "key": 3,
    "foo.a": 5,
    "foo.bar.baz": 8
}
```

You can assume keys do not contain dots in them, i.e. no clobbering
will occur.

Implemented in Python: [Day 173](dcp_py/day173)


## Day 174 \[Medium\]

This problem was asked by Microsoft.

Describe and give an example of each of the following types of polymorphism:

* Ad-hoc polymorphism
* Parametric polymorphism
* Subtype polymorphism

### Ad-hoc polymorphism:

Ad-hoc polymorphism is specifically bound to a type, and thus requires
different implementations depending on type in order to invoke. This
is essentially method or function overloading. An example includes
addition: lists would be concatenated, whereas integers would be
added.

```C++
int add1(int a1) {
    return a1 + 1;
}
double add(double d) {
    return d + 1;
}
```

### Parametric polymorphism

Parametric polymorphism imposes a type parameter on a
structure. Examples include lists (e.g. `List[Int]`,
`List[Double]`). A method, say, `head`, is parameterized in the sense
that it doesn't care about the type of the list: it simply returns the
first item in the list and its implementation is the same for all types.

```scala
import scala.math.sqrt

case class Tree[+T](value: T, left: Option[Tree[T]], right: Option[Tree[T]]) {
  def tree_map[S](f: T => S): Tree[S] =
    Tree(f(value), left.map(_.tree_map(f)), right.map(_.tree_map(f)))
}

object Tree extends App {
  val leaf1 = Tree(5, None, None)
  val leaf2 = Tree(3, None, None)
  val root = Tree(4, Some(leaf2), Some(leaf1))
  println(root.tree_map(x => sqrt(x.toDouble)))
}
```

### Subtype polymorphism

Subclasses provide different implementations of a superclass method:
these are like virtual functions in C++. A simple example would be:

```C++
using sound = std::optional<std::string>;

struct Animal {
  virtual sound noise() const noexcept = 0;
};

struct Cat {
  virtual sound noise() const noexcept {
    return "meow";
  }
};

struct Earthworm {
  sound noise() const noexcept {
    return {};
  }
};
```


## Day 175: \[Easy\]

This problem was asked by Google.

You are given a starting state start, a list of transition probabilities for a Markov chain, and a number of steps num_steps. Run the Markov chain starting from start for num_steps and compute the number of times we visited each state.

For example, given the starting state `a`, number of steps `5000`, and the following transition probabilities:
```
[
  ('a', 'a', 0.9),
  ('a', 'b', 0.075),
  ('a', 'c', 0.025),
  ('b', 'a', 0.15),
  ('b', 'b', 0.8),
  ('b', 'c', 0.05),
  ('c', 'a', 0.25),
  ('c', 'b', 0.25),
  ('c', 'c', 0.5)
]
```
One instance of running this Markov chain might produce:
```
{ 'a': 3012, 'b': 1656, 'c': 332 }
```

Implemented in Python: [Day 175](dcp_py/day175)


## Day 176 \[Easy\]

This problem was asked by Bloomberg.

Determine whether there exists a one-to-one character mapping from one string `s1` to another `s2`.

For example, given `s1 = abc` and `s2 = bcd`, return true since we can map `a` to `b`, `b` to `c`, and `c` to `d`.

Given `s1 = foo` and `s2 = bar`, return `false` since the `o` cannot
map to two characters.

Implemented in Python: [Day 176](dcp_py/day176)


## Day 177 \[Easy\]

This problem was asked by Airbnb.

Given a linked list and a positive integer `k`, rotate the list to the right by `k` places.

For example, given the linked list `7 -> 7 -> 3 -> 5` and `k = 2`, it should become `3 -> 5 -> 7 -> 7`.

Given the linked list `1 -> 2 -> 3 -> 4 -> 5` and `k = 3`, it should
become `3 -> 4 -> 5 -> 1 -> 2`.

Implemented in C++: [Day 177](dcp_cpp/src/day177)


## Day 178 \[Hard\]

This problem was asked by Two Sigma.

Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter? Write a program to simulate the two games and calculate their expected value.

Simultation implemented in Python, and explanation given here:
[Day 178](dcp_py/day178)


## Day 179 \[Medium\]

This problem was asked by Google.

Given the sequence of keys visited by a postorder traversal of a binary search tree, reconstruct the tree.

For example, given the sequence `2, 4, 3, 8, 7, 5`, you should construct the following tree:

```
    5
   / \
  3   7
 / \   \
2   4   8
```

Implemented in Scala: [Day 179](dcp_jvm/src/main/scala/dcp/day179)


## Day 180 \[Medium\]

This problem was asked by Google.

Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one other queue. This should be done in-place.

Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.

For example, if the stack is `[1, 2, 3, 4, 5]`, it should become `[1, 5, 2, 4, 3]`. If the stack is `[1, 2, 3, 4]`, it should become `[1, 4, 2, 3]`.

Hint: Try working backwards from the end state.

Implemented in C++: [Day 180](dcp_cpp/src/day180)


## Day 182 \[Medium\]

This problem was asked by Facebook.

A graph is minimally-connected if it is connected and there is no edge that can be removed while still leaving the graph connected. For example, any binary tree is minimally-connected.

Given an undirected graph, check if the graph is
minimally-connected. You can choose to represent the graph as either
an adjacency matrix or adjacency list.

Implemented in Python: [Day 182](dcp_py/day182)


## Day 185 \[Easy\]

This problem was asked by Google.

Given two rectangles on a 2D graph, return the area of their intersection. If the rectangles don't intersect, return `0`.

For example, given the following rectangles:

```
{
    "top_left": (1, 4),
    "dimensions": (3, 3) # width, height
}
```

and

```
{
    "top_left": (0, 5),
    "dimensions": (4, 3) # width, height
}
```

return `6`.


## Day 184 \[Easy\]

This problem was asked by Amazon.

Given n numbers, find the greatest common denominator between them.

For example, given the numbers `[42, 56, 14]`, return `14`.

Implemented in C++ using variadic templates: [Day 184](dcp_cpp/src/day184)


## Day 185 \[Easy\]

This problem was asked by Google.

Given two rectangles on a 2D graph, return the area of their intersection. If the rectangles don't intersect, return `0`.

For example, given the following rectangles:

```
{
    "top_left": (1, 4),
    "dimensions": (3, 3) # width, height
}
```

and

```
{
    "top_left": (0, 5),
    "dimensions": (4, 3) # width, height
}
```

return `6`.

Implemented in Kotlin: [Day 185](dcp_kotlin/src/main/kotlin/dcp/day185)


## Day 186 \[Hard\]

This problem was asked by Microsoft.

Given an array of positive integers, divide the array into two subsets such that the difference between the sum of the subsets is as small as possible.

For example, given `[5, 10, 15, 20, 25]`, return the sets `{10, 25}` and
`{5, 15, 20}`, which has a difference of `5`, which is the smallest
possible difference.

Implemented in Python: [Day 186](dcp_py/day186)

## Day 188 \[Medium\]

This problem was asked by Google.

What will this code print out?

```
def make_functions():
    flist = []

    for i in [1, 2, 3]:
        def print_i():
            print(i)
        flist.append(print_i)

    return flist

functions = make_functions()
for f in functions:
    f()
```

How can we make it print out what we apparently want?

Implemented in Python: [Day 188](dcp_py/day188)

## Day 194 \[Easy\]

This problem was asked by Facebook.

Suppose you are given two lists of `n` points, one list:

* `p1, p2, ..., pn` on the line `y = 0`; and
* the other list `q1, q2, ..., qn` on the line `y = 1`.

Imagine a set of `n` line segments connecting each point `pi` to
`qi`. Write an algorithm to determine how many pairs of the line
segments intersect.

Implemented in Kotlin: [Day 194](dcp_kotlin/src/main/kotlin/dcp/day194)


## Day 198 \[Medium\]

This problem was asked by Google.

Given a set of distinct positive integers, find the largest subset such that every pair of elements in the subset `(i, j)` satisfies either `i % j = 0` or `j % i = 0`.

For example, given the set `[3, 5, 10, 20, 21]`, you should return `[5, 10, 20]`. Given `[1, 3, 6, 24]`, return `[1, 3, 6, 24]`.

Implemented in Kotlin: [Day 198](dcp_kotlin/src/main/kotlin/dcp/day198)


## Day 199 - \[Hard\]

This problem was asked by Facebook.

Given a string of parentheses, find the balanced string that can be produced from it using the minimum number of insertions and deletions. If there are multiple solutions, return any of them.

For example, given `"(()"`, you could return `"(())"`. Given
`"))()("`, you could return `"()()()()"`.

Implemented in Kotlin: [Day 199](dcp_kotlin/src/main/kotlin/dcp/day199)

## Day 200 \[Hard\]

This problem was asked by Microsoft.

Let `X` be a set of `n` intervals on the real line. We say that a set of points `P` "stabs" `X` if every interval in `X` contains at least one point in `P`. Compute the smallest set of points that stabs `X`.

For example, given the intervals `[(1, 4), (4, 5), (7, 9), (9, 12)]`,
you should return `[4, 9]`.

Implemented in Kotlin: [Day 200](dcp_kotlin/src/main/kotlin/dcp/day200)

## Day 201 \[Easy\]

This problem was asked by Google.

You are given an array of arrays of integers, where each array corresponds to a row in a triangle of numbers. For example, `[[1], [2, 3], [1, 5, 1]]` represents the triangle:

```
  1
 2 3
1 5 1
```
We define a path in the triangle to start at the top and go down one row at a time to an adjacent value, eventually ending with an entry on the bottom row. For example, `1 -> 3 -> 5`. The weight of the path is the sum of the entries.

Write a program that returns the weight of the maximum weight path.

Implemented in Kotlin: [Day 201](dcp_kotlin/src/main/kotlin/dcp/day201)

## Day 206 \[Easy\]

This problem was asked by Twitter.

A permutation can be specified by an array `P`, where `P[i]` represents the location of the element at `i` in the permutation. For example, `[2, 1, 0]` represents the permutation where elements at the index 0 and 2 are swapped.

Given an array and a permutation, apply the permutation to the array. For example, given the array `["a", "b", "c"]` and the permutation `[2, 1, 0]`, return `["c", "b", "a"]`.

Implemented in Kotlin: [Day 206](dcp_kotlin/src/main/kotlin/dcp/day206)

## Day 207 \[Medium\]

This problem was asked by Dropbox.

Given an undirected graph `G`, check whether it is bipartite. Recall
that a graph is bipartite if its vertices can be divided into two
independent sets, `U` and `V`, such that no edge connects vertices of
the same set.

Implemented in Kotlin:
[Day 207](dcp_kotlin/src/main/kotlin/dcp/day207)

## Day 208 \[Easy\]

This problem was asked by Apple.

A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:

* If n is even, the next number in the sequence is `n / 2`.
* If n is odd, the next number in the sequence is `3n + 1`.

It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.

Bonus: What input `n <= 1000000` gives the longest sequence?

Implemented in C++: [Day 208](dcp_cpp/src/day208)


## Day 211 \[Medium\]

This problem was asked by Microsoft.

Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string. For example, given the string `"abracadabra"` and the pattern `"abr"`, you should return `[0, 7]`.

Implemented in Kotlin:
[Day 211](dcp_kotlin/src/main/kotlin/dcp/day211)

## Day 212 \[Easy\]

This problem was asked by Dropbox.

Spreadsheets often use this alphabetical encoding for its columns: `"A"`, `"B"`, `"C"`, ..., `"AA"`, `"AB"`, ..., `"ZZ"`, `"AAA"`, `"AAB"`, ....

Given a column number, return its alphabetical column id. For example,
given `1`, return `"A"`. Given `27`, return `"AA"`.

Implemented in Kotlin:
[Day 212](dcp_kotlin/src/main/kotlin/dcp/day212)


## Day 213 \[Medium\]

This problem was asked by Snapchat.

Given a string of digits, generate all possible valid IP address combinations.

IP addresses must follow the format `A.B.C.D`, where `A`, `B`, `C`, and `D` are numbers between `0` and `255`. Zero-prefixed numbers, such as `01` and `065`, are not allowed, except for `0` itself.

For example, given `"2542540123"`, you should return `['254.25.40.123', '254.254.0.123']`.

Implemented in Python: [Day 213](dcp_py/day213)


## Day 215 \[Medium\]

INCOMPLETE

This problem was asked by Yelp.

The horizontal distance of a binary tree node describes how far left or right the node will be when the tree is printed out.

More rigorously, we can define it as follows:

* The horizontal distance of the root is `0`.
* The horizontal distance of a left child is `hd(parent) - 1`.
* The horizontal distance of a right child is `hd(parent) + 1`.

For example, for the following tree, `hd(1) = -2`, and `hd(6) = 0`.

```
             5
          /     \
        3         7
      /  \      /   \
    1     4    6     9
   /                /
  0                8
```

The bottom view of a tree, then, consists of the lowest node at each horizontal distance. If there are two nodes at the same depth and horizontal distance, either is acceptable.

For this tree, for example, the bottom view could be `[0, 1, 3, 6, 8, 9]`.

Given the root to a binary tree, return its bottom view.


## Day 216 \[Medium\]

TODO: NEEDS KOTLIN CLEANUP.

This problem was asked by Facebook.

Given a number in Roman numeral format, convert it to decimal.

The values of Roman numerals are as follows:

```
    'M': 1000,
    'D': 500,
    'C': 100,
    'L': 50,
    'X': 10,
    'V': 5,
    'I': 1
```

In addition, note that the Roman numeral system uses subtractive notation for numbers such as `IV` and `XL`.

For the input `XIV`, for instance, you should return `14`.

Implemented in Kotlin:
[Day 216](dcp_kotlin/src/main/kotlin/dcp/day216)


## Day 218 \[Medium\]

This problem was asked by Yahoo.

Write an algorithm that computes the reversal of a directed graph. For example, if a graph consists of `A -> B -> C`, it should become `A <- B <- C`.

Implemented in Kotlin:
[Day 218](dcp_kotlin/src/main/kotlin/dcp/day218)


## Day 219 \[Hard\]

This problem was asked by Salesforce.

Connect 4 is a game where opponents take turns dropping red or black discs into a `7 x 6` vertically suspended grid. The game ends either when one player creates a line of four consecutive discs of their color (horizontally, vertically, or diagonally), or when there are no more spots left in the grid.

Design and implement Connect 4.

Implemented in Java:
* [Day 219](src/main/java/dcp/day219)


## Day 221 \[Easy\]

This problem was asked by Zillow.

Let's define a "sevenish" number to be one which is either a power of 7, or the sum of unique powers of 7. The first few sevenish numbers are 1, 7, 8, 49, and so on. Create an algorithm to find the `n`th sevenish number.

mplemented in Kotlin:
[Day 221](dcp_kotlin/src/main/kotlin/dcp/day221)


## Day 222 \[Medium\]

This problem was asked by Quora.

Given an absolute pathname that may have `.` or `..` as part of it, return the shortest standardized path.

For example, given `"/usr/bin/../bin/./scripts/../"`, return
`"/usr/bin/"`.

Implemented in Python: [Day 222](dcp_py/day222)


## Outstanding problems

Some problems are outstanding, and others have been repeated, so they
were not attempted again.
