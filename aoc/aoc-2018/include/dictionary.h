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
int addChildDictNode(struct dictNode* parent, struct dictNode* child, const char letter);

/* addWordToDict
 *
 * desc:
 */
int addWordToDict(struct dictNode* parent, const char* word, const size_t len);

/* isWordInDictOneWildcard
 *
 * desc:
 * Checks if the word is in the dictionary with one wildcard letter.
 *
 * return value: int
 * if 1 wildcard:
 *   - first wildcard letter - 97,
 * else:
 *   - -1
 */
int isWordInDictOneWildcard(struct dictNode* parent, const char* word, const size_t len);

int isWordInDict(struct dictNode* parent, const char* word, const size_t len);

int deleteDictNode(struct dictNode* node);

#endif
