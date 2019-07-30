/**
 * day114.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <algorithm>
#include <numeric>
#include <string>
#include <iostream>

namespace dcp::day114 {
    void juggle_rotation(std::string &s, size_t start1, size_t end1, size_t start2, size_t end2, size_t d) {
        // Find the size of the subsets to juggle.
        const auto length = end1 - start1 + end2 - start2 + 2;

        if (length <= 1)
            return;

        const auto num_sets = std::gcd(length, d);
        const auto num_rotations_per_set = length / num_sets;

        for (auto start = start1; start < num_sets; ++start) {
            // Save the first element to put into the last place.
            const char tmp = s[start];

            auto current_position = start;
            for (size_t idx = 0; idx < num_rotations_per_set - 1; ++idx) {
                // Now rotate the character at distance d, skipping the middle gap, into the current position.
                auto next_position = current_position + d;

                // Mind the gap if elements are on different sides of it.
                if (current_position <= end1 && next_position > end1)
                    next_position = next_position - end1 + start2 - 1;
                // If we have overlapped, jump back to the beginning.
                if (next_position > end2)
                    next_position = next_position - end2 + start1 - 1;

                s[current_position] = s[next_position];
                //std::cout << current_position << '=' << next_position << ": " << s << '\n';
                current_position = next_position;
            }
            s[current_position] = tmp;
            //std::cout << s << '\n';
        }
    }

    // Juggle rotation using the whole string.
    void juggle_rotation(std::string &s, size_t d) {
        // This is fiddly because of the length calculation in the previous juggle_rotation, so we
        // break the string into substrings of all but the last character and the last character
        // to get the length correct.
        const auto end = s.length() - 1;
        juggle_rotation(s, 0, end-1, end, end, d);
    }
}