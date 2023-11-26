#include <stdio.h>

#include "intMatrix2D.h"

int main() {
    struct IntMatrix2D* matrix = newIntMatrix2D(5, 10);

    // size correct? elements zeroed?:
    printf("rows: %zu, cols: %zu, (4, 9): %d\n", matrix->rows, matrix->cols, matrix->elements[4][9]);

    matrix->elements[4][9] = 10;
    // grow test
    growIntMatrix2D(matrix, 20, 20);
    printf("rows: %zu, cols: %zu, (4, 9): %d, (19, 19): %d\n", matrix->rows, matrix->cols, matrix->elements[4][9], matrix->elements[19][19]);

    deleteIntMatrix2D(matrix);
    return 0;
}
