package Binary_Tree.BT_03_K_sum_paths;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node root = new Node(8);

        root.left = new Node(4);
        root.right = new Node(5);

        root.left.left = new Node(3);
        root.left.right = new Node(2);

        root.right.right = new Node(2);

        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);

        root.left.right.right = new Node(1);
        System.out.println("enter the sum: ");
        int k = sc.nextInt();

        System.out.println(countAllPaths(root, k));

    }

    static int countAllPaths(Node root, int k) {
        // code here
        int[] count = {0};
        int cur_sum = 0;

        Map<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);

        helper(root, k, cur_sum, mp, count);


        return count[0];
    }

    static void helper(Node node, int k, int cur, Map<Integer, Integer> mp, int[] count) {

        if (node == null) return;

        cur += node.data;

        if (mp.containsKey(cur - k)) count[0] += mp.getOrDefault(cur - k, 0);

        mp.put(cur, mp.getOrDefault(cur, 0) + 1);

        helper(node.left, k, cur, mp, count);
        helper(node.right, k, cur, mp, count);

        mp.put(cur, mp.get(cur) - 1);

        if (mp.get(cur) == 0) mp.remove(cur);


    }
}
