//
//  data.h
//  binarySearchTree
//
//  Created by Melina laird on 2017-03-16.
//  Copyright © 2017 Melina laird. All rights reserved.
//

#ifndef data_h
#define data_h

#include <stdio.h>

typedef struct {char *key; int data;} Node;
void print_node(Node node);
char *key_dup(char *key);

#endif /* data_h */
