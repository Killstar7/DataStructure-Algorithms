# 🤖 Maximum Walls Destroyed by Robots

## 📌 Problem Statement

You are given:

- `robots[i]` → position of robot i
- `distance[i]` → attack range of robot i
- `walls[]` → positions of walls

---

## ⚙️ Rules

Each robot can:

- Shoot **LEFT** or **RIGHT** (only one direction)
- Destroy all walls in that direction within its range

---

## 🚫 Constraint

👉 Robots **cannot overlap or cross each other's range**

---

## 🎯 Goal

Maximize the **total number of walls destroyed**

---

# 💡 Intuition

Each robot creates a **valid range**:

```
[pos - dist, pos + dist]
```

BUT:

👉 This range must be **clipped** so it doesn't overlap with neighboring robots

---

# 🧠 Step 1: Build Valid Ranges

For each robot:

```
Left boundary = max(previous robot + 1, pos - dist)
Right boundary = min(next robot - 1, pos + dist)
```

👉 Ensures:
- No overlap
- No crossing

---

# 🔥 Step 2: DP Decision

At each robot:

```
Choose:
    → Shoot LEFT
    → Shoot RIGHT
```

---

# 🧠 State Definition

```
dp[idx][dir]
```

---

### 🔹 Meaning:

| Parameter | Meaning |
|----------|--------|
| `idx` | current robot |
| `dir` | previous direction (0 = left, 1 = right) |

---

## ❓ Why `dir`?

👉 If previous robot fired RIGHT:

- It may occupy some right space
- So current robot’s LEFT range must shrink

---

# ⚙️ Transition

---

## 🔹 Option 1: Shoot LEFT

```
left_walls =
    walls in [adjusted_left, robot_pos]
    + solve(next robot, dir = LEFT)
```

---

## 🔹 Option 2: Shoot RIGHT

```
right_walls =
    walls in [robot_pos, right_limit]
    + solve(next robot, dir = RIGHT)
```

---

## ✅ Final

```
dp[idx][dir] = max(left_walls, right_walls)
```

---

# 🔍 Counting Walls Efficiently

We use:

```
Binary Search (lowerBound, upperBound)
```

To count walls in range:

```
count = upperBound(right) - lowerBound(left)
```

---

# 🚀 Code (Functions Only)

```java
static int maxWalls(int[] robots, int[] distance, int[] walls) {

    int n = robots.length;

    ArrayList<Pair> robotdist = new ArrayList<>();
    ArrayList<Pair> range = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        robotdist.add(new Pair(robots[i], distance[i]));
    }

    robotdist.sort(Comparator.comparingInt(Pair::first));

    Arrays.sort(walls);
    walls = Arrays.stream(walls).distinct().toArray();

    int[][] dp = new int[n][2];
    for (int[] row : dp) Arrays.fill(row, -1);

    for (int i = 0; i < n; i++) {

        int pos = robotdist.get(i).first();
        int dist = robotdist.get(i).second();

        int leftLimit = (i == 0) ? 1 : robotdist.get(i - 1).first() + 1;
        int rightLimit = (i == n - 1) ? (int)1e9 : robotdist.get(i + 1).first() - 1;

        int Left = Math.max(leftLimit, pos - dist);
        int Right = Math.min(rightLimit, pos + dist);

        range.add(new Pair(Left, Right));
    }

    return helper(robotdist, range, walls, 1, 0, dp);
}
```

---

### 🔹 Helper

```java
static int helper(ArrayList<Pair> robodist,
                  ArrayList<Pair> range,
                  int[] walls,
                  int dir,
                  int idx,
                  int[][] dp) {

    if (idx == robodist.size()) return 0;

    if (dp[idx][dir] != -1) return dp[idx][dir];

    int left_start = range.get(idx).first();

    if (dir == 1 && idx > 0) {
        left_start = Math.max(left_start, range.get(idx - 1).second() + 1);
    }

    int left_walls =
        countWalls(walls, left_start, robodist.get(idx).first())
        + helper(robodist, range, walls, 0, idx + 1, dp);

    int right_walls =
        countWalls(walls, robodist.get(idx).first(), range.get(idx).second())
        + helper(robodist, range, walls, 1, idx + 1, dp);

    return dp[idx][dir] = Math.max(left_walls, right_walls);
}
```

---

# 🧪 Dry Run (Simple)

```
robots = [4]
distance = [3]
walls = [1,1]
```

---

### Range:

```
[4 - 3, 4 + 3] → [1, 7]
```

---

### Walls in range:

```
Both walls = 2
```

---

### Output:
```
2
```

---

# 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(n log n + q log w) |
| Space | O(n) |

---

# 🎯 Key Takeaways

- Convert problem → range + decision
- Use DP for direction dependency
- Use binary search for fast counting
- Avoid overlapping via constraints

---

# 🏁 Summary

> For each robot, choose best direction while respecting neighbor constraints.

---

# 📚 Related Topics

- Interval Problems
- Dynamic Programming
- Binary Search
- Greedy + DP Hybrid  