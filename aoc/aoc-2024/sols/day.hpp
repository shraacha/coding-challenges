#pragma once

#include <string>
#include <optional>

#include "solution.hpp"

class Day {
public:
  virtual std::optional<Solution> part1(const std::string &fileName) const {
    return std::nullopt;
  }

  virtual std::optional<Solution> part2(const std::string &fileName) const {
    return std::nullopt;
  }
};
