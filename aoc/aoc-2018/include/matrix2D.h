#ifndef MATRIX2D_H
#define MATRIX2D_H

#include <stdbool.h>
#include <stddef.h>

// generic
struct Matrix2D {
    size_t rows, cols;
    void*** elements;
};

struct Matrix2D* newMatrix2D(size_t width, size_t height);

int deleteMatrix2D (struct Matrix2D* matrix);

/*
 * - move pointers in matrix1 into matrix2
 * - assumes matrix2 is fresh
 */
int moveMatrix2DContents (struct Matrix2D* matrix1, struct Matrix2D* matrix2);

/* allocateIntMatrix
 *
 * desc:
 * - allocates ints for the matrix passed in.
 * - assumes that the matrix is fresh
 */
int allocateIntMatrix(struct Matrix2D*);

#endif
