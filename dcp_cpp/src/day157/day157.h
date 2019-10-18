/**
 * day157.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <string>
#include <unordered_map>

namespace dcp::day157 {
    // A string is a permutation of a palindrome if every character - with the possible exception of one - occurs
    // an even number of times. This runs in O(n).
    bool is_palindrome_permutation(const std::string &str) {
        std::unordered_map<char, size_t> counts{};

        // Count the number of appearances of each character in the string.
        std::for_each(std::cbegin(str), std::cend(str), [&counts] (const char c) {
           ++counts[c];
        });

        // Now count the number of odd-appearing characters in counts.
        const auto odds = std::count_if(std::cbegin(counts), std::cend(counts), [](const auto entry) {
            return entry.second % 2 == 1;
        });

        return odds <= 1;
    }
}
