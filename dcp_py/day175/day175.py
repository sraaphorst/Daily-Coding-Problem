#!/usr/bin/env python3
# day175.py
# By Sebastian Raaphorst, 2019.

from typing import Callable, Dict, List, Tuple
from random import random

# Type aliases.
State = str
Probability = float
Transition = Tuple[State, State, Probability]
StateProbability = Tuple[State, Probability]
Markov = List[Transition]
MarkovChain = Dict[State, int]


def run_markov(markov: Markov, initial_state: State, steps: int) -> MarkovChain:
    """
    Given a representation of probabilities for a Markov chain, an initial state, and a number of steps to take,
    run the Markov chain for the number of steps indicated, and return the number of times each state was visited.

    :param markov: the representation of the Markov chain probabilities
    :param initial_state: the state in which to initially begin: does not contribute to the visits
    :param steps: the number of steps to take
    :return:
    """
    # Create a function that performs the a based on the probabilities.
    def create_step_function(for_state: State, probabilities: List[StateProbability]) -> Callable[[], State]:
        """
        This function creates a step function from a list of states and probabilities.
        Example: for_state = 'a' and probabilities = [('a', 0.25), ('b', 0.75)], this creates a function that
        assumes the chain is at state a, generates a probability, and then returns either a or b based on that
        probabilty (with 0.25 for a and 0.75 for b respectively).

        :param for_state: the state for which this transition function is generated (used for exception reporting)
        :param probabilities: the list of states and probabilities representing the function.
        :return: the function as described above, which takes no parameters and simply takes a step based on probability
        """
        def step() -> State:
            """
            A simple function that generates a probability and takes a step based on probabilities passed to the
            parent function.
            :return: the new state as a result of the step.
            """
            probability = random()
            for state, state_probability in probabilities:
                if probability <= state_probability:
                    return state
                probability -= state_probability

            # If this point is ever reached, the probability of the states did not equate to 1.
            raise ValueError(f"Probabilities do not sum to 1 for state {for_state}.")

        # Return the parameterless step function.
        return step

    # Get the list of all states, which are considered
    states = [state for state, _, _ in markov]

    # Convert into a list of machines.
    # For our purposes, a machine is a state and a list of states to which it can move via a probability.
    machines = {state: [(destination, probability) for initial_state, destination, probability
                        in markov if state == initial_state] for state in states}

    # Now we have the actual machinery that handles taking the next step by using a probability function generated
    # by transition for each state.
    step_machines = {state: create_step_function(state, machines[state]) for state in states}

    # Record the number of visits per state.
    visits = {state: 0 for state in states}
    current_state = initial_state
    for _ in range(steps):
        # Call the step function to move to the next step.
        current_state = step_machines[current_state]()
        visits[current_state] += 1

    return visits


if __name__ == '__main__':
    # The example of the Markov probability chain given in the question.
    m = [
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

    visits = run_markov(m, 'a', 5000)
    print(visits)
