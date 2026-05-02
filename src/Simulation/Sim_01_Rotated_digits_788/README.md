# 🔄 Rotated Digits

## 📌 Problem Statement

Given an integer `n`, count how many numbers in the range:

    [1, n]

are **good numbers** after rotating every digit by 180 degrees.

---

## 🎯 Goal

Return the count of good numbers from `1` to `n`.

---

# 💡 What is a Good Number?

A number is good if:

1. Every digit remains valid after rotation
2. The rotated number is different from original number

---

# 🔁 Digit Rotation Rules

## ✅ Same after rotation

    0 → 0
    1 → 1
    8 → 8

---

## ✅ Valid and changes after rotation

    2 → 5
    5 → 2
    6 → 9
    9 → 6

---

## ❌ Invalid after rotation

    3, 4, 7

---

# 🔥 Key Idea

For a number to be good:

- It must NOT contain `3`, `4`, or `7`
- It must contain at least one of:

      2, 5, 6, 9

Because these digits make the rotated number different.

---

# 🧠 Thought Process

For every number from `1` to `n`:

1. Check every digit
2. If digit is invalid → number is not good
3. If digit changes after rotation → mark `diff = true`
4. At the end:
   return true only if diff is true

---

# 🚀 Code (Function Only)

```java
static int rotatedDigits(int n) {

    int ans = 0;

    for (int i = 2; i <= n; i++) {

        if (isChange(i)) {
            ans++;
        }
    }

    return ans;
}

static boolean isChange(int n) {

    int num = n;
    boolean diff = false;

    while (num > 0) {

        int rem = num % 10;

        if (rem == 3 || rem == 4 || rem == 7) {
            return false;
        }

        if (rem == 2 || rem == 5 || rem == 6 || rem == 9) {
            diff = true;
        }

        num /= 10;
    }

    return diff;
}
```

---

# 🧪 Dry Run

Input:

    n = 10

---

Check numbers:

    1  → rotates to 1  → same → not good
    2  → rotates to 5  → different → good
    3  → invalid
    4  → invalid
    5  → rotates to 2  → different → good
    6  → rotates to 9  → different → good
    7  → invalid
    8  → rotates to 8  → same → not good
    9  → rotates to 6  → different → good
    10 → rotates to 10 → same → not good

---

Output:

    4

Good numbers:

    2, 5, 6, 9

---

# 🔁 Logic Flow

    Loop from 1 to n
        ↓
    Extract digits using % 10
        ↓
    If digit is 3,4,7 → invalid
        ↓
    If digit is 2,5,6,9 → changed
        ↓
    Good only if valid and changed

---

# 📊 Complexity

Time:

    O(n * digits)

Space:

    O(1)

---

# 🎯 Key Takeaways

- Invalid digits immediately reject number
- At least one changing digit is required
- Digits `0`, `1`, `8` alone are not enough
- This is a simulation + digit processing problem

---

# 🔥 Most Important Insight

    A good number must be valid after rotation AND must change after rotation.

---

# 🏁 Summary

To solve:

    Check each number digit by digit
    Reject invalid digits
    Track if at least one digit changes
    Count valid changed numbers