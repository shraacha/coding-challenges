#pragma once

#include <iostream>
#include <optional>

using Solution = int;

std::ostream& operator<<(std::ostream & os, const std::optional<Solution> & sol);
