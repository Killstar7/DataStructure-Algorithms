# 🔺 Angles of a Triangle (Using Law of Cosines)

## 📌 Problem Statement

You are given an array:

```
sides = [a, b, c]
```

👉 These represent the **lengths of 3 sides of a triangle**

---

## 🎯 Goal

Return the **3 internal angles (in degrees)** of the triangle

👉 If triangle is **not valid**, return empty array

---

# ⚠️ Triangle Validity Check

A triangle is valid if:

```
a + b > c
a + c > b
b + c > a
```

---

# 💡 Intuition

We are given **all 3 sides**

👉 So we use:

# 🧠 Law of Cosines

---

## 🔹 Formula

:contentReference[oaicite:0]{index=0}

---

## 🔹 To find angle:

```
cos(C) = (a² + b² - c²) / (2ab)
```

---

👉 Then:

```
C = acos(value)
```

👉 Convert to degrees:

```
C = degrees(acos(value))
```

---

# 🔁 Apply for All Angles

---

## 🔹 Angle opposite side `a`

```
angleA = acos((b² + c² - a²) / (2bc))
```

---

## 🔹 Angle opposite side `b`

```
angleB = acos((a² + c² - b²) / (2ac))
```

---

## 🔹 Angle opposite side `c`

```
angleC = acos((a² + b² - c²) / (2ab))
```

---

# 🚀 Code (Function Only)

```java
static double[] internalAngles(int[] sides) {

    int a = sides[0];
    int b = sides[1];
    int c = sides[2];

    if (!(a + b > c && a + c > b && b + c > a)) {
        return new double[0];
    }

    double angleA = Math.acos((b*b + c*c - a*a) / (2.0 * b * c));
    double angleB = Math.acos((a*a + c*c - b*b) / (2.0 * a * c));
    double angleC = Math.acos((a*a + b*b - c*c) / (2.0 * a * b));

    angleA = Math.toDegrees(angleA);
    angleB = Math.toDegrees(angleB);
    angleC = Math.toDegrees(angleC);

    double[] ans = {angleA, angleB, angleC};

    Arrays.sort(ans);

    return ans;
}
```

---

# 🧪 Dry Run Example

## Input:
```
sides = [3, 4, 5]
```

---

## Calculation:

```
angleA ≈ 37°
angleB ≈ 53°
angleC ≈ 90°
```

---

## Output:
```
[37, 53, 90]
```

---

# 📊 Complexity

| Type  | Complexity |
|-------|------------|
| Time  | O(1) |
| Space | O(1) |

---

# 🎯 Key Takeaways

- Use triangle inequality to validate
- Apply Law of Cosines
- Convert radians → degrees
- Sort result if required

---

# 🏁 Summary

> Given 3 sides → use Law of Cosines to compute all angles.

---

# 📚 Related Topics

- Geometry
- Trigonometry
- Mathematical Computation  