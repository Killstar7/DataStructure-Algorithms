# 🌳 Minimum Height Tree Roots (Leaf Trimming Approach)

## 📌 Problem Statement

Given an undirected tree with `V` vertices and a list of edges, find all possible **roots** such that the tree has **minimum height**.

These roots are called **Minimum Height Tree (MHT) roots**.

---

## 💡 Intuition

A tree’s height depends on the root.
To minimize height, we want to choose nodes that are as **centrally located** as possible.

### 🔍 Key Observations:

* Leaf nodes (degree = 1) are the **farthest from the center**.
* If we **remove leaves layer by layer**, we move toward the center.
* The last remaining node(s) are the **centroids** of the tree.
* A tree can have:

    * **1 centroid** (odd length)
    * **2 centroids** (even length)

---

## 🧠 Thought Process

Instead of trying every node as a root (which is inefficient), we:

1. Build an **adjacency list** to represent the graph.
2. Identify all **leaf nodes** (nodes with degree = 1).
3. Push all leaves into a queue.
4. Repeatedly:

    * Remove all current leaves.
    * Update their neighbors.
    * Add new leaves to the queue.
5. Stop when:

    * Only **1 or 2 nodes remain** → these are our answers.

This is similar to **topological trimming from outside to inside**.

---

## ⚙️ Algorithm Steps

1. Create adjacency list using `HashSet` for easy removal.
2. Add all leaf nodes to a queue.
3. Track remaining nodes (`total = V`).
4. While `total > 2`:

    * Remove current leaves.
    * Decrease total count.
    * Update neighbors.
5. Return remaining nodes in queue.

---

## 🚀 Code

```java
static ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
    List<HashSet<Integer>> AdjL = new ArrayList<>();
    for (int i = 0; i < V; i++) {
        AdjL.add(new HashSet<>());
    }

    // Build graph
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        AdjL.get(u).add(v);
        AdjL.get(v).add(u);
    }

    // Initialize leaves
    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < V; i++) {
        if (AdjL.get(i).size() == 1) q.add(i);
    }

    int total = V;

    // Trim leaves layer by layer
    while (total > 2) {
        int size = q.size();

        for (int i = 0; i < size; i++) {
            int cur = q.poll();
            int next = AdjL.get(cur).iterator().next();

            AdjL.get(next).remove(cur);

            if (AdjL.get(next).size() == 1) q.add(next);
        }

        total -= size;
    }

    return new ArrayList<>(q);
}
```

---

## 🧪 Example

### Input:

```
V = 5
edges = [[0,2],[1,2],[2,3],[3,4]]
```

### Tree Structure:

```
    0   1
     \ /
      2
      |
      3
      |
      4
```

### Output:

```
[2, 3]
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
| ----- | ---------- |
| Time  | O(V)       |
| Space | O(V)       |

* Each node and edge is processed at most once.

---

## 🎯 Key Takeaways

* This is a **BFS + Greedy trimming** problem.
* Instead of growing from root → we **shrink toward center**.
* Works because a tree has **no cycles**.
* The final nodes are always the **optimal roots**.

---

## 🏁 Summary

> Remove outer layers (leaves) repeatedly until only the center remains.

This approach guarantees finding all Minimum Height Tree roots efficiently.

---

## 📚 Related Concepts

* Graph Theory
* BFS (Breadth-First Search)
* Topological Sorting (Leaf trimming variant)
* Tree Centroids

---

