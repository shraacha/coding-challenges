#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "intMatrix2D.h"

#define MYINPUTLOC              "../input/day03_test1.txt"
#define MAXINPUTLINECOUNT        2000

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

    int* overlappedIDs = malloc(MAXINPUTLINECOUNT * sizeof(int));

    printf("test\n");

    while (fgets(currWord, 32, input) != NULL) {
        // tokenizing the input
        // #id & '@'
        token = strtok(currWord, " \n\r");
        id = atoi(token + 1);
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
                while (y + i >= claimCountMatrix->rows) {
                  growIntMatrix2D(&claimCountMatrix, claimCountMatrix->rows * 2,
                                  claimCountMatrix->cols);
                  growIntMatrix2D(&originalIDMatrix, claimCountMatrix->rows * 2,
                                  claimCountMatrix->cols);
                }

                while (x + j >= claimCountMatrix->cols) {
                  growIntMatrix2D(&claimCountMatrix, claimCountMatrix->rows,
                                  claimCountMatrix->cols * 2);
                  growIntMatrix2D(&originalIDMatrix, claimCountMatrix->rows,
                                  claimCountMatrix->cols * 2);
                }

                // if there were previously no claims, we add this as the original claim.
                //  - this ID will be added to the trie once there are 2 claims
                if((originalIDMatrix->elements)[y + i][x + j] == 0) {
                    (originalIDMatrix->elements)[y + i][x + j] = id;
                }

                // increment value in the matrix
                int claimCount = ++((claimCountMatrix->elements)[y + i][x + j]);

                // if the # of claims is now >= 2, we must mark this ID as being
                //    overlapped.
                if (claimCount >= 2) {
                    // TODO: Mark an id as having been claimed already so that
                    //     we don't need to do all of this checking for every
                    //     subsequent tile in the claim.

                    // We must also add the original claim as being overlapped.
                    if (claimCount == 2) {
                      overlappedIDs[(originalIDMatrix->elements)[y + i][x + j] -
                                    1] = 1;
                    }

                    overlappedIDs[id - 1] = 1;
                }

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

    // Check for an ID that has not been overlapped with.
    // We assume that the ids are increasing s.t. the id i is the i'th
    //     entry in the input. This means we have n ids in total.
    int nonOverlapID = 0;
    for (int i = 0; i < id; i++) {
        if(overlappedIDs[i] == 0) {
            nonOverlapID = i + 1;
        }
    }

    printf("P1: %d\n", twoOrMoreClaims);
    printf("P2: %d\n", nonOverlapID);

    // cleanup
    free(overlappedIDs);
    deleteIntMatrix2D(claimCountMatrix);
    deleteIntMatrix2D(originalIDMatrix);
    fclose(input);
    return 0;
}
