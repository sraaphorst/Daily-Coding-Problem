/**
 * day014.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <math.h>
#include <random>

namespace dcp::day014 {
/**
 * Monte Carlo estimation of pi:
 * If we embed a circle of radius 0.5 in the unit square [0,1] x [0,1], we have that
 * the area of the circle is:
 * A = pi * r^2 = pi * (0.5)^2 = pi / 4.
 * If we sample points over the unit square and determine what ratio of them lie in the
 * circle, we get an approximation of pi / 4.
 */
    double estimate_pi() {
        std::random_device rd;
        std::mt19937 gen(rd());
        std::uniform_real_distribution<> dis(0.0, 1.0);

        size_t trials = 0UL;
        size_t hits = 0UL;

        do {
            ++trials;
            const double x = dis(gen);
            const double y = dis(gen);
            if (x * x + y * y < 1.0) ++hits;
        } while (std::abs(4.0 * hits / trials - M_PI) > 1e-3);

        return 4.0 * hits / trials;
    }
}
