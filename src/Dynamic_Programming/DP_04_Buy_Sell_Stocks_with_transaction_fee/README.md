# 📈 Buy & Sell Stocks with Transaction Fee (Dynamic Programming)

## 📌 Problem Statement

You are given an array `prices[]` where:

- `prices[i]` = price of a stock on day `i`
- You are also given a transaction fee `fee`

### 🎯 Goal:
Maximize profit by buying and selling stocks such that:
- You can buy and sell multiple times
- You must sell before buying again
- Each transaction incurs a **fixed fee**

---

## 💡 Intuition

At each day, you have **2 choices**:

1. **Buy** the stock
2. **Sell** the stock
3. **Do nothing**

👉 This becomes a **decision-making DP problem**

---

## 🧠 Thought Process

We define a state:

```java
helper(idx, canBuy)
```

### 🔹 Parameters:

| Parameter | Meaning |
|----------|--------|
| `idx` | current day |
| `canBuy` | 1 → allowed to buy, 0 → must sell |

---

### 🔹 Why `canBuy`?

- If `canBuy = 1` → we are **not holding stock**
- If `canBuy = 0` → we are **holding stock**

👉 This helps us track the state of trading

---

## 🔁 Recursion Choices

### ✅ Case 1: canBuy = 1 (We can buy)

Two options:

1. **Buy stock**
```java
- price[idx] - fee + helper(...)
```

- Spend money to buy
- Pay transaction fee here

2. **Skip**
```java
helper(...)
```

---

### ✅ Case 2: canBuy = 0 (We can sell)

Two options:

1. **Sell stock**
```java
+ price[idx] + helper(...)
```

- Earn money

2. **Skip**
```java
helper(...)
```

---

## ⚙️ Recurrence

```java
if(canBuy == 1):
    max(-price[idx] - fee + next,  skip)
else:
    max(price[idx] + next, skip)
```

---

## 🧠 Why Fee is Subtracted at Buy?

You can subtract fee at:
- buy OR sell → both are valid

Here:
```java
- price - fee
```

👉 ensures fee is counted once per transaction

---

## 🚀 Code (Java - Memoization)

```java

    static int maxProfit(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][2];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return helper(arr, k, 0, 1, n, dp);
    }

    static int helper(int[] arr, int k, int idx, int canBuy, int n, int[][] dp) {

        if (idx == n) return 0;

        if (dp[idx][canBuy] != -1) return dp[idx][canBuy];

        int pick, not_pick;

        if (canBuy == 1) {
            pick = -arr[idx] - k + helper(arr, k, idx + 1, 0, n, dp);
            not_pick = helper(arr, k, idx + 1, 1, n, dp);
        } else {
            pick = arr[idx] + helper(arr, k, idx + 1, 1, n, dp);
            not_pick = helper(arr, k, idx + 1, 0, n, dp);
        }

        return dp[idx][canBuy] = Math.max(pick, not_pick);
    }
```

---

## ⚡ Tabulation (Optimized)

```java
static int Tabulation_maxProfit(int[] prices, int fee) {
    int cur = -prices[0]; // holding stock
    int profit = 0;       // not holding

    for (int i = 1; i < prices.length; i++) {

        profit = Math.max(profit, cur + prices[i] - fee); // sell
        cur = Math.max(cur, profit - prices[i]);          // buy
    }

    return profit;
}
```

---

## 🧪 Dry Run Example

### Input:
```
prices = [6,1,7,2,8,4]
fee = 2
```

---

### Step-by-step:

| Day | Price | Action | Profit |
|-----|------|--------|--------|
| 1   | 6    | skip   | 0 |
| 2   | 1    | buy    | -3 |
| 3   | 7    | sell   | +4 |
| 4   | 2    | buy    | +0 |
| 5   | 8    | sell   | +6 |
| 6   | 4    | skip   | 6 |

---

### Final Output:
```
Max Profit = 6
```

---

## 🔁 Recursion Flow (Simplified)

```
Day 0 → canBuy
  ├── Buy → go to canSell
  │       ├── Sell → go to canBuy
  │       └── Skip
  └── Skip → stay canBuy
```

👉 DP stores results to avoid recomputation

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n * 2) |
| Space | O(n * 2) |

---

## 🎯 Key Takeaways

- State = `(day, canBuy)`
- Only 2 states → very efficient DP
- Fee handled once per transaction
- Can be optimized to **O(1) space**

---

## 🏁 Summary

> At each step, decide whether to buy, sell, or skip to maximize profit with transaction fee.

---

## 📚 Related Topics

- Stock Buy & Sell Series
- Dynamic Programming
- Greedy Optimization
- State Machine DP