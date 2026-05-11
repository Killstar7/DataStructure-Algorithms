# 📊 Find Degrees of Graph

## 📌 Problem Statement

Given an adjacency matrix of an undirected graph.

Find the degree of every vertex.

The degree of a vertex means:

    Number of edges connected to that vertex

---

## 🎯 Goal

Return an array where:

    ans[i] = degree of vertex i

---

## 💡 Intuition

In an adjacency matrix:

    matrix[i][j] = 1

means:

    There is an edge between i and j

For an undirected graph:

    if i is connected to j
    then j is also connected to i

So each edge appears twice in the matrix:

    matrix[i][j]
    matrix[j][i]

The code converts the matrix into an adjacency list first.

Then:

    Degree of a node =
        size of its adjacency list

But because edges are inserted twice again while building adjacency list:

    divide by 2

to get the actual degree.

---

## 🔥 Key Idea

Convert adjacency matrix into adjacency list.

Then:

    degree(node) = adjacencyList[node].size() / 2

Why divide by 2?

Because while traversing matrix:

    both:
        AdjL.get(i).add(j)
        AdjL.get(j).add(i)

are executed for every edge.

And since matrix already contains both directions:

    every edge is added twice again.

So total duplication:

    2 times

Hence:

    size / 2

---

## 🧠 Thought Process

Suppose graph:

``` id="triangle-graph"
0 ----- 1
 \     /
   \ /
    2
```

Adjacency matrix:

``` id="adj-matrix"
[
 [0,1,1],
 [1,0,1],
 [1,1,0]
]
```

Now traverse matrix.

At:

    matrix[0][1] = 1

We add:

    1 into list of 0
    0 into list of 1

But later:

    matrix[1][0] = 1

Again we add:

    0 into list of 1
    1 into list of 0

So every edge is added twice.

That is why final size must be divided by 2.

---

## 💻 Code

```java
static int[] findDegrees(int[][] matrix) {

    int n = matrix.length;

    ArrayList<ArrayList<Integer>> AdjL = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        AdjL.add(new ArrayList<>());
    }

    for (int i = 0; i < n; i++) {

        for (int j = 0; j < n; j++) {

            if (matrix[i][j] == 1) {

                AdjL.get(i).add(j);
                AdjL.get(j).add(i);
            }
        }
    }

    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
        ans[i] = AdjL.get(i).size() / 2;
    }

    return ans;
}
```

---

## 🧪 Dry Run

Input matrix:

``` id="matrix-input"
[
 [0,1,1],
 [1,0,1],
 [1,1,0]
]
```

Number of vertices:

    n = 3

---

Step 1: Create adjacency list

Initial:

``` id="initial-adj"
AdjL = [
 [],
 [],
 []
]
```

---

Step 2: Traverse matrix

---

i = 0, j = 1

``` id="matrix01"
matrix[0][1] = 1
```

Add edge:

``` id="add01"
AdjL[0].add(1)
AdjL[1].add(0)
```

Adjacency list:

``` id="adj-after01"
[
 [1],
 [0],
 []
]
```

---

i = 0, j = 2

``` id="matrix02"
matrix[0][2] = 1
```

Add edge:

``` id="add02"
AdjL[0].add(2)
AdjL[2].add(0)
```

Adjacency list:

``` id="adj-after02"
[
 [1,2],
 [0],
 [0]
]
```

---

i = 1, j = 0

Again:

``` id="matrix10"
matrix[1][0] = 1
```

Add edge again:

``` id="add10"
AdjL[1].add(0)
AdjL[0].add(1)
```

Adjacency list:

``` id="adj-after10"
[
 [1,2,1],
 [0,0],
 [0]
]
```

---

Continue similarly.

Final adjacency list becomes:

``` id="final-adj"
[
 [1,2,1,2],
 [0,0,2,2],
 [0,1,0,1]
]
```

---

Step 3: Find degree

Vertex 0:

``` id="deg0"
AdjL[0].size() = 4

degree = 4 / 2 = 2
```

---

Vertex 1:

``` id="deg1"
AdjL[1].size() = 4

degree = 4 / 2 = 2
```

---

Vertex 2:

``` id="deg2"
AdjL[2].size() = 4

degree = 4 / 2 = 2
```

---

Final Answer:

``` id="final-answer"
[2, 2, 2]
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Read adjacency matrix
      |
      v
    Create adjacency list
      |
      v
    Traverse every matrix cell
      |
      v
    If matrix[i][j] == 1
      |
      v
    Add j to i list
    Add i to j list
      |
      v
    After traversal:
        degree = adjacencyList size / 2
      |
      v
    Store degrees in answer array
      |
      v
    Return answer

---

## 📊 Complexity

Time Complexity:

    O(n²)

Reason:

    Entire adjacency matrix is traversed.

---

Space Complexity:

    O(n²)

Reason:

    Adjacency list stores duplicated edges.

---

## 🎯 Key Takeaways

- Degree means number of connected edges.
- Adjacency matrix represents graph connections.
- Adjacency list stores neighbors of each node.
- In undirected graph:
  edges appear twice
- This implementation duplicates edges again,
  so divide by 2.
- Degree can also be found directly from matrix row sum.

---

## 🔥 Most Important Insight

Because the matrix already contains:

    matrix[i][j]
    matrix[j][i]

Adding both directions again causes duplication.

So actual degree becomes:

    adjacencyList size / 2

---

## 🏁 Summary

To solve this problem:

    1. Convert adjacency matrix into adjacency list.
    2. Add edges for every matrix entry with value 1.
    3. Count adjacency list size.
    4. Divide by 2 because edges are duplicated.
    5. Store degree of every vertex.

Efficient solution:

    Time  : O(n²)
    Space : O(n²)