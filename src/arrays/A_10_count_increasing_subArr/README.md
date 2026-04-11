# 📈 Count Increasing Subarrays

## 📌 Problem Statement

Given an array `arr[]`, count the total number of **strictly increasing subarrays**.

### 🎯 Goal:

A subarray is valid if:
```
arr[i] < arr[i+1] < arr[i+2] < ...
```

Return the **total count of such subarrays (length ≥ 2)**

---

## 💡 Intuition

Instead of checking all subarrays (O(n²)):

👉 Observe **continuous increasing segments**

---

## 🧠 Thought Process

If we have:

```
[1, 4, 5]
```

Increasing subarrays are:
```
[1,4], [4,5], [1,4,5]
```

👉 Count = 3

---

### 🔥 Key Observation

If a segment has length `L`, then:

```
Number of increasing subarrays = L * (L - 1) / 2
```

---

## ⚡ Optimized Idea

Instead of computing formula:

👉 Track **current increasing streak**

---

### 🔁 Logic

- If `arr[i+1] > arr[i]`
    - Increase streak
    - Add streak to answer
- Else
    - Reset streak

---

## 🚀 Code (Function Only)

```java
static int countIncreasing(int[] arr) {

    int count = 0; // current streak
    int ans = 0;

    for (int i = 0; i < arr.length - 1; i++) {

        if (arr[i + 1] > arr[i]) {
            count++;
            ans += count;
        } else {
            count = 0;
        }
    }

    return ans;
}
```

---

## 🧪 Dry Run Example

### Input:
```
arr = [1, 4, 5, 3, 7, 9]
```

---

### Step-by-step:

| i | arr[i] → arr[i+1] | Increasing? | count | ans |
|--|-------------------|------------|-------|-----|
|0|1 → 4|✅|1|1|
|1|4 → 5|✅|2|3|
|2|5 → 3|❌|0|3|
|3|3 → 7|✅|1|4|
|4|7 → 9|✅|2|6|

---

### Output:
```
6
```

---

## 🔍 Why `ans += count` works?

For sequence:
```
1 → 4 → 5
```

Steps:
```
1st increase → [1,4]
2nd increase → [4,5], [1,4,5]
```

👉 So we add:
```
1 + 2 = 3
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n) |
| Space | O(1) |

---

## 🎯 Key Takeaways

- Avoid generating all subarrays
- Track increasing streak
- Each step contributes multiple subarrays
- Very important **pattern problem**

---

## 🏁 Summary

> Count increasing subarrays by tracking continuous increasing segments.

---

## 📚 Related Topics

- Subarrays
- Sliding Window
- Pattern Recognition
- Greedy Counting  