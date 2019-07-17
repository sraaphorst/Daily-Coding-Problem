#!/usr/bin/env python3

# Bad code:
# Prints the number 9 ten times, since i is a global variable.
functions = []
for i in range(10):
    functions.append(lambda: i)

for f in functions:
    print(f())

# Good code:
# Take value of i and use it to create a lambda function via a lambda function.
functions = []
for i in range(10):
    functions.append((lambda x: (lambda: x))(i))

for f in functions:
    print(f())
