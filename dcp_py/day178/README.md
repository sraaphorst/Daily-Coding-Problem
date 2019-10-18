# Day 178 \[Hard\]

This problem was asked by Two Sigma.

Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter? Write a program to simulate the two games and calculate their expected value.


# Explanation:

To see which game is better, consider the possibilities that Alice faces.

There are three possible states:
1. Initial state. (A first five must be rolled.)
2. Potential victory state. (The previous roll was a five.)
3. Winning state. (A five followed by the required number was rolled.)

Regardless of choice of game, Alice must first enter into the potential victory state by rolling a 5.

In the 5-5 game, once Alice has entered the potential victory state, she wins if she rolls a 5. She returns to the initial state if she rolls a 1, 2, 3, 4, or 6.

In the 5-6 game, once Alice has entered the potential victory state, she wins if she rolls a 6. She remains in the potential victory state if she rolls a 5. She returns to the initial state if she rolls a 1, 2, 3, or 4.

Thus, the 5-6 game is the better choice of game.
