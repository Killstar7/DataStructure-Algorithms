# ⚪⚫ Segregate 0s and 1s

## 📌 Problem Statement

Given a binary array `arr[]` containing only `0`s and `1`s:

### 🎯 Goal:
Rearrange the array such that:
```
All 0s come first, followed by all 1s
```

---

## 💡 Intuition

This is a **partitioning problem**:

👉 Move all `0`s to the left  
👉 Move all `1`s to the right

---

# 🧠 Approach 1: Counting Method

---

## 🔹 Idea

1. Count number of `0`s
2. Fill first part with `0`s
3. Fill remaining with `1`s

---

## ⚙️ Steps

- Count total zeros → `count_0`
- Fill:
    - `[0 → count_0-1] = 0`
    - `[count_0 → n-1] = 1`

---

## 🚀 Code (Function Only)

```java
static void segregate0and1(int[] arr) {
    int count_0 = 0;

    for (int i : arr) {
        if (i == 0) count_0++;
    }

    for (int i = 0; i < arr.length; i++) {
        if (i < count_0) arr[i] = 0;
        else arr[i] = 1;
    }
}
```

---

## 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

# ⚡ Approach 2: Two Pointer (Optimal)

---

## 💡 Intuition

Use two pointers:

```
low  → start (for 0)
high → end (for 1)
```

---

## 🔁 Working

- Move `low` forward until you find `1`
- Move `high` backward until you find `0`
- Swap them

---

## ⚙️ Key Logic

```
0 should be left
1 should be right
```

---

## 🚀 Code (Function Only)

```java
static void segregate0and1_swap(int[] arr) {

    int low = 0;
    int high = arr.length - 1;

    while (low < high) {

        while (low < high && arr[low] == 0) low++;
        while (low < high && arr[high] == 1) high--;

        if (low < high) {
            arr[low++] = 0;
            arr[high--] = 1;
        }
    }
}
```

---

## 🧪 Dry Run Example

### Input:
```
[0, 1, 0, 1, 0, 0, 1]
```

---

### Steps:

```
low=0, high=6

→ low moves to first 1
→ high moves to first 0

swap

repeat...
```

---

### Output:
```
[0, 0, 0, 0, 1, 1, 1]
```

---

## 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

## 🎯 Key Takeaways

- Counting = simple but 2 passes
- Two-pointer = single pass, optimal
- Classic partition problem

---

## 🏁 Summary

> Move 0s left and 1s right using counting or two-pointer approach.

---

## 📚 Related Topics

- Two Pointer Technique
- Partitioning Problems
- Dutch National Flag Problem  