# K-Sum Paths in a Binary Tree

## Problem Statement

Given the root of a binary tree and an integer **k**, determine the number of **downward-only paths** where the sum of node values equals **k**.

**Important rules:**

* A path can **start at any node**.
* A path can **end at any node**.
* The path must move **downward only** (parent → child).
* Paths **do not have to start from the root**.

---

# Example

### Binary Tree

```
           8
         /   \
        4     5
       / \     \
      3   2     2
     / \   \
    3  -2   1
```

### Target

```
k = 7
```

### Valid Paths

```
4 → 3
4 → 2 → 1
5 → 2
```

**Total paths = 3**

---

# Intuition

A simple idea is:

```
Keep calculating sum along the path
If sum == k → count++
```

But this only detects paths **starting from the root**.

Example:

```
8 → 4 → 3
```

But what if the valid path starts in the **middle of the tree**?

Example:

```
3 → 3 → 1
```

This path **does not start from the root**, so we need a smarter approach.

---

# Key Idea: Prefix Sum

While traversing the tree we maintain a **running sum (prefix sum)**.

```
prefixSum = sum of nodes from root to current node
```

If we want a path sum **k**, then:

```
prefixSum(current) - prefixSum(previous) = k
```

So:

```
prefixSum(previous) = prefixSum(current) - k
```

If this previous prefix sum already exists, it means a valid path exists.

---

# Why We Use a HashMap

We store prefix sums in a map:

```
prefixSum → frequency
```

Example:

```
{8:1, 12:1, 15:1}
```

Meaning these prefix sums have appeared along the current path.

When visiting a new node:

```
cur = current prefix sum
```

We check:

```
cur - k
```

If it exists in the map, we found valid path(s).

---

# Why We Add (0 → 1) in the Map

We initialize the map with:

```
mp.put(0,1)
```

This represents an **empty path before the root**.

Example:

```
Tree:   5
        /
       3

k = 8
```

Prefix sums:

```
5
8
```

At node `3`:

```
cur = 8
cur - k = 0
```

Since `0` exists in the map:

```
path = 5 → 3
```

Without `(0,1)` this path would not be counted.

---

# Why We Do NOT Use `cur == k`

You might think:

```
if(cur == k) count++
```

But this only counts **root → node paths**.

Example:

```
3 → 3 → 1
```

This path sum is **7**, but the prefix sums might be:

```
8
12
15
18
```

Here:

```
cur == 7 never happens
```

But:

```
18 - 7 = 11
```

If `11` appeared earlier, we detect the path.

So the **prefix-sum method generalizes the `cur == k` idea**.

---

# Why We Use Frequency Instead of Removing Keys

Multiple paths can produce the **same prefix sum**.

Example:

```
prefix sums: 10, 15, 15
```

Map:

```
{10:1, 15:2}
```

So we store **how many times a prefix appeared**.

When backtracking:

```
mp.put(cur, mp.get(cur) - 1)
```

We reduce the frequency instead of deleting immediately.

If frequency becomes `0`, we remove it.

---

# Why Backtracking Is Required

The HashMap should only store **prefix sums of the current path**.

Example path:

```
8 → 4 → 3
```

Prefix sums:

```
8
12
15
```

If we now move to the right subtree:

```
8 → 5
```

The prefix sums `12` and `15` **should not exist anymore**, because they belong to the left branch.

So we remove them while returning from recursion.

---

# Why Backtracking Happens After Both Subtrees

Structure of recursion:

```
visit node
add prefix sum

explore left subtree
explore right subtree

remove prefix sum
```

Important point:

* The current prefix sum belongs to **both children**.
* Both left and right subtrees share the same path prefix.

Example:

```
      8
     / \
    4   5
```

Prefix sum `8` must remain while exploring:

```
8 → 4
8 → 5
```

So we remove it **only after both subtrees are explored**.

---

# What Happens Inside the Left Subtree

Inside the left subtree, each node also performs its own backtracking.

Example:

```
8 → 4 → 3
```

When returning from node `3`, its prefix sum is removed automatically.

So by the time we return to node `8`, all prefix sums from the left subtree are already cleaned.

---

# Algorithm Steps

1. Maintain a running prefix sum.
2. Check if `(curSum - k)` exists in the map.
3. Add current prefix sum to the map.
4. Recursively traverse left and right subtrees.
5. Backtrack by removing the current prefix sum.

---

# Code

```java
static int countAllPaths(Node root, int k) {

    int[] count = {0};
    int cur_sum = 0;

    Map<Integer, Integer> mp = new HashMap<>();
    mp.put(0, 1);

    helper(root, k, cur_sum, mp, count);

    return count[0];
}

static void helper(Node node, int k, int cur, Map<Integer, Integer> mp, int[] count) {

    if (node == null) return;

    cur += node.data;

    if (mp.containsKey(cur - k))
        count[0] += mp.getOrDefault(cur - k, 0);

    mp.put(cur, mp.getOrDefault(cur, 0) + 1);

    helper(node.left, k, cur, mp, count);
    helper(node.right, k, cur, mp, count);

    mp.put(cur, mp.get(cur) - 1);

    if (mp.get(cur) == 0)
        mp.remove(cur);
}
```

---

# Complexity

### Time Complexity

```
O(N)
```

Each node is visited once.

### Space Complexity

```
O(N)
```

For recursion stack and prefix sum map.

---

# Key Takeaways

* The prefix sum technique detects paths starting **anywhere in the tree**.
* `mp.put(0,1)` allows paths that start from the root.
* Backtracking ensures each branch uses only its own prefix sums.
* Frequency tracking handles duplicate prefix sums correctly.

---

This approach is the **optimal solution (O(N))** and is commonly asked in interviews as **“Path Sum III” style problems**.
