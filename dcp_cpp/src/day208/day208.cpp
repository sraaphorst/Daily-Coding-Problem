// day208.cpp
// By Sebastian Raaphorst, 2019.

#include <array>
#include <iostream>

#include "day208.h"

using namespace dcp::day208;

int main() {
    constexpr auto N = 100000;
    constexpr auto maxval = collatz_max_up_to(N);
    std::cout << "The longest collatz sequence in the range is given by " << maxval << '\n';
}