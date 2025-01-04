#include "dayHelpers.hpp"

#include <cassert>
#include <cstddef>
#include <functional>
#include <fstream>
#include <iostream>
#include <memory>
#include <string>
#include <utility>

std::pair<std::optional<Solution>, std::optional<Solution>>
evaluate(const Day &day, const std::string &fileName) {
  return std::make_pair(day.part1(fileName), day.part2(fileName));
}

void printResults(
    std::ostream &os,
    std::pair<std::optional<Solution>, std::optional<Solution>> results) {
  std::cout << "part 1: " << results.first << std::endl;
  std::cout << "part 2: " << results.second << std::endl;
}

std::string dayNumAsString(const int &i, const unsigned int &width) {
  std::string str = std::to_string(i);
  assert(str.size() <= width);

  int numPad = width - str.size();
  str.insert(0, numPad, '0');

  return str;
}
