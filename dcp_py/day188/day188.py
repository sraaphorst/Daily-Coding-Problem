#!/usr/bin/env python3
# day188.py
# By Sebastian Raaphorst, 2019.


# This will print:
# 3
# 3
# 3
# This is not what we want, because 3 is bound to the outside of the function scope.

def make_functions():
    flist = []

    for i in [1, 2, 3]:
        def print_i():
            # 3 is bound to the outside of the function scope.
            print(i)
        flist.append(print_i)

    return flist


functions = make_functions()
for f in functions:
    f()


# Desired output:
# Instead, we want this. This captures the current state of i inside a closure and passes it inside a function,
# thus printing the expected:
# 1
# 2
# 3
def make_functions2():
    flist2 = []

    for i in [1, 2, 3]:
        def print_i2(var):
            def print2():
                print(var)
            return print2
        flist2.append(print_i2(i))

    return flist2


functions2 = make_functions2()
for f2 in functions2:
    f2()
