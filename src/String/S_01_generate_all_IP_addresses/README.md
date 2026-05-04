# 🌐 Generate All IP Addresses

## 📌 Problem Statement

Given a string `s` containing only digits.

We need to generate all possible valid IP addresses by inserting three dots into the string.

A valid IP address has exactly 4 parts:

    part1.part2.part3.part4

Each part must satisfy:

    1. It must be between 0 and 255.
    2. It cannot have leading zeros.
    3. It must contain at least 1 digit.
    4. It can contain at most 3 digits.

Example:

    Input:
    s = "25525511135"

    Output:
    255.255.11.135
    255.255.111.35

---

## 🎯 Goal

Return all valid IP addresses that can be formed from the given digit string.

---

## 💡 Intuition

An IP address always has exactly 4 parts.

Each part can have length:

    1 digit
    2 digits
    3 digits

So at every step, we try to take:

    1 digit
    2 digits
    3 digits

Then we check whether that part is valid.

If it is valid, we add it to the current answer and recursively build the next part.

This is a classic:

    Backtracking + String Partitioning

problem.

---

## 🔥 Key Idea

At each recursive call, we track:

    index = current position in the string
    part  = how many IP parts have been created
    cur   = current IP address being built
    list  = final answer list

We stop when:

    index reaches the end of string
    and
    part == 4

Then we add the current IP address to the answer.

---

## 🧠 Thought Process

For a valid IP address:

    We must divide the string into exactly 4 parts.

For every part:

    Minimum length = 1
    Maximum length = 3

So from the current index, we try:

    S[index ... index]
    S[index ... index + 1]
    S[index ... index + 2]

For each substring:

    1. Check if it is within string boundary.
    2. Check if it is valid using valid().
    3. If valid, choose it.
    4. Move to the next index.
    5. Increase part count.
    6. Continue recursion.

If at any point:

    part == 4 but string is not fully used

then it is invalid.

If:

    string is fully used but part != 4

then it is also invalid.

Only this condition is accepted:

    index == S.length() && part == 4

---

## 💻 Code

```java
static ArrayList<String> generateIp(String s) {
    ArrayList<String> list = new ArrayList<>();

    if (s.length() > 12) {
        return new ArrayList<>();
    }

    String cur = "";

    helper(s, 0, 0, cur, list);

    return list;
}

static void helper(
    String S,
    int index,
    int part,
    String cur,
    ArrayList<String> list
) {
    int n = S.length();

    if (index == S.length() && part == 4) {
        list.add(cur.substring(0, cur.length() - 1));
        return;
    }

    if (index == S.length() || part == 4) {
        return;
    }

    for (int i = 1; i <= 3; i++) {
        if (index + i <= n && valid(S.substring(index, index + i))) {
            helper(
                S,
                index + i,
                part + 1,
                cur + S.substring(index, index + i) + ".",
                list
            );
        }
    }
}

static boolean valid(String cur) {
    if (cur.charAt(0) == '0' && cur.length() > 1) {
        return false;
    } else {
        int t = Integer.parseInt(cur);
        return t <= 255;
    }
}
```

---

## 🧪 Dry Run

Input:

    s = "25525511135"

Initial call:

    helper("25525511135", index = 0, part = 0, cur = "")

---

Step 1: First part choices from index 0

Possible substrings:

    "2"
    "25"
    "255"

All are valid.

Choose:

    "255"

Current IP:

    cur = "255."

Next call:

    index = 3
    part = 1

---

Step 2: Second part choices from index 3

Remaining string:

    "25511135"

Possible substrings:

    "2"
    "25"
    "255"

All are valid.

Choose:

    "255"

Current IP:

    cur = "255.255."

Next call:

    index = 6
    part = 2

---

Step 3: Third part choices from index 6

Remaining string:

    "11135"

Possible substrings:

    "1"
    "11"
    "111"

All are valid.

Choose:

    "11"

Current IP:

    cur = "255.255.11."

Next call:

    index = 8
    part = 3

---

Step 4: Fourth part choices from index 8

Remaining string:

    "135"

Possible substrings:

    "1"
    "13"
    "135"

Choose:

    "135"

Current IP:

    cur = "255.255.11.135."

Next call:

    index = 11
    part = 4

Now:

    index == s.length()
    part == 4

So this is a valid IP.

Before adding, remove the last dot:

    "255.255.11.135."

becomes:

    "255.255.11.135"

Add to answer:

    list = ["255.255.11.135"]

---

Another valid path:

    First part  = 255
    Second part = 255
    Third part  = 111
    Fourth part = 35

This gives:

    "255.255.111.35"

Final answer:

    ["255.255.11.135", "255.255.111.35"]

---

## 🔁 Logic Flow

    Start
      |
      v
    Create answer list
      |
      v
    If string length > 12, return empty list
      |
      v
    Call helper(index = 0, part = 0, cur = "")
      |
      v
    At each index, try length 1, 2, and 3
      |
      v
    Check if substring is valid
      |
      v
    If valid, add it to current IP with "."
      |
      v
    Move to next index and next part
      |
      v
    If index == length and part == 4
      |
      v
    Remove last dot and add IP to answer
      |
      v
    Return final list

---

## 📊 Complexity

Time Complexity:

    O(3^4)

Reason:

    There are exactly 4 parts.
    For each part, we try at most 3 lengths.

So:

    3 * 3 * 3 * 3 = 81 possibilities

Since IP address always has 4 parts, this is effectively constant.

For practical explanation:

    O(1)

because the maximum valid input length is only 12.

Space Complexity:

    O(1)

Reason:

    Recursion depth is at most 4.
    Each IP address has fixed maximum length.

If counting output storage:

    O(number of valid IP addresses)

---

## 🎯 Key Takeaways

- This is a backtracking problem.
- IP address must have exactly 4 parts.
- Each part can have only 1 to 3 digits.
- A part must be between 0 and 255.
- Leading zeros are not allowed.
- Use recursion to try all valid partitions.
- Remove the last dot before storing the final answer.

---

## 🔥 Most Important Insight

The problem is not about generating random dots.

The correct idea is:

    Build exactly 4 valid parts,
    where each part has length 1 to 3,
    and each part must be between 0 and 255.

---

## 🏁 Summary

To solve this problem:

    1. Start from index 0.
    2. Try substrings of length 1, 2, and 3.
    3. Validate each substring.
    4. Recursively build the next IP part.
    5. Accept only when all digits are used and exactly 4 parts are formed.
    6. Store the IP after removing the last dot.

Efficient solution:

    Time  : O(1)
    Space : O(1)