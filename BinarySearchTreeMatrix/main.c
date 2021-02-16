#include <stdio.h>
#include <stdlib.h>
#include <string.h>       /* imports matrix header and libraries */
#include "matrix.h"

int main(){
  Matrix m = matrix_construction();             /*initializes matrix */
  printf("Enter the number of pairs of subkeys you would like to enter :");
  int num;
  scanf("%d", &num);              /* reads number of pairs to input from stdin */
  int i;
  for (i = 0; i < num; i++){     /* loops through and add given number of pairs */
    Index index1, index2;
    char  *str1, *str2;
    str1 = (char*) malloc(sizeof(char)*20);
    str2 = (char*) malloc(sizeof(char)*20);     /* dynammically allocates memory for strings */
    printf("Enter two strings, two subkeys to insert :");
    scanf("%s%s", str1, str2);                 /* reads the two subkeys */
    index1 = str1;
    index2 = str2;
    if (matrix_isin(m, index1, index2)==0){
      matrix_set(m, index1, index2, 1);            /* if key is not in matrix inserts */
    }else{
      matrix_inc(m, index1, index2, 1);            /* if key is in matrix increments data */
      }
    index1 = NULL;
    index2 = NULL;                                 /* resets indexes */
  }
  printf("String 1   String 2   Occurences");
  printf("\n");                         /* formats output */
  matrix_list(m);
  matrix_destruction(m);                 /*traverses and displays matrix results, then frees memory */
  return 0;
}
