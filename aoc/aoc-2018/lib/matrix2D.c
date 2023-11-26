#include <stdlib.h>

#include "matrix2D.h"

struct Matrix2D *newMatrix2D(size_t rows, size_t cols) {
    struct Matrix2D *matrix2D = malloc(sizeof(struct Matrix2D));

    // storing deets
    matrix2D->rows = rows;
    matrix2D->cols = cols;

    // allocating the row array
    matrix2D->elements = malloc(rows * sizeof(void**));

    // allocating the col arrays
    for(size_t i = 0; i < rows; i++) {
        matrix2D->elements[i] = malloc(cols * sizeof(void*));

        // setting pointers to NULL
        for(size_t j = 0; j < cols; j++) {
            matrix2D->elements[i][j] = NULL;
        }
    }

    return matrix2D;
}

int deleteMatrix2D (struct Matrix2D* matrix) {
    for(size_t i = 0; i < matrix->rows; i++) {
        // looping through the rows
        for(size_t j = 0; j < matrix->cols; j++) {
            // looping through the col indeces
            // freeing the entries
            free(matrix->elements[i][j]);
        }
        // freeing the row
        free(matrix->elements[i]);
    }

    // freeing the array of rows
    free(matrix->elements);

    // freeing the matrix itself
    free(matrix);
    return 0;
}

int moveMatrix2DContents (struct Matrix2D* matrix1, struct Matrix2D* matrix2) {
    if (matrix2->rows < matrix1->rows || matrix2->cols < matrix1->cols) {
        // matrix 2 is not large enough
        return -1;
    }

    for(size_t i = 0; i < matrix1->rows; i++) {
        // looping through the rows
        for(size_t j = 0; j < matrix1->cols; j++) {
            // looping through the col indeces

            // move values
            matrix2->elements[i][j] = matrix1->elements[i][j];

            // null matrix1 entries
            matrix1->elements[i][j] = NULL;
        }
    }
}

int allocateIntMatrix(struct Matrix2D* matrix) {
    for(size_t i = 0; i < matrix->rows; i++) {
        // looping through the rows
        for(size_t j = 0; j < matrix->cols; j++) {
            // looping through the col indeces
            matrix->elements[i][j] = malloc(sizeof(int));
        }
    }
    return 0;
}
