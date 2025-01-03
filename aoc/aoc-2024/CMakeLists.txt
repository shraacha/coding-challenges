cmake_minimum_required(VERSION 3.10)
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)
set(CMAKE_CXX_STANDARD 23)
set(CMAKE_CXX_EXTENSIONS OFF)

project(aoc-2024)

file (GLOB_RECURSE aoc-2024_SOURCES CONFIGURE_DEPENDS "src/*.cpp")

add_executable(aoc-2024 ${aoc-2024_SOURCES})

add_subdirectory(sols)

target_link_libraries(aoc-2024 PUBLIC sols)

target_include_directories(aoc-2024
   PUBLIC "${PROJECT_BINARY_DIR}" "${PROJECT_SOURCE_DIR}/sols")
