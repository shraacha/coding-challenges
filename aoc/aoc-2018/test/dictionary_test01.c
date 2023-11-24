#include <stdio.h>

#include "dictionary.h"

int main() {
    struct dictNode* dictionary = newDictNode();
    char word1[5] = "aaaa";
    char word2[5] = "abaa";
    int result;

    // testing search
    addWordToDict(dictionary, word1, 4);
    result = isWordInDict(dictionary, word1, 4);

    printf("Regular search test: %d\n", result);

    // testing wildcard search
    result = indexOfSingleWildcardChar(dictionary, word2, 4);

    printf("Wildcard search test: %d\n", result);

    deleteDictNode(dictionary);
    return 0;
}
