#pragma once

#include <memory>
#include <vector>

#include "solution.hpp"
#include "day.hpp"

std::pair<std::optional<Solution>, std::optional<Solution>>
evaluate(const Day &day, const std::string &fileName);

void printResults(
    std::ostream &os,
    std::pair<std::optional<Solution>, std::optional<Solution>> results);

std::string dayNumAsString(const int &i, const unsigned int &width = 2);

template <typename DayNum>
std::unique_ptr<Day> makeBaseDayUniquePointer ()
{
    return std::make_unique<DayNum>();
}

template <typename ... Ts>
std::vector<std::unique_ptr<Day>> makeVectorOfDayUniquePointers ()
{
    std::vector<std::unique_ptr<Day>> dayVec;

    ((dayVec.emplace_back(makeBaseDayUniquePointer<Ts>())), ...);

    return dayVec;
}
