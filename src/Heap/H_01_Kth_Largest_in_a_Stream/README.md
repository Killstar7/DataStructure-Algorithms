# 🏆 Kth Largest Element in a Stream

## 📌 Problem Statement

Given an input stream `arr[]` of `n` integers and an integer `k`.

After inserting each element from the stream, find the **Kth largest element**.

If the Kth largest element does not exist yet, return:

    -1

---

## 🎯 Goal

Return a list where each index contains:

    Kth largest element after inserting arr[i]

---

# 💡 Intuition

We need the Kth largest after every insertion.

Instead of sorting again and again, use a heap.

---

# 🔥 Key Idea

Use a **Min Heap of size k**.

The heap will store the current top `k` largest elements.

👉 The smallest element inside this heap is the Kth largest overall.

So:

    heap.peek() = Kth largest element

---

# 🧠 Why Min Heap?

Suppose:

    k = 4

If heap contains the 4 largest elements:

    [3, 4, 5, 6]

Then the smallest among them:

    3

is the 4th largest.

That is why we keep a min heap.

---

# ⚙️ Process

For each element:

1. Add element into heap
2. If heap size becomes greater than `k`:
   remove smallest element
3. If heap size is less than `k`:
   answer = -1
4. Else:
   answer = heap.peek()

---

# 🚀 Code (Function Only)

```java
static ArrayList<Integer> kthLargest(int[] arr, int k) {

    ArrayList<Integer> list = new ArrayList<>();

    PriorityQueue<Integer> queue = new PriorityQueue<>();

    for (int num : arr) {

        queue.add(num);

        if (queue.size() > k) {
            queue.poll();
        }

        if (queue.size() < k) {
            list.add(-1);
        } else {
            list.add(queue.peek());
        }
    }

    return list;
}
```

---

# 🧪 Dry Run

Input:

    arr = [1, 2, 3, 4, 5, 6]
    k = 4

---

## Step-by-step

### Insert 1

    heap = [1]
    size < 4

Answer:

    -1

---

### Insert 2

    heap = [1, 2]
    size < 4

Answer:

    -1

---

### Insert 3

    heap = [1, 2, 3]
    size < 4

Answer:

    -1

---

### Insert 4

    heap = [1, 2, 3, 4]
    heap.peek() = 1

Answer:

    1

---

### Insert 5

    heap before trim = [1, 2, 3, 4, 5]

Remove smallest:

    1

heap contains top 4 largest:

    [2, 4, 3, 5]

heap.peek() = 2

Answer:

    2

---

### Insert 6

    heap before trim = [2, 4, 3, 5, 6]

Remove smallest:

    2

heap contains top 4 largest:

    [3, 4, 6, 5]

heap.peek() = 3

Answer:

    3

---

## Final Output

    [-1, -1, -1, 1, 2, 3]

---

# 🔁 Heap Flow

    Insert new element
        ↓
    Keep heap size ≤ k
        ↓
    If heap size < k → -1
        ↓
    Else heap.peek() = Kth largest

---

# 📊 Complexity

Time:

    O(n log k)

Space:

    O(k)

---

# 🎯 Key Takeaways

- Use Min Heap
- Keep only top `k` largest elements
- Heap top gives Kth largest
- Much better than sorting after every insertion

---

# 🔥 Most Important Insight

    Min Heap of size k stores the k largest elements.
    The smallest among them is the Kth largest.

---

# 🏁 Summary

To solve:

    Maintain min heap of size k
    Add each stream element
    Remove extra smallest
    Return heap.peek() when size == k