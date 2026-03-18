# 🧮 LeetCode 1622 — Fancy Sequence

---

# ❓ Problem (In Simple Words)

You need to design a data structure that supports:

1. `append(val)` → add a number to the sequence
2. `addAll(inc)` → add `inc` to **every element**
3. `multAll(m)` → multiply **every element** by `m`
4. `getIndex(idx)` → return value at index `idx`

---

# 🚨 The Real Challenge

If you try to actually update all elements for every operation:

```text
append → O(1)
addAll → O(n)
multAll → O(n)
```

❌ This will be too slow (TLE)

---

# 💡 Core Idea (VERY IMPORTANT)

Instead of updating each element, we store a **formula**:

[
value = stored \times mul + add
]

👉 Think of it like:

* `stored` = base value
* `mul`, `add` = global transformation applied to everything

---

# 🔁 How Each Operation Works

### 1. append(val)

We want:
[
stored \times mul + add = val
]

So:
[
stored = \frac{val - add}{mul}
]

⚠️ But division is not allowed → we fix it using modular inverse (explained later)

---

### 2. addAll(inc)

Just update:
[
add = add + inc
]

---

### 3. multAll(m)

Update both:
[
mul = mul \times m
]
[
add = add \times m
]

---

### 4. getIndex(idx)

[
value = stored \times mul + add
]

---

# 🧪 Step-by-Step Example (VERY IMPORTANT)

### Input:

```text
append(2)
addAll(3)
append(7)
multAll(2)
getIndex(0)
```

---

### Step 1: append(2)

```text
stored = 2
list = [2]
add = 0, mul = 1
```

---

### Step 2: addAll(3)

```text
add = 3
```

---

### Step 3: append(7)

We reverse transformation:

[
stored = (7 - 3) / 1 = 4
]

```text
list = [2, 4]
```

---

### Step 4: multAll(2)

```text
mul = 2
add = 6
```

---

### Step 5: getIndex(0)

[
2 × 2 + 6 = 10
]

✔ Output = **10**

---

# 🔥 MOST IMPORTANT PART: MODULO

---

# ❓ Why do we use MOD = 10⁹ + 7?

Because numbers grow like this:

```text
2 → +3 → ×2 → ×100000 → ...
```

👉 Numbers become HUGE (overflow)

---

# ✅ Solution

We always keep values within range:

[
x % MOD
]

---

# ⚠️ Important Modulo Rules

---

## ✅ Addition

[
(a + b) % M = ((a % M) + (b % M)) % M
]

Example:

```text
(1000000006 + 5) % MOD = 4
```

---

## ✅ Multiplication

[
(a \times b) % M = ((a % M) \times (b % M)) % M
]

---

## ❌ Division (THIS IS THE PROBLEM)

[
(a / b) % M \neq (a % M) / (b % M)
]

---

### ❌ Example (WRONG)

```text
6 / 2 = 3

But:
(6 % 5) / 2 = 1 / 2 ❌ invalid
```

---

# ✅ Solution: Modular Inverse

Instead of dividing by `b`, we multiply by its inverse:

[
\frac{a}{b} \mod M = a \times b^{-1} \mod M
]

---

# ⚡ How to Find Inverse?

Since MOD is prime:

[
b^{-1} = b^{MOD-2} \mod MOD
]

---

# 🧪 Example

Find:

```text
4 / 2 mod MOD
```

Step 1:

```text
inverse(2) = 500000004
```

Step 2:

```text
4 × 500000004 % MOD = 2
```

✔ Correct!

---

# 🧠 Applying This to append()

We need:
[
stored = (val - add) / mul
]

Replace division:

[
stored = (val - add) \times mul^{-1}
]

---

# ⚠️ Handling Negative Values

```text
val - add can be negative
```

Fix:
[
((val - add) % MOD + MOD) % MOD
]

---

# 🧩 Final Code

```java
class Fancy {
    ArrayList<Long> list;
    long MOD = 1000000007L;
    long add = 0;
    long mul = 1;

    public Fancy() {
        list = new ArrayList<>();
    }

    public void append(int val) {
        long v = ((val - add) % MOD + MOD) % MOD;
        list.add(v * modInverse(mul) % MOD);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;
        return (int)((list.get(idx) * mul % MOD + add) % MOD);
    }

    long modInverse(long x) {
        return power(x, MOD - 2);
    }

    long power(long x, long y) {
        long res = 1;
        x %= MOD;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            y >>= 1;
        }
        return res;
    }
}
```

---

# 🎯 What You Should Learn From This

---

## 🧠 Concepts Used

* Lazy transformation (very important pattern)
* Modular arithmetic
* Modular inverse
* Fast exponentiation

---

## 🚀 Key Insight

👉 Instead of updating all elements:

* Store **base values**
* Apply transformation **only when needed**

---

## ❌ What Beginners Do

* Update whole array → TLE
* Use normal division → WRONG
* Ignore modulo → overflow

---

## ✅ What You Did

* Used transformation ✔
* Used modular inverse ✔
* Optimized to O(1) ✔

---

# 🏁 Final Intuition (Remember This)

> You are not storing real values.
> You are storing values in a **mathematical system**
> and reconstructing them using formulas.

---

