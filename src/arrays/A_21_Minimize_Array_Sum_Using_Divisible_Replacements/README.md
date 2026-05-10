# 🔢 Minimize Array Sum Using Divisible Replacements

## 📌 Problem Statement

Given an integer array `nums`.

You can perform the following operation any number of times:

    Choose indices a and b such that:
        nums[a] % nums[b] == 0

Then replace:

    nums[a] = nums[b]

Return the minimum possible sum of the array after performing any number of operations.

---

## 🎯 Goal

Minimize the total sum of the array using valid divisible replacements.

---

## 💡 Intuition

For any element `x`, we want to replace it with the smallest possible value available in the array that divides `x`.

Why?

Because replacing with a smaller divisor always reduces or keeps the value same.

Example:

    x = 8

Possible divisors:

    1, 2, 4, 8

If `2` exists in the array:

    8 → 2

which minimizes the contribution of 8 in the final sum.

So for every number:

    Find the smallest divisor already present in the array.

---

## 🔥 Key Idea

Store all array elements inside a HashSet for:

    O(1) lookup

Then for every element `x`:

    Iterate through all divisors of x

For every divisor pair:

    d
    x / d

Check whether either divisor exists in the array.

If yes:

    update smallest possible replacement

Finally:

    add the smallest achievable value to answer

---

## 🧠 Thought Process

Suppose:

    nums = [4, 2, 8, 3]

For:

    x = 8

Its divisors are:

    1, 2, 4, 8

Among these, values existing in array:

    2, 4, 8

Best replacement:

    2

because:

    8 % 2 == 0

and replacing by the smallest divisor minimizes sum.

---

Important Observation:

If:

    a divides b
    and b divides c

Then eventually:

    c can also become a

So directly finding the minimum divisor present in the array is enough.

No simulation is needed.

---

## 💻 Code

```java
static long minArraySum(int[] nums) {

    HashSet<Integer> set = new HashSet<>();

    for (int x : nums) {
        set.add(x);
    }

    long sum = 0;

    for (int x : nums) {

        int best = x;

        for (int d = 1; d * d <= x; d++) {

            if (x % d == 0) {

                int d1 = d;
                int d2 = x / d;

                if (set.contains(d1)) {
                    best = Math.min(best, d1);
                }

                if (set.contains(d2)) {
                    best = Math.min(best, d2);
                }
            }
        }

        sum += best;
    }

    return sum;
}
```

---

## 🧪 Dry Run

Input:

    nums = [4, 2, 8, 3]

---

Step 1: Store all elements in HashSet

    set = {2, 3, 4, 8}

---

Step 2: Process x = 4

Initial:

    best = 4

Find divisors:

For d = 1:

    divisors = 1 and 4

Check set:

    1 → not present
    4 → present

So:

    best = 4

---

For d = 2:

    divisors = 2 and 2

Check set:

    2 → present

Update:

    best = min(4, 2)
         = 2

Final replacement for 4:

    2

---

Step 3: Process x = 2

Initial:

    best = 2

Divisors:

    1 and 2

Check set:

    2 exists

So:

    best = 2

Final replacement:

    2

---

Step 4: Process x = 8

Initial:

    best = 8

Divisors:

For d = 1:

    1 and 8

8 exists:

    best = 8

---

For d = 2:

    2 and 4

Both exist.

Update:

    best = min(8, 2)
         = 2

---

Final replacement for 8:

    2

---

Step 5: Process x = 3

Divisors:

    1 and 3

3 exists.

Final replacement:

    3

---

Final transformed array:

    [2, 2, 2, 3]

Final sum:

    2 + 2 + 2 + 3
    = 9

Answer:

    9

---

## 🔁 Logic Flow

    Start
      |
      v
    Store all array elements in HashSet
      |
      v
    For every element x
      |
      v
    Initialize:
        best = x
      |
      v
    Find all divisors of x
      |
      v
    For each divisor pair:
        d and x/d
      |
      v
    Check if divisor exists in set
      |
      v
    Update minimum possible replacement
      |
      v
    Add best value to final sum
      |
      v
    Return total sum

---

## 📊 Complexity

Time Complexity:

    O(n * √m)

Where:

    n = number of elements
    m = maximum value in array

Reason:

    For every number,
    we iterate through divisors up to √x.

---

Space Complexity:

    O(n)

Reason:

    HashSet stores all unique array elements.

---

## 🎯 Key Takeaways

- Replacing with the smallest divisor minimizes contribution.
- No need to simulate operations repeatedly.
- HashSet provides fast divisor lookup.
- Divisor enumeration works in O(√x).
- Divisors always come in pairs:
  d and x/d

---

## 🔥 Most Important Insight

The problem can be reduced to:

    "For every number, find the smallest divisor already present in the array."

Because repeated operations eventually reduce a number to the minimum reachable divisor.

---

## 🏁 Summary

To solve this problem:

    1. Store all numbers in HashSet.
    2. For every element:
           find all divisors.
    3. Check which divisors exist in array.
    4. Choose the minimum possible divisor.
    5. Add it to final answer.

Efficient solution:

    Time  : O(n * √m)
    Space : O(n)