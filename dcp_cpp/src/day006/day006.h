/**
 * day006.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <stdexcept>
#include <sstream>

namespace dcp::day006 {
    template<typename T>
    class xor_list;

    template<typename T>
    class node final {
        T data;
        node *both;

        template<typename U>
        node(U &&t, node<T> *prev) noexcept: data{t}, both{prev} {}

        friend class xor_list<T>;
    };

    template<typename T>
    class xor_list final {
        node<T> *front;
        node<T> *back;
        size_t siz;

        // Used to combine pointers to create both.
        static node<T> *combine(node<T> *n1, node<T> *n2) {
            return reinterpret_cast<node<T>*>(reinterpret_cast<uint64_t>(n1) ^ reinterpret_cast<uint64_t>(n2));
        }

    public:
        xor_list() noexcept: front{nullptr}, back{nullptr}, siz{0} {}

        ~xor_list() {
            // Delete all the nodes.
            back = nullptr;
            node<T> *prev = nullptr;
            node<T> *next = nullptr;
            node<T> *curr = front;
            for (size_t i = 0; i < siz; ++i ){
                next = combine(prev, curr->both);
                curr->both = nullptr;
                prev = curr;
                delete curr;
                curr = next;
            }
            front = nullptr;
        }

        T &get(size_t index) {
            if (index > siz) {
                std::ostringstream str;
                str << "out of bounds: tried to access " << index << " in list of size " << siz;
                throw std::invalid_argument{str.str()};
            }
            node<T> *curr = front;
            node<T> *prev = nullptr;
            node<T> *prevtmp = nullptr;

            for (size_t i = 0; i < index; ++i) {
                prevtmp = curr;
                curr = combine(prev, curr->both);
                prev = prevtmp;
            }

            return curr->data;
        }

        template<typename U>
        void add(U&& u) noexcept {
            auto n = new node<T>{u, back};
            if (siz == 0)
                front = n;
            else
                back->both = combine(back->both, n);
            back = n;
            ++siz;
        }

        [[nodiscard]] size_t size() const noexcept {
            return siz;
        }
    };
}