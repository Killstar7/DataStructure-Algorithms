# 💰 Buy & Sell Stock (Multiple Transactions Allowed)

## 📌 Problem Statement

Given an array `arr[]` where:
```
arr[i] = price of stock on day i
```

### 🎯 Goal:
Find the **maximum profit** you can achieve by making **multiple buy-sell transactions**

---

## ⚙️ Rules

- You can:
    - Buy and sell multiple times
- But:
    - You must **sell before buying again**

---

## 💡 Intuition

👉 Capture every **increasing price movement**

---

### 🔥 Key Idea

If:
```
price[i] > price[i-1]
```

Then:
```
profit += (price[i] - price[i-1])
```

---

## 🧠 Why this works?

Instead of:
```
Buy at lowest, sell at highest
```

We do:
```
Buy → Sell at every rise
```

👉 This gives **same total profit**

---

### Example:

```
Prices: [1, 5, 3, 8]
```

Approach:

```
1 → 5 → profit = 4
3 → 8 → profit = 5

Total = 9
```

---

# ⚡ Greedy Approach

---

## 🚀 Code (Function Only)

```java
static int tabulation(int[] arr){

    int profit = 0;

    for (int i = 1; i < arr.length; i++) {

        if (arr[i] > arr[i - 1]) {
            profit += arr[i] - arr[i - 1];
        }
    }

    return profit;
}
```

---

## 🧪 Dry Run Example

### Input:
```
arr = [6, 1, 7, 2, 8, 4]
```

---

### Step-by-step:

| Day | Price | Action | Profit |
|-----|------|--------|--------|
|0→1|6→1|❌|0|
|1→2|1→7|+6|6|
|2→3|7→2|❌|6|
|3→4|2→8|+6|12|
|4→5|8→4|❌|12|

---

### Output:
```
12
```

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

# 🎯 Key Takeaways

- Capture every increasing slope
- Greedy works perfectly here
- No need for DP in this version
- Equivalent to summing all positive differences

---

# 🏁 Summary

> Buy at every local minimum and sell at every local maximum.

---

# 📚 Related Topics

- Greedy Algorithms
- Stock Problems
- Peak-Valley Approach  