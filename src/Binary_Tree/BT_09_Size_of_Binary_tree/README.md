# 🌳 Size of Binary Tree

## 📌 Problem Statement

Given the root of a binary tree.

Return the size of the binary tree.

The size of a binary tree means:

    Total number of nodes present in the tree

---

## 🎯 Goal

Count all nodes present in the binary tree and return the total count.

---

## 💡 Intuition

For every node in the tree:

    Total size =
        current node
        + size of left subtree
        + size of right subtree

This naturally follows the recursive structure of a binary tree.

If a node is:

    null

then it contributes:

    0 nodes

---

## 🔥 Key Idea

Use recursion.

For every node:

    size(node) =
        1
        + size(node.left)
        + size(node.right)

Where:

    1 represents the current node itself

---

## 🧠 Thought Process

A binary tree is recursively divided into:

    1. Current node
    2. Left subtree
    3. Right subtree

To find total nodes:

Step 1:

    Count current node

Step 2:

    Recursively count nodes in left subtree

Step 3:

    Recursively count nodes in right subtree

Step 4:

    Add all counts together

Base case:

    If node == null
    return 0

because a null node does not contribute anything.

---

## 💻 Code

```java
int getSize(Node root) {
    return size(root);
}

static int size(Node node) {

    if (node == null) {
        return 0;
    }

    int left = size(node.left);
    int right = size(node.right);

    return 1 + left + right;
}
```

---

## 🧪 Dry Run

Consider the tree:

``` id="bt-tree"
        1
       / \
      2   3
```

---

Initial call:

    size(1)

---

At node 1:

    left = size(2)
    right = size(3)

---

At node 2:

    left = size(null) = 0
    right = size(null) = 0

Return:

    1 + 0 + 0 = 1

So:

    left = 1

---

At node 3:

    left = size(null) = 0
    right = size(null) = 0

Return:

    1 + 0 + 0 = 1

So:

    right = 1

---

Back to node 1:

Return:

    1 + left + right
    = 1 + 1 + 1
    = 3

Final Answer:

    3

---

## 🔁 Logic Flow

    Start
      |
      v
    Call size(root)
      |
      v
    Is node null?
      |
     Yes -------> Return 0
      |
     No
      |
      v
    Recursively find size of left subtree
      |
      v
    Recursively find size of right subtree
      |
      v
    Return:
        1 + left + right

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    Every node is visited exactly once.

---

Space Complexity:

    O(h)

Where:

    h = height of tree

Reason:

    Recursive call stack stores nodes up to tree height.

Worst case:

    O(n)

for skewed tree.

Best case:

    O(log n)

for balanced tree.

---

## 🎯 Key Takeaways

- Tree problems naturally fit recursion.
- Every subtree is itself a binary tree.
- Size calculation follows divide-and-conquer.
- Base case is very important:
  null → 0
- Final answer is:
  current + left + right

---

## 🔥 Most Important Insight

The size of a binary tree is simply:

    Number of nodes in left subtree
    + Number of nodes in right subtree
    + Current node

Formula:

    size(node) =
        1 + size(left) + size(right)

---

## 🏁 Summary

To solve this problem:

    1. Use recursion.
    2. If node is null, return 0.
    3. Recursively calculate left subtree size.
    4. Recursively calculate right subtree size.
    5. Add:
           current node + left + right
    6. Return total.

Efficient solution:

    Time  : O(n)
    Space : O(h)