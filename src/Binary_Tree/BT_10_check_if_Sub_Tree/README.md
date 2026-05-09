# 🌳 Check if Subtree

## 📌 Problem Statement

Given two binary trees:

    root1 → root of main tree T
    root2 → root of tree S

Determine whether tree `S` is a subtree of tree `T`.

A tree `S` is considered a subtree of `T` if there exists a node in `T` such that the subtree starting from that node is exactly identical to `S`.

Two trees are identical if:

    1. They have the same structure
    2. They have the same node values

---

## 🎯 Goal

Return:

    true  → if root2 is a subtree of root1
    false → otherwise

---

## 💡 Intuition

To check whether `root2` is a subtree of `root1`, we need to search every node of `root1`.

At every node in `root1`, we ask:

    "Can the subtree starting from this node match root2 completely?"

If yes:

    root2 is a subtree

If no:

    continue searching in left and right subtrees of root1

So the problem is divided into two tasks:

    1. Find a possible matching starting point in root1
    2. Check whether both trees are identical from that point

---

## 🔥 Key Idea

Use two recursive functions:

    1. check(source, target)
       → searches for target root inside source tree

    2. traverse(source, target)
       → checks whether two trees are identical

The logic is:

    For every node in root1:
        if node value == root2 value:
            check if both trees are identical
        otherwise:
            search left subtree
            search right subtree

---

## 🧠 Thought Process

We start from `root1`.

At each node:

    1. If source is null:
           return false

       Because if main tree is finished and subtree is not found,
       then target cannot exist here.

    2. If source.data == target.data:
           call traverse(source, target)

       Because this node can be a possible starting point.

    3. If traverse returns true:
           subtree exists, return true

    4. Otherwise:
           recursively check source.left

    5. If not found in left:
           recursively check source.right

If none of the nodes match fully:

    return false

---

## 💻 Code

```java
boolean isSubTree(Node root1, Node root2) {
    return check(root1, root2);
}

static boolean check(Node source, Node target) {
    if (source == null) {
        return false;
    }

    if (source.data == target.data) {
        if (traverse(source, target)) {
            return true;
        }
    }

    if (check(source.left, target)) {
        return true;
    }

    if (check(source.right, target)) {
        return true;
    }

    return false;
}

static boolean traverse(Node source, Node target) {
    if (source == null && target == null) {
        return true;
    }

    if (source == null || target == null) {
        return false;
    }

    if (source.data != target.data) {
        return false;
    }

    boolean left = traverse(source.left, target.left);
    boolean right = traverse(source.right, target.right);

    return left && right;
}
```

---

## 🧪 Dry Run

Input:

    root1 = [1, 2, 3, N, N, 4]
    root2 = [3, 4]

Tree root1:

        1
       / \
      2   3
         /
        4

Tree root2:

        3
       /
      4

---

Initial call:

    isSubTree(root1, root2)

This calls:

    check(source = 1, target = 3)

---

Step 1: At node 1

Compare:

    source.data = 1
    target.data = 3

Since:

    1 != 3

No full tree comparison is done.

Now search left subtree:

    check(source = 2, target = 3)

---

Step 2: At node 2

Compare:

    source.data = 2
    target.data = 3

Since:

    2 != 3

Search left of 2:

    check(null, 3) → false

Search right of 2:

    check(null, 3) → false

So node 2 does not contain root2.

Return:

    false

---

Step 3: Back to node 1

Left subtree did not contain target.

Now search right subtree:

    check(source = 3, target = 3)

---

Step 4: At node 3

Compare:

    source.data = 3
    target.data = 3

Since values match, this can be a possible subtree root.

Now call:

    traverse(source = 3, target = 3)

---

Step 5: Compare node 3 with node 3

Both values are same:

    3 == 3

Now compare left subtree:

    traverse(source.left = 4, target.left = 4)

And compare right subtree:

    traverse(source.right = null, target.right = null)

---

Step 6: Compare node 4 with node 4

Both values are same:

    4 == 4

Compare left:

    traverse(null, null) → true

Compare right:

    traverse(null, null) → true

So:

    traverse(4, 4) → true

---

Step 7: Compare right subtree of node 3

Both are null:

    traverse(null, null) → true

So:

    left = true
    right = true

Therefore:

    traverse(3, 3) = true && true = true

---

Step 8: Final result

Since traverse returned true:

    check(3, 3) → true

Therefore:

    isSubTree(root1, root2) → true

Final Answer:

    true

---

## 🔁 Logic Flow

    Start
      |
      v
    Call isSubTree(root1, root2)
      |
      v
    Call check(root1, root2)
      |
      v
    Is current source node null?
      |
     Yes
      |
      v
    Return false

      |
     No
      |
      v
    Does source.data == target.data?
      |
     Yes
      |
      v
    Call traverse(source, target)
      |
      v
    Are both trees identical?
      |
     Yes
      |
      v
    Return true

      |
     No
      |
      v
    Search in left subtree
      |
      v
    If found, return true
      |
      v
    Search in right subtree
      |
      v
    If found, return true
      |
      v
    Otherwise return false

---

## 📊 Complexity

Time Complexity:

    O(n * m)

Where:

    n = number of nodes in root1
    m = number of nodes in root2

Reason:

    In the worst case, for many nodes in root1,
    we may call traverse() to compare root2.

If many nodes have the same value as root2 root:

    each comparison can take O(m)

So total worst-case time is:

    O(n * m)

---

Space Complexity:

    O(h1 + h2)

Where:

    h1 = height of root1
    h2 = height of root2

Reason:

    Recursion stack is used for check() and traverse().

Worst case:

    O(n + m)

for skewed trees.

---

## 🎯 Key Takeaways

- This problem combines tree traversal and tree comparison.
- `check()` searches for a possible subtree root.
- `traverse()` checks whether two trees are exactly identical.
- Matching only root values is not enough.
- Structure and values both must match.
- Recursion is the natural way to solve this problem.

---

## 🔥 Most Important Insight

To prove that `root2` is a subtree of `root1`, we need two checks:

    1. Find a node in root1 whose value matches root2 root
    2. From that node, both trees must be completely identical

So the core logic is:

    search + identical tree comparison

---

## 🏁 Summary

To solve this problem:

    1. Traverse every node of root1.
    2. Whenever source.data == target.data, compare both trees.
    3. If both trees are identical, return true.
    4. Otherwise continue searching left and right.
    5. If no matching subtree is found, return false.

Efficient solution:

    Time  : O(n * m)
    Space : O(h1 + h2)