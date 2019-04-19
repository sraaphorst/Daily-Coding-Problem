# Day 12 Problem \[Hard\]

This problem was asked by Amazon.

There exists a staircase with `N` steps, and you can climb up either 1 or 2 steps at a time. Given `N`, write a function
that returns the number of unique ways you can climb the staircase. The order of the steps matters.

What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive
integers `X`? For example, if `X = {1, 3, 5}`, you could climb 1, 3, or 5 steps at a time.

## Notes

Note that the initial version of this question reduces down to:

Given a checkerboard of size `1 x N`, how many ways are there to tile it using dominoes of length 1 and 2?

This is easily seen to have recurrence relation:

```
f(n) = f(n-1) + f(n-2)
```

with initial conditions:

```
f(0) = 1
f(1) = 1
```

This is exactly the Fibonacci sequence.

The `X = {1, 3, 5}` variation is `A060961` in the Online Encyclopedia of Integer Sequences.

https://oeis.org/A060961

We could possibly find closed forms for certain values of `X` using recurrence relations; we do not do so here.