#include <stdio.h>
#include "datatype.h"
#ifndef BStree_h         /*includes header of datatype, contains definitions and prototypes for bstree */
#define BStree_h

typedef struct BStree_node{
    Key key;
    Data data;
    struct BStree_node *left, *right;
    } BStree_node;
typedef BStree_node** BStree;
BStree bstree_ini(void);
void bstree_insert(BStree bst, Key key, Data data);
BStree_node *new_node(Key key, Data data);
Data bstree_search(BStree bst, Key key);
void bstree_traversal(BStree bst);
void bstree_free(BStree bst);

#endif
