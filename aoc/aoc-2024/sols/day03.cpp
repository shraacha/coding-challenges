#include "day03.hpp"

#include <algorithm>
#include <fstream>
#include <iostream>
#include <optional>
#include <string>
#include <vector>

class PatternMatcher {
  std::vector<std::string> pattern;
  unsigned int t = 0;
  unsigned int p = 0;
  std::string token = "";
  std::vector<std::string> tokens;

  void resetState () {
    t = 0;
    p = 0;
    token = "";
    tokens = {};
  };

  void resetTokenState () {
    p = 0;
    token = "";
  };

  void nextPosition () {
    ++p;
  };

  void nextToken () {
    ++t;
    tokens.push_back(token);
    resetTokenState();
  };

  bool isPatternParsed () {
    if (t >= pattern.size()) {
      return true;
    } else {
      return false;
    }
  };

  bool isTokenParsed () {
    if (p >= pattern[t].size()) {
      return true;
    } else {
      return false;
    }
  };

public:
  PatternMatcher(const std::vector<std::string> &pattern) : pattern{pattern} {}
  PatternMatcher(const std::vector<std::string> &&pattern)
      : pattern{std::move(pattern)} {}

  /*
   * desc:
   * - does an online match against a pattern, assuming if the pattern match
   * is broken the earliest the pattern can reappear is where the match was
   * broken
   *
   * return value:
   * - nullopt if pattern has not been matched
   * - vector of the parsed tokens if matched
   */
  std::optional<std::vector<std::string>>
  simpleOnlineMatchAgainstPattern(const char &c) {
    if (pattern[t] == "UINT") {
      // indefinite token
      if (c >= '0' && c <= '9') {
        nextPosition();
        token.push_back(c);
      } else {
        nextToken();
        if (isPatternParsed()) {
          std::vector<std::string> parsedTokens = std::move(tokens);
          resetState();
          return parsedTokens;
        } else {
          return simpleOnlineMatchAgainstPattern(c);
        }
      }
    } else {
      // definite token
      if (pattern[t][p] == c) {
        nextPosition();
        token.push_back(c);
        if (isTokenParsed()) {
          nextToken();
          if (isPatternParsed()) {
            std::vector<std::string> parsedTokens = std::move(tokens);
            resetState();
            return parsedTokens;
          }
        }
      } else {
        resetState();
      }
    }

    return std::nullopt;
  }
};

// returns true of the token is indefinite
bool isIndefinite(const std::string &str) {
  if (str == "UINT") {
    return true;
  } else {
    return false;
  }
}


// sols
int day03Part1 (const std::string & fileName)
{
    int sum = 0;

    std::ifstream fileStream(fileName, std::ios::in);

    char c;
    PatternMatcher pattern({"mul(", "UINT", ",", "UINT", ")"});

    while (fileStream >> c) {
        auto result = pattern.simpleOnlineMatchAgainstPattern(c);

        if (result.has_value()) {
            sum += std::stoi(result.value()[1]) * std::stoi(result.value()[3]);
        }
    }

    return sum;
}

int day03Part2 (const std::string & fileName)
{
    int sum = 0;

    std::ifstream fileStream(fileName, std::ios::in);

    char c;
    bool doState = true;
    PatternMatcher mulPattern({"mul(", "UINT", ",", "UINT", ")"});
    PatternMatcher doPattern({"do()"});
    PatternMatcher dontPattern({"don't()"});

    while (fileStream >> c) {
        auto mulResult = mulPattern.simpleOnlineMatchAgainstPattern(c);
        auto doResult = doPattern.simpleOnlineMatchAgainstPattern(c);
        auto dontResult = dontPattern.simpleOnlineMatchAgainstPattern(c);

        if (doResult.has_value()) {
            doState = true;
        } else if (dontResult.has_value()) {
            doState = false;
        }

        if (mulResult.has_value() && doState) {
            sum += std::stoi(mulResult.value()[1]) * std::stoi(mulResult.value()[3]);
        }
    }

    return sum;
}
