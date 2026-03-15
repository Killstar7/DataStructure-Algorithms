# Vertical Order Traversal of Binary Tree

## Problem Statement

Given the **root of a Binary Tree**, find the **vertical traversal of the tree** starting from the **leftmost level to the rightmost level**.

**Note:**
If multiple nodes pass through the same vertical line, they should be printed **in the same order as they appear in the level order traversal** of the tree.

---

# Example

## Input Tree

```
            1
         /     \
        2       3
      /   \   /   \
     4     5 6     7
            \       \
             8       9
            /
          11
                 \
                 10
```

---

## Vertical Distance Concept

Each node has a **horizontal distance from the root**.

| Node        | Vertical Distance |
| ----------- | ----------------- |
| Root        | 0                 |
| Left Child  | -1                |
| Right Child | +1                |

---

## Vertical Groups

```
Vertical -2 : [4]

Vertical -1 : [2]

Vertical  0 : [1, 5, 6]

Vertical  1 : [3, 8, 9]

Vertical  2 : [7, 11]

Vertical  3 : [10]
```

---

## Output

```
[
 [4],
 [2],
 [1,5,6],
 [3,8,9],
 [7,11],
 [10]
]
```

---

# Approach

We use **Breadth First Search (BFS)** to traverse the tree.

Each element stored in the queue contains:

* The **node**
* Its **vertical distance**

To store this pair we create a helper class.

```
pair {
    Node first
    int second
}
```

Where:

| Variable | Meaning           |
| -------- | ----------------- |
| `first`  | Binary tree node  |
| `second` | vertical distance |

---

# Algorithm

1. Assign the **root vertical distance = 0**
2. Use a **queue for BFS traversal**
3. Store nodes in a **map based on vertical distance**
4. For every node:

    * Left child → `vertical - 1`
    * Right child → `vertical + 1`
5. Track **minimum and maximum vertical levels**
6. we could have used Tree-map as well here to get the data from left to right, however that will cost us **log(N)** time for each push, that is why we use left length and right l variable to store the distance and then iterate over it.
7. Finally collect results from **leftmost to rightmost**


---

# Code

```java
class Solution {

static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {

    int left_l = 0;
    int right_l = 0;

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    Map<Integer,ArrayList<Integer>> mp = new HashMap<>();

    Queue<pair> q = new LinkedList<>();

    q.add(new pair(root,0));

    while(!q.isEmpty()){

        int s = q.size();

        for(int i=0;i<s;i++){
            
            pair pair = q.poll();

            mp.putIfAbsent(pair.second,new ArrayList<>());
            mp.get(pair.second).add(pair.first.data);

            if(pair.first.left != null){
                q.add(new pair(pair.first.left,pair.second-1));
                left_l = Math.min(left_l,pair.second-1);
            }

            if(pair.first.right != null){
                q.add(new pair(pair.first.right,pair.second+1));
                right_l = Math.max(right_l,pair.second+1);
            }
        }
    }

    for(int i=left_l;i<=right_l;i++){
        list.add(new ArrayList<>(mp.get(i)));
    }

    return list;
}

static class pair {
    Node first;
    int second;

    pair(Node first,int second){
        this.first = first;
        this.second = second;
    }
}

}
```

---

# Time Complexity

```
O(N)
```

Each node is visited exactly once.

---

# Space Complexity

```
O(N)
```

Space is used for:

* Queue (BFS traversal)
* HashMap storing vertical groups

---

# Key Concepts Used

* Binary Tree Traversal
* Breadth First Search (BFS)
* HashMap
* Queue
* Vertical Distance Tracking

---

# Summary

The idea is simple:

1. Assign each node a **horizontal distance**.
2. Store nodes with the same distance together.
3. Traverse using **BFS to maintain level order**.
4. Output groups from **leftmost vertical line to rightmost vertical line**.
