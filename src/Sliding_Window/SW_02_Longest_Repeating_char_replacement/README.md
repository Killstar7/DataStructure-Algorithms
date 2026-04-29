# 🔁 Longest Repeating Character Replacement

## 📌 Problem Statement

Given a string `s` containing only uppercase English letters and an integer `k`.

You can perform at most `k` operations.

In one operation:

    Change any character to any other uppercase character

---

## 🎯 Goal

Find the length of the longest substring that can be converted into a string with all same characters using at most `k` changes.

---

# 💡 Intuition

For any substring:

    Length of substring = window size

To make all characters same:

    Keep the most frequent character
    Replace all other characters

So required changes:

    window_size - max_frequency

If:

    window_size - max_frequency <= k

Then this window is valid.

---

# 🔥 Key Formula

    characters_to_replace = window_length - max_frequency

Valid window condition:

    window_length - max_frequency <= k

---

# 🧠 Thought Process

Use sliding window:

1. Expand right pointer
2. Count frequency of characters
3. Track max frequency in current window
4. If replacement needed > k:
   shrink from left
5. Update answer

---

# ✅ Approach 1: Sliding Window with Recalculating Max Frequency

---

## 💡 Idea

Every time window changes, calculate max frequency again by scanning 26 letters.

Since only uppercase letters exist:

    26 scan is constant

So complexity is still approximately:

    O(n * 26) ≈ O(n)

---

## 🚀 Code (Function Only)

```java
static int longestSubstr(String s, int k) {

    int n = s.length();

    int[] freq = new int[26];

    int ans = 0;
    int max_freq = 0;

    int left = 0;
    int right = 0;

    while (right < n) {

        freq[s.charAt(right) - 'A']++;

        max_freq = getmax(freq);

        while ((right - left + 1) - max_freq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;

            max_freq = getmax(freq);
        }

        ans = Math.max(ans, right - left + 1);

        right++;
    }

    return ans;
}

static int getmax(int[] freq) {

    int max_f = 0;

    for (int f : freq) {
        max_f = Math.max(max_f, f);
    }

    return max_f;
}
```

---

# ⚡ Approach 2: Optimized Sliding Window

---

## 💡 Idea

Instead of recalculating max frequency every time:

    max_freq = max(max_freq, current character frequency)

---

## ❓ Why don't we decrease max_freq when left moves?

Because `max_freq` represents the best frequency seen in current/previous valid windows.

Even if it becomes slightly stale:

    It does not affect correctness of maximum answer

Why?

- We only need the maximum length
- A stale max_freq may delay shrinking
- But it never creates a better answer than a real valid window already seen

This is a common optimized sliding window trick.

---

## 🚀 Code (Function Only)

```java
static int longestSubstr1(String s, int k) {

    int n = s.length();

    int[] freq = new int[26];

    int ans = 0;
    int max_freq = 0;

    int left = 0;
    int right = 0;

    while (right < n) {

        freq[s.charAt(right) - 'A']++;

        max_freq = Math.max(max_freq, freq[s.charAt(right) - 'A']);

        while ((right - left + 1) - max_freq > k) {
            freq[s.charAt(left) - 'A']--;
            left++;
        }

        ans = Math.max(ans, right - left + 1);

        right++;
    }

    return ans;
}
```

---

# 🧪 Dry Run

Input:

    s = "ABBA"
    k = 2

---

Window expansion:

    A
    max_freq = 1
    changes = 1 - 1 = 0
    valid

---

    AB
    max_freq = 1
    changes = 2 - 1 = 1
    valid

---

    ABB
    max_freq = 2
    changes = 3 - 2 = 1
    valid

---

    ABBA
    max_freq = 2
    changes = 4 - 2 = 2
    valid

---

Answer:

    4

---

# 🧪 Another Example

Input:

    s = "ADBD"
    k = 1

---

Best substring:

    DBD

Most frequent char:

    D appears 2 times

Changes needed:

    3 - 2 = 1

Convert:

    DBD → DDD

Output:

    3

---

# 🔁 Sliding Window Flow

    Expand right
        ↓
    Update frequency
        ↓
    Track max frequency
        ↓
    Check:
        window_length - max_freq > k
        ↓
    If invalid → shrink left
        ↓
    Update answer

---

# 📊 Complexity

## Approach 1

Time:

    O(n * 26) ≈ O(n)

Space:

    O(26) = O(1)

---

## Approach 2

Time:

    O(n)

Space:

    O(26) = O(1)

---

# 🎯 Key Takeaways

- This is a sliding window problem
- Keep the most frequent character
- Replace the rest
- Condition:

      window_length - max_frequency <= k

- Optimized version avoids recalculating max frequency

---

# 🔥 Most Important Insight

    To make a substring same:
    replacements needed = window length - frequency of most common character

---

# 🏁 Summary

To solve:

    Use sliding window
    Track character frequency
    Track max frequency
    Shrink only when replacements exceed k