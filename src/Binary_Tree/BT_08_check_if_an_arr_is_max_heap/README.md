# 🌳 Check if Array Represents a Max Heap

## 📌 Problem Statement

Given an array `arr[]`, determine whether it represents a **Max Heap**.

---

## 🎯 Goal

Return:

    true  → if array is a valid Max Heap
    false → otherwise

---

# 💡 What is a Max Heap?

A Max Heap is a binary tree where:

    Parent ≥ Children

---

## 🧠 Array Representation of Heap

For any index `i`:

    Parent index = (i - 1) / 2

    Left child  = 2*i + 1
    Right child = 2*i + 2

---

# 🔥 Key Idea

To verify Max Heap:

👉 Every element must be:

    ≤ its parent

So for every index `i`:

    arr[i] ≤ arr[(i - 1) / 2]

---

# 🧠 Thought Process

1. Start from index `1` (skip root)
2. For each element:
   Find its parent
3. Compare:
   If child > parent → NOT a heap
4. If all pass → valid heap

---

# 🚀 Code (Function Only)

```java
static boolean isMaxHeap(int[] arr) {

    for (int i = 1; i < arr.length; i++) {

        int parent = arr[(i - 1) / 2];

        if (arr[i] > parent) {
            return false;
        }
    }

    return true;
}
```

---

# 🧪 Dry Run

Input:

    arr = [90, 15, 10, 7, 12, 2]

---

Check:

    i = 1 → parent = 90 → 15 ≤ 90 ✔
    i = 2 → parent = 90 → 10 ≤ 90 ✔
    i = 3 → parent = 15 → 7 ≤ 15 ✔
    i = 4 → parent = 15 → 12 ≤ 15 ✔
    i = 5 → parent = 10 → 2 ≤ 10 ✔

---

Output:

    true

---

# ❌ Invalid Example

Input:

    arr = [10, 20, 5]

Check:

    i = 1 → parent = 10 → 20 > 10 ❌

---

Output:

    false

---

# 🔁 Logic Flow

    Start from index 1
        ↓
    Find parent
        ↓
    Compare child ≤ parent
        ↓
    If any fails → return false
        ↓
    Else → return true

---

# 📊 Complexity

Time:

    O(n)

Space:

    O(1)

---

# 🎯 Key Takeaways

- Heap property depends only on parent-child relation
- No need to build tree explicitly
- Array index math is enough

---

# 🔥 Most Important Insight

    In Max Heap → every child must be ≤ its parent

---

# 🏁 Summary

To check Max Heap:

    Loop through array
    Compare each element with its parent
    If all satisfy → valid Max Heap