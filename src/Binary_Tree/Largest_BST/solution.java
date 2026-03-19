package Binary_Tree.Largest_BST;

import java.util.zip.Checksum;

class Node {
    int data;
    Node left, right;

    Node(int val) {
        this.data = val;
        this.left = null;
        this.right = null;
    }
}

public class solution {
    public static void main(String[] args) {
        Node root = new Node(5);

        root.left = new Node(2);
        root.right = new Node(4);

        root.left.left = new Node(1);
        root.left.right = new Node(3);

        System.out.println(largestBst(root));

    }

    static int largestBst(Node root) {
        // Write your code here
       return helper(root).count;

    }

    static check helper(Node node) {
    if(node == null)
        return new check(0, Integer.MIN_VALUE,Integer.MAX_VALUE);

    check left = helper(node.left);
    check right = helper(node.right);

    if(node.data > left.max && node.data < right.min)
        return new check(left.count + right.count + 1, Math.max(right.max, node.data),Math.min(node.data,left.min));

    return new check(Math.max(left.count, right.count), Integer.MAX_VALUE,Integer.MIN_VALUE);

    }

    static class check {
        int max;
        int min;
        int count;

        check(int count, int max, int min) {
            this.max = max;
            this.min = min;
            this.count = count;
        }
    }


}
