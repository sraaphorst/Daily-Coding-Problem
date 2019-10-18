# Day 171 \[Medium\]

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