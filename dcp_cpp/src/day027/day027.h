/**
 * day027.h
 *
 * By Sebastian Raaphorst, 2019.
 */

#pragma once

#include <stack>
#include <stdexcept>
#include <string>
#include <iostream>

namespace dcp::day027 {
    auto is_well_formed(const std::string &parens) {
        std::stack<char> stk{};

        for (const auto s: parens) {
            if (s != '{' && s != '}' && s != '(' && s != ')' && s != '[' && s != ']') {
                std::string msg = "Illegal character: ";
                msg += s;
                throw std::invalid_argument(msg);
            }
            if (s == '{' || s == '(' || s == '[')
                stk.emplace(s);
            else if (stk.empty())
                return false;
            else if (s == '}' && stk.top() != '{')
                return false;
            else if (s == ')' && stk.top() != '(')
                return false;
            else if (s == ']' && stk.top() != '[')
                return false;
            else
                stk.pop();
        }

        return stk.empty();
    }
}
