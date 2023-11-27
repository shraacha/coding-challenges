#include <stdio.h>

#include "constants.h"
#include "dictionary.h"

int main() {
    struct dictNode* wordDictionary = newDictNode();
    struct dictNode* intDictionary = newDictNode();
    char word1[5] = "aaaa";
    char word2[5] = "abaa";
    char word3[5] = "aaa";
    char word4[5] = "1111";
    char word5[5] = "1211";
    char word6[5] = "111";
    int result;

    // testing search
    printf("Adding %s to the trie\n", word1);
    addWordToDict(wordDictionary, word1, 4, MYLOWERAOFFSET);

    result = isWordInDict(wordDictionary, word1, 4, MYLOWERAOFFSET);
    printf("Search result for %s: %d\n", word1, result);

    result = isWordInDict(wordDictionary, word3, 3, MYLOWERAOFFSET);
    printf("Search result for %s: %d\n", word3, result);


    // testing wildcard search
    result = indexOfSingleWildcardChar(wordDictionary, word2, 4, MYLOWERAOFFSET);
    printf("Wildcard search result for %s: %d\n", word2, result);


    // testing int offset
    printf("Adding %s to the trie\n", word4);
    addWordToDict(wordDictionary, word4, 4, MYASCII0OFFSET);

    result = isWordInDict(wordDictionary, word4, 4, MYASCII0OFFSET);
    printf("Search result for %s: %d\n", word4, result);

    result = isWordInDict(wordDictionary, word6, 4, MYASCII0OFFSET);
    printf("Search result for %s: %d\n", word6, result);

    deleteDictNode(wordDictionary);
    deleteDictNode(intDictionary);
    return 0;
}
