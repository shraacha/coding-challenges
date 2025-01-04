#pragma once

#include <string>

#include "day.hpp"

class Day03 : public Day {
  virtual std::optional<Solution> part1(const std::string &fileName) const override;

  virtual std::optional<Solution> part2(const std::string &fileName) const override;
};
