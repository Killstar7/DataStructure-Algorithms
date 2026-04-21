# 🔀 Count Derangements

## 📌 Problem Statement

Given an integer `n`, count the total number of **derangements** of numbers from `1` to `n`.

A **derangement** is a permutation in which **no element appears in its original position**.

Example:
- `[2, 1]` is a derangement of `[1, 2]`
- `[2, 3, 1]` is a derangement of `[1, 2, 3]`

---

## 🎯 Goal

Return the total number of derangements for `n`.

---

## 💡 Intuition

For a derangement:

- Element `1` cannot stay at position `1`
- Element `2` cannot stay at position `2`
- ...
- Element `n` cannot stay at position `n`

This problem has a famous recurrence:

    D(n) = (n - 1) * (D(n - 1) + D(n - 2))

---

## 🧠 Thought Process

Suppose we place element `1`.

It cannot go to position `1`, so it has `(n - 1)` choices.

Now assume `1` goes to position `k`.

Then there are 2 cases:

1. Element `k` goes to position `1`
    - Then remaining problem becomes derangement of `(n - 2)` elements
    - Contribution = `D(n - 2)`

2. Element `k` does NOT go to position `1`
    - Then remaining problem behaves like derangement of `(n - 1)` elements
    - Contribution = `D(n - 1)`

So total:

    D(n) = (n - 1) * (D(n - 1) + D(n - 2))

---

## ⚙️ Base Cases

    D(1) = 0
    D(2) = 1

Why?

- For `n = 1`:
    - Only permutation is `[1]`
    - It is not a derangement
    - So answer = `0`

- For `n = 2`:
    - Permutations: `[1,2]`, `[2,1]`
    - Only `[2,1]` is valid
    - So answer = `1`

---

## 🚀 Recurrence

    D(n) = (n - 1) * (D(n - 1) + D(n - 2))

---

## 🔹 Brute Force / Pure Recursion

Directly apply recurrence:

```java
static int bruteforce(int n){
    if(n == 1) return 0;
    if(n == 2) return 1;

    return (n - 1) * (bruteforce(n - 1) + bruteforce(n - 2));
}
```

### Why this works

- For every `n`, recurrence splits into smaller subproblems
- But it recalculates same values again and again
- So it is inefficient

---

## 🔹 Memoization

Store already computed values in `dp[]`

```java
static int memoization(int n){
    int[] dp = new int[n + 1];
    Arrays.fill(dp, -1);
    return helper(n, dp);
}

static int helper(int n, int[] dp){
    if(n == 1) return 0;
    if(n == 2) return 1;

    if(dp[n] != -1) return dp[n];

    return dp[n] = (n - 1) * (helper(n - 1, dp) + helper(n - 2, dp));
}
```

### Why memoization helps

- `helper(n - 1)` and `helper(n - 2)` repeat many times
- `dp[n]` stores answer once
- Future calls return instantly

---

## 🔹 Tabulation

Build answer bottom-up

```java
static int tabulation(int n){
    if(n == 1) return 0;

    int[] dp = new int[n + 1];
    dp[1] = 0;
    dp[2] = 1;

    for(int i = 3; i <= n; i++){
        dp[i] = (i - 1) * (dp[i - 1] + dp[i - 2]);
    }

    return dp[n];
}
```

### Why tabulation works

- We already know:
    - `dp[1]`
    - `dp[2]`
- Then compute:
    - `dp[3]`
    - `dp[4]`
    - ...
    - `dp[n]`

---

## 🔹 Space Optimized

Since only previous 2 values are needed:

```java
static int spaceOptimization(int n){
    if(n == 1) return 0;

    int prev2 = 0; // D(1)
    int prev1 = 1; // D(2)
    int cur = 1;

    for(int i = 3; i <= n; i++){
        cur = (i - 1) * (prev1 + prev2);
        prev2 = prev1;
        prev1 = cur;
    }

    return cur;
}
```

### Why this works

The recurrence uses only:

    D(n - 1)
    D(n - 2)

So no need for full array

---

## 🧪 Dry Run Example

### Input

    n = 5

### Using recurrence

    D(1) = 0
    D(2) = 1
    D(3) = (3 - 1) * (D(2) + D(1))
         = 2 * (1 + 0)
         = 2

    D(4) = (4 - 1) * (D(3) + D(2))
         = 3 * (2 + 1)
         = 9

    D(5) = (5 - 1) * (D(4) + D(3))
         = 4 * (9 + 2)
         = 44

### Output

    44

---

## 🔁 Recursion Flow

For `n = 5`:

    D(5)
    = 4 * (D(4) + D(3))

    D(4)
    = 3 * (D(3) + D(2))

    D(3)
    = 2 * (D(2) + D(1))

This continues until base cases `D(1)` and `D(2)` are reached.

Then recursion comes back upward:

    D(1) = 0
    D(2) = 1
    D(3) = 2
    D(4) = 9
    D(5) = 44

---

## 📊 Complexity Analysis

### Brute Force
- Time: Exponential
- Space: O(n) recursion stack

### Memoization
- Time: O(n)
- Space: O(n)

### Tabulation
- Time: O(n)
- Space: O(n)

### Space Optimized
- Time: O(n)
- Space: O(1)

---

## 🎯 Key Takeaways

- Derangement means no item stays in its original position
- Main recurrence:

      D(n) = (n - 1) * (D(n - 1) + D(n - 2))

- This is a classic DP recurrence problem
- Best implementation is the space-optimized version

---

## 🏁 Summary

To count derangements:
- Use recurrence relation
- Start from base cases `D(1)=0`, `D(2)=1`
- Compute upward
- Optimize space using only two previous values

---