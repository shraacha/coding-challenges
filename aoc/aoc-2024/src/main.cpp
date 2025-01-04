#include "solution.hpp"
#include "day.hpp"
#include "day01.hpp"
#include "day02.hpp"
#include "day03.hpp"

#include <cassert>
#include <functional>
#include <fstream>
#include <iostream>
#include <memory>
#include <string>
#include <utility>

std::pair<std::optional<Solution>, std::optional<Solution>> evaluateDay(const Day & day,
                           const std::string &fileName)
{
    return std::make_pair(day.part1(fileName), day.part2(fileName));
}

void printResults(std::ostream & os, std::pair<std::optional<Solution>, std::optional<Solution>> results)
{
    std::cout << "part 1: " << results.first << std::endl;
    std::cout << "part 2: " << results.second << std::endl;
}

std::string dayNumAsString (const int & i, const unsigned int & width = 2)
{
    std::string str = std::to_string(i);
    assert(str.size() <= width);

    int numPad = width - str.size();
    str.insert(0, numPad, '0');

    return str;
}

int main(int argc, char* argv[])
{
    std::vector<std::unique_ptr<Day>> days;

    days.emplace_back(std::make_unique<Day01>());
    days.emplace_back(std::make_unique<Day02>());
    days.emplace_back(std::make_unique<Day03>());

    if (argc == 1) {
      // evaluate all days

      for (int i = 0; i < days.size(); ++i) {
        int dayNum = i + 1;
        std::string dayNumString = dayNumAsString(dayNum);

        std::cout << "Day " << dayNum << ":" << std::endl;

        std::string fileName = "../input/day" + dayNumString + ".txt";
        std::cout << "using file: " << fileName << std::endl;
        std::ifstream file = std::ifstream(fileName, std::ios::in);

        if (!file)
        {
            std::cout << "file opening failed" << std::endl;
        } else {
            printResults(std::cout, evaluateDay(*days[i], fileName));
        }

        std::cout << std::endl;
      }
    }

    return 0;
}
