# CMakeLists.txt
# By Sebastian Raaphorst, 2019.

cmake_minimum_required(VERSION 3.12)
project(daily_coding_problem)

set(CMAKE_CXX_STANDARD 17)
if (MSVC)
    add_compile_options(/W4)
else()
    add_compile_options(-Wall -Wextra -Wpedantic -Wno-narrowing -ftemplate-depth=10000 -fconstexpr-depth=2000 -fconstexpr-ops-limit=1000000000 -fconstexpr-loop-limit=1000000 -std=c++17 -g)
endif()

include_directories("${PROJECT_SOURCE_DIR}/src")
add_subdirectory(test)

add_executable(day064 src/day064/day064.cpp)
add_executable(wtf wtf.cpp)