#include "day01.hpp"

#include <algorithm>
#include <fstream>
#include <iostream>
#include <ranges>
#include <set>
#include <utility>
#include <vector>

std::pair<std::vector<int>, std::vector<int> > parseDay01 (std::istream & in)
{
    int first, second;
    std::vector<int> v1, v2;

    while (in >> first >> second) {
      v1.emplace_back(first);
      v2.emplace_back(second);
    }

    return std::make_pair(v1, v2);
}

std::optional<Solution> Day01::part1(const std::string &fileName) const {
    std::ifstream file = std::ifstream(fileName, std::ios::in);
    std::pair<std::vector<int>, std::vector<int>> input = parseDay01(file);

    std::sort(input.first.begin(), input.first.end());
    std::sort(input.second.begin(), input.second.end());

    int sum = 0;

    for (std::tuple<const int &, const int &> i :
         std::views::zip(input.first, input.second)) {
      sum += std::abs(std::get<0>(i) - std::get<1>(i));
    }

    return sum;
}

std::optional<Solution> Day01::part2(const std::string &fileName) const {
    std::ifstream file = std::ifstream(fileName, std::ios::in);
    std::pair<std::vector<int>, std::vector<int>> input = parseDay01(file);

    std::multiset<int> set(input.second.begin(), input.second.end());

    int sum = 0;
    for (const int &i : input.first) {
      sum += i * set.count(i);
    }

    return sum;
}
