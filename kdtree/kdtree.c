#include <stdlib.h>
#include <stdio.h>
#include "kdtree.h"

kd_node *kd_insert(kd_node *root, int *data, int axis, int dimension) {
  // Stop if we have no data to insert.
  if (data == NULL) {
    return root;
  }

  if (root == NULL) {
    root = malloc(sizeof(kd_node));
    root->left = NULL;
    root->right = NULL;

    // Allocate space for the data.
    root->data = malloc(sizeof(int) * dimension);

    // Do a secure copy of the data.
    int i;
    for(int i = 0; i < dimension; i++) {
      root->data[i] = data[i];
    }

    return root;
  }

  if (data[axis] <= root->data[axis]) {
    root->left = kd_insert(root->left, data, ++axis % dimension, dimension);
  } else {
    root->right = kd_insert(root->right, data, ++axis % dimension, dimension);
  }

  return root;
}

kd_node *kd_delete(kd_node *root, int *data, int axis, int dimension) {
  if (root == NULL)
    return NULL;

  // Does this node contain the data?
  int flag = 1;
  int i;
  for(i = 0; i < dimension; i++) {
    if (root->data[i] != data[i]) {
      flag = 0; break;
    }
  }

  // it doesn't contain what we're looking for.
  if (!flag) {
    if (data[axis % dimension] <= root->data[axis % dimension]) {
      root->left = kd_delete(root->left, data, ++axis, dimension);
    } else {
      root->right = kd_delete(root->right, data, ++axis, dimension);
    }
  } else {
  // it contains our data.
    if (root->left != NULL) {
        int *left = kd_findmax(root->left, axis, axis % dimension, dimension);

        for(i = 0; i < dimension; i++) {
          root->data[i] = left[i];
        }

        root->left = kd_delete(root->left, left, ++axis, dimension);
    } else if (root->right != NULL) {
      int *right = kd_findmax(root->right, axis, axis % dimension, dimension);

      for(i = 0; i < dimension; i++) {
        root->data[i] = right[i];
      }

      root->left = kd_delete(root->right, right, ++axis, dimension);
      root->right = NULL;

    } else {
      root = NULL;
    }
  }

  return root;
}

void kd_print(kd_node *root, int traverse_method, int dimension) {
  switch (traverse_method) {
    case TRAVERSE_PRE:
      kd_print_pre(root, dimension);
      break;
    case TRAVERSE_IN:
      kd_print_in(root, dimension);
      break;
    case TRAVERSE_POST:
      kd_print_post(root, dimension);
      break;
    default:
      printf("Undefined traversal method: %d.\n", traverse_method);
      break;
  }
}

int *kd_findmax(kd_node *root, int axis, int cd, int dimension) {
  if (root == NULL) {
    return NULL;
  }

  if (axis == (cd % dimension)) {
    if (root->right == NULL) {
      return root->data;
    } else {
      return kd_findmax(root->right, axis, ++cd, dimension);
    }
  } else {
    int *left = kd_findmax(root->left, axis, ++cd, dimension);
    int *right = kd_findmax(root->right, axis, ++cd, dimension);
    int *result = root->data;

    if (left != NULL)
      result = (root->data[axis] < left[axis]) ? left : root->data;

    if (right != NULL)
      result = (result[axis] < right[axis]) ? right : result;

    return result;
  }
}

void kd_print_pre(kd_node *root, int dimension) {
  if (root == NULL) {
    return;
  }

  printf("{%d", root->data[0]);
  int i;
  for (int i = 1; i < dimension; i++) {
    printf(",%d", root->data[i]);
  }
  printf("}\n");

  kd_print_pre(root->left, dimension);
  kd_print_pre(root->right, dimension);
}

void kd_print_in(kd_node *root, int dimension) {
  if (root == NULL) {
    return;
  }

  kd_print_in(root->left, dimension);

  printf("{%d", root->data[0]);
  int i;
  for (int i = 1; i < dimension; i++) {
    printf(",%d", root->data[i]);
  }
  printf("}\n");

  kd_print_in(root->right, dimension);
}

void kd_print_post(kd_node *root, int dimension) {
  if (root == NULL) {
    return;
  }

  kd_print_post(root->left, dimension);
  kd_print_post(root->right, dimension);

  printf("{%d", root->data[0]);
  int i;
  for (int i = 1; i < dimension; i++) {
    printf(",%d", root->data[i]);
  }
  printf("}\n");
}
