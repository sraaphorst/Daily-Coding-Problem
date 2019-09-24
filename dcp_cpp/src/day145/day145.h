/**
 * day145.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <iterator>
#include <memory>
#include <ostream>

namespace dcp::day145 {
    template<typename T>
    struct Node {
        T data{};
        std::shared_ptr<Node> next;
        explicit Node(T data) : data{data} {}
    };


    /**
     * Singly linked list. As a result, it acts like a stack, and elements are only added at the front.
     * @tparam T
     */
    template<typename T>
    struct List {
        List(): head{nullptr} {}

        void add(T value) {
            addElement(value);
        }

        // Add all the elements in reverse order.
        template<typename... Ts>
        void addAll(Ts... ts) {
            (...,addElement(ts));
        }

        auto &getHead() const {
            return head;
        }

        // The swapper as defined in the code.
        void swapConsecutive() {
            head = swapConsecutiveAux(head);
        }

        bool operator==(const List<T> &other) const {
            auto l1 = head;
            auto l2 = other.head;

            while (l1 && l2) {
                if (l1->data != l2->data)
                    return false;
                l1 = l1->next;
                l2 = l2->next;
            }
            return l1 == nullptr && l2 == nullptr;
        }

    private:

        // The swapper auxiliary method.
        std::shared_ptr<Node<T>> swapConsecutiveAux(std::shared_ptr<Node<T>> n1) {
            // Premature termination: nothing to swap.
            if (n1 == nullptr ||  n1->next == nullptr)
                return n1;

            // Swap the values
            auto n2 = n1->next;
            auto n3 = n2->next;

            n2->next = n1;
            n1->next = n3;

            // Iterate to make the new root the tail of this bit.
            auto nextHead = swapConsecutiveAux(n3);
            n1->next = nextHead;

            return n2;
        }

        void addElement(T data) {
            auto newElem{std::make_shared<Node<T>>(data)};
            if (head) {
                newElem->next = std::move(head);
                head = std::move(newElem);
            } else {
                head = std::move(newElem);
            }
        }

        std::shared_ptr<Node<T>> head{nullptr};

        template<T>
        friend std::ostream& operator<<(std::ostream&, const List<T>&);
    };

    template<typename T>
    std::ostream& operator<<(std::ostream &out, const List<T> &list) {
        out << '[';
        std::shared_ptr<Node<T>> node = list.getHead();
        while (node) {
            out << node->data;
            node = node->next;
            if (node)
                std::cout << ", ";
        }
        return out << ']';
    }
}