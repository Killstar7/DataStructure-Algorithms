# 🔁 Flip to Maximize 1s

## 📌 Problem Statement

Given a binary array `arr[]` (only 0s and 1s):

### 🎯 Goal:
You can flip **at most one subarray**:
```
0 → 1
1 → 0
```

Return the **maximum number of 1s** possible after the flip.

---

## 💡 Intuition

Flipping:
- `0 → 1` → gain +1
- `1 → 0` → loss -1

👉 So instead of flipping directly, think in terms of **profit**

---

# 🔥 Key Transformation

Convert array into:

```
0 → +1
1 → -1
```

---

## 🧠 Why?

Because:
- We want to maximize number of 1s
- So we want subarray with **maximum net gain**

---

## ⚡ Problem Becomes:

👉 Find **maximum subarray sum**

👉 (Classic Kadane’s Algorithm)

---

# 🧠 Thought Process

---

## 🔹 Step 1: Count existing 1s

This is your base:

```
totalOnes
```

---

## 🔹 Step 2: Apply Kadane

Find best subarray to flip:
- Maximize gain

---

## 🔹 Step 3: Final Answer

```
answer = totalOnes + maxGain
```

---

## 🚀 Code (Function Only)

```java
static int maxOnes(int[] arr) {

    int one = 0;

    for (int x : arr) {
        if (x == 1) one++;
    }

    int max_len = 0;
    int cur_len = 0;

    for (int i = 0; i < arr.length; i++) {

        if (arr[i] == 0) cur_len += 1;
        else cur_len += -1;

        if (cur_len < 0) cur_len = 0;

        max_len = Math.max(max_len, cur_len);
    }

    return one + max_len;
}
```

---

# 🧪 Dry Run Example

## Input:
```
[1, 0, 0, 1, 0]
```

---

## Step 1: Count 1s

```
ones = 2
```

---

## Step 2: Transform

```
[1, 0, 0, 1, 0]
→ [-1, +1, +1, -1, +1]
```

---

## Step 3: Kadane

```
subarray: [1,1,-1,1]
max sum = 2
```

---

## Final Answer:

```
2 + 2 = 4
```

---

# ⚠️ Edge Case

## All 1s

```
[1,1,1]
```

Transformation:
```
[-1,-1,-1]
```

Kadane → 0

👉 Answer = original count

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

# 🎯 Key Takeaways

- Convert problem into **maximum subarray sum**
- Use Kadane’s algorithm
- Think in terms of **gain/loss**
- Very common interview pattern

---

# 🏁 Summary

> Flip the subarray that gives maximum gain in number of 1s.

---

# 📚 Related Topics

- Kadane’s Algorithm
- Maximum Subarray
- Greedy
- Array Transformation  