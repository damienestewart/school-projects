#ifndef KDTREE_H
#define KDTREE_H

#define TRAVERSE_PRE 0
#define TRAVERSE_IN 1
#define TRAVERSE_POST 2

typedef struct kd_node {
  int *data;
  struct kd_node *left;
  struct kd_node *right;
} kd_node;

kd_node *kd_insert(kd_node *root, int *data, int axis, int dimension);
kd_node *kd_delete(kd_node *root, int *data, int axis, int dimension);
int *kd_findmax(kd_node *root, int axis, int cd, int dimension);
void kd_print(kd_node *root, int traverse_method, int dimension);
void kd_print_pre(kd_node *root, int dimension);
void kd_print_in(kd_node *root, int dimension);
void kd_print_post(kd_node *root, int dimension);

#endif
