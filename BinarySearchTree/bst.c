//
//  bst.c
//  binarySearchTree
//
//  Melina laird


#include <stdio.h>
#include <stdlib.h>
#include "bst.h"
#include <string.h>

BStree bstree_ini(int size) {
    BStree bst = malloc(sizeof(*bst));                      //allocates memory for the size of a BStree
    int nodesize = (size+1) * sizeof(Node);                 //node size multiplied by inputted size+1
    bst -> tree_nodes = (Node*) malloc(nodesize);           //allocate memory taking the correct size
    int charsize = (size+1) * sizeof(unsigned char);        //char size multiplied by inputted size+1
    bst -> is_free = (unsigned char*) malloc(charsize);     //allocate memory taking the correct size
    bst -> is_free = memset(bst->is_free, '1', size + 1);   //fill is_free with ones
    bst -> size = size;                                     //set size to the inputted size
    return  0;
}

    
void inserti(BStree bst, char *key, int data, int i){
    if (bst->is_free[i] == '1'){                            //if you have found an availble spot
        bst->tree_nodes[i].key = key;                       //add key into treenode at availble entry
        bst->tree_nodes[i].data = data;                     //add data into treenode at available entry
        bst->is_free[i] = '0';                              //mark is free at this location at zero
    }
    if (strcmp(bst->tree_nodes[i].key,key) > 0){            //positve value means 1st greater than 2nd
        inserti(bst, key, data, 2*i);                       //check if index 2i is available
    }
    if (strcmp(bst->tree_nodes[i].key,key) > 0){            //negative value means 2nd greater than 1st
        inserti(bst, key, data, (2*i + 1));                 //check if index 2i+1 is available
    }
}

void bstree_insert(BStree bst, char *key, int data) {       //call helper method that takes index ^
    inserti(bst, key, data, 0);
}

int inorder(BStree bst, int i){                             //if the spot has a '1' in it, there is
    if (bst->is_free[i] == '1'){                            //nothing there, end
        return 0;
    }
    inorder(bst, (2*i));                                    //first check the left "subtree"
    print_node(bst->tree_nodes[i]);                         //print out the node values here
    inorder(bst, (2*i + 1));                                //check the right "subtree"
    return 0;                                               //exit
}

void bstree_traversal(BStree bst) {                         //calls helper function that takes index
    inorder(bst, 0);
}

void bstree_free(BStree bst) {                              //frees up bst using the free() method
    free(bst);
}
