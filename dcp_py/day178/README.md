# Day 178 \[Hard\]

This problem was asked by Two Sigma.

Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter? Write a program to simulate the two games and calculate their expected value.


# Explanation:

Regardless of which game Alice is playing, in order to put herself in a state
where she is capable of winning, she must roll a 5. After this, the choice
of game becomes important:

1. Regardless of game, if Alice rolls 0-4, she is back in the state where she
is not capable of winning in one roll.
2. If Alice rolls a 5, then regardless of which game Alice is playing, she either wins
or remains in the state where she is capable of winning in one roll.
3. If Alice rolls a 6 in the 5 followed by 6 game, then Alice wins.

Playing either game, Alice needs to roll a 5 to put her in the potential of winning.
1. In the 5-5 game, if Alice rolls anything other than a 6, Alice drops to the losing state where she must first roll a 5.
2. In the 5-6 game, if Alice rolls a 5, she remains in the potential winning state. 