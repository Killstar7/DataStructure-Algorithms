# 🔁 Create Palindrome String from Unique Characters

## 📌 Problem Statement

Given a string `s`, create a **palindrome string** using:

- Only the **unique characters** present in `s`
- Characters should appear **once in first half**
- Then mirror to form a palindrome

---

## 🎯 Goal

Return a palindrome string built from **distinct characters of input**

---

## 💡 Intuition

We don't care about frequency ❌  
We only care about **unique characters** ✅

Steps:

1. Extract unique characters
2. Build first half
3. Mirror it to create palindrome

---

## 🧠 Thought Process

---

### 🔹 Step 1: Mark Unique Characters

Use array:

    int[26] → for 'a' to 'z'

Mark presence:

    chars[c - 'a'] = 1

---

### 🔹 Step 2: Build First Half

Loop from `a → z`

If character exists:

    add to string

Example:

    s = "abcdabcd"
    unique = "abcd"

---

### 🔹 Step 3: Mirror

Take first half:

    "abcd"

Mirror it:

    "abcd" + "dcba"

Final:

    "abcddcba"

---

## 🚀 Code (Function Only)

```java
static String f(String s){

    int n = s.length();

    int[] chars = new int[26];

    // Step 1: Mark unique characters
    for (int i = 0; i < n; i++) {
        char c = s.charAt(i);
        chars[c - 'a'] = 1;
    }

    // Step 2: Build first half
    String ans = "";

    for (int i = 0; i < 26; i++) {
        if (chars[i] == 1){
            char c = (char)(i + 'a');
            ans += c;
        }
    }

    // Step 3: Mirror
    int idx = ans.length() - 1;

    while (idx >= 0){
        ans += ans.charAt(idx);
        idx--;
    }

    return ans;
}
```

---

## 🧪 Dry Run

Input:

    s = "abcdabcd"

---

Step 1:

    unique chars → a, b, c, d

---

Step 2:

    first half = "abcd"

---

Step 3:

    mirror = "dcba"

---

Final:

    "abcddcba"

---

## 📊 Complexity

Time:  O(n + 26) ≈ O(n)  
Space: O(26) → O(1)

---

## 🎯 Key Takeaways

- Use array for fast uniqueness check
- Build half → mirror it
- Works because palindrome = symmetric

---

## 🏁 Summary

Extract unique characters → build string → mirror it → get palindrome