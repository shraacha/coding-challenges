#ifndef INTMATRIX2D_H
#define INTMATRIX2D_H

#include <stdbool.h>
#include <stddef.h>

// generic
struct IntMatrix2D {
    size_t rows, cols;
    int** elements;
};

struct IntMatrix2D* newIntMatrix2D(size_t rows, size_t cols);

int deleteIntMatrix2D (struct IntMatrix2D* matrix);

/*
 * - grow matrix1 contents to size of rows x cols
 */
int growIntMatrix2D (struct IntMatrix2D** matrix, size_t newRows, size_t newCols);

#endif
