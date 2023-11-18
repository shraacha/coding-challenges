#include <stdio.h>
#include <unistd.h>
#include <sys/syslimits.h>

/* const char inputLoc [] = "input/day01_in.txt"; */

#define MYINPUTLOC       "input/day01_in.txt"
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

int main(int argc, char *argv[]) {
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

    while (1) {
        rewind(input);

        while (fscanf(input, "%d", &currNum) > 0) {
            sum += currNum;

            if (isInArray(sum, array, size)) {
              result = 1;
              goto end;
            } else {
                if (addToArray(sum, array, &size) == 0) {
                    goto end;
                }
            }
        }
    }

end:
    if (result) {
        printf("P2: %d\n", sum);
    } else {
        printf("P2: oops\n");
    }

    fclose(input);
    return 0;
}
