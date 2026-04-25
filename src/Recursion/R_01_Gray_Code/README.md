# 🔢 Gray Code (Recursion + Bit Manipulation)

## 📌 Problem Statement

Given an integer `n`, generate all **n-bit Gray Codes**.

---

## 🎯 Goal

Return all binary strings of length `n` such that:

    Only one bit changes between consecutive values

---

## 💡 What is Gray Code?

Gray Code is a sequence where:

    Adjacent values differ by exactly 1 bit

---

## 🧪 Example (n = 3)

    000
    001
    011
    010
    110
    111
    101
    100

---

# 🔥 Approach 1: Recursion + Reflection

---

## 💡 Intuition

To build n-bit Gray Code:

1. Generate (n-1)-bit Gray Code
2. Prefix '0' to all values
3. Reverse the list
4. Prefix '1' to reversed values

---

## 🧠 Why Reverse?

Without reverse, more than 1 bit may change

Reverse ensures:

    Only one bit changes at the junction

---

## 🔁 Construction Logic

Example:

    n = 2
    00
    01
    11
    10

Now build n = 3:

    Prefix 0:
    000
    001
    011
    010

    Prefix 1 (reverse):
    110
    111
    101
    100

---

## 🚀 Code (Recursion)

```java
static ArrayList<String> graycode(int n) {
    return helper(n);
}

static ArrayList<String> helper(int n){

    if (n == 1){
        ArrayList<String> temp = new ArrayList<>();
        temp.add("0");
        temp.add("1");
        return temp;
    }

    ArrayList<String> cur = helper(n - 1);
    ArrayList<String> ans = new ArrayList<>();

    for (int i = 0; i < cur.size(); i++){
        ans.add("0" + cur.get(i));
    }

    for (int i = cur.size() - 1; i >= 0; i--){
        ans.add("1" + cur.get(i));
    }

    return ans;
}
```

---

## 🔁 Recursion Flow

    helper(3)
      → helper(2)
          → helper(1)

Then builds upward:

    n=1 → base
    n=2 → from n=1
    n=3 → from n=2

---

# ⚡ Approach 2: Bit Manipulation

---

## 💡 Intuition

Use direct formula:

    gray = i ^ (i >> 1)

Where:

- `i` = number
- `>> 1` = right shift
- `^` = XOR

---

## 🧠 Why This Works

Each Gray Code bit:

    gray[i] = binary[i] XOR binary[i-1]

This ensures:

    Only one bit changes between consecutive numbers

---

## 🚀 Code (Bit Manipulation)

```java
static ArrayList<String> graycodeBitManipulation(int n) {

    ArrayList<String> ans = new ArrayList<>();

    int total = 1 << n; // 2^n

    for (int i = 0; i < total; i++) {

        int gray = i ^ (i >> 1);

        String binary = Integer.toBinaryString(gray);

        while (binary.length() < n) {
            binary = "0" + binary;
        }

        ans.add(binary);
    }

    return ans;
}
```

---

## 🧪 Dry Run (Bit Method)

Input:

    n = 3

Compute:

    total = 8

Table:

    i    i>>1   XOR    Gray
    0    000    000    000
    1    000    001    001
    2    001    011    011
    3    001    010    010
    4    010    110    110
    5    010    111    111
    6    011    101    101
    7    011    100    100

---

# ⚖️ Comparison

| Approach | Idea | Complexity | Use |
|----------|------|-----------|-----|
| Recursion | Reflection | O(2^n) | Conceptual |
| Bit Manipulation | Formula | O(2^n) | Best |

---

# 📊 Complexity

Time:

    O(2^n)

Space:

    O(2^n)

---

# 🎯 Key Takeaways

- Gray Code ensures **1-bit difference**
- Recursive method uses **reflection**
- Bit method uses:

  gray = i ^ (i >> 1)

- Bit method is shorter and preferred in interviews

---

# 🔥 Most Important Insight

    Gray Code = original + reverse(original)

AND

    gray = i ^ (i >> 1)

---

# 🏁 Summary

To generate Gray Code:

- Use recursion + reflection (intuitive)
- OR use bit manipulation (optimal)

👉 Best answer in interviews:

    Use bit manipulation