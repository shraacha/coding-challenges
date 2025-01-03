#include "day01.hpp"
#include "day02.hpp"
#include "day03.hpp"

#include <iostream>
#include <functional>
#include <utility>

int placeHolderSol (const std::string &) {
    return 0;
}

std::pair<int, int> evaluateDay(const std::function<int(const std::string &)> &part1,
                           const std::function<int(const std::string &)> &part2,
                           const std::string &fileName)
{
    return std::make_pair(part1(fileName), part2(fileName));
}

void printDayResults(std::ostream & os, std::pair<int, int> results)
{
    std::cout << "part 1: " << results.first << std::endl;
    std::cout << "part 2: " << results.second << std::endl;
}

int main()
{
    std::string fileName("../input/day03.txt");

    std::cout << "file name: " << fileName << std::endl;

    printDayResults(std::cout, evaluateDay(day03Part1, day03Part2, fileName));

    return 0;
}
