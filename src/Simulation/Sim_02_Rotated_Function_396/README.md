# 🔄 Rotate Function

## 📌 Problem Statement

Given an integer array `nums` of length `n`.

Assume `arrK` is the array obtained by rotating `nums` clockwise by `k` positions.

The rotation function is:

    F(k) = 0 * arrK[0] + 1 * arrK[1] + 2 * arrK[2] + ... + (n - 1) * arrK[n - 1]

Return the maximum value among:

    F(0), F(1), F(2), ..., F(n - 1)

---

## 🎯 Goal

Find the maximum rotation function value without physically rotating the array.

---

## 💡 Intuition

A brute force approach would rotate the array for every `k` and calculate `F(k)` again.

That would take:

    O(n²)

because there are `n` rotations and each rotation needs `n` calculations.

Instead of recalculating every rotation from the beginning, we can derive the next rotation value from the previous one.

---

## 🔥 Key Idea

First calculate:

    total = sum of all elements
    F(0) = initial rotation function value

Then use:

    F(k) = F(k - 1) + total - n * nums[n - k]

Where:

    nums[n - k]

is the element that moves from the end to the front during the `k-th` clockwise rotation.

---

## 🧠 Thought Process

When the array rotates clockwise by one position:

    Before rotation:
    [a0, a1, a2, ..., an-1]

    After rotation:
    [an-1, a0, a1, a2, ..., an-2]

Now observe:

    Most elements shift one index to the right.
    So their contribution increases by their own value.
    Therefore, total contribution increases by total sum.

But the last element moves to the front.

Previously:

    an-1 was at index n - 1

After rotation:

    an-1 comes to index 0

So its contribution changes from:

    (n - 1) * an-1

to:

    0 * an-1

To adjust this correctly, we subtract:

    n * movedElement

Therefore:

    nextF = currentF + total - n * movedElement

---

## 💻 Code

```java
static int maxRotateFunction(int[] nums) {
    int n = nums.length;

    int total = 0;
    int f0 = 0;

    for (int i = 0; i < n; i++) {
        total += nums[i];
        f0 += i * nums[i];
    }

    int ans = f0;

    for (int i = 1; i < n; i++) {
        int temp = f0 + total - n * nums[n - i];

        ans = Math.max(ans, temp);

        f0 = temp;
    }

    return ans;
}
```

---

## 🧪 Dry Run

Input:

    nums = [4, 3, 2, 6]

Length:

    n = 4

Step 1: Calculate total sum

    total = 4 + 3 + 2 + 6
          = 15

Step 2: Calculate F(0)

    F(0) = 0*4 + 1*3 + 2*2 + 3*6
         = 0 + 3 + 4 + 18
         = 25

So:

    f0 = 25
    ans = 25

Step 3: Calculate F(1)

Element moving to front:

    nums[n - 1] = nums[3] = 6

Formula:

    F(1) = F(0) + total - n * nums[n - 1]

Substitute values:

    F(1) = 25 + 15 - 4 * 6
         = 40 - 24
         = 16

Update:

    ans = max(25, 16) = 25
    f0 = 16

Step 4: Calculate F(2)

Element moving to front:

    nums[n - 2] = nums[2] = 2

Formula:

    F(2) = F(1) + total - n * nums[n - 2]

Substitute values:

    F(2) = 16 + 15 - 4 * 2
         = 31 - 8
         = 23

Update:

    ans = max(25, 23) = 25
    f0 = 23

Step 5: Calculate F(3)

Element moving to front:

    nums[n - 3] = nums[1] = 3

Formula:

    F(3) = F(2) + total - n * nums[n - 3]

Substitute values:

    F(3) = 23 + 15 - 4 * 3
         = 38 - 12
         = 26

Update:

    ans = max(25, 26) = 26
    f0 = 26

Final answer:

    26

---

## 🔁 Logic Flow

    Start
      |
      v
    Calculate n
      |
      v
    Calculate total sum and F(0)
      |
      v
    Store F(0) as answer
      |
      v
    For each rotation from 1 to n - 1
      |
      v
    Find element moving to front: nums[n - i]
      |
      v
    Calculate current rotation:
        temp = previousF + total - n * nums[n - i]
      |
      v
    Update maximum answer
      |
      v
    Make current value previous for next round
      |
      v
    Return answer

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    One loop calculates total and F(0).
    One loop calculates remaining rotations.

Space Complexity:

    O(1)

Reason:

    No extra array is used.
    Only constant variables are used.

---

## 🎯 Key Takeaways

- Do not rotate the array physically.
- Brute force takes O(n²), which is inefficient.
- Use the relation between consecutive rotation values.
- First calculate `F(0)` and total sum.
- Then calculate every next rotation in O(1).
- This problem is based on mathematical optimization.

---

## 🔥 Most Important Insight

The whole problem depends on this formula:

    F(k) = F(k - 1) + totalSum - n * nums[n - k]

This formula avoids repeated rotation and repeated calculation.

---

## 🏁 Summary

To solve the Rotate Function problem:

    1. Calculate total sum of nums.
    2. Calculate initial value F(0).
    3. Use the recurrence formula for every rotation.
    4. Track the maximum value.
    5. Return the maximum.

Efficient solution:

    Time  : O(n)
    Space : O(1)