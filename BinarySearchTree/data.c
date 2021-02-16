//
//  data.c
//  binarySearchTree
//
//  Created by Melina laird on 2017-03-16.
//  Copyright © 2017 Melina laird. All rights reserved.
//

#include <stdio.h>
#include <string.h>
#include "data.h"

void print_node(Node node) {
       printf("%s   %d", node.key, node.data);
}

char *key_dup(char *key) {
  int i;
  char *point = (*key) malloc(sizeof *key);  //dynamically allocate memory for chars, the same size as *key
    for (i = 0; i < sizeof *key; i++){
        *(point + i) = *(key + i); //what is at the location of key is now also at the location at point
    }
    return point;
}
