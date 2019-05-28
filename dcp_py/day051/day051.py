#!/usr/bin/env python3
# -*- coding: UTF-8 -*-
# We need previous line since we have UTF-8 characters that we are working with, i.e. the suit representations.
# day051.py
# By Sebastian Raaphorst, 2019.
#
# Perm rank / unrank algorithms inspired from Kreher & Stinson, and the following webpage:
# http://tryalgo.org/en/permutations/2016/09/05/permutation-rank/

# Note that there is no good way to test this problem as a whole, because to demonstrate uniformity, we would have
# to show that each of the 52! = 80658175170943878571660636856403766975289505440883277824000000000000 possible deck
# configurations are equally likely to appear, which would require many more trials than this.

from random import randrange
from math import factorial


class Card:
    ranks = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A']
    suits = ['♠️', '♥️', '♦️', '♣️']
    suit_dict = { 'spades': suits[0], 'hearts': suits[1], 'diamonds': suits[2], 'clubs': suits[3]}

    def __init__(self, rank, suit):
        assert(rank in Card.ranks)
        assert(suit in Card.suits)
        self.rank = rank
        self.suit = suit

    def __str__(self):
        return f'{self.rank}{self.suit}'

    def __repr__(self):
        return f"Card('{self.rank}','{self.suit}')"

    def __eq__(self, other):
        return self.suit == other.suit and self.rank == other.rank

    def __ne__(self, other):
        return not self.__eq__(other)

    def __int__(self):
        """
        Given the current card, rank it as a number in [0,52), with suits being arranged as per the Card.suits list
        and ranks being arranged as per the Card.ranks list.
        :return: the rank of the card.
        """
        return Card.ranks.index(self.rank) + Card.suits.index(self.suit) * len(Card.ranks)

    @staticmethod
    def from_int(rank: int):
        """
        Given the rank of a card as a number in [0,52), with suits being arranged as per the Card.suits list
        and ranks being arranged as per the Cards.ranks list, unrank the card.
        :param rank: the rank of the card
        :return: a representation of the card

        To test, rank / unrank every possible card and make sure the final set contains all the numbers [0,52).
        >>> {int(Card.from_int(i)) for i in range(52)} == set(range(52))
        True
        """
        r = Card.ranks[rank % len(Card.ranks)]
        s = Card.suits[rank // len(Card.ranks)]
        return Card(r, s)

    def __lt__(self, other):
        return self.__int__() < other.__int__()

    def __le__(self, other):
        return self.__int__() <= other.__int__()

    def __ge__(self, other):
        return self.__int__() >= other.__int__()

    def __gt__(self, other):
        return self.__int__() > other.__int__()


class Deck:
    def __init__(self):
        self.cards = [Card(rank, suit) for suit in Card.suits for rank in Card.ranks]

    @staticmethod
    def ordered_deck(s: str):
        """
        This method takes a deck in string form and deserializes it to a real deck.
        :param s: the serialization string of the deck
        :return: the deck
        """
        deck = Deck()
        deck.cards = eval(s)
        return deck

    @staticmethod
    def subdeck(suit: str):
        assert(suit in Card.suits)
        deck = Deck()
        deck.cards = [Card(rank, suit) for rank in Card.ranks]
        return deck

    def __str__(self):
        return str(self.cards)

    def __repr__(self):
        """
        Create a serialization of the current deck, which can be deserialized using ordered_deck.
        :return: a string representing the serialization of the current deck
        """
        return f'Deck.ordered_deck(\"{str(self.cards)}\")'

    def __eq__(self, other):
        return self.cards == other.cards

    def __ne__(self, other):
        return not self.__eq__(other)

    def __int__(self):
        """
        Rank:
        Given an ordering of the cards, determine the rank of the permutation of said ordering.
        :return: an integer in [0,52!) indicating the rank of the permutation.
        """
        n = len(self.cards)
        p = [int(c) for c in self.cards]

        # Compute (n-1)!
        fact = factorial(n - 1)

        # Compute the rank of p.
        r = 0
        digits = list(range(n))
        for i in range(n - 1):
            q = digits.index(p[i])
            r += fact * q
            del digits[q]
            fact //= (n - 1 - i)
        return r

    @staticmethod
    def from_int(rank: int):
        """
        Unrank:
        Given the rank of a deck of cards (in the range [0,52!)), determine the permutation that the rank refers to.
        :param rank: the rank of the permutation of the cards in the deck
        :return: the deck with cards sorted as per the rank
        """
        n = 52
        fact = factorial(n-1)
        digits = list(range(n))
        p = []
        for i in range(n):
            q = rank // fact
            rank %= fact
            p.append(digits[q])
            del digits[q]
            if i != n - 1:
                fact //= (n - 1 - i)
        return Deck.ordered_deck(f'{[Card.from_int(i) for i in p]}')

    def __lt__(self, other):
        return self.__int__() < other.__int__()

    def __le__(self, other):
        return self.__int__() <= other.__int__()

    def __ge__(self, other):
        return self.__int__() >= other.__int__()

    def __gt__(self, other):
        return self.__int__() > other.__int__()

    def shuffle(self):
        """
        This is the famous Fischer-Yates shuffle, also known as the Knuth shuffle or Sattolo's algorithm.
        It shuffles in the following way:
        For an array of length n (indexed [0,n-1]), for each i in descending range [n-1,0]:
            Pick an element from [0,n-1]. Swap it with the n-1th element of the array.
        It is equivalent to picking a random element and putting it face down as the last element in the array.
        Then it picks another random element and puts it on top as the second-last element in the array.
        We continue this until we're done.

        The time complexity is O(n) and as the shuffle is done by swapping, the space complexity is down in O(1).
        """
        n = len(self.cards)
        for i in range(n-1, 0, -1):
            # Pick a card from range(0, i) using our perfect random number generator.

            card_idx = randrange(i)
            self.cards[card_idx], self.cards[i] = self.cards[i], self.cards[card_idx]

    def shuffle_range(self, r):
        for i in range(r-1, 0, -1):
            # Pick a card from range(0, i) using our perfect random number generator.

            card_idx = randrange(i)
            self.cards[card_idx], self.cards[i] = self.cards[i], self.cards[card_idx]


for i in range(10):
    assert(int(Deck.from_int(i)) == i)

