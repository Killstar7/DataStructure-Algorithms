# 🔄 Minimum Swaps to Group 1s (Sliding Window)

## 📌 Problem Statement

Given a binary array `arr[]` consisting of only `0`s and `1`s:

Find the **minimum number of swaps** required to group all `1`s together in a contiguous subarray.

---

## 🎯 Goal

Return:

    Minimum swaps required

Special cases:

    No 1s → return -1
    All 1s → return 0

---

# 💡 Intuition

We don’t actually swap elements.

👉 Instead, we **find the best window** where all 1s can fit.

---

# 🔥 Key Idea

Let:

    total_ones = number of 1s

👉 If we group all 1s together, they must occupy a window of size:

    window_size = total_ones

---

## 🧠 What to Minimize?

Inside this window:

    Every 0 must be swapped with a 1 outside

👉 So:

    Number of swaps = number of 0s inside window

---

# 🧠 Thought Process

1. Count number of 1s → window size
2. Slide window of size `count1`
3. Count how many 0s inside each window
4. Take minimum

---

# 🚀 Code (Function Only)

```java
static int minSwaps(int[] arr) {

    int n = arr.length;

    int count0 = 0;

    for (int i : arr) {
        if (i == 0) count0++;
    }

    if (count0 == n) return -1;
    if (count0 == 0) return 0;

    int count1 = n - count0;

    int minSwaps = Integer.MAX_VALUE;
    int temp = 0;

    // first window
    for (int i = 0; i < count1; i++) {
        if (arr[i] == 0) temp++;
    }

    minSwaps = temp;

    int left = 0;

    // sliding window
    for (int i = count1; i < n; i++) {

        if (arr[i] == 0) temp++;
        if (arr[left++] == 0) temp--;

        minSwaps = Math.min(minSwaps, temp);
    }

    return minSwaps;
}
```

---

# 🧪 Dry Run

Input:

    arr = [1, 0, 1, 0, 1]

---

Step 1:

    total 1s = 3
    window size = 3

---

Check windows:

    [1,0,1] → 1 zero
    [0,1,0] → 2 zeros
    [1,0,1] → 1 zero

---

Minimum:

    1

---

Output:

    1

---

# 🔁 Sliding Window Flow

    Fix window size = count of 1s
        ↓
    Count zeros in window
        ↓
    Slide window
        ↓
    Update minimum zeros

---

# 📊 Complexity

Time:

    O(n)

Space:

    O(1)

---

# 🎯 Key Takeaways

- Convert problem → sliding window
- Window size = number of 1s
- Minimize zeros in window
- No actual swapping required

---

# 🔥 Most Important Insight

    Minimum swaps = minimum zeros inside a window of size (number of 1s)

---

# 🏁 Summary

To group all 1s:

    Slide a window of size = count of 1s
    Count zeros inside
    Return minimum