# 🎨 Painting the Fence

## 📌 Problem Statement

You are given:
- `n` fences
- `k` colors

### 🎯 Goal:
Count the number of ways to paint the fences such that:

```
❌ No more than 2 adjacent fences have the same color
```

---

## 💡 Intuition

At every fence, we decide:
- Same color as previous?
- Different color?

👉 But constraint:
```
At most 2 consecutive same colors allowed
```

---

# 🧠 Thought Process

We track two states:

---

## 🔹 1. `same`

Ways where:
```
Last two fences have SAME color
```

---

## 🔹 2. `diff`

Ways where:
```
Last two fences have DIFFERENT colors
```

---

# 🔥 Why do we need this?

Because:
- If already 2 same → next must be different
- If different → next can be same or different

---

# ⚙️ Recurrence

---

## 🔹 For fence i:

### SAME:
```
newSame = diff
```

👉 Why?
- We can only make same if previous were different

---

### DIFFERENT:
```
newDiff = (same + diff) * (k - 1)
```

👉 Why?
- From any previous state
- Choose a different color from last one → (k - 1)

---

# 🧠 Base Case

---

## For n = 1:
```
k ways
```

---

## For n = 2:

```
same = k        (AA)
diff = k*(k-1)  (AB)
```

---

# 🚀 Code (Function Only)

```java
static int countWays(int n, int k) {

    if (n == 1) return k;

    int same = k;
    int diff = k * (k - 1);

    for (int i = 3; i <= n; i++) {

        int newsame = diff;
        int newdiff = (same + diff) * (k - 1);

        same = newsame;
        diff = newdiff;
    }

    return same + diff;
}
```

---

# 🧪 Dry Run Example

## Input:
```
n = 3, k = 2
```

---

## Step 1: n = 2

```
same = 2   → (AA, BB)
diff = 2   → (AB, BA)
```

---

## Step 2: n = 3

```
newSame = diff = 2

newDiff = (same + diff)*(k-1)
        = (2 + 2)*1 = 4
```

---

## Final Answer:
```
same + diff = 2 + 4 = 6
```

---

# 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

# 🎯 Key Takeaways

- Track last two fence relationship
- Use `same` and `diff` states
- Avoid invalid sequences (3 same colors)
- Classic DP pattern

---

# 🏁 Summary

> Count ways by tracking whether last two fences are same or different.

---

# 📚 Related Topics

- Dynamic Programming
- State Optimization
- Combinatorics  