# Day 35 \[Hard\]

This problem was asked by Google.

Given an array of strictly the characters `R`, `G`, and `B`, segregate the values of the array so that all the `R`s
come first, the `G`s come second, and the `B`s come last. You can only swap elements of the array.

Do this in linear time and in-place.

For example, given the array:
 ```
 ['G', 'B', 'R', 'R', 'B', 'R', 'G'],
 ```
 it should become
 ```
 ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
 ```
 
 Note: we work with `std::string` but this could trivially be changed to another collection type that allows
 bidirectional iterators.
 
 Link: [Testing code](../../test/TestDay035.cpp)