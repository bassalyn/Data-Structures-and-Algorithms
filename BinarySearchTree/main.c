//
//  main.c
//  binarySearchTree
//
//  Created by Melina laird on 2017-03-11.
//  Copyright © 2017 Melina laird. All rights reserved.
//

#include <stdio.h>
#include "bst.h"

int main(void) {
    BStree bst;
    bst = bstree_ini(1000);
    bstree_insert(bst, "Once", 1);
    bstree_insert(bst, "Upon", 22);
    bstree_insert(bst, "a", 3);
    bstree_insert(bst, "Time", 4);
    bstree_insert(bst, "is", 5);
    bstree_insert(bst, "filmed", 6);
    bstree_insert(bst, "in", 7);
    bstree_insert(bst, "Vancouver", 8);
    bstree_insert(bst, "!", 99);
    bstree_traversal(bst);
    bstree_free(bst);
}
