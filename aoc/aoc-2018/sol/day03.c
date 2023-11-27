#include "intMatrix2D.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MYINPUTLOC              "../input/day03_test1.txt"

int main() {
    // input
    FILE * input = fopen(MYINPUTLOC, "r");

    if (input == NULL) {
      printf("input fp null\n");
    } else {
      /* printf("not null\n"); */
    }


    // p1
    char currWord[32];
    char corner[32];
    char size[32];
    char* token;
    int id;
    int x, y, width, height;
    int twoOrMoreClaims = 0;

    struct IntMatrix2D* claimCountMatrix = newIntMatrix2D(2, 2);
    struct IntMatrix2D* originalIDMatrix = newIntMatrix2D(2, 2);

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
                // grow matrix until it is large enough
                while (y + i > claimCountMatrix->rows || x + j > claimCountMatrix->cols) {
                    growIntMatrix2D(&claimCountMatrix, claimCountMatrix->rows * 2, claimCountMatrix->cols * 2);
                }

                // increment value in the matrix
                (claimCountMatrix->elements)[y + i][x + j]++;
                /* printf("*TEST* (%d, %d) = %d\n", x + j, y + i, (matrix->elements)[y + i][x + j]); //testing */
            }
        }
    }

    // check for cover
    for (int i = 0; i < claimCountMatrix->rows; i++) {
        for (int j = 0; j < claimCountMatrix->cols; j++) {
            if ((claimCountMatrix->elements)[i][j] >= 2) {
                twoOrMoreClaims++;
            }
        }
    }

    printf("P1: %d\n", twoOrMoreClaims);

    // p2

    // cleanup
    deleteIntMatrix2D(claimCountMatrix);
    fclose(input);
    return 0;
}
