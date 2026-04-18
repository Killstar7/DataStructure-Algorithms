# ⌨️ Minimum Distance to Type a Word Using Two Fingers

## 📌 Problem Statement

You are given a string `word` consisting of uppercase letters.

👉 You type using **two fingers** on a keyboard grid:

```
A B C D E F
G H I J K L
M N O P Q R
S T U V W X
Y Z
```

---

## ⚙️ Rules

- You can move either finger to press a character
- Movement cost = **Manhattan Distance**
- Initial finger positions = **free (no cost)**

---

## 🎯 Goal

Minimize total typing distance

---

# 💡 Intuition

At each character:

👉 You choose:
```
Use LEFT finger
OR
Use RIGHT finger
```

---

# 🧠 Why this is tricky?

Because:
- Future cost depends on current finger positions
- So we must track both finger positions

---

# 🔥 State Definition

```
dp[idx][left][right]
```

---

### 🔹 Meaning:

| Parameter | Meaning |
|----------|--------|
| `idx` | current character index |
| `left` | position of left finger |
| `right` | position of right finger |

---

👉 Positions range:
```
0 → not used yet
1–26 → A–Z
```

---

# ⚙️ Choices

---

## 🔹 Option 1: Use LEFT finger

```
cost = distance(left → current)
     + solve(idx+1, current, right)
```

---

## 🔹 Option 2: Use RIGHT finger

```
cost = distance(right → current)
     + solve(idx+1, left, current)
```

---

## ✅ Final

```
dp[idx][left][right] = min(pickLeft, pickRight)
```

---

# 📏 Distance Calculation

```
Convert letter → (row, col)
Distance = |x1 - x2| + |y1 - y2|
```

---

## 🔹 Keyboard Mapping

```
index = letter - 'A'

row = index / 6
col = index % 6
```

---

# 🚀 Code (Functions Only)

---

## 🔹 Distance Function

```java
static int findDist(int start, int target){

    if (start == 0) return 0;

    int x1 = (start - 1) / 6;
    int y1 = (start - 1) % 6;

    int x2 = (target - 1) / 6;
    int y2 = (target - 1) % 6;

    return Math.abs(x2 - x1) + Math.abs(y2 - y1);
}
```

---

## 🔹 Tabulation (Best Approach)

```java
static int Tabulation(String word){

    int n = word.length();

    int[][][] dp = new int[n + 1][27][27];

    for (int idx = n - 1; idx >= 0; idx--) {
        for (int left = 26; left >= 0; left--) {
            for (int right = 26; right >= 0; right--) {

                int cur = word.charAt(idx) - 'A' + 1;

                int pickLeft =
                    findDist(left, cur) + dp[idx + 1][cur][right];

                int pickRight =
                    findDist(right, cur) + dp[idx + 1][left][cur];

                dp[idx][left][right] =
                    Math.min(pickLeft, pickRight);
            }
        }
    }

    return dp[0][0][0];
}
```

---

## 🔹 Space Optimized

```java
static int spaceOpt(String word){

    int n = word.length();

    int[][] next = new int[27][27];
    int[][] curr = new int[27][27];

    for (int idx = n - 1; idx >= 0; idx--) {
        for (int left = 26; left >= 0; left--) {
            for (int right = 26; right >= 0; right--) {

                int cur = word.charAt(idx) - 'A' + 1;

                int pickLeft =
                    findDist(left, cur) + next[cur][right];

                int pickRight =
                    findDist(right, cur) + next[left][cur];

                curr[left][right] =
                    Math.min(pickLeft, pickRight);
            }
        }

        int[][] temp = curr;
        curr = next;
        next = temp;
    }

    return next[0][0];
}
```

---

# 🧪 Dry Run Example

## Input:
```
word = "CAKE"
```

---

## Steps:

```
C → A → K → E
```

---

## Optimal Strategy:

```
Left finger:
    C → A

Right finger:
    K → E
```

👉 This reduces movement cost

---

## Output:
```
3
```

---

# 🔁 Recursion Flow (Simplified)

```
idx=0
├── use left → move left to 'C'
└── use right → move right to 'C'

Each branch explores future optimally
```

---

# 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n * 27 * 27) |
| Space | O(n * 27 * 27) |

---

# 🎯 Key Takeaways

- Track both finger positions → 3D DP
- Try both choices at every step
- Distance = Manhattan metric
- Optimize using space reduction

---

# 🏁 Summary

> At each character, choose the finger that minimizes total future cost.

---

# 📚 Related Topics

- 3D Dynamic Programming
- Grid Distance Problems
- State Optimization  