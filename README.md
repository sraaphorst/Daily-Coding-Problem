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

Note that this was partly an exercise to learn Kotlin, so many of the
exercises are done in Kotlin to reinforce the learning I was doing in
Coursera's class, Kotlin for Java Developers. The earlier Kotlin
exercises done in 2019 will clearly be rough around the edges due to my inexperience.

Here are the descriptions of the problems solved (not all of them have
been attempted but will progress as time goes on.)


---

### Day 1 \[Easy\]

This problem was recently asked by Google.

Given a list of numbers and a number `k`, return whether any two numbers from the list add up to `k`.

For example, given `[10, 15, 3, 7]` and `k` of `17`, return true since `10 + 7` is `17`.

Bonus: Can you do this in one pass?

* [C++ implementation](dcp_cpp/src/day001)
* [Unit testing](dcp_cpp/test/TestDay001.cpp)


---

### Day 2 \[Hard\]

This problem was asked by Uber.

Given an array of integers, return a new array such that each element at index `i` of the new array is the product of all the numbers in the original array except the one at `i`.

For example, if our input was `[1, 2, 3, 4, 5]`, the expected output would be `[120, 60, 40, 30, 24]`. If our input was `[3, 2, 1]`, the expected output would be `[2, 3, 6]`.

Follow-up: what if you can't use division?

* [C++ implementation](dcp_cpp/src/day002)
* [Unit testing](dcp_cpp/test/TestDay002.cpp)


---

### Day 3 \[Medium\]

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

### Day 4 \[Hard\]

This problem was asked by Stripe.

Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.

For example, the input `[3, 4, -1, 1]` should give `2`. The input `[1, 2, 0]` should give `3`.

You can modify the input array in-place.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day004)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day004/day004.kt)

---

### Day 5 \[Medium\]

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

### Day 6 \[Hard\]

This problem was asked by Google.

An XOR linked list is a more memory efficient doubly linked list. Instead of each node holding `next` and `prev` fields, it holds a field named `both`, which is an XOR of the next node and the previous node. Implement an XOR linked list; it has an
* `add(element)` which adds the element to the end, and a
* `get(index)` which returns the node at index.

If using a language that has no pointers (such as Python), you can assume you have access to `get_pointer` and `dereference_pointer` functions that converts between nodes and memory addresses.

* [C++ implementation](dcp_cpp/src/day006)
* [Unit testing](dcp_cpp/test/TestDay006.cpp)

---

### Day 7 \[Medium\]

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

### Day 8 \[Easy\]

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

### Day 9 \[Hard\]

This problem was asked by Airbnb.

Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.

For example, `[2, 4, 6, 2, 5]` should return `13`, since we pick `2`, `6`, and `5`. `[5, 1, 1, 5]` should return `10`, since we pick `5` and `5`.

Follow-up: Can you do this in `O(N)` time and constant space?

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day009)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day009/day009.kt)

---

### Day 10 \[Medium\]

This problem was asked by Apple.

Implement a job scheduler which takes in a function `f` and an integer `n`, and calls `f` after `n` milliseconds.

* [Java implementation](dcp_jvm/src/main/java/dcp/day010)
* [Unit testing](dcp_jvm/src/test/scala/dcp/day010/TestDay010.scala)

---

### Day 11 \[Medium\]

This problem was asked by Twitter.

Implement an autocomplete system. That is, given a query string `s` and a set of all possible query strings, return all strings in the set that have `s` as a prefix.

For example, given the query string `de` and the set of strings `[dog, deer, deal]`, return `[deer, deal]`.

Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day011)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day011/day011.kt)

---

### Day 12 \[Hard\]

This problem was asked by Amazon.

There exists a staircase with `N` steps, and you can climb up either 1 or 2 steps at a time. Given `N`, write a function
that returns the number of unique ways you can climb the staircase. The order of the steps matters.

What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive
integers `X`? For example, if `X = {1, 3, 5}`, you could climb 1, 3, or 5 steps at a time.

* [C++ implementation](dcp_cpp/src/day012)
* [Unit testing](dcp_cpp/test/TestDay012.cpp)


---

### Day 13 \[Hard\]

This problem was asked by Amazon.

Given an integer `k` and a string `s`, find the length of the longest substring that contains at most `k` distinct characters.

For example, given `s = "abcba"` and `k = 2`, the longest substring with `k` distinct characters is `"bcb"`.

* NOT YET IMPLEMENTED


---

### Day 14 \[Medium\]

This problem was asked by Google.

The area of a circle is defined as `πr^2`.

Estimate `π` to 3 decimal places using a Monte Carlo method.

Hint: The basic equation of a circle is `x^2 + y^2 = r^2`.

* [C++ implementation](dcp_cpp/src/day014)
* [Unit testing](dcp_cpp/test/TestDay014.cpp)

---

### Day 15 \[Medium\]

This problem was asked by Facebook.

Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day015)
* Testing done in `main` function.

---

### Day 16 \[Easy\]

This problem was asked by Twitter.

You run an e-commerce website and want to record the last `N` order ids in a log. Implement a data structure to accomplish this, with the following API:

* `record(order_id)`: adds the `order_id` to the log
* `get_last(i)`: gets the `i`th last element from the log. `i` is guaranteed to be smaller than or equal to `N`.

You should be as efficient with time and space as possible.

* [C++ implementation](dcp_cpp/src/day016)
* [Unit testing](dcp_cpp/test/TestDay016.cpp)

---

### Day 17 \[Hard\]

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

### Day 18 \[Hard\]

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

### Day 20 \[Easy\]

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

### Day 27 \[Easy\]

This problem was asked by Facebook.

Given a string of round, curly, and square open and closing brackets, return whether the brackets are balanced (well-formed).

For example, given the string `"([])[]({})"`, you should return `true`.

Given the string `"([)]"` or `"((()"`, you should return `false`.

* [C++ implementation](dcp_cpp/src/day027)
* [Unit testing](dcp_cpp/test/TestDay027.cpp)

---

### Day 28 \[Medium\]

This problem was asked by Palantir.

Write an algorithm to justify text. Given a sequence of words and an integer line length `k`, return a list of strings which represents each line, fully justified.

More specifically, you should have as many words as possible in each line. There should be at least one space between each word. Pad extra spaces when necessary so that each line has exactly length `k`. Spaces should be distributed as equally as possible, with the extra spaces, if any, distributed starting from the left.

If you can only fit one word on a line, then you should pad the right-hand side with spaces.

Each word is guaranteed not to be longer than `k`.

For example, given the list of words `["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"]` and` k = 16`, you should return the following:

* `["the  quick brown"`, # 1 extra space on the left
* `"fox  jumps  over"`, # 2 extra spaces distributed evenly
* `"the   lazy   dog"]` # 4 extra spaces distributed evenly

Implemented in Python: [Day 28](dcp_py/day028)

---

### Day 29 \[Easy\]

This problem was asked by Amazon.

Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated successive characters as a single count and character. For example, the string `"AAAABBBCCDAA"` would be encoded as `"4A3B2C1D2A"`.

Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists solely of alphabetic characters. You can assume the string to be decoded is valid.

Implemented in Scala: [Day 29](dcp_jvm/src/main/scala/dcp/day029)

---

### Day 30 \[Medium\]

Elevation water pooling.

Implemented in C++: [Day 30](dcp_cpp/src/day30)

---

### Day 31 \[Easy\]

Computing word edit distance

Implemented in Python: [Day 31](dcp_py/day031)

---

### Day 35 \[Hard\]

Sort a string of `R`s, `G`s, and `B`s  so that all `R`s are on the
left, followed by all `G`s, and then by all `B`s.

Implemented in C++: [Day 35](dcp_cpp/src/day035)

---

### Day 36 \[Medium\]

Find the second largest element in a binary search tree.

Implemented in Scala (and property tested with ScalaCheck):
[Day 36](dcp_jvm/src/main/scala/dcp/day036)

---

### Day 38 \[Hard\]

Count the number of solutions to the n-queen problem.

Implemented in C++: [Day 38](dcp_cpp/src/day038)

Future enhancements planned.

---

### Day 39 \[Medium\]

Visual implementation of Conway's Game of Life.

Implemented in Java: [Day 39](dcp_jvm/src/main/java/dcp/day039)

---

### Day 43 \[Easy\]

Implement a mutable stack that keeps track of its largest element in
constant time.

Implemented in Scala (and property tested with ScalaCheck):
[Day 43](dcp_jvm/src/main/scala/dcp/day043)

---

### Day 44 \[Medium\]

Count the number of inversions in an array. Both brute force and
mergesort are implemented.

Implemented in Scala (and property tested with ScalaCheck):
[Day 44](dcp_jvm/src/main/scala/dcp/day044)

---

### Day 45 \[Easy\]

Given `rand5` that generates `[1,5]` uniformly, derive `rand7`that
generates `[1,7]` uniformly.

Implemented in Python: [Day 45](dcp_py/day045)

---

### Day 46 \[Hard\]

Given a string, find the longest palindrome that it contains.

Implemented in Python (and property tested with hypothesis):
[Day 46](dcp_py/day046)

----

### Day 47 \[Easy\]

Given a list of stock price over time, determine the highest profit
per stock you can make.

Implemented in Python (and property tested with hypothesis):
[Day 47](dcp_py/day047)

---

### Day 48 \[Medium\]

Given the prefix and infix reading of a binary tree, recreate the
tree.

Implemented in Scala (and property tested with Scalacheck):
[Day 48](dcp_jvm/src/main/scala/dcp/day048)

---

### Day 49 \[Medium\]

Given an array, find the maximum sum amongst its consecutive
subarrays.

Implemented in Python (and property tested with hyptothesis):
[Day 49](dcp_py/day049)

---

### Day 50 \[Easy\]

Create and evaluate an expression tree.

Implemented in Scala (and property tested with Scalacheck):
[Day 50](dcp_jvm/src/main/scala/dcp/day050)

---

### Day 51 \[Medium\]

Shuffle a deck of cards uniformly given a uniform random number
generator.

Implemented in Python:
[Day 51](dcp_py/day051)

---

### Day 53 \[Medium\]

Implement a queue with two stacks.

Implemented in Java:
[Day 53](dcp_jvm/src/main/java/dcp/day053)

---

### Day 54 \[Hard\]

Implement an efficient sudoku solver.
I have already done this many times:

* [Brute force backtracking in C++ with `constexpr`](https://github.com/sraaphorst/sudoku_constexpr)
* [Using constraint programming](https://github.com/sraaphorst/sudoku_cp)
* [Using Knuth's DLX algorithm in C++ with `constexpr`](https://github.com/sraaphorst/dlx_constexpr)
* [Using Knuth's DLX algorithm in Python](https://github.com/sraaphorst/dlxpy)
* [Using stochastic methods](https://github.com/sraaphorst/sudoku_stochastic)

---

### Day 55 \[Easy\]

Impleent a base-62 URL shortening algorithm.

Implemented in Python (and property tested with hypothesis)
[Day 55](dcp_py/day055)

---

### Day 56 \[Medium\]

Write a graph colouring algorithm.

Implemented as brute force backtracking (with basic isomorphic
pruning) in `constexpr` C++: [Day 56](dcp_cpp/src/day056)

---

### Day 57 \[Medium\]

Split text into lines of maximum length.

Implemented in Python (and property tested with hypothesis):
[Day 57](dcp_py/day057)

---

### Day 58 \[Medium\]

Given a sorted array that has been rotated and an element, determine
if and where the element is in the array in time less than `O(n)`.

Implemented in Python (and property tested with hypothesis):
[Day 58](dcp_py/day058)

---

### Day 59 \[Hard\]

Use a Merkle tree to determine the differences between two file
systems.

Implemented in Java, and currently untested:
[Day59](dcp_jvm/src/main/java/dcp/day059)

---

### Day 62 \[Medium\]

Given an n x m matrix, determine the number of paths from the top left
to the bottom right.

Implemented in Python:
[Day 62](dcp_day062)

---

### Day 63 \[Easy\]

Given a square matrix of letters, determine if a word appears amongst
the rows or columns.

Implemented in Python:
[Day 63](dcp_day063)

---

### Day 64 \[Hard\

This problem was asked by Google.

A knight's tour is a sequence of moves by a knight on a chessboard such that all squares are visited once.

Given `N`, write a function to return the number of knight's tours on an `N` by `N` chessboard.

Implemented as brute force backtracking in `constexpr` C++: [Day 64](dcp_cpp/src/day064). The implementation takes `N` and a a starting position and calculates the number of paths. Isomorphism is ignored.

---

### Day 65 \[Easy\]

Given an `n` by `m` matrix, unwind it as a spiral clockwise from the
top left corner.

Implemented in Python:
[Day 65](dcp_day065)

---

### Day 66 \[Medium\]

Given a biased coin, find a way to use it to produce an unbiased coin.

Implemented in Python (and property tested with hypothesis):
[Day 66](dcp_py/day066)

---

### Day 68 \[Medium\]

Given a board of bishops, determine the number of attacking pairs.

Implemented in Python:
[Day 68](dcp_py/day068)

---

### Day 69 \[Easy\]

Given a list of integers, return the largest product that can be made by multiplying any three integers.

Implemented in Python (and property tested with hypothesis):
[Day 69](dcp_py/day069)

---

### Day 70 \[Easy\]

Generate "perfect" numbers. Very unclear.

Implemented in Python.
[Day 70](dcp_py/day070)

---

### Day 73 \[Easy\]

Reverse a singly linked list in place.

Implemented in Java (and property tested with ScalaCheck):
[Day 73](dcp_jvm/src/main/scala/dcp/day073)

---

### Day 74 \[Medium\]

Find the number of occurrences of a product in a muliplication table.

Implemented in Python (and property tested with hypothesis):
[Day 74](dcp_py/day074)

---

### Day 75 \[Hard\]

Find the length of the largest increasing subarray within an array.

Implemented in Scala (and property tested with ScalaCheck):
[Day 75](dcp_jvm/src/main/scala/dcp/day075)

---

### Day 76 \[Medium\]

Given a grid, find the mnimum number of columns that can be removed to
ensure each row is ordered from top to borrom lexicographically.

Implemented in Scala (and property checked with ScalaCheck):
[Day 76](dcp_jvm/src/main/scala/dcp/day076)

---

### Day 77 \[Easy\]

Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals have been merged.

Implemented in Python (and property tested with hypothesis):
[Day 77](dcp_py/day077)

---

### Day 91 \[Easy\]

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

---

### Day 102 \[Medium\]

Given a sum `k` and a list of elements `L`, find a contiguous sublist
of `L` that sums to `k`.

Implemented in Python (and property tested with hypothesis):
[Day 102](dcp_py/day102)

---

### Day 104 \[Easy\]

Check if the contents of a doubly linked list are palindromic.

How about a singly linked list?

Implemented in C++: [Day 104](dcp_cpp/src/day104)

---

### Day 106 \[Medium\]

Given an array of hops, see if you can make it to the end.

Implemented in Python (and property tested with hypothesis):
[Day 106](dcp_py/day106)

---

### Day 107 \[Easy\]

Given a binary tree, print it row-by-row.

Implemented in Scala: [Day 107](dcp_jvm/src/main/scala/dcp/day107)

---

### Day 108 \[Easy\]

Determine if one string can be rotated to match antoher.

Implemented in Python: [Day 108](dcp_py/day108)

---

### Day 109 \[Medium\]

Given a binary string of length 8, interleave the bits, i.e.:

```
a7 a6 a5 a4 a3 a2 a1 a0
```

becomes:

```
a6 a7 a4 a5 a2 a3 a0 a1.
```

Implemented in C++: [Day 109](dcp_cpp/src/day109)

---

### Day 110 \[Medium\]

Given a binary tree, return all paths from the roots to the leaves.

Implemented in Scala (and property checked with ScalaCheck):
[Day 110](dcp_jvm/src/main/scala/dcp/day110)

---

### Day 112 \[Hard\]

Given two nodes in a binary tree, find their least common ancestor.

Implemented in Java:
[Day 112](dcp_jvm/src/main/java/dcp/day112)

---

### Day 113 \[Medium\]

For a mutable string, reverse the order of its words.

Implemented in C++: [Day 113](dcp_cpp/src/day113)

---

### Day 114 \[Hard\]

For a mutable string, reverse the order of its words while maintaining
the order of its delimiters. WIP.

Implemented in C++: [Day 114](dcp_cpp/src/day114)

---

### Day 118 \[Easy\]

Given a sorted array, square the contents of the array and provide the
output in sorted order.

Implemented in `constexpr` C++: [Day 118](dcp_cpp/src/day118)

---

### Day 119 \[Medium\]

Given a set of intervals, determine a smallest collection of intervals
that cover the intervals.

Implemented in Kotlin: [Day 119](dcp_kotlin/src/main/kotlin/dcp/day119)

---

### Day 120 \[Medium\]

Instead of a singleton, implement an alternating pair of singletons.

Implemented in Java: 
[Day 120](dcp_jvm/src/main/java/dcp/day120)

---

### Day 121 \[Hard\]

Write a means of identifying k-palindromes.

Implemented in Python:
[Day 121](dcp_py/day121)

---

### Day 122 \[Medium\]

Given an `n` by `m` matrix, starting at the top left and moving only
right and down, find the largest sum.

Implemeneted in Python: [Day 122](dcp_py/day122)

---

### Day 140 \[Medium\]

Find the two non-repeated elements in a list of integral elements.

Implemented in `constexpr` C++: [Day 140](dcp_cpp/src/day140)

---

### Day 142 \[Hard\]

Given a string made out of `(`, `)`, and `*`, where `*` can be a
parenthesis or an empty string, determine if it is balanced.

Implemented in Python (and property tested with hypothesis):
[Day 142](dcp_py/day142)

---

### Day 143 \[Medium\]

Partition a list around a pivot element.

Implemented in Python (and property tested with hypothesis):
[Day 143](dcp_py/day143)

---

### Day 144 \[Medium\]

This problem was asked by Google.

Given an array of numbers and an index `i`, return the index of the nearest larger number of the number at index `i`, where distance is measured in array indices.

For example, given `[4, 1, 3, 5, 6]` and index `0`, you should return `3`.

If two distances to larger numbers are the equal, then return any one of them. If the array at `i` doesn't have a nearest larger integer, then return null.

Follow-up: If you can preprocess the array, can you do this in constant time?

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day281)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day281/day281.kt)

---

### Day 145 \[Easy\]

This problem was asked by Google.

Given the head of a singly linked list, swap every two nodes and return its head.

For example, given:

`1 -> 2 -> 3 -> 4`

return:

`2 -> 1 -> 4 -> 3.`

Implemented in C++: [Day 145](dcp_cpp/src/day145)

---

### Day 146 \[Medium\]

Given a zero-one tree, process the tree.

Implemented in Scala: [Day 146](dcp_jvm/src/main/scala/dcp/day146)

---

### Day 151 \[Medium\]

Perform flood-fill on an image.

Implemened in Python: [Day 151](dcp_py/day151)

---

### Day 154 \[Easy\]

Implement a stack via a heap or priority queue.

Impemented in C++: [Day 154](dcp_cpp/src/day154)

---

### Day 155 \[Medium\]

Given a list of elements, find the majority element, which appears
more than half the time.

Implemented in C++: [Day 155](dcp_cpp/src/day155)

---

### Day 156 \[Medium\]

For a given positive integer `n`, determine the smallest numnber of
squared integers which sums up to `n`.

Implemented in C++: [Day 156](dcp_cpp/src/day156)

---

### Day 157 \[Easy\]

Determine if a string is a permutation of a palindrome.

Implemented in C++: [Day 157](dcp_cpp/src/day157)

---

### Day 158 \[Medium\]

Given a matrix indicating a floor plan, determine the number of paths
through a matrix.

Implemented in Python: [Day 158](dcp_py/day158)

---

### Day 159 \[Medium\]

For a given string, return the first duplicated character in it if one
exists.

Implemented in Python: [Day 159](dcp_py/day159)

---

### Day 161 \[Easy\]

Flip a 32 bit integer.

Implemented in C++: [Day 161](dcp_cpp/src/day161)

---

### Day 162 \[Medium\]

Given a set of words, determine its unique set of minimal prefixes.

Implemented in C++: [Day 162](dcp_cpp/src/day162)

---

### Day 163 \[Hard\]

Implement a Reverse Polish Notation calculator evaluator.

Implemented in Scala: [Day 163](dcp_jvm/src/main/scala/dcp/day163)

---

### Day 164 \[Medium\]

This problem was asked by Google.

You are given an array of length `n + 1` whose elements belong to the set
`{1, 2, ..., n}`.

By the pigeonhole principle, there must be a duplicate. Find it in linear time and space.

Implemented in C++: [Day 164](dcp_cpp/src/day164)

---

### Day 170 \[Medium\]

This problem was asked by Facebook.

Given a start word, an end word, and a dictionary of valid words, find the shortest transformation sequence from start to end such that only one letter is changed at each step of the sequence, and each transformed word exists in the dictionary. If there is no possible transformation, return null. Each word in the dictionary have the same length as start and end and is lowercase.

For example, given `start = "dog"`, `end = "cat"`, and `dictionary = {"dot", "dop", "dat", "cat"}`, return `["dog", "dot", "dat", "cat"]`.

Given `start = "dog"`, `end = "cat"`, and `dictionary = {"dot", "tod", "dat", "dar"}`, return `null` as there is no possible transformation from `dog` to `cat`.

Implemented in Python: [Day 170](dcp_py/day170)

---

### Day 171 \[Medium\]

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

---

### Day 173 \[Easy\]

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

---

### Day 174 \[Medium\]

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

---

### Day 175: \[Easy\]

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

---

### Day 176 \[Easy\]

This problem was asked by Bloomberg.

Determine whether there exists a one-to-one character mapping from one string `s1` to another `s2`.

For example, given `s1 = abc` and `s2 = bcd`, return true since we can map `a` to `b`, `b` to `c`, and `c` to `d`.

Given `s1 = foo` and `s2 = bar`, return `false` since the `o` cannot
map to two characters.

Implemented in Python: [Day 176](dcp_py/day176)

---

### Day 177 \[Easy\]

This problem was asked by Airbnb.

Given a linked list and a positive integer `k`, rotate the list to the right by `k` places.

For example, given the linked list `7 -> 7 -> 3 -> 5` and `k = 2`, it should become `3 -> 5 -> 7 -> 7`.

Given the linked list `1 -> 2 -> 3 -> 4 -> 5` and `k = 3`, it should
become `3 -> 4 -> 5 -> 1 -> 2`.

Implemented in C++: [Day 177](dcp_cpp/src/day177)

---

### Day 178 \[Hard\]

This problem was asked by Two Sigma.

Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter? Write a program to simulate the two games and calculate their expected value.

Simultation implemented in Python, and explanation given here:
[Day 178](dcp_py/day178)

---

### Day 179 \[Medium\]

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

---

### Day 180 \[Medium\]

This problem was asked by Google.

Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one other queue. This should be done in-place.

Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.

For example, if the stack is `[1, 2, 3, 4, 5]`, it should become `[1, 5, 2, 4, 3]`. If the stack is `[1, 2, 3, 4]`, it should become `[1, 4, 2, 3]`.

Hint: Try working backwards from the end state.

Implemented in C++: [Day 180](dcp_cpp/src/day180)

---

### Day 182 \[Medium\]

This problem was asked by Facebook.

A graph is minimally-connected if it is connected and there is no edge that can be removed while still leaving the graph connected. For example, any binary tree is minimally-connected.

Given an undirected graph, check if the graph is
minimally-connected. You can choose to represent the graph as either
an adjacency matrix or adjacency list.

Implemented in Python: [Day 182](dcp_py/day182)

---

### Day 184 \[Easy\]

This problem was asked by Amazon.

Given n numbers, find the greatest common denominator between them.

For example, given the numbers `[42, 56, 14]`, return `14`.

Implemented in C++ using variadic templates: [Day 184](dcp_cpp/src/day184)

---

### Day 185 \[Easy\]

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

---

### Day 186 \[Hard\]

This problem was asked by Microsoft.

Given an array of positive integers, divide the array into two subsets such that the difference between the sum of the subsets is as small as possible.

For example, given `[5, 10, 15, 20, 25]`, return the sets `{10, 25}` and
`{5, 15, 20}`, which has a difference of `5`, which is the smallest
possible difference.

Implemented in Python: [Day 186](dcp_py/day186)

---

### Day 188 \[Medium\]

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

---

### Day 194 \[Easy\]

This problem was asked by Facebook.

Suppose you are given two lists of `n` points, one list:

* `p1, p2, ..., pn` on the line `y = 0`; and
* the other list `q1, q2, ..., qn` on the line `y = 1`.

Imagine a set of `n` line segments connecting each point `pi` to
`qi`. Write an algorithm to determine how many pairs of the line
segments intersect.

Implemented in Kotlin: [Day 194](dcp_kotlin/src/main/kotlin/dcp/day194)

---

### Day 198 \[Medium\]

This problem was asked by Google.

Given a set of distinct positive integers, find the largest subset such that every pair of elements in the subset `(i, j)` satisfies either `i % j = 0` or `j % i = 0`.

For example, given the set `[3, 5, 10, 20, 21]`, you should return `[5, 10, 20]`. Given `[1, 3, 6, 24]`, return `[1, 3, 6, 24]`.

Implemented in Kotlin: [Day 198](dcp_kotlin/src/main/kotlin/dcp/day198)

---

### Day 199 - \[Hard\]

This problem was asked by Facebook.

Given a string of parentheses, find the balanced string that can be produced from it using the minimum number of insertions and deletions. If there are multiple solutions, return any of them.

For example, given `"(()"`, you could return `"(())"`. Given
`"))()("`, you could return `"()()()()"`.

Implemented in Kotlin: [Day 199](dcp_kotlin/src/main/kotlin/dcp/day199)

---

### Day 200 \[Hard\]

This problem was asked by Microsoft.

Let `X` be a set of `n` intervals on the real line. We say that a set of points `P` "stabs" `X` if every interval in `X` contains at least one point in `P`. Compute the smallest set of points that stabs `X`.

For example, given the intervals `[(1, 4), (4, 5), (7, 9), (9, 12)]`,
you should return `[4, 9]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day200)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day201/day200.kt)

---

### Day 201 \[Easy\]

This problem was asked by Google.

You are given an array of arrays of integers, where each array corresponds to a row in a triangle of numbers. For example, `[[1], [2, 3], [1, 5, 1]]` represents the triangle:

```
  1
 2 3
1 5 1
```
We define a path in the triangle to start at the top and go down one row at a time to an adjacent value, eventually ending with an entry on the bottom row. For example, `1 -> 3 -> 5`. The weight of the path is the sum of the entries.

Write a program that returns the weight of the maximum weight path.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day201)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day201/day201.kt)

---

### Day 202 \[Easy\]

This problem was asked by Palantir.

Write a program that checks whether an integer is a palindrome. For example, `121` is a palindrome, as well as `888`. `678` is not a palindrome. Do not convert the integer into a string.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day202)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day202/day202.kt)

---

### Day 204 \[Easy\]

This problem was asked by Amazon.

Given a complete binary tree, count the number of nodes in faster than `O(n)` time. Recall that a complete binary tree has every level filled except the last, and the nodes in the last level are filled starting from the left.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day204)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day204/day204.kt)

---

### Day 206 \[Easy\]

This problem was asked by Twitter.

A permutation can be specified by an array `P`, where `P[i]` represents the location of the element at `i` in the permutation. For example, `[2, 1, 0]` represents the permutation where elements at the index 0 and 2 are swapped.

Given an array and a permutation, apply the permutation to the array. For example, given the array `["a", "b", "c"]` and the permutation `[2, 1, 0]`, return `["c", "b", "a"]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day206)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day206/day206.kt)

---

### Day 207 \[Medium\]

This problem was asked by Dropbox.

Given an undirected graph `G`, check whether it is bipartite. Recall
that a graph is bipartite if its vertices can be divided into two
independent sets, `U` and `V`, such that no edge connects vertices of
the same set.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day207)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day207/day207.kt)

---

### Day 208 \[Easy\]

This problem was asked by Apple.

A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:

* If n is even, the next number in the sequence is `n / 2`.
* If n is odd, the next number in the sequence is `3n + 1`.

It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.

Bonus: What input `n <= 1000000` gives the longest sequence?

Implemented in C++: [Day 208](dcp_cpp/src/day208)

---

### Day 211 \[Medium\]

This problem was asked by Microsoft.

Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string. For example, given the string `"abracadabra"` and the pattern `"abr"`, you should return `[0, 7]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day211)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day211/day211.kt)

---

### Day 212 \[Easy\]

This problem was asked by Dropbox.

Spreadsheets often use this alphabetical encoding for its columns: `"A"`, `"B"`, `"C"`, ..., `"AA"`, `"AB"`, ..., `"ZZ"`, `"AAA"`, `"AAB"`, ....

Given a column number, return its alphabetical column id. For example,
given `1`, return `"A"`. Given `27`, return `"AA"`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day212)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day212/day212.kt)

---

### Day 213 \[Medium\]

This problem was asked by Snapchat.

Given a string of digits, generate all possible valid IP address combinations.

IP addresses must follow the format `A.B.C.D`, where `A`, `B`, `C`, and `D` are numbers between `0` and `255`. Zero-prefixed numbers, such as `01` and `065`, are not allowed, except for `0` itself.

For example, given `"2542540123"`, you should return `['254.25.40.123', '254.254.0.123']`.

Implemented in Python: [Day 213](dcp_py/day213)

---

### Day 214 \[Easy\]

This problem was asked by Stripe.

Given an integer `n`, return the length of the longest consecutive run of 1s in its binary representation.

For example, given `156`, you should return `3`.

* [C++ implementation](dcp_cpp/src/day214)
* [Unit testing](dcp_cpp/test/TestDay214.cpp)

---

### Day 215 \[Medium\]

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

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day215)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day215/day215.kt)

---

### Day 216 \[Medium\]

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

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day216)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day216/day216.kt)


---

### Day 217 \[Hard\]

This problem was asked by Oracle.

We say a number is sparse if there are no adjacent ones in its binary representation. For example, 21 (10101) is sparse, but 22 (10110) is not. For a given input N, find the smallest sparse number greater than or equal to N.

Do this in faster than O(N log N) time.

* NOT YET IMPLEMENTED

---

### Day 218 \[Medium\]

This problem was asked by Yahoo.

Write an algorithm that computes the reversal of a directed graph. For example, if a graph consists of `A -> B -> C`, it should become `A <- B <- C`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day218)
* [Specification testing](dcp_kotlin/src/test/kotlin/dcp/day218/day218.kt)

---

### Day 219 \[Hard\]

This problem was asked by Salesforce.

Connect 4 is a game where opponents take turns dropping red or black discs into a `7 x 6` vertically suspended grid. The game ends either when one player creates a line of four consecutive discs of their color (horizontally, vertically, or diagonally), or when there are no more spots left in the grid.

Design and implement Connect 4.

* [Java implementation](dcp_jvm/src/main/java/dcp/day219)

---

### Day 220 \[Medium\]

This problem was asked by Square.

In front of you is a row of `N` coins, with values `v1`, `v2`, ..., `vn`.

You are asked to play the following game. You and an opponent take turns choosing either the first or last coin from the row, removing it from the row, and receiving the value of the coin.

Write a program that returns the maximum amount of money you can win with certainty, if you move first, assuming your opponent plays optimally.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day220)
* [Specification testing](dcp_kotlin/src/test/kotlin/dcp/day220/day220.kt)

---

### Day 221 \[Easy\]

This problem was asked by Zillow.

Let's define a "sevenish" number to be one which is either a power of 7, or the sum of unique powers of 7. The first few sevenish numbers are 1, 7, 8, 49, and so on. Create an algorithm to find the `n`th sevenish number.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day221)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day221/day221.kt)

---

### Day 222 \[Medium\]

This problem was asked by Quora.

Given an absolute pathname that may have `.` or `..` as part of it, return the shortest standardized path.

For example, given `"/usr/bin/../bin/./scripts/../"`, return
`"/usr/bin/"`.

* [Python implementation](dcp_py/day222)

---

### Day 223 \[Hard\]

This problem was asked by Palantir.

Typically, an implementation of in-order traversal of a binary tree has `O(h)` space complexity, where `h` is the height of the tree. Write a program to compute the in-order traversal of a binary tree using `O(1)` space.

* [C++ implementation](dcp_cpp/src/day223)
* [Unit testing](dcp_cpp/test/TestDay223.cpp)

---

### Day 224 \[Easy\]

This problem was asked by Amazon.

Given a sorted array, find the smallest positive integer that is not the sum of a subset of the array.

For example, for the input `[1, 2, 3, 10]`, you should return 7.

Do this in `O(N)` time.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day224)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day224/day224.kt)

---

### Day 225 \[Easy\]

This problem was asked by Bloomberg.

There are `N` prisoners standing in a circle, waiting to be executed. The executions are carried out starting with the `k`th person, and removing every successive `k`th person going clockwise until there is no one left.

Given `N` and `k`, write an algorithm to determine where a prisoner should stand in order to be the last survivor.

For example, if `N = 5` and `k = 2`, the order of executions would be `[2, 4, 1, 5, 3`], so you should return `3`.

Bonus: Find an `O(log N)` solution if `k = 2`.

* [Python implementation](dcp_py/day225)

---

### Day 226 \[Hard\]

This problem was asked by Airbnb.

You come across a dictionary of sorted words in a language you've never seen before. Write a program that returns the correct order of letters in this language.

For example, given `['xww', 'wxyz', 'wxyw', 'ywx', 'ywz']`, you should return `['x', 'z', 'w', 'y']`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day226)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day226/day226.kt)

---

### Day 228 \[Medium\]

This problem was asked by Twitter.

Given a list of numbers, create an algorithm that arranges them in
order to form the largest possible integer. For example, given
`[10, 7, 76, 415]`, you should return `77641510`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day228)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day228/day228.kt)

---

### Day 229 \[Medium\]

This problem was asked by Flipkart.

Snakes and Ladders is a game played on a `10 x 10` board, the goal of which is get from square 1 to square 100. On each turn players will roll a six-sided die and move forward a number of spaces equal to the result. If they land on a square that represents a snake or ladder, they will be transported ahead or behind, respectively, to a new square.

Find the smallest number of turns it takes to play snakes and ladders.

For convenience, here are the squares representing snakes and ladders, and their outcomes:

* `snakes = {16: 6, 48: 26, 49: 11, 56: 53, 62: 19, 64: 60, 87: 24, 93: 73, 95: 75, 98: 78}`
* `ladders = {1: 38, 4: 14, 9: 31, 21: 42, 28: 84, 36: 44, 51: 67, 71: 91, 80: 100}`

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day229)

---

### Day 230 \[Medium\]

This problem was asked by Goldman Sachs.

You are given `N` identical eggs and access to a building with `k` floors. Your task is to find the lowest floor that will cause an egg to break, if dropped from that floor. Once an egg breaks, it cannot be dropped again. If an egg breaks when dropped from the `x`th floor, you can assume it will also break when dropped from any floor greater than `x`.

Write an algorithm that finds the minimum number of trial drops it will take, in the worst case, to identify this floor.

For example, if `N = 1` and `k = 5`, we will need to try dropping the
egg at every floor, beginning with the first, until we reach the fifth
floor, so our solution will be `5`.

* [Python implementation](dcp_py/day230)

---

### Day 231 \[Easy\]

This problem was asked by IBM.

Given a string with repeated characters, rearrange the string so that no two adjacent characters are the same. If this is not possible, return `None`.

For example, given `"aaabbc"`, you could return `"ababac"`. Given `"aaab"`, return `None`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day231)
* [Unit and property testing](dcp_kotlin/src/test/kotlin/dcp/day231/day231.kt)

---

### Day 232 \[Easy\]

This problem was asked by Google.

Implement a `PrefixMapSum` class with the following methods:

* `insert(key: str, value: int)`: Set a given key's value in the map. If the key already exists, overwrite the value.

* `sum(prefix: str)`: Return the sum of all values of keys that begin with a given prefix.
For example, you should be able to run the following code:

```
mapsum.insert("columnar", 3)
assert mapsum.sum("col") == 3

mapsum.insert("column", 2)
assert mapsum.sum("col") == 5
```

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day232)
* [Unit and property testing](dcp_kotlin/src/test/kotlin/dcp/day232/day232.kt)

---

### Day 233 \[Easy\]

This problem was asked by Apple.

Implement the function `fib(n)`, which returns the nth number in the Fibonacci sequence, using only `O(1)` space.

* [Python implementation](dcp_py/day233)

---

### Day 234 \[Hard\]

Recall that the minimum spanning tree is the subset of edges of a tree that connect all its vertices with the smallest possible total edge weight. Given an undirected graph with weighted edges, compute the maximum weight spanning tree.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day234)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day234/day234.kt)

---

### Day 235 \[Hard\]

This problem was asked by Facebook.

Given an array of numbers of length `N`, find both the minimum and
maximum using less than `2 * (N - 2)` comparisons.

* [Python implementation](dcp_py/day235)

---

### Day 237 \[Easy\]

This problem was asked by Amazon.

A tree is symmetric if its data and shape remain unchanged when it is reflected about the root node. The following tree is an example:
```
        4
      / | \
    3   5   3
  /           \
9              9
```
Given a `k`-ary tree, determine whether it is symmetric.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day237)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day237/day237.kt)

---

### Day 241 \[Easy\]

This problem was asked by Palantir.

In academia, the `h`-index is a metric used to calculate the impact of a researcher's papers. It is calculated as follows:

A researcher has index `h` if at least `h` of her `N` papers have `h` citations each. If there are multiple `h` satisfying this formula, the maximum is chosen.

For example, suppose `N = 5`, and the respective citations of each paper are `[4, 3, 0, 1, 5]`. Then the `h`-index would be 3, since the researcher has 3 papers with at least 3 citations.

Given a list of paper citations of a researcher, calculate their `h`-index.


* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day241)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day241/day241.kt)

---

### Day 244 \[Easy\]

This problem was asked by Square.

The Sieve of Eratosthenes is an algorithm used to generate all prime numbers smaller than `N`. The method is to take increasingly larger prime numbers, and mark their multiples as composite.

For example, to find all primes less than 100, we would first mark `[4, 6, 8, ...]` (multiples of two), then `[6, 9, 12, ...]` (multiples of three), and so on. Once we have done this for all primes less than `N`, the unmarked numbers that remain will be prime.

Implement this algorithm.

Bonus: Create a generator that produces primes indefinitely (that is, without taking `N` as an input).

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day244)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day244/day244.kt)

---

### Day 245 \[Medium\]

This problem was asked by Yelp.

You are given an array of integers, where each element represents the maximum number of steps that can be jumped going forward from that element. Write a function to return the minimum number of jumps you must take in order to get from the start to the end of the array.

For example, given `[6, 2, 4, 0, 5, 1, 1, 4, 2, 9]`, you should return `2`, as the optimal solution involves jumping from `6` to `5`, and then from `5` to `9`.


* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day245)
* [Unit and property testing](dcp_kotlin/src/test/kotlin/dcp/day245/day245.kt)

---

### Day 247 \[Easy\]

This problem was asked by PayPal.

Given a binary tree, determine whether or not it is height-balanced. A height-balanced binary tree can be defined as one in which the heights of the two subtrees of any node never differ by more than one.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day247)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day247/day247.kt)

---

### Day 248 \[Hard\]

This problem was asked by Nvidia.

Find the maximum of two numbers without using any if-else statements, branching, or direct comparisons.

* [C++ implementation](dcp_cpp/src/day248)
* [Unit testing](dcp_cpp/test/TestDay248.cpp)

---

### Day 251 \[Medium\}

This problem was asked by Amazon.

Given an array of a million integers between zero and a billion, out of order, how can you efficiently sort it? Assume that you cannot store an array of a billion elements in memory.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day251)
* [Property testing](dcp_kotlin/src/test/kotlin/dcp/day251/day251.kt)

---

### Day 252 [Easy\]

This problem was asked by Palantir.

The ancient Egyptians used to express fractions as a sum of several terms where each numerator is one. For example, `4 / 13` can be represented as `1 / 4 + 1 / 18 + 1 / 468`.

Create an algorithm to turn an ordinary fraction `a / b`, where `a < b`, into an Egyptian fraction.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day252)
* [Property and unit testing](dcp_kotlin/src/test/kotlin/dcp/day252/day252.kt)

---

### Day 253 \[Easy\]

This problem was asked by PayPal.

Given a string and a number of lines `k`, print the string in zigzag form. In zigzag, characters are printed out diagonally from top left to bottom right until reaching the kth line, then back up to top right, and so on.

For example, given the sentence `"thisisazigzag"` and `k = 4`, you should print:

```
t     a     g
 h   s z   a
  i i   i z
   s     g
   ```

* [Python implementation](dcp_py/day253)

---

### Day 254 \[Easy\]

This problem was asked by Yahoo.

Recall that a full binary tree is one in which each node is either a leaf node, or has two children. Given a binary tree, convert it to a full one by removing nodes with only one child.

For example, given the following tree:
```
         0
      /     \
    1         2
  /            \
3                 4
  \             /   \
    5          6     7
```
You should convert it to:
```
     0
  /     \
5         4
        /   \
       6     7
```

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day254)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day254/day254.kt)

---

### Day 255 \[Easy\]

This problem was asked by Microsoft.

The transitive closure of a graph is a measure of which vertices are reachable from other vertices. It can be represented as a matrix `M`, where `M[i][j] == 1` if there is a path between vertices `i` and `j`, and otherwise `0`.

For example, suppose we are given the following graph in adjacency list form:
```
graph = [
    [0, 1, 3],
    [1, 2],
    [2],
    [3]
]
```
The transitive closure of this graph would be:
```
[1, 1, 1, 1]
[0, 1, 1, 0]
[0, 0, 1, 0]
[0, 0, 0, 1]
```
Given a graph, find its transitive closure.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day255)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day255/day255.kt)

---

### Day 256 \[Medium\]

This problem was asked by Fitbit.

Given a linked list, rearrange the node values such that they appear in alternating `low -> high -> low -> high` ... form. For example, given `1 -> 2 -> 3 -> 4 -> 5`, you should return `1 -> 3 -> 2 -> 5 -> 4`.

* [C++ implementation](dcp_cpp/src/day256)
* [Unit testing](dcp_cpp/test/TestDay256.cpp)

---

### Day 257 \[Easy\]

This problem was asked by WhatsApp.

Given an array of integers out of order, determine the bounds of the
smallest window that must be sorted in order for the entire array to
be sorted. For example, given `[3, 7, 5, 6, 9]`, you should return
`(1, 3)`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day257)
* [Property and unit testing](dcp_kotlin/src/test/kotlin/dcp/day257/day257.kt)

---

### Day 258 \[Easy\]

This problem was asked by Morgan Stanley.

In Ancient Greece, it was common to write text with the first line going left to right, the second line going right to left, and continuing to go back and forth. This style was called "boustrophedon".

Given a binary tree, write an algorithm to print the nodes in boustrophedon order.

For example, given the following tree:
```
       1
    /     \
  2         3
 / \       / \
4   5     6   7
```
You should return `[1, 3, 2, 4, 5, 6, 7]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day258)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day258/day258.kt)

---

### Day 261 \[Easy\]

This problem was asked by Amazon.

Huffman coding is a method of encoding characters based on their frequency. Each letter is assigned a variable-length binary string, such as `0101` or `111110`, where shorter lengths correspond to more common letters. To accomplish this, a binary tree is built such that the path from the root to any leaf uniquely maps to a character. When traversing the path, descending to a left child corresponds to a `0` in the prefix, while descending right corresponds to `1`.

Here is an example tree (note that only the leaf nodes have letters):

```
        *
      /   \
    *       *
   / \     / \
  *   a   t   *
 /             \
c               s
```
With this encoding, cats would be represented as `0000110111`.

Given a dictionary of character frequencies, build a Huffman tree, and use it to determine a mapping between characters and their encoded binary strings.


* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day261)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day261/day261.kt)

---

### Day 262 \[Medium\]

This problem was asked by Mozilla.

A bridge in a connected (undirected) graph is an edge that, if removed, causes the graph to become disconnected. Find all the bridges in a graph.

**Note:** This solution could be improved.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day262)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day262/day262.kt)

---

### Day 263 \[Medium\]

This problem was asked by Nest.

Create a basic sentence checker that takes in a stream of characters and determines whether they form valid sentences. If a sentence is valid, the program should print it out.

We can consider a sentence valid if it conforms to the following rules:

The sentence must start with a capital letter, followed by a lowercase letter or a space.
All other characters must be lowercase letters, separators (`,;:`) or terminal marks (`.?!‽`).
There must be a single space between each word.
The sentence must end with a terminal mark immediately following a
word.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day262)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day262/day262.kt)

---

### Day 264 \[Hard\]

This problem was asked by LinkedIn.

Given a set of characters `C` and an integer `k`, a de Bruijn sequence is a cyclic sequence in which every possible `k`-length string of characters in `C` occurs exactly once.

For example, suppose `C = {0, 1}` and `k = 3`. Then our sequence should contain the substrings `{'000', '001', '010', '011', '100', '101', '110', '111'}`, and one possible solution would be `00010111`.

Create an algorithm that finds a de Bruijn sequence.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day264)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day264/day264.kt)

---

### Day 265 \[Easy\]

This problem was asked by Atlassian.

MegaCorp wants to give bonuses to its employees based on how many lines of codes they have written. They would like to give the smallest positive amount to each worker consistent with the constraint that if a developer has written more lines of code than their neighbor, they should receive more money.

Given an array representing a line of seats of employees at MegaCorp, determine how much each one should get paid.

For example, given `[10, 40, 200, 1000, 60, 30]`, you should return `[1, 2, 3, 4, 2, 1]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day265)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day265/day265.kt)

---

### Day 266 \[Easy\]

This problem was asked by Pivotal.

A step word is formed by taking a given word, adding a letter, and anagramming the result. For example, starting with the word `"APPLE"`, you can add an `"A"` and anagram to get `"APPEAL"`.

Given a dictionary of words and an input word, create a function that returns all valid step words.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day266)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day266/day266.kt)

---

### Day 267 \[Hard\]

This problem was asked by Oracle.

You are presented with an 8 by 8 matrix representing the positions of pieces on a chess board. The only pieces on the board are the black king and various white pieces. Given this matrix, determine whether the king is in check.

For details on how each piece moves, see here.

For example, given the following matrix:
```
...K....
........
.B......
......P.
.......R
..N.....
........
.....Q..
```
You should return `True`, since the bishop is attacking the king diagonally.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day267)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day267/day267.kt)

---

### Day 268 \[Medium\]

This problem was asked by Indeed.

Given a 32-bit positive integer `N`, determine whether it is a power of four in faster than `O(log N)` time.

* [C++ implementation](dcp_cpp/src/day268)
* [Unit testing](dcp_cpp/test/TestDay268.cpp)

---

### Day 269 \[Easy\]

This problem was asked by Microsoft.

You are given an string representing the initial conditions of some dominoes. Each element can take one of three values:

* `L`, meaning the domino has just been pushed to the left,
* `R`, meaning the domino has just been pushed to the right, or
* `.`, meaning the domino is standing still.

Determine the orientation of each tile when the dominoes stop falling. Note that if a domino receives a force from the left and right side simultaneously, it will remain upright.

For example, given the string `.L.R....L`, you should return `LL.RRRLLL`.

Given the string `..R...L.L`, you should return `..RR.LLLL`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day269)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day269/day269.kt)

---

### Day 271 \[Hard\]

Given a sorted list of integers of length `N`, determine if an element `x` is in the list without performing any multiplication, division, or bit-shift operations.

Do this in `O(log N)` time.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day271)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day271/day271.kt)

---

### Day 272 \[Medium\]

This problem was asked by Spotify.

Write a function, `throw_dice(N, faces, total)`, that determines how many ways it is possible to throw `N` dice with some number of `faces` each to get a specific total.

For example, `throw_dice(3, 6, 7)` should equal 15.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day272)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day272/day272.kt)

---

### Day 273 \[Easy\]

A fixed point in an array is an element whose value is equal to its index. Given a sorted array of distinct elements, return a fixed point, if one exists. Otherwise, return `False`.

For example, given `[-6, 0, 2, 40]`, you should return `2`. Given `[1, 5, 7, 8]`, you should return `False`.


* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day273)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day273/day273.kt)

---

### Day 275 \[Medium\]

This problem was asked by Epic.

The "look and say" sequence is defined as follows: beginning with the term 1, each subsequent term visually describes the digits appearing in the previous term. The first few terms are as follows:
```
1
11
21
1211
111221
```
As an example, the fourth term is `1211`, since the third term consists of one `2` and one `1`.

Given an integer `N`, print the Nth term of this sequence.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day275)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day275/day275.kt)

---

### Day 276 \[Hard\]

This problem was asked by Dropbox.

Implement an efficient string matching algorithm.

That is, given a string of length `N` and a pattern of length `k`, write a program that searches for the pattern in the string with less than `O(N * k)` worst-case time complexity.

If the pattern is found, return the start index of its location. If not, return `False`.

* [C++ implementation](dcp_cpp/src/day276)
* [Unit testing](dcp_cpp/test/TestDay276.cpp)

---

### Day 277 \[Easy\]

This problem was asked by Google.

UTF-8 is a character encoding that maps each symbol to one, two, three, or four bytes.

For example, the Euro sign, €, corresponds to the three bytes `11100010 10000010 10101100`. The rules for mapping characters are as follows:

For a single-byte character, the first bit must be zero.
For an `n`-byte character, the first byte starts with `n` ones and a zero. The other `n - 1` bytes all start with `10`.
Visually, this can be represented as follows.

```
 Bytes   |           Byte format
-----------------------------------------------
   1     | 0xxxxxxx
   2     | 110xxxxx 10xxxxxx
   3     | 1110xxxx 10xxxxxx 10xxxxxx
   4     | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
```

Write a program that takes in an array of integers representing byte values, and returns whether it is a valid UTF-8 encoding.

* [C++ implementation](dcp_cpp/src/day277)
* [Unit testing](dcp_cpp/test/TestDay277.cpp)

---

### Day 278 \[Easy\]

This problem was asked by Amazon.

Given an integer `N`, construct all possible binary search trees with `N` nodes.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day278)
* [Property and Unit testing](dcp_kotlin/src/test/kotlin/dcp/day278/day278.kt)

---

### Day 279 \[Easy\]

This problem was asked by Twitter.

A classroom consists of `N` students, whose friendships can be represented in an adjacency list. For example, the following describes a situation where `0` is friends with `1` and `2`, `3` is friends with `6`, and so on.

```
{0: [1, 2],
 1: [0, 5],
 2: [0],
 3: [6],
 4: [],
 5: [1],
 6: [3]}
``` 
Each student can be placed in a friend group, which can be defined as the transitive closure of that student's friendship relations. In other words, this is the smallest set such that no student in the group has any friends outside this group. For the example above, the friend groups would be `{0, 1, 2, 5}, {3, 6}, {4}`.

Given a friendship list such as the one above, determine the number of friend groups in the class.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day279)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day279/day279.kt)

---

### Day 280 \[Easy\]

This problem was asked by Pandora.

Given an undirected graph, determine if it contains a cycle.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day280)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day280/day280.kt)

---

### Day 281 \[Medium\]

This problem was asked by LinkedIn.

A wall consists of several rows of bricks of various integer lengths and uniform height. Your goal is to find a vertical line going from the top to the bottom of the wall that cuts through the fewest number of bricks. If the line goes through the edge between two bricks, this does not count as a cut.

For example, suppose the input is as follows, where values in each row represent the lengths of bricks in that row:
```
[[3, 5, 1, 1],
 [2, 3, 3, 2],
 [5, 5],
 [4, 4, 2],
 [1, 3, 3, 3],
 [1, 1, 6, 1, 1]]
```
The best we can we do here is to draw a line after the eighth brick, which will only require cutting through the bricks in the third and fifth row.

Given an input consisting of brick lengths for each row such as the one above, return the fewest number of bricks that must be cut to create a vertical line.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day281)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day281/day281.kt)

---

### Day 282 \[Easy\]

This problem was asked by Netflix.

Given an array of integers, determine whether it contains a Pythagorean triplet. Recall that a Pythogorean triplet `(a, b, c)` is defined by the equation:

```math
a^2+ b^2 = c^2.
```

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day282)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day282/day282.kt)

---

### Day 283 \[Easy\]

This problem was asked by Google.

A regular number in mathematics is defined as one which evenly divides some power of 60. Equivalently, we can say that a regular number is one whose only prime divisors are `2`, `3`, and `5`.

These numbers have had many applications, from helping ancient Babylonians keep time to tuning instruments according to the diatonic scale.

Given an integer `N`, write a program that returns, in order, the first `N` regular numbers.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day283)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day283/day283.kt)

---

### Day 284 \[Medium\]

This problem was asked by Yext.

Two nodes in a binary tree can be called cousins if they are on the same level of the tree but have different parents. For example, in the following diagram `4` and `6` are cousins.

```
    1
   / \
  2   3
 / \   \
4   5   6
```
Given a binary tree and a particular node, find all cousins of that node.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day284)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day284/day284.kt)

---

### Day 285 \[Medium\]

This problem was asked by Mailchimp.

You are given an array representing the heights of neighboring buildings on a city street, from east to west. The city assessor would like you to write an algorithm that returns how many of these buildings have a view of the setting sun, in order to properly value the street.

For example, given the array `[3, 7, 8, 3, 6, 1]`, you should return `3`, since the top floors of the buildings with heights `8`, `6`, and `1` all have an unobstructed view to the west.

Can you do this using just one forward pass through the array?

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day285)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day285/day285.kt)

---

### Day 286 \[Hard\]

This problem was asked by VMware.

The skyline of a city is composed of several buildings of various widths and heights, possibly overlapping one another when viewed from a distance. We can represent the buildings using an array of `(left, right, height)` tuples, which tell us where on an imaginary `x`-axis a building begins and ends, and how tall it is. The skyline itself can be described by a list of `(x, height)` tuples, giving the locations at which the height visible to a distant observer changes, and each new height.

Given an array of buildings as described above, create a function that returns the skyline.

For example, suppose the input consists of the buildings `[(0, 15, 3), (4, 11, 5), (19, 23, 4)]`. In aggregate, these buildings would create a skyline that looks like the one below.

```
     ______  
    |      |        ___
 ___|      |___    |   | 
|   |   B  |   |   | C |
| A |      | A |   |   |
|   |      |   |   |   |
------------------------
```

As a result, your function should return `[(0, 3), (4, 5), (11, 3), (15, 0), (19, 4), (23, 0)]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day286)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day286/day286.kt)

---

# Day 287 \[Medium\]

This problem was asked by Quora.

You are given a list of `(website, user)` pairs that represent users visiting websites. Come up with a program that identifies the top `k` pairs of websites with the greatest similarity.

For example, suppose `k = 1`, and the list of tuples is:

```
[('a', 1), ('a', 3), ('a', 5),
 ('b', 2), ('b', 6),
 ('c', 1), ('c', 2), ('c', 3), ('c', 4), ('c', 5)
 ('d', 4), ('d', 5), ('d', 6), ('d', 7),
 ('e', 1), ('e', 3), ('e': 5), ('e', 6)]
```

Then a reasonable similarity metric would most likely conclude that `a` and `e` are the most similar, so your program should return `[('a', 'e')]`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day287)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day287/day287.kt)

---

### Day 288 \[Medium\]

This problem was asked by Salesforce.

The number `6174` is known as __Kaprekar's contant__, after the mathematician who discovered an associated property: for all four-digit numbers with at least two distinct digits, repeatedly applying a simple procedure eventually results in this value. The procedure is as follows:

For a given input `x`, create two new numbers that consist of the digits in `x` in ascending and descending order.
Subtract the smaller number from the larger number.
For example, this algorithm terminates in three steps when starting from `1234`:

```
4321 - 1234 = 3087
8730 - 0378 = 8352
8532 - 2358 = 6174
```

Write a function that returns how many steps this will take for a given input `N`.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day288)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day288/day288.kt)

---

# Day 289 \[Hard\]

This problem was asked by Google.

The game of Nim is played as follows. Starting with three heaps, each containing a variable number of items, two players take turns removing one or more items from a single pile. The player who eventually is forced to take the last stone loses. For example, if the initial heap sizes are `3`, `4`, and `5`, a game could be played as shown below:

```
  A  |  B  |  C
-----------------
  3  |  4  |  5
  3  |  1  |  3
  3  |  1  |  3
  0  |  1  |  3
  0  |  1  |  0
  0  |  0  |  0
```

 
In other words, to start, the first player takes three items from pile `B`. The second player responds by removing two stones from pile `C`. The game continues in this way until player one takes last stone and loses.

Given a list of non-zero starting values `[a, b, c]`, and assuming optimal play, determine whether the first player has a forced win.

**NOTE:** We support numbers of heaps equal to or larger than 3.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day289)
* [Specification testing](dcp_kotlin/src/test/kotlin/dcp/day289/day289.kt)

---

### Day 290 \[Easy\]

On a mysterious island there are creatures known as Quxes which come in three colors: red, green, and blue. One power of the Qux is that if two of them are standing next to each other, they can transform into a single creature of the third color.

Given N Quxes standing in a line, determine the smallest number of them remaining after any possible sequence of such transformations.

For example, given the input `['R', 'G', 'B', 'G', 'B']`, it is possible to end up with a single Qux through the following steps:

```
        Arrangement       |   Change
----------------------------------------
['R', 'G', 'B', 'G', 'B'] | (R, G) -> B
['B', 'B', 'G', 'B']      | (B, G) -> R
['B', 'R', 'B']           | (R, B) -> G
['B', 'G']                | (B, G) -> R
['R']                     |
```

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day290)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day290/day290.kt)

---

### Day 291 \[Medium\]

This problem was asked by Glassdoor.

An imminent hurricane threatens the coastal town of Codeville. If at most two people can fit in a rescue boat, and the maximum weight limit for a given boat is `k`, determine how many boats will be needed to save everyone.

For example, given a population with weights `[100, 200, 150, 80]` and a boat limit of `200`, the smallest number of boats required will be three.

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day291)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day291/day291.kt)

---

### Day 292 \[Hard\]

This problem was asked by Twitter.

A teacher must divide a class of students into two teams to play dodgeball. Unfortunately, not all the kids get along, and several refuse to be put on the same team as that of their enemies.

Given an adjacency list of students and their enemies, write an algorithm that finds a satisfactory pair of teams, or returns False if none exists.

For example, given the following enemy graph you should return the teams `{0, 1, 4, 5}` and `{2, 3}`.
```
students = {
    0: [3],
    1: [2],
    2: [1, 4],
    3: [0, 4, 5],
    4: [2, 3],
    5: [3]
}
```
On the other hand, given the input below, you should return `False`.
```
students = {
    0: [3],
    1: [2],
    2: [1, 3, 4],
    3: [0, 2, 4, 5],
    4: [2, 3],
    5: [3]
}
```

* [Kotlin implementation](dcp_kotlin/src/main/kotlin/dcp/day292)
* [Unit testing](dcp_kotlin/src/test/kotlin/dcp/day292/day292.kt)
