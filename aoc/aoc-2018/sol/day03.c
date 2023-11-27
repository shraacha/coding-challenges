#include "intMatrix2D.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MYINPUTLOC              "../input/day03_in.txt"

int main() {
    // input
    FILE * input = fopen(MYINPUTLOC, "r");

    if (input == NULL) {
      printf("input fp null\n");
    } else {
      /* printf("not null\n"); */
    }


    char currWord[32];
    char corner[32];
    char size[32];
    int x, y, width, height;
    char* token;

    // TODO init matrix
    struct IntMatrix2D* matrix = newIntMatrix2D(10, 10);

    // p1
    while (fgets(currWord, 32, input) != NULL) {
        // tokenizing the input
        // id & '@'
        token = strtok(currWord, " \n\r");
        token = strtok(NULL, " \n\r");

        // top left corner
        token = strtok(NULL, " \n\r");
        strcpy(corner, token);

        // size
        token = strtok(NULL, " \n\r");
        strcpy(size, token);

        // getting corner data
        token = strtok(corner, ",");
        x = atoi(token);
        token = strtok(NULL, ",");
        y = atoi(token);

        // getting size data
        // getting corner data
        token = strtok(size, "x");
        width = atoi(token);
        token = strtok(NULL, "");
        height = atoi(token);

        /* printf("*TEST* x = %d, y = %d, width = %d, height = %d\n", x, y, width, height); //testing */

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (y + i > matrix->rows || x + j > matrix->cols) {
                    growIntMatrix2D(&matrix, matrix->rows * 2, matrix->cols * 2);
                }

                (matrix->elements)[y + i][x + j]++;
                /* printf("*TEST* (%d, %d) = %d\n", x + j, y + i, (matrix->elements)[y + i][x + j]); //testing */
            }
        }
    }

    // TODO check for cover
    int twoOrMoreClaims = 0;
    for (int i = 0; i < matrix->rows; i++) {
        for (int j = 0; j < matrix->cols; j++) {
            if ((matrix->elements)[i][j] >= 2) {
                twoOrMoreClaims++;
            }
        }
    }
    printf("P1: %d\n", twoOrMoreClaims);

    // p2

    deleteIntMatrix2D(matrix);
    fclose(input);
    return 0;
}
