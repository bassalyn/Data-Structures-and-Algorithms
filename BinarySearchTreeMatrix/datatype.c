#include <stdlib.h>
#include <stdio.h>
#include <string.h>  /*imports these */
#include "datatype.h"

char * string_dup(char *str){
  char *dup = (char *) malloc(sizeof(strlen(str)+1));
  dup = strcpy(dup, str);        /*duplicates given string wth dynamic memory allocation, uses strcpy */
  return dup;
}

Key key_gen(char *skey1, char *skey2){
  Key k = (Key) malloc(strlen(skey1)+strlen(skey2)+2);
  k->skey1 = string_dup(skey1);         /* generates a key from two given subkeys with malloc, and returns the key */
  k->skey2 = string_dup(skey2);
  return k;
}

int key_comp(Key key1, Key key2){
  Key *k1 = &key1, *k2 = &key2;
  if (k1 == NULL && k2 == NULL){ /* compares two keys, if both are null returns 0 */
    return 0;
  }else if (k1 == NULL){
    return -1;
  }else if (k2 == NULL){        /* if one is null by default the other is bigger returns accordingly */
    return 1;
  }
  if (strcmp(key1->skey1, key2->skey1)!=0){
    return strcmp(key1->skey1, key2->skey1);       /*compares the strings and returns accordingly using strcmp */
  }else return strcmp(key1->skey2, key2->skey2);
}

void key_print(Key key){
  printf("%-11s%-11s", key->skey1, key->skey2);   /* prints a given key with formating */
}

void key_free(Key key){
  free(key->skey1);                      /*frees a given gey, both skeys and then itself */
  free(key->skey2);
  free(key);
}

Data data_gen(int idata){
  Data d = (Data) malloc(sizeof(*d));      /* generates data for a given int idata and returns Data */
  *d = idata;
  return d;
}

void data_set(Data data, int idata){     /* sets the value of data to an int idata */
  *data = idata;
}

void data_print(Data data){           /* prints the value of data with a given formatting */
  printf("%-10d\n", *data);
}

void data_free(Data data){             /* free a given data */
  free(data);
}
