#include "day02.hpp"

#include <algorithm>
#include <fstream>
#include <functional>
#include <numeric>
#include <optional>
#include <ranges>
#include <sstream>
#include <string>

static std::vector<std::vector<int>> parseDay02 (std::istream & in)
{
    std::vector<std::vector<int>> lists;

    for(std::string line; std::getline(in, line);)
    {
        std::vector<int> list;
        std::istringstream stream(line);

        for (int num; stream >> num;) {
            list.emplace_back(num);
        }

        lists.push_back(list);
    }

    return lists;
}

static bool isPairWithinThreshold(const int &n1, const int &n2, const int threshold = 4) {
    int diff = n2 - n1;
    return diff > -threshold && diff < threshold;
}

static bool isPairAscending(const int &n1, const int &n2) {
    return n1 < n2;
}

static bool isPairDescending(const int &n1, const int &n2) {
    return n1 > n2;
}

/**
 * desc:
 *  - returns nullopt if valid, returns the index of the first found invalid
 * number otherwise
 *
 * params:
 *  - ignore
 *    index of number to ignore
 */
static std::optional<int>
findFirstInvalidIndexIgnoringOne(const std::vector<int> &vec, int ignore) {
    // check if the numbers are ascending and satisfy the other rules
    bool subResult = true;
    std::function<bool(const int &, const int &)> isPairOrdered;

    auto getNextIndex = [&](int i) -> int
    {
        if (i == ignore - 1)
        {
            return ignore + 1;
        } else {
            return i + 1;
        }
    };

    for (int i = 0; i < vec.size() - 1; ++i) {
        if (!(i == ignore || (ignore == vec.size() - 1 && i == ignore - 1))) {
            const int &n1 = vec[i];
            const int &n2 = vec[getNextIndex(i)];

            if ((ignore != 0 && i == 0) || (ignore == 0 && i == 1)) {
                if (isPairAscending(n1, n2)) {
                  isPairOrdered = isPairAscending;
                } else {
                  isPairOrdered = isPairDescending;
                }
            }

            if (!isPairOrdered(n1, n2) || !isPairWithinThreshold(n1, n2)) {
                return i;
            }
        }
    }

    return std::nullopt;
}

/**
 * desc:
 *  - returns nullopt if valid, returns the index of the first found invalid
 * number otherwise
 */
static std::optional<int> findFirstInvalidIndex(const std::vector<int> &vec) {
    return findFirstInvalidIndexIgnoringOne(vec, -1);
}

static bool areNumbersValid(const std::vector<int> & vec)
{
    if (!findFirstInvalidIndex(vec).has_value()) {
        return true;
    } else {
        return false;
    }
}

static bool areNumbersValidIgnoringIndex(const std::vector<int> & vec, int i)
{
    if (!findFirstInvalidIndexIgnoringOne(vec, i).has_value()) {
        return true;
    } else {
        return false;
    }
}

static bool areNumbersValidWithDampening(const std::vector<int> & vec)
{
    if (auto optionalInvalidIndex = findFirstInvalidIndex(vec);
        optionalInvalidIndex.has_value()) {
        int i = optionalInvalidIndex.value();
        return areNumbersValidIgnoringIndex(vec, i - 1) ||
               areNumbersValidIgnoringIndex(vec, i) ||
               areNumbersValidIgnoringIndex(vec, i + 1);
    } else {
        return true;
    }
}

int day02Part1 (const std::string & fileName)
{
    std::ifstream fileStream(fileName, std::ios::in);
    auto input = parseDay02(fileStream);

    return std::accumulate(input.begin(), input.end(), 0,
                    [](const int &prev, const std::vector<int> &vec) -> int {
                        return prev + areNumbersValid(vec);
                    });
}

int day02Part2 (const std::string & fileName)
{
    std::ifstream fileStream(fileName, std::ios::in);
    auto input = parseDay02(fileStream);

    return std::accumulate(input.begin(), input.end(), 0,
                    [](const int &prev, const std::vector<int> &vec) -> int {
                        return prev + areNumbersValidWithDampening(vec);
                    });
}
