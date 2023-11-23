#include <stdio.h>
#include <string.h>
#include "constants.h"
#include "dictionary.h"

#define MYINPUTLOC              "../input/day02_in.txt"

int main(int argc, char *argv[]) {
    // input
    FILE * input = fopen(MYINPUTLOC, "r");

    if (input == NULL) {
      printf("input fp null\n");
    } else {
      /* printf("not null\n"); */
    }

    // problem
    // part 1
    char charCounter[ALPHACOUNT];
    char currWord[32];
    int appearsTwice;
    int appearsThrice;
    int totalAppearsTwice = 0;
    int totalAppearsThrice = 0;

    while (fscanf(input, "%s", currWord) > 0) {
      appearsTwice = 0;
      appearsThrice = 0;

      // re-set the count for the characters
      for (int i = 0; i < ALPHACOUNT; i++) {
        charCounter[i] = 0;
      }

      for (int i = 0; i < strlen(currWord); i++) {
        charCounter[currWord[i] - MYLOWERAOFFSET]++;
      }

      // re-set the count for the characters
      for (int i = 0; i < ALPHACOUNT; i++) {
        if (charCounter[i] == 3) {
          appearsThrice = 1;
        }
        if (charCounter[i] == 2) {
          appearsTwice = 1;
        }
      }

      totalAppearsTwice += appearsTwice;
      totalAppearsThrice += appearsThrice;
    }

    printf("P1 total: %d\n", totalAppearsTwice * totalAppearsThrice);

    // part 2
    // using a dictionary
    struct dictNode* dictionary = newDictNode();
    int result;

    while (fscanf(input, "%s", currWord) > 0) {
      size_t len = strlen(currWord);

      result = isWordInDictOneWildcard(dictionary, currWord, len);
      if (result >= 0) {
        break;
      } else {
        addWordToDict(dictionary, currWord, len);
      }
    }

    printf("P12 ID, diff char: %s, %c\n", currWord, (char) result + MYLOWERAOFFSET);

    return 0;
}
