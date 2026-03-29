# 🔢 Count Partitions with Given Difference (Dynamic Programming)

## 📌 Problem Statement

Given an array of positive integers `arr` and a number `diff`, partition the array into **two subsets** `S1` and `S2` such that:

```
sum(S1) - sum(S2) = diff
```

Return the **number of such partitions**.

### Example:

```
arr = [1, 1, 1, 1]
diff = 0
Output: 6
```

Explanation: There are 6 ways to split the array into two subsets with equal sum.

---

## 💡 Intuition

- Let `sum(S1) = s1` and `sum(S2) = s2`
- We know:

```
s1 - s2 = diff
s1 + s2 = total_sum
```

- Solving for `s1`:

```
s1 = (diff + total_sum) / 2
```

- Now the problem reduces to **counting subsets with sum = s1**

> This is a classic **Subset Sum Problem**.

---

## 🧠 Thought Process

1. Calculate `total_sum` of the array
2. Check feasibility:
    - `(total_sum + diff)` must be divisible by 2
    - `total_sum >= diff`  
      If not, return 0
3. Compute `target = (total_sum + diff) / 2`
4. Use **recursive + memoization DP** to count subsets that sum to `target`

---

### 1. Recursive Function

```java
helper(arr, curSum, target, n, index, dp)
```

- `curSum` → sum collected so far
- `index` → current position in array
- `target` → desired sum
- `dp[i][curSum]` → memoization table to store already computed results

---

### 2. Base Cases

```java
if(curSum > target) return 0;
if(index == n) return (curSum == target) ? 1 : 0;
```

- If `curSum` exceeds target → invalid path
- If all elements used → return 1 if `curSum == target` else 0

---

### 3. Recursion & Choices

At each index:

1. **Pick current element**: `curSum + arr[i]`
2. **Do not pick**: keep `curSum` unchanged
3. Return **sum of both choices**

```java
pick = helper(arr, curSum + arr[i], target, n, i + 1, dp)
notPick = helper(arr, curSum, target, n, i + 1, dp)
dp[i][curSum] = pick + notPick
```

- Memoization ensures **O(n * target)** complexity

---

## ⚙️ Algorithm Steps

1. Compute `total_sum = sum(arr)`
2. Check `(total_sum + diff) % 2 == 0` and `total_sum >= diff`
3. Compute `target = (total_sum + diff)/2`
4. Initialize DP table with `-1`
5. Call recursive function `helper(arr, 0, target, n, 0, dp)`
6. Return result

---

## 🚀 Code (Java)

```java

public class solution {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1};
        int dif = 0;
        System.out.println(countPartitions(arr, dif));
    }

    static int countPartitions(int[] arr, int diff) {
        int n = arr.length;
        int total = 0;
        for (int i : arr) total += i;

        if ((total + diff) % 2 != 0 || total < diff) return 0;

        int target = (total + diff) / 2;

        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        return helper(arr, 0, target, n, 0, dp);
    }

    static int helper(int[] arr, int cur, int target, int n, int i, int[][] dp) {
        if (cur > target) return 0;
        if (i == n) return (cur == target) ? 1 : 0;

        if (dp[i][cur] != -1) return dp[i][cur];

        int pick = helper(arr, cur + arr[i], target, n, i + 1, dp);
        int notPick = helper(arr, cur, target, n, i + 1, dp);

        return dp[i][cur] = pick + notPick;
    }
}
```

---

## 🧪 Dry Run Example

Array: `[1,1,1,1]`, `diff = 0`

1. `total_sum = 4`
2. `target = (4 + 0)/2 = 2`
3. Count subsets of sum = 2:

| Subset  | Sum |
|---------|-----|
| [1,1]   | 2   |
| [1,1]   | 2   |
| [1,1]   | 2   |
| [1,1]   | 2   |
| [1,1]   | 2   |
| [1,1]   | 2   |

- **6 valid subsets → 6 partitions**

---

## 📊 Complexity Analysis

| Type | Complexity |
|------|------------|
| Time | O(n * target) |
| Space | O(n * target) |

- `n` = number of elements
- `target` = sum to reach (s1)

---

## 🎯 Key Takeaways

- Transform **subset difference problem → subset sum problem**
- Use **recursion + DP memoization**
- Handles duplicate sums efficiently
- Avoids recomputation using `dp[i][curSum]`

---

## 📚 Related Topics

- Subset Sum Problem
- Dynamic Programming (Top-down / Bottom-up)
- Partitioning Arrays
- Memoization