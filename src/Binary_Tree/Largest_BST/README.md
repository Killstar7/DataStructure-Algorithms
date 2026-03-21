# Largest BST in a Binary Tree

This Java program finds the **size of the largest Binary Search Tree (BST)** present in a given binary tree.

---

## Problem Statement

Given a binary tree, find the **number of nodes** in the largest subtree that forms a Binary Search Tree (BST).

**BST property**:
- For every node:
    - All nodes in the left subtree < node's value
    - All nodes in the right subtree > node's value

---

## Code

```java
static int largestBst(Node root) {
    // Returns the count of nodes in the largest BST
    return helper(root).count;
}

static check helper(Node node) {
    // Base case: empty tree
    if(node == null)
        return new check(0, Integer.MIN_VALUE, Integer.MAX_VALUE);

    // Recursively check left and right subtrees
    check left = helper(node.left);
    check right = helper(node.right);

    // Check if current node forms a BST with its subtrees
    if(node.data > left.max && node.data < right.min)
        return new check(
            left.count + right.count + 1,  // total nodes in BST
            Math.max(right.max, node.data), // max value in this BST
            Math.min(node.data, left.min)   // min value in this BST
        );

    // If not a BST, return the largest BST count from left or right
    return new check(Math.max(left.count, right.count), Integer.MAX_VALUE, Integer.MIN_VALUE);
}

// Helper class to store BST info
static class check {
    int max;   // Maximum value in the subtree
    int min;   // Minimum value in the subtree
    int count; // Number of nodes in the largest BST in this subtree

    check(int count, int max, int min) {
        this.max = max;
        this.min = min;
        this.count = count;
    }
}
```

