#include <stdlib.h>

#include "intMatrix2D.h"

struct IntMatrix2D *newIntMatrix2D(size_t rows, size_t cols) {
    struct IntMatrix2D *matrix = malloc(sizeof(struct IntMatrix2D));

    // storing deets
    matrix->rows = rows;
    matrix->cols = cols;

    // allocating the row array
    matrix->elements = malloc(rows * sizeof(int*));

    // allocating the col arrays
    for(size_t i = 0; i < rows; i++) {
        matrix->elements[i] = malloc(cols * sizeof(int));

        // setting ints to 0
        for(size_t j = 0; j < cols; j++) {
            matrix->elements[i][j] = 0;
        }
    }

    return matrix;
}

int deleteIntMatrix2D (struct IntMatrix2D* matrix) {
    for(size_t i = 0; i < matrix->rows; i++) {
        // looping through the rows
        // freeing the row
        free(matrix->elements[i]);
    }

    // freeing the array of rows
    free(matrix->elements);

    // freeing the matrix itself
    free(matrix);
    return 0;
}

int growIntMatrix2D (struct IntMatrix2D** matrix, size_t newRows, size_t newCols) {
    // TODO use reolloc instead of creating a new matrix
    struct IntMatrix2D* newMatrix = newIntMatrix2D(newRows, newCols);

    for(size_t i = 0; i < (*matrix)->rows && i < newMatrix->rows ; i++) {
        // looping through the rows
        for(size_t j = 0; j < (*matrix)->cols && j < newMatrix->cols; j++) {
            // looping through the col indeces

            // copy values
            newMatrix->elements[i][j] = (*matrix)->elements[i][j];
        }
    }

    deleteIntMatrix2D(*matrix);
    *matrix = newMatrix;
    return 0;
}
