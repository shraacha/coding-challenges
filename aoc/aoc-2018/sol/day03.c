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


    char currWord[32];
    char corner[32];
    char size[32];
    int x, y, width, height;
    char* token;

    // TODO init matrix

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

        /* printf("x = %d, y = %d, width = %d, height = %d\n", x, y, width, height); //testing */


        // TODO Add to the matrix
    }

    // TODO check for cover

    // p2

    fclose(input);
    return 0;
}
