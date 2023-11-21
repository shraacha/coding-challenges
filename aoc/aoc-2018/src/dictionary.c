#include "dictionary.h"
#include "constants.h"
#include <stdlib.h>

struct dictNode *newDictNode() {
    struct dictNode *node = malloc(sizeof(struct dictNode));

    // clear children pointers
    for (int i = 0; i < ALPHACOUNT; i++) {
        node->children[i] = NULL;
    }

    // node has no children to begin with, set to be ISLEAF
    node->isLeaf = ISLEAF;

    return NULL;
}

int addChildDictNode(struct dictNode* parent, struct dictNode* child, const char letter) {
    int index = letter - MYLOWERAOFFSET;

    if (parent->children[index] != NULL) {
        // error, child already exists
        return -1;
    } else {
        parent->children[index] = child;
        parent->isLeaf = ISNOTLEAF;
        return 0;
    }
}

int isWordInDictOneWildcard(struct dictNode* parent,const char* word) {
    // TODO

    // Check first letter against current node.
    // If it exists, descend to the corresponding child.
    // If it does not exist,
    //   If this is the first non-existant letter,
    //     we descend on all other existing children and search as normal.
    //   Else, we return back.
}

int isWordInDict(struct dictNode* parent, const char* word) {

}

int deleteDictNode(struct dictNode* node) {
    struct dictNode* currNode = NULL;

    // recursively delete children
    for(int i = 0; i < ALPHACOUNT; i++) {
        if((currNode = node->children[i]) != NULL) {
            deleteDictNode(currNode);
        }
    }

    // delete the data in this node
    free(node->children);

    return 0;
}
