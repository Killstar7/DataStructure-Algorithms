# 🤖 Minimum Total Distance Traveled

## 📌 Problem Statement

You are given:

- `robot[i]` → position of robots
- `factory[j] = [position, limit]`

---

## ⚙️ Rules

- Each robot must go to a factory
- Each factory can repair **at most `limit` robots**
- Robots can move left or right freely
- Distance = `|robot - factory|`

---

## 🎯 Goal

Minimize **total distance traveled by all robots**

---

# 💡 Intuition

👉 This is NOT just greedy

Because:
- Factories have **capacity limits**
- Assigning one robot affects others

---

# 🔥 Key Idea

### Sort everything

```
robots → sorted
factories → sorted
```

👉 Why?
- Closest assignments minimize distance
- Prevents crossing assignments

---

# 🧠 Core Transformation

---

## 🔹 Trick: Expand Factories

Example:

```
factory = [[2,2], [6,2]]
```

👉 Expand:

```
[2,2,6,6]
```

---

👉 Now problem becomes:

```
Assign robots → factory slots
```

---

# 🧠 State Definition

```
dp[i][j]
```

---

### 🔹 Meaning:

| Parameter | Meaning |
|----------|--------|
| `i` | current robot |
| `j` | current factory slot |

---

# ⚙️ Choices

---

## 🔹 Option 1: Assign robot i → factory j

```
take =
    |robot[i] - factory[j]|
    + dp[i+1][j+1]
```

---

## 🔹 Option 2: Skip factory j

```
skip = dp[i][j+1]
```

---

## ✅ Final

```
dp[i][j] = min(take, skip)
```

---

# 🧠 Base Cases

---

## All robots assigned

```
i == n → return 0
```

---

## No factories left

```
j == s → return ∞
```

---

# 🚀 Code (Functions Only)

---

## 🔹 Memoization (Expanded Factories)

```java
static long helper(int i, int j,
                   List<Integer> robot,
                   ArrayList<Integer> factories,
                   int n, int s,
                   long[][] dp) {

    if (i >= n) return 0;
    if (j >= s) return Long.MAX_VALUE / 2;

    if (dp[i][j] != -1) return dp[i][j];

    long take =
        Math.abs(factories.get(j) - robot.get(i))
        + helper(i + 1, j + 1, robot, factories, n, s, dp);

    long skip =
        helper(i, j + 1, robot, factories, n, s, dp);

    return dp[i][j] = Math.min(take, skip);
}
```

---

# 🔥 Better Approach (No Expansion)

---

## 🧠 Why improve?

Expansion can be large if limits are big

---

## 🔹 New Idea

For each factory:

👉 Assign **k robots (0 → limit)**

---

## 🔹 Code

```java
static long threeParameters(int i, int j,
                           List<Integer> robot, int n,
                           int[][] factory, int m,
                           long[][] dp) {

    if (i == n) return 0;
    if (j == m) return Long.MAX_VALUE / 2;

    if (dp[i][j] != -1) return dp[i][j];

    long skip =
        threeParameters(i, j + 1, robot, n, factory, m, dp);

    long take = Long.MAX_VALUE / 2;
    long cost = 0;

    for (int k = 0; k < factory[j][1] && i + k < n; k++) {

        cost += Math.abs(robot.get(i + k) - factory[j][0]);

        long next =
            threeParameters(i + k + 1, j + 1, robot, n, factory, m, dp);

        take = Math.min(take, cost + next);
    }

    return dp[i][j] = Math.min(take, skip);
}
```

---

# 🧪 Dry Run Example

## Input:
```
robot = [0,4,6]
factory = [[2,2],[6,2]]
```

---

## After sorting:

```
robots = [0,4,6]
factories = [2,6]
```

---

## Best Assignment:

```
0 → 2  (dist = 2)
4 → 2  (dist = 2)
6 → 6  (dist = 0)
```

---

## Output:
```
4
```

---

# 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n * m * limit) |
| Space | O(n * m) |

---

# 🎯 Key Takeaways

- Sort first → avoid crossing
- Convert to assignment problem
- Use DP to handle capacity
- Try multiple assignments per factory

---

# 🏁 Summary

> Assign robots to factories optimally using DP while respecting capacity.

---

# 📚 Related Topics

- Dynamic Programming
- Assignment Problems
- Greedy + DP
- Interval Matching  