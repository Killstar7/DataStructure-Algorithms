# 🔢 Count Binary Strings with No Consecutive 1s (Dynamic Programming - Optimized)

## 📌 Problem Statement

Given an integer `n`, count the number of **binary strings of length n** such that:

❌ No two consecutive `1`s are allowed

---

## 🎯 Goal

Return the **total number of valid binary strings** of length `n`.

---

## 💡 Intuition

Instead of tracking previous character (`prev`), we optimize:

- Place `0` → move 1 step
- Place `10` → move 2 steps

👉 This converts the problem into a **Fibonacci pattern**

---

## 🧠 Thought Process

Define state:

    helper(idx)

Where:
- `idx` = current index in string

---

## 🔁 Recursion Choices

1. Place `0` → move ahead

   helper(idx + 1)

2. Place `10` → skip next index

   helper(idx + 2)

---

## ⚙️ Recurrence

    f(idx) = f(idx + 1) + f(idx + 2)

---

## 🧠 Base Case

    if (idx >= n) return 1;

✔ Valid string formed

---

## 🚀 Code (Java - Memoization)

```java
static int countStrings(int n) {
    int[] dp = new int[n];
    java.util.Arrays.fill(dp, -1);
    return helper(n, 0, dp);
}

static int helper(int n, int idx, int[] dp) {
    if (idx >= n) return 1;

    if (dp[idx] != -1) return dp[idx];

    return dp[idx] = helper(n, idx + 1, dp)
                   + helper(n, idx + 2, dp);
}
```

---

## 🧪 Dry Run

Input:
n = 3

Recursion:

    f(0)
    ├── f(1)
    │   ├── f(2)
    │   │   ├── f(3) → 1
    │   │   └── f(4) → 1
    │   │   => f(2) = 2
    │   └── f(3) → 1
    │   => f(1) = 3
    └── f(2) = 2

Final:

    f(0) = 3 + 2 = 5

---

## ✅ Valid Strings (n = 3)

    000
    001
    010
    100
    101

Total = 5

---

## ⚡ Why This Works

- +1 step → placing `0`
- +2 step → placing `10` (avoids consecutive 1s)

👉 Constraint handled implicitly

---

## 📊 Complexity

Time:  O(n)  
Space: O(n)

---

## 🔥 Key Insight

Binary strings without consecutive 1s follow **Fibonacci sequence**

---

## 🏁 Summary

- Removed `prev` state
- Converted to 1D DP
- Reduced to Fibonacci
- Cleaner + optimized solution