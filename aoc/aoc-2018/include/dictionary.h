#ifndef DICTIONARY_H
#define DICTIONARY_H

#include <stdlib.h>

#include "constants.h"

#define HOLDSVALUE            1
#define DOESNOTHOLDVALUE      0

// TODO: add offset to the struct

// struct for a dictionary implementation as a trie
// Currently only has 26 children (designed primarily for an english word trie) per node.
struct dictNode {
    int status;   // status of the node (holds a value/doesn't hold a value);
    struct dictNode* children[ALPHACOUNT];
};


struct dictNode* newDictNode();

int deleteDictNode(struct dictNode *node);

/* addChildDictNode
 *
 * desc:
 * Adds a child dictionary node for some letter.
 *
 * parameters:
 * - parent node
 * - child node
 * - the letter to add the child at
 * - offset to subtract the letter by before adding to the dict
 *
 * return value:
 * int:
 * - -1: error
 * -  0: otherwise
 */
int addChildDictNode(struct dictNode *const parent, struct dictNode *child,
                     const char letter, const int offset);

/* addWordToDict
 *
 * desc:
 */
int addWordToDict(struct dictNode *const parent, const char *word,
                  const int len, const int offset);

int isWordInDict(struct dictNode *const parent, const char *word,
                 const int len, const int offset);

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
                            const int len, const int offset);

#endif
