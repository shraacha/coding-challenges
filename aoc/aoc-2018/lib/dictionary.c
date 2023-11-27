#include "dictionary.h"
#include "constants.h"
#include <stdlib.h>
#include <stdio.h>

struct dictNode *newDictNode() {
    struct dictNode* node = malloc(sizeof(struct dictNode));

    // clear children pointers
    for (int i = 0; i < ALPHACOUNT; i++) {
        node->children[i] = NULL;
    }

    // node has no children to begin with, set to be DOESNOTHOLDVALUE
    node->status = DOESNOTHOLDVALUE;

    return node;
}

int addChildDictNode(struct dictNode *parent, struct dictNode *child,
                     const char letter, const int offset) {
    int index = letter - offset;

    if (parent->children[index] != NULL) {
        // error, child already exists
        return -1;
    } else {
        parent->children[index] = child;
        return 0;
    }
}

int addWordToDict(struct dictNode *parent, const char *word, const int len,
                  const int offset) {
    struct dictNode *currNode = parent;

    for (int i = 0; i < len; i++) {
        // regular word search in trie
        if(currNode->children[word[i] - offset] == NULL) {
            addChildDictNode(currNode, newDictNode(), word[i], offset);
        }

        currNode = currNode->children[word[i] - offset];
    }

    // once we have traversed to the end of the word, we set the node's status
    //   to "HOLDSVALUE"
    currNode->status = HOLDSVALUE;
    return 1;
}

int isWordInDict(struct dictNode *parent, const char *word,
                 const int len, const int offset) {
    struct dictNode *currNode = parent;

    for (int i = 0; i < len; i++) {
        // checking if the next letter exists as a child
        if(currNode->children[word[i] - offset] != NULL) {
            currNode = currNode->children[word[i] - offset];
        } else {
            // if not then the word does not exist in the trie
            return 0;
        }
    }

    // if we have reached to the end of the word, we check if
    //   it holds a value
    if (currNode->status == HOLDSVALUE) {
        return 1;
    } else {
        return 0;
    }
}

int indexOfSingleWildcardChar(struct dictNode *parent, const char *word,
                              const int len, const int offset) {
    // Check first letter against current node.
    // If it exists, descend to the corresponding child.
    // If it does not exist,
    //   If this is the first non-existant letter,
    //     we descend on all other existing children and search as normal.
    //   Else, we return back.
    struct dictNode* currNode = parent;

    for (int i = 0; i < len; i++) {
        // regular word search in trie
        if(currNode->children[word[i] - offset] != NULL) {
            currNode = currNode->children[word[i] - offset];
        } else {
            // Once we find a difference, we do regualar word search for
            //   the remaining portion of the word on every other child.
            // If we cannot find tail of the word in any of the children,
            //   there's more than 1 different character.

            for (int j = 0; j < ALPHACOUNT; j++) {
              if (currNode->children[j] != NULL &&
                  isWordInDict(currNode->children[j], &(word[i + 1]),
                               len - (i + 1), offset)) {
                return i; // return the index of the letter in the word
              }
            }
            return -1; // if we do not match with any trailing pattern
        }
    }

    return -2;
}

int deleteChildren(struct dictNode* node) {
    struct dictNode* currNode = NULL;

    // recursively delete children
    for(int i = 0; i < ALPHACOUNT; i++) {
        if((currNode = node->children[i]) != NULL) {
            deleteChildren(currNode);   // delete subtries first
            free(node->children[i]);     // delete the child
        }
    }

    return 0;
}

int deleteDictNode(struct dictNode* node) {
    deleteChildren(node);   // delete children
    free(node);             // delete itself

    return 0;
}
