# 🤖 Maximum Amount of Money Robot Can Earn

## 📌 Problem Statement

You are given a grid `coins[][]`:

- Start at `(0,0)`
- Reach `(m-1,n-1)`
- Allowed moves:
  ```
  → Right
  ↓ Down
  ```

---

## 💰 Cell Value Meaning

- `coins[i][j] >= 0` → gain coins
- `coins[i][j] < 0` → lose coins (robber)

---

## ⚡ Special Power

You can **neutralize robbers at most 2 times**

👉 Meaning:
- Ignore negative value (treat as 0)

---

## 🎯 Goal

Maximize total coins collected

---

# 💡 Intuition

This is a **grid DP problem**, but with an extra twist:

👉 You have **limited power (2 uses)**

---

# 🧠 State Definition

We define:

```
dp[x][y][power]
```

---

### 🔹 Meaning:

| Parameter | Meaning |
|----------|--------|
| `x, y` | current cell |
| `power` | neutralizations left (0,1,2) |

---

👉 This is why we use **3D DP**

---

# 🔥 Choices at Each Cell

---

## 🔹 1. Take the value

```
take = coins[x][y] + max(right, down)
```

---

## 🔹 2. Skip negative (use power)

Only if:
```
coins[x][y] < 0 AND power > 0
```

```
notTake = max(right, down) with power-1
```

---

## ✅ Final Choice

```
dp[x][y][power] = max(take, notTake)
```

---

# 🧠 Base Case

---

## Out of bounds

```
return -∞
```

---

## Destination cell

```
if negative AND power available → return 0
else → return coins[x][y]
```

---

# 🚀 Code (Functions Only)

```java
static int maximumAmount(int[][] coins) {

    int m = coins.length;
    int n = coins[0].length;

    int[][][] dp = new int[m][n][3];

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < 3; k++) {
                dp[i][j][k] = Integer.MIN_VALUE;
            }
        }
    }

    return helper(coins, m, n, 0, 0, 2, dp);
}
```

---

### 🔹 Helper Function

```java
static int helper(int[][] coins, int m, int n,
                  int x, int y, int power, int[][][] dp) {

    if (x >= m || y >= n) return Integer.MIN_VALUE;

    if (x == m - 1 && y == n - 1) {
        if (coins[x][y] < 0 && power > 0) return 0;
        return coins[x][y];
    }

    if (dp[x][y][power] != Integer.MIN_VALUE)
        return dp[x][y][power];

    int right = helper(coins, m, n, x, y + 1, power, dp);
    int down = helper(coins, m, n, x + 1, y, power, dp);

    int take = coins[x][y] + Math.max(right, down);

    int notTake = Integer.MIN_VALUE;

    if (coins[x][y] < 0 && power > 0) {

        int notRight = helper(coins, m, n, x, y + 1, power - 1, dp);
        int notDown = helper(coins, m, n, x + 1, y, power - 1, dp);

        notTake = Math.max(notRight, notDown);
    }

    return dp[x][y][power] = Math.max(take, notTake);
}
```

---

# 🧪 Dry Run Example

## Input:
```
coins = [
 [0, 1, -1],
 [1, -2, 3],
 [2, -3, 4]
]
```

---

## Best Path:

```
(0,0) → (0,1) → (1,1) → (1,2) → (2,2)
```

---

## Steps:

```
0 → +1 → neutralize -2 → +3 → +4
```

---

## Output:
```
8
```

---

# 🔁 Recursion Flow (Simplified)

```
helper(0,0,2)
 ├── right → helper(0,1,2)
 └── down  → helper(1,0,2)

Each explores:
    take OR neutralize
```

👉 DP avoids recomputation

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(m * n * 3) |
| Space | O(m * n * 3) |

---

# 🎯 Key Takeaways

- Add extra dimension for constraints
- Use `power` to track usage
- Try both:
    - take
    - skip (if possible)
- Classic **3D DP problem**

---

# 🏁 Summary

> At each cell, choose between taking value or using power to skip loss.

---

# 📚 Related Topics

- Grid DP
- 3D Dynamic Programming
- Memoization
- Path Optimization  