cmake_minimum_required(VERSION 3.10)

file (GLOB_RECURSE sols_SOURCES CONFIGURE_DEPENDS "*.cpp")

add_library(sols ${sols_SOURCES})
