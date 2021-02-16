#include <stdlib.h>
#include <stdio.h>
#include "bstree.h"  /* includes header */

BStree bstree_ini(void){
  BStree bst;
  bst = (BStree) malloc (sizeof(BStree_node*));
  *bst = NULL;                /*function initiaties a bst with dynamic memory allocation, sets to null and returns */
  return bst;
}

void bstree_insert(BStree bst, Key key, Data data){
  BStree_node * node = new_node(key,data);
  if (*bst == NULL){
    *bst = node;      /* if the bst is null then returns */
    return;
  }
  BStree_node root = **bst;
  if (key_comp(root.key, key) < 0){ 
    if (root.right == NULL){      /* traverses the bst to find where to insert the node */
      (**bst).right = node;         
    }else {                       /* if node is empty inserts else continues to traverse */ 
      bstree_insert(&root.right, key, data);    
    }
  }else if (key_comp(root.key, key) > 0){
    if (root.left == NULL){          /* if node is empty inserts else continues to traverse */
	(**bst).left = node;
      }else {
	bstree_insert(&root.left, key, data);     
      }
 }
}

BStree_node *new_node(Key key, Data data){
  BStree_node *node = (BStree_node *) malloc (sizeof(BStree_node));
  (*node).key = key;              /* creates a new node with given key and data, with dynammic memory allocation */
  (*node).data = data;
  (*node).left = NULL;
  (*node).right = NULL;
  return node;
}

Data bstree_search(BStree bst, Key key){
  if (*bst == NULL){
    return NULL;                     /* searches a bst for a key, if bst is empty returns NULL */
  }
  BStree_node root = **bst;
  if (key_comp(root.key, key)== 0){
    return (*bst)->data;                    /* if found in tree returns the data */
  }
  if (key_comp(root.key, key)<0){
    if (root.right == NULL){
      return NULL;                      /* if search returns null returns NULL otherwise recursively searches */
    }else{
      return bstree_search(&root.right, key);
    }
  }else{
    if (root.left == NULL){
      return NULL;                       /* searches left side, if find null returns NULL otherwise keeps searching */
    }else{
      return bstree_search(&root.left, key);
    }
  }
}

void bstree_traversal(BStree bst){
  if (*bst == NULL){                 /* traverse the bstree and prints entries inorder */
    return;
  }
  BStree_node root = **bst;
  if (root.left != NULL){
    bstree_traversal(&root.left);      /* traverses left child */
  }
  key_print(root.key);
  data_print(root.data);               /* prints key and data  then traverse right tree */
  if (root.right != NULL){
    bstree_traversal(&root.right);
  }
}

void bstree_free(BStree bst){
  if (*bst == NULL){                /* frees a given bst */
    return;
  }
  bstree_free(&(**bst).left);
  bstree_free(&(**bst).right);
  key_free((**bst).key);           /* calls on left and right, then frees key and data, followed by bst */
  data_free((**bst).data);
  free(*bst);
}
