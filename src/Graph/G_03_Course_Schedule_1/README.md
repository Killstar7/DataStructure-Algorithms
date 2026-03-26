Here’s a clean, GitHub-style `README.md` for your code:

---

# 📚 Course Schedule I (Cycle Detection using DFS)

This project implements a solution to the **Course Schedule I** problem using **Graph Theory (Directed Graph + DFS Cycle Detection)** in Java.

## 🚀 Problem Statement

Given:

* `n` courses labeled from `0` to `n-1`
* A list of prerequisites where each pair `[u, v]` means you must complete course `v` before course `u`

👉 Determine whether it is possible to finish all courses.

---

## 🧠 Approach

We model the problem as a **Directed Graph**:

* Each course → a node
* Each prerequisite → a directed edge (`v → u`)

### 🔍 Key Idea

* If the graph contains a **cycle**, it is impossible to complete all courses.
* We use **DFS (Depth First Search)** with:

    * `vis[]` → tracks visited nodes
    * `cur[]` → tracks nodes in the current recursion path

### ⚠️ Cycle Detection Logic

* If we revisit a node that is already in the current path (`cur[] == 1`), a cycle exists.

---

## 🛠️ Implementation Details

### Function: `canFinish(int n, int[][] prerequisites)`

* Builds adjacency list
* Runs DFS on each unvisited node
* Returns:

    * `false` → if cycle detected
    * `true` → otherwise

### Function: `helper(...)`

* Performs DFS traversal
* Detects cycle using recursion stack

---

## 📦 Code Structure

```
Graph/
 └── G_03_Course_Schedule_1/
      └── solution.java
```

---

## 🧪 Example

### Input

```java
int[][] arr = {{2, 0}, {2, 1}, {3, 2}};
int n = 4;
```

### Output

```
true
```

### Explanation

* Course dependencies form a valid DAG (no cycle)
* All courses can be completed

---

## ⏱️ Complexity Analysis

| Operation        | Complexity |
| ---------------- | ---------- |
| Time Complexity  | O(V + E)   |
| Space Complexity | O(V + E)   |

* `V` = number of courses
* `E` = number of prerequisites

---

## ✅ Features

* Efficient cycle detection using DFS
* Works for disconnected graphs
* Clean and modular design

---

