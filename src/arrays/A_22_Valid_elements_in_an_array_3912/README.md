# 📌 Valid Elements in an Array

## 📌 Problem Statement

Given an integer array `nums`.

An element `nums[i]` is considered valid if it satisfies at least one of the following conditions:

    1. nums[i] is strictly greater than every element to its left
    2. nums[i] is strictly greater than every element to its right
    3. First and last elements are always valid

Return all valid elements in the same order as they appear in the array.

---

## 🎯 Goal

Find and return all valid elements according to the given conditions.

---

## 💡 Intuition

For every index `i`, we need to quickly know:

    1. Maximum element on the left side
    2. Maximum element on the right side

Then we can check:

    nums[i] > leftMax
    OR
    nums[i] > rightMax

If either condition is true:

    nums[i] is valid

Instead of checking left and right every time using nested loops:

    O(n²)

we precompute:

    left maximum array
    right maximum array

This reduces the complexity to:

    O(n)

---

## 🔥 Key Idea

Create two arrays:

    left[i]
        = maximum element before index i

    right[i]
        = maximum element after index i

Then for every element:

    if nums[i] > left[i]
    OR
    nums[i] > right[i]

then it is valid.

Additionally:

    first and last elements are always valid

---

## 🧠 Thought Process

Suppose:

``` id="example-array"
nums = [1, 2, 4, 2, 3, 2]
```

We build:

---

Left maximum array:

``` id="left-build"
left[0] = -∞

left[1] = max(left[0], nums[0]) = 1
left[2] = max(left[1], nums[1]) = 2
left[3] = max(left[2], nums[2]) = 4
left[4] = max(left[3], nums[3]) = 4
left[5] = max(left[4], nums[4]) = 4
```

Final:

``` id="left-final"
left = [-∞, 1, 2, 4, 4, 4]
```

---

Right maximum array:

``` id="right-build"
right[5] = -∞

right[4] = max(right[5], nums[5]) = 2
right[3] = max(right[4], nums[4]) = 3
right[2] = max(right[3], nums[3]) = 3
right[1] = max(right[2], nums[2]) = 4
right[0] = max(right[1], nums[1]) = 4
```

Final:

``` id="right-final"
right = [4, 4, 3, 3, 2, -∞]
```

---

Now check validity:

``` id="valid-checks"
nums[0] = 1 → first element → valid
nums[1] = 2 → 2 > left[1] = 1 → valid
nums[2] = 4 → 4 > left[2] = 2 → valid
nums[3] = 2 → not greater than left or right → invalid
nums[4] = 3 → 3 > right[4] = 2 → valid
nums[5] = 2 → last element → valid
```

Final answer:

``` id="answer"
[1, 2, 4, 3, 2]
```

---

## 💻 Code

```java
static List<Integer> findValidElements(int[] nums) {

    int n = nums.length;

    List<Integer> list = new ArrayList<>();

    int[] left = new int[n];

    left[0] = Integer.MIN_VALUE;

    for (int i = 1; i < n; i++) {
        left[i] = Math.max(left[i - 1], nums[i - 1]);
    }

    int[] right = new int[n];

    right[n - 1] = Integer.MIN_VALUE;

    for (int i = n - 2; i >= 0; i--) {
        right[i] = Math.max(right[i + 1], nums[i + 1]);
    }

    for (int i = 0; i < n; i++) {

        if (
            i == 0 ||
            i == n - 1 ||
            nums[i] > left[i] ||
            nums[i] > right[i]
        ) {
            list.add(nums[i]);
        }
    }

    return list;
}
```

---

## 🧪 Dry Run

Input:

``` id="dry-input"
nums = [1, 2, 4, 2, 3, 2]
```

Length:

``` id="n-value"
n = 6
```

---

Step 1: Build left array

``` id="left-process"
left[0] = -∞

left[1] = max(-∞, 1) = 1
left[2] = max(1, 2) = 2
left[3] = max(2, 4) = 4
left[4] = max(4, 2) = 4
left[5] = max(4, 3) = 4
```

Final:

``` id="left-result"
left = [-∞, 1, 2, 4, 4, 4]
```

---

Step 2: Build right array

``` id="right-process"
right[5] = -∞

right[4] = max(-∞, 2) = 2
right[3] = max(2, 3) = 3
right[2] = max(3, 2) = 3
right[1] = max(3, 4) = 4
right[0] = max(4, 2) = 4
```

Final:

``` id="right-result"
right = [4, 4, 3, 3, 2, -∞]
```

---

Step 3: Check each element

Index 0:

``` id="check0"
First element → valid
```

Add:

``` id="add0"
1
```

---

Index 1:

``` id="check1"
nums[1] = 2

2 > left[1] = 1
```

Valid.

Add:

``` id="add1"
2
```

---

Index 2:

``` id="check2"
nums[2] = 4

4 > left[2] = 2
```

Valid.

Add:

``` id="add2"
4
```

---

Index 3:

``` id="check3"
nums[3] = 2

2 > left[3] = 4 → false
2 > right[3] = 3 → false
```

Invalid.

---

Index 4:

``` id="check4"
nums[4] = 3

3 > right[4] = 2
```

Valid.

Add:

``` id="add4"
3
```

---

Index 5:

``` id="check5"
Last element → valid
```

Add:

``` id="add5"
2
```

---

Final Answer:

``` id="final-valid-answer"
[1, 2, 4, 3, 2]
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Create left max array
      |
      v
    Create right max array
      |
      v
    Traverse array
      |
      v
    Is element first or last?
      |
     Yes -----> valid
      |
     No
      |
      v
    Check:
        nums[i] > left[i]
        OR
        nums[i] > right[i]
      |
      v
    If true:
        add to answer
      |
      v
    Return final list

---

## 📊 Complexity

Time Complexity:

    O(n)

Reason:

    One traversal for left array
    One traversal for right array
    One traversal for checking validity

Total:

    O(n)

---

Space Complexity:

    O(n)

Reason:

    Two extra arrays are used:

        left[]
        right[]

---

## 🎯 Key Takeaways

- Precomputation avoids repeated comparisons.
- Prefix maximum helps track left side maximum.
- Suffix maximum helps track right side maximum.
- First and last elements are always valid.
- Strictly greater condition is important.

---

## 🔥 Most Important Insight

Instead of checking all left and right elements repeatedly:

    Precompute maximums once.

Then validity checking becomes:

    O(1) per element

---

## 🏁 Summary

To solve this problem:

    1. Build left maximum array.
    2. Build right maximum array.
    3. Traverse every element.
    4. Check if:
           greater than all left
           OR
           greater than all right
    5. Add valid elements to answer.
    6. Return final list.

Efficient solution:

    Time  : O(n)
    Space : O(n)