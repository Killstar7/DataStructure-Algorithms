# ➕ Not a Subset Sum

## 📌 Problem Statement

Given an array `arr[]` of positive integers.

Find the smallest positive integer that cannot be represented as the sum of elements of any subset of the array.

---

## 🎯 Goal

Return the minimum positive number that cannot be formed using subset sums of the given array.

---

## 💡 Intuition

Suppose we already know:

    We can form every number from:
        1 to res - 1

Now consider the next array element:

    num

Two cases arise:

---

### Case 1:

``` id="case1"
num <= res
```

Then:

    We can now extend the reachable range.

Why?

Because:

    Using num together with previous sums,
    we can form all values up to:

        res + num - 1

So we update:

``` id="update-res"
res += num
```

---

### Case 2:

``` id="case2"
num > res
```

Then:

    res cannot be formed.

Because:

- All previous subset sums are smaller than `res`
- Current number itself is larger than `res`
- So no subset can create `res`

Hence:

``` id="answer-break"
res is the answer
```

---

## 🔥 Key Idea

Sort the array first.

Maintain:

``` id="res-meaning"
res = smallest value that cannot yet be formed
```

Initially:

``` id="initial-res"
res = 1
```

Because before using any numbers:

    We cannot form 1.

Then process numbers in sorted order.

---

## 🧠 Thought Process

Suppose:

``` id="example"
arr = [1, 2, 3]
```

After sorting:

``` id="sorted"
[1, 2, 3]
```

Initially:

``` id="start"
res = 1
```

Meaning:

    Smallest value not formable yet is 1

---

Take:

``` id="take1"
num = 1
```

Since:

``` id="condition1"
1 <= res
```

We can now form:

``` id="range1"
1
```

Update:

``` id="res2"
res = 1 + 1 = 2
```

Now:

    We can form all values from 1 to 1.

Smallest missing becomes:

    2

---

Take:

``` id="take2"
num = 2
```

Since:

``` id="condition2"
2 <= res
```

We can now form:

``` id="range2"
1 to 3
```

Update:

``` id="res3"
res = 2 + 2 = 4
```

---

Take:

``` id="take3"
num = 3
```

Since:

``` id="condition3"
3 <= res
```

We can now form:

``` id="range3"
1 to 6
```

Update:

``` id="res4"
res = 4 + 3 = 7
```

Now:

    All sums from 1 to 6 are possible.

So smallest impossible sum is:

``` id="final7"
7
```

---

## 💻 Code

```java
static int findSmallest(int[] arr) {

    Arrays.sort(arr);

    int res = 1;

    for (int num : arr) {

        if (num > res) {
            break;
        }

        res += num;
    }

    return res;
}
```

---

## 🧪 Dry Run

Input:

``` id="dry-input"
arr = [3, 1, 2]
```

---

Step 1: Sort array

``` id="sorted-array"
[1, 2, 3]
```

---

Step 2: Initialize

``` id="initial"
res = 1
```

Meaning:

    Smallest sum not possible yet is 1

---

Step 3: Process num = 1

Check:

``` id="check1"
1 <= res
```

True.

Now we can form:

``` id="range-form1"
1
```

Update:

``` id="update1"
res = 1 + 1 = 2
```

Now:

    All sums from 1 to 1 are possible.

---

Step 4: Process num = 2

Check:

``` id="check2"
2 <= res
```

True.

Now we can form:

``` id="range-form2"
1 to 3
```

Update:

``` id="update2"
res = 2 + 2 = 4
```

---

Step 5: Process num = 3

Check:

``` id="check3"
3 <= res
```

True.

Now we can form:

``` id="range-form3"
1 to 6
```

Update:

``` id="update3"
res = 4 + 3 = 7
```

---

End of array.

Final answer:

``` id="final-answer"
7
```

---

Another Example:

``` id="example2"
arr = [3, 10, 9, 6, 20, 28]
```

Sorted:

``` id="sorted2"
[3, 6, 9, 10, 20, 28]
```

Initially:

``` id="res-init2"
res = 1
```

First number:

``` id="num3"
3
```

Since:

``` id="3greater"
3 > 1
```

We immediately stop.

Because:

    1 cannot be formed.

Answer:

``` id="answer1"
1
```

---

## 🔁 Logic Flow

    Start
      |
      v
    Sort array
      |
      v
    Initialize:
        res = 1
      |
      v
    Traverse array
      |
      v
    Is current number > res ?
      |
     Yes -------> res is answer
      |
     No
      |
      v
    Extend reachable range:
        res += num
      |
      v
    Continue loop
      |
      v
    Return res

---

## 📊 Complexity

Time Complexity:

``` id="tc"
O(n log n)
```

Reason:

- Sorting takes:

``` id="sorttc"
O(n log n)
```

- Traversal takes:

``` id="looptc"
O(n)
```

Overall:

``` id="overalltc"
O(n log n)
```

---

Space Complexity:

``` id="sc"
O(1)
```

Reason:

    Only constant extra variables are used.

(ignoring sorting space)

---

## 🎯 Key Takeaways

- Sorting is essential.
- Maintain smallest impossible sum.
- Greedy approach works perfectly.
- If current number is larger than `res`,
  then `res` can never be formed.
- Otherwise extend reachable range.

---

## 🔥 Most Important Insight

If we can already form:

``` id="range-important"
1 to res - 1
```

and current number satisfies:

``` id="important-condition"
num <= res
```

then after adding `num` we can form:

``` id="new-range"
1 to res + num - 1
```

This is the entire foundation of the greedy solution.

---

## 🏁 Summary

To solve this problem:

    1. Sort the array.
    2. Maintain:
           res = smallest impossible sum
    3. Traverse elements:
           if num > res:
               stop
           else:
               extend reachable range
    4. Return res.

Efficient solution:

    Time  : O(n log n)
    Space : O(1)