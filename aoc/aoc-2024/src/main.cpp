#include "solution.hpp"
#include "day.hpp"
#include "day01.hpp"
#include "day02.hpp"
#include "day03.hpp"
#include "dayHelpers.hpp"

#include <fstream>
#include <iostream>
#include <memory>
#include <stdexcept>
#include <string>
#include <vector>
#include <stdexcept>

std::string getPathToInputDir()
{
    return "../input";
}

bool canOpenFile(const std::string &fileName) {
  std::ifstream file(fileName);
  return file.good();
}

int main(int argc, char* argv[])
{
    std::vector<std::unique_ptr<Day>> days = makeVectorOfDayUniquePointers<Day01, Day02, Day03>();

    auto evaluateDay = [&](const int &dayNum,
                                          std::string fileName = "") -> std::pair<std::optional<Solution>, std::optional<Solution>> {
      std::cout << "Day " << dayNum << ":" << std::endl;

      if (fileName == "") {
        fileName = getPathToInputDir() + "/day" + dayNumAsString(dayNum) + ".txt";
      }

      std::cout << "Using file: " << fileName << std::endl;

      if (canOpenFile(fileName)) {
        return evaluate(*days[dayNum - 1], fileName);
      } else {
        std::cout << "File opening failed." << std::endl;
        std::cout << std::endl;
        throw std::invalid_argument("File not found.");
      }
    };

    try {
      if (argc == 1) {
        // evaluate all days
        for (int i = 0; i < days.size(); ++i) {
          printResults(std::cout, evaluateDay(i+1));
        }
      } else if (argc == 2) {
        // evaluate a specific day
        if (int i = std::stoi(argv[1]); i > 0 && i <= days.size()) {
          printResults(std::cout, evaluateDay(i));
        } else {
          throw std::invalid_argument("Day number out of bounds.");
        }
      } else if (argc == 3) {
        // evaluate a specific day using a specific file
        int i = std::stoi(argv[1]);
        std::string fileName(argv[2]);

        if (!(i > 0 && i <= days.size())) {
          throw std::invalid_argument("Day number out of bounds.");
        }

        printResults(std::cout, evaluateDay(i, fileName));
      }
    } catch (const std::exception &ex) {
      std::cout << ex.what() << std::endl;
    }

    return 0;
}
