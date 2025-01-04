#include "solution.hpp"

std::ostream& operator<<(std::ostream & os, const std::optional<Solution> & sol)
{
    if(sol.has_value())
    {
        os << sol.value();
    } else {
        os << "Incomplete Solution";
    }

    return os;
}
