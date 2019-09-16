#include <algorithm>
#include <unordered_map>
#include <memory>
#include <tuple>

#pragma once

namespace dcp::day162 {
    struct MinimalPrefixNode final {
        // Number of times this node has been visited: only nodes visited once comprise a unique common prefix.
        // For example, dotty, dog, ditch:
        // 1. d has been visited 3 times, so not a unique common prefix
        // 2. do has been visited 2 times, so not a unique common prefix
        // 3. dot has been visited once, so a unique common prefix
        // 3. dog has been visited once, so a unique common prefix
        // 4. di has been visited once, so a unique common prefix
        // So our unique common prefix set is dot, dog, di
        int visits = 1;
        std::unordered_map<char, std::shared_ptr<MinimalPrefixNode>> subnodes{};

        // We also need to keep track of if something is a word. This comes up, for example, if we have, say,
        // dog and doggy in our trie. The visits for dog will be 2, so it will not register as a unique prefix,
        // though it is the only available prefix for dog. Thus, if visits > 1 and is_word is true, we include
        // the prefix represented by this node as a prefix.
        bool is_word = false;
    };

    namespace details {
        // Auxiliary method to iterate over the trie and collect the unique prefixes into the output iterator.
        // This collects the string as it progresses through the trie.
        void output_minimal_prefixes_iterator_aux(const std::string &str,
                                                  const std::shared_ptr<MinimalPrefixNode> &node,
                                                  std::set<std::string> &prefixes) {
            // If we have reached a node with only one visit, it is a unique prefix for the word its extension
            // represents in the trie.
            if (node->visits == 1 || (node->visits > 1 && node->is_word)) {
                prefixes.insert(str);

                // If we are not a word, then we can terminate: otherwise, we might be a subword of another word
                // and need to continue.
                if (!node->is_word)
                    return;
            }

            // Iterate over the trie nodes.
            for (const auto &iter: node->subnodes) {
                const char ch = iter.first;
                output_minimal_prefixes_iterator_aux(str + ch, iter.second, prefixes);
            }
        }


        // Iterate over the trie and collect the unique prefixes into the output iterator.
        std::set<std::string> output_minimal_prefixes_iterator(const std::shared_ptr<MinimalPrefixNode> &node) {
            // Begin with the empty string and continue to collect the characters as we traverse the trie.
            std::set<std::string> prefixes;
            details::output_minimal_prefixes_iterator_aux("", node, prefixes);
            return prefixes;
        }
    }

    template<typename InputIter>
    std::set<std::string> create_minimal_representation(InputIter inputBegin, InputIter inputEnd) {
        auto root = std::make_shared<MinimalPrefixNode>();

        // Process each word.
        for (auto str = inputBegin; str != inputEnd; ++str) {
            // Reset to the root of the trie.
            auto node = root;
            ++node->visits;

            // Process each character from each word: hence running time is O(n), where n is the number of characters.
            for (const auto ch: *str) {
                // Try to find MinimalPrefixNode corresponding to ch.
                auto iter = node->subnodes.find(ch);

                // If we cannot find it, create a new node with one visit representing this prefix and advance
                // pointer so that we can complete the word, which is necessary to account for further overlapped
                // words.
                if (iter == std::end(node->subnodes)) {
                    auto newNode = std::make_shared<MinimalPrefixNode>();
                    node->subnodes.insert({ch, newNode});
                    node = newNode;

                // Otherwise, we have found it. Increase the number of visits and advance the pointer.
                } else {
                    ++(iter->second->visits);
                    node = iter->second;
                }
            }

            // Mark the complete word as a word in the trie.
            node->is_word = true;
        }

        // Return the complete trie with the number of words passing through each mode.
        return details::output_minimal_prefixes_iterator(root);
    }
}