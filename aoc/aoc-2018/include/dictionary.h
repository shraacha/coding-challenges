#ifndef DICTIONARY_H
#define DICTIONARY_H

#include "constants.h"
#include <stdlib.h>

#define ISLEAF         1
#define ISNOTLEAF      0

// struct for a dictionary implementation as a trie
struct dictNode {
    int isLeaf;
    struct dictNode* children[ALPHACOUNT];
};


struct dictNode* newDictNode();

/* addChildDictNode
 *
 * desc:
 * Adds a child dictionary node for some letter.
 *
 * parameters:
 * - parent node
 * - child node
 * - the letter to add the child at
 *
 * return value:
 * int:
 * - -1: error
 * -  0: otherwise
 */
int addChildDictNode(struct dictNode *const parent, struct dictNode *child,
                     const char letter);

/* addWordToDict
 *
 * desc:
 */
int addWordToDict(struct dictNode *const parent, const char *word,
                  const int len);

/* indexOfSingleWildcardChar
 *
 * desc:
 * Checks if the word is in the dictionary with one wildcard letter.
 * Returns the index of this letter in the word.
 *
 * return value: int
 * if 1 wildcard:
 *   - the index of the wildcard in the word
 * else if multiple wildcards:
 *   - -1
 * else (i.e. word exists in the trie):
 *   - -2
 */
int indexOfSingleWildcardChar(struct dictNode *const parent, const char *word,
                            const int len);

int isWordInDict(struct dictNode *const parent, const char *word,
                 const int len);

int deleteDictNode(struct dictNode *node);

#endif
