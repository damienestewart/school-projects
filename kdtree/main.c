#include <stdio.h>
#include <stdlib.h>
#include "kdtree.h"

int main(void) {
  int dim = 3;

  int arr[] = {3,2,5};
  kd_node root = {arr, NULL, NULL};

  int a[] = {2,4,6};
  int b[] = {4,2,7};
  int c[] = {5,3,1};
  int d[] = {4,3,2};
  int e[] = {1,3,5};
  int f[] = {2,9,1};
  int g[] = {6,2,3};

  kd_insert(&root, a, 0, dim);
  kd_insert(&root, b, 0, dim);
  kd_insert(&root, c, 0, dim);
  kd_insert(&root, d, 0, dim);
  kd_insert(&root, e, 0, dim);
  kd_insert(&root, f, 0, dim);
  kd_insert(&root, g, 0, dim);

  kd_print(&root, TRAVERSE_PRE, dim);

  printf("\n\n");
  kd_node *rt = kd_delete(&root, b, 0, 3);
  rt = kd_delete(&root, g, 0, 3);
  rt = kd_delete(&root, f, 0, 3);

  kd_print(rt, TRAVERSE_PRE, dim);

  return 0;
}
