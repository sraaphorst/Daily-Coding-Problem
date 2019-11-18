// day223.h
// By Sebastian Raaphorst, 2019.

#pragma once

#include <memory>

namespace dcp::day223 {

    template<typename T> struct Node;
    template<typename T>
    using NodePtr = std::shared_ptr<Node<T>>;

// Convert a tree with a given root to a threaded binary tree.
    template<typename T>
    struct Node {
        Node(const T &t) noexcept: value{t}, left{nullptr}, right{nullptr}, isThreaded{false} {};

        Node(const Node &) = default;

        Node(Node &&) = default;

        T value;
        std::shared_ptr<Node<T>> left;
        std::shared_ptr<Node<T>> right;
        bool isThreaded = false;
    };

    template<typename T>
    std::shared_ptr<T> createThreadedBinaryTree(std::shared_ptr<T> &root) {
        // Base cases: empty or single node.
        if (root == nullptr)
            return nullptr;
        if (root->left == nullptr && root->right == nullptr)
            return root;

        // Find the predecessor, if it exists.
        if (root->left != nullptr) {
            // Predecessor of root is rightmost child in left subtree.
            std::shared_ptr<T> l = createThreadedBinaryTree(root->left);
            l->right = root;
            l->isThreaded = true;
        }

        // If the current node is the rightmost child:
        if (root->right == nullptr)
            return root;

        // Recurse for the right subtree.
        return createThreadedBinaryTree(root->right);
    }

    template<typename T>
    std::shared_ptr<T> leftmost(std::shared_ptr<T> &node) {
        if (node == nullptr)
            return nullptr;
        while (node->left != nullptr)
            node = node->left;
        return node;
    }

    template<typename T, typename C>
    void inOrderTraversal(std::shared_ptr<Node<T>> &root, C c) {
        std::shared_ptr<Node<T>> cur = leftmost(root);
        while (cur != nullptr) {
            c(cur->value);
            if (cur->isThreaded)
                cur = cur->right;
            else
                cur = leftmost(cur->right);
        }
    }
}
