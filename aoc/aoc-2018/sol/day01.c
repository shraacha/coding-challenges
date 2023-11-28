#include <stdio.h>
#include <string.h>
#include <unistd.h>

#include "constants.h"
#include "dictionary.h"

#define MYINPUTLOC       "../input/day01_test1.txt"
#define MYARRAYSIZE      200000

int addToArray(int num, int * array, int * size) {
    if(*size >= MYARRAYSIZE) {
        return 0;
    } else {
        array[*size] = num;
        ++(*size);
        return 1;
    }
}

int isInArray(int num, int * array, int size) {
    for(int i = 0; i < size && i < MYARRAYSIZE; i ++){
        if (num == array[i]) {
            return 1;
        }
    }
    return 0;
}

int main() {
    // input
    FILE * input = fopen(MYINPUTLOC, "r");

    if (input == NULL) {
      printf("null\n");
    } else {
      printf("not null\n");
    }

    // part 1
    int sum = 0;
    int currNum;
    while (fscanf(input, "%d", &currNum) > 0) {

        sum += currNum;
    }

    printf("P1: %d\n", sum);

    // part 2
    sum = 0;
    currNum = 0;
    int size = 0;
    int array[MYARRAYSIZE];
    int result = 0;
    char numAsString[33];
    struct dictNode* dictionary = newDictNode();

    while (1) {
        rewind(input);

        while (fscanf(input, "%d", &currNum) > 0) {
            sum += currNum;

            sprintf(numAsString, "%d", sum);

            // Kinda hacky way to store ints in the dictionary:
            //    - The dictionary currently has space for 26 children (english alphabet)
            //    - Our ints contain digits {0 - 9} and also possibly '-'
            //        - the ASCII value of '0' is 48
            //        - the ASCII value of '-' is 45
            //        - we subtract the characters in our numbers by 45 to index our children
            //           and we still have ample space for our digits
            if (isWordInDict(dictionary, numAsString, strlen(numAsString),
                             MYASCIIMINUSOFFSET)) {
              result = 1;
              goto end;
            } else {
              addWordToDict(dictionary, numAsString, strlen(numAsString),
                            MYASCIIMINUSOFFSET);
            }
        }
    }

end:
    if (result) {
        printf("P2: %d\n", sum);
    } else {
        printf("P2: oops\n");
    }

    deleteDictNode(dictionary);
    fclose(input);
    return 0;
}
