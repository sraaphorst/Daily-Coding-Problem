/**
 * day020.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <map>
#include <memory>

namespace dcp::day020 {
    // A simple singly linked list struct node.
    // We use this instead of an STL collection because node addresses matter.
    template<typename T>
    using visit_map = std::map<std::shared_ptr<T>, bool>;

    template<typename T>
    struct SNode;

    template<typename T>
    struct SNode {
        T data;
        std::shared_ptr<SNode> next;
    };

    template<typename T>
    std::shared_ptr<T> find_common_node(const std::shared_ptr<T> &sl1, const std::shared_ptr<T> &sl2,
            visit_map<T> &visits) {
        return std::nullptr_t;
    }

    template<typename T>
    std::shared_ptr<T> find_common_node(const std::shared_ptr<T> &sl1, const std::shared_ptr<T> &sl2) {
        return find_common_node(sl1, sl2, visits_map<T>{});
    }
}