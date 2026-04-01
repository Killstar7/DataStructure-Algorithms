# 🤖 Robot Collisions (Simulation using Stack)

## 📌 Problem Statement

You are given:

- `positions[]` → positions of robots on a line
- `healths[]` → health of each robot
- `directions` → string where:
    - `'L'` = moving left
    - `'R'` = moving right

### ⚔️ Collision Rules:

1. Robots move simultaneously.
2. If a robot moving right meets a robot moving left → **collision occurs**.
3. After collision:
    - Robot with **higher health survives**, but loses 1 health.
    - Robot with **lower health dies**.
    - If both have equal health → **both die**.

---

## 🎯 Goal

Return the **healths of robots that survive**, in the **original order**.

---

## 💡 Intuition

- Collisions only happen when:
  ```
  Right-moving robot (R) comes before Left-moving robot (L)
  ```
- This is similar to:
  👉 **"Asteroid Collision Problem"**

---

## 🧠 Thought Process

### 🔹 Why Sorting?

- Robots are given in random positions.
- To simulate movement correctly → process from **left to right**.
- So we sort robots by **position**.

---

### 🔹 Why Stack?

- Stack stores **right-moving robots (R)**.
- When a left-moving robot (L) comes:
    - It may collide with **previous R robots**.

---

### 🔹 Key Idea

```
R → push into stack
L → collide with stack top (if R exists)
```

---

## ⚙️ Approach

1. Store robot data:
    - position
    - health
    - index
    - direction

2. Sort by position

3. Use stack:
    - Push all `R` robots
    - For each `L` robot:
        - Fight with stack top
        - Continue until:
            - stack empty OR
            - L robot dies

4. Store survivors in result

---

## 🔥 Collision Handling Logic

Inside loop:

### Case 1: Current robot = 'R'
```java
st.push(cur);
```

---

### Case 2: Current robot = 'L'

```java
while (!stack empty AND top is 'R')
```

Three cases:

---

### ✅ Case A: Left robot stronger

```java
cur.health > top.health
```

- Pop top robot
- Decrease current health by 1
- Continue fighting

---

### ❌ Case B: Right robot stronger

```java
cur.health < top.health
```

- Decrease top health
- Current robot dies
- Stop

---

### 💥 Case C: Equal health

```java
cur.health == top.health
```

- Both robots die
- Stop

---

## 🚀 Code (Java)

```java

    static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;

        List<values> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new values(positions[i], healths[i], i, directions.charAt(i)));
        }

        list.sort(Comparator.comparing(v -> v.position));

        Stack<values> st = new Stack<>();

        int[] temp = new int[n];
        Arrays.fill(temp, -1);

        int idx = 0;

        while (idx < n) {

            values cur = list.get(idx);

            if (cur.direction == 'R') {
                st.push(cur);
            } else {

                boolean survives = true;

                while (!st.isEmpty() && st.peek().direction == 'R') {

                    values top = st.peek();

                    if (cur.health > top.health) {
                        st.pop();
                        cur.health--;

                    } else if (cur.health < top.health) {
                        top.health--;
                        survives = false;
                        break;

                    } else {
                        st.pop();
                        survives = false;
                        break;
                    }
                }

                if (st.isEmpty() && survives) {
                    temp[cur.index] = cur.health;
                }
            }

            idx++;
        }

        while (!st.isEmpty()) {
            values value = st.pop();
            temp[value.index] = value.health;
        }

        List<Integer> result = new ArrayList<>();

        for (int i : temp) {
            if (i != -1) result.add(i);
        }

        return result;
    }

    static class values {
        int position;
        int health;
        int index;
        char direction;

        values(int position, int health, int index, char direction) {
            this.position = position;
            this.health = health;
            this.index = index;
            this.direction = direction;
        }
    }
}
```

---

## 🧪 Dry Run Example

### Input:

```
positions = [1, 2, 5, 6]
healths  = [10,10,11,11]
directions = "RLRL"
```

### Step-by-step:

1. Sort by position:
```
(1,R,10), (2,L,10), (5,R,11), (6,L,11)
```

---

### 🔁 Simulation:

- Robot 1 (R) → push stack
- Robot 2 (L) → collide with R
    - equal health → both die

---

- Robot 3 (R) → push
- Robot 4 (L) → collide
    - equal health → both die

---

### Output:

```
(no survivors)
```

---

## 📊 Complexity Analysis

| Type  | Complexity |
|-------|------------|
| Time  | O(n log n) (sorting) |
| Space | O(n) |

---

## 🎯 Key Takeaways

- Use **sorting + stack simulation**
- Only **R vs L** collisions matter
- Similar to:
  👉 Asteroid Collision Problem
- Stack helps simulate **previous interactions efficiently**

---

## 🏁 Summary

> Sort robots → simulate collisions using stack → return surviving healths

---

## 📚 Related Topics

- Stack Problems
- Simulation Problems
- Greedy Approach
- Asteroid Collision  