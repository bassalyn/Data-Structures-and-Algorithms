#include <stdio.h>
#include "matrix.h"      /*import header file */

Matrix matrix_construction(void){
  Matrix m = (Matrix) bstree_ini();     /*constructor calls bstree initiation returns a new matrix */
  return m;
}

unsigned char matrix_isin(Matrix m, Index index1, Index index2){
  Key k = key_gen(index1, index2);
  if (bstree_search(m, k)== NULL){         /* if matrix search is null returns 0 otherwise returns 1 */
    return 0;
  }else{
    return 1;
  }
}

Value *matrix_get(Matrix m, Index index1, Index index2){
  Key k = key_gen(index1,index2);        /* returs bstree search for matrix with the given indexes */
    return bstree_search(m,k);
}

void matrix_set(Matrix m, Index index1, Index index2, Value value){
  Key k = key_gen(index1, index2);
  if (matrix_isin(m, index1, index2) == 0){         /*if the given entry is not in, inserts, otherise changes the value of data */
    bstree_insert(m, k, data_gen(value));
  }else{
    *matrix_get(m, index1, index2) = value;
  }
}

void matrix_inc(Matrix m, Index index1, Index index2, Value value){
  if (matrix_isin(m, index1, index2) == 1){
    Value newvalue = value + *matrix_get(m, index1, index2);      /*increments the given entry by the value */
    matrix_set(m, index1, index2, newvalue);
  }else{
    printf("Error! location not defined in matrix. \n");  /* if not in matrix returns error */
  }
}

void matrix_list(Matrix m){     /* calls bstree traversal and traverse the matrix */
  bstree_traversal(m);
}

void matrix_destruction(Matrix m){     /* calls bstree free and frees the memory */
  bstree_free(m);
}

