/**
 * day016.h
 *
 * By Sebastian Raaphorst, 2020.
 */

#pragma once

#include <array>
#include <stdexcept>
#include <sstream>

namespace dcp::day016 {
    using LogID = size_t;

    /**
     * A history of order numbers of fixed size using a circular buffer.
     * @tparam N
     */
    template <int N>
    class OrderHistory final {
        static_assert(N >= 0);
    private:
        std::array<LogID, N> logHistory{};
        int logPtr = 0;

    public:
        OrderHistory() noexcept = default;
        OrderHistory(const OrderHistory&) noexcept = default;
        OrderHistory(OrderHistory&&) noexcept = default;
        ~OrderHistory() noexcept = default;

        OrderHistory &operator=(const OrderHistory&) noexcept = default;
        OrderHistory &operator=(OrderHistory&&) noexcept = default;

        bool operator==(const OrderHistory &other) const noexcept {
            return logHistory == other.logHistory && logPtr == other.logPtr;
        }

        bool operator!=(const OrderHistory &other) const noexcept {
            return !(*this == other);
        }

        /**
         * Record an order ID in the history, purging the oldest order ID if full (i.e. contains N orders).
         * @param orderId the order ID
         */
        void record(const LogID orderId) noexcept {
            logHistory[logPtr] = orderId;
            logPtr = (logPtr + 1) % N;
        }

        /**
         * Get the idx-th oldest order ID from the history, up to N orders in the past.
         * If there are less than idx orders in the history, returns 0.
         * @param idx the index of the history, with 0 being the most recent and N-1 being the least recent
         * @return the ID of the order if it exists, else 0
         * @throws invalid_argument exception if idx is outside the bounds of the history, i.e. 0 <= idx < N
         */
        [[nodiscard]] LogID getLast(const int idx) const {
            if (idx < 0 || idx >= N) {
                std::stringstream str;
                str << "Invalid index: " << idx;
                throw std::invalid_argument(str.str());
            }
            return logHistory[(logPtr + N - idx - 1) % N];
        }
    };
}