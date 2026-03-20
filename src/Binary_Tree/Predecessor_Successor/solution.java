package Binary_Tree.Predecessor_Successor;

import java.util.ArrayList;

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
        Node root = new Node(50);

        // Left subtree
        root.left = new Node(30);
        root.left.left = new Node(20);
        root.left.right = new Node(40);

        // Right subtree
        root.right = new Node(70);
        root.right.left = new Node(60);
        root.right.right = new Node(80);

        ArrayList<Node> list = findPreSuc(root, 65);
        System.out.println("ans is " + list.get(0).data + " " + list.get(1).data);

    }

    //Without Recursion
    static ArrayList<Node> findPreSuc(Node root, int key) {
        ArrayList<Node> result = new ArrayList<>();
        Node pre = null, suc = null;
        Node curr = root;

        // Find predecessor
        while (curr != null) {
            if (curr.data < key) {
                pre = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        curr = root;
        // Find successor
        while (curr != null) {
            if (curr.data > key) {
                suc = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        result.add(pre);
        result.add(suc);
        return result;
    }


    //Using Recursion
    static ArrayList<Node> findPreSuc1(Node root, int key) {
        // code here
        ArrayList<Node> list = new ArrayList<>();
        Node[] pre = {null};
        PreS(root, key, pre);
        Node[] post = {null};
        PostS(root, key, post);
        list.add(pre[0]);
        list.add(post[0]);

        return list;
    }

    static void PreS(Node node, int key, Node[] pre) {
        if (node == null) return;

        if (node.data >= key) {

            PreS(node.left, key, pre);
        }
        if (node.data < key) {
            pre[0] = node;
            PreS(node.right, key, pre);
        }
    }

    static void PostS(Node node, int key, Node[] post) {
        if (node == null) return;

        if (node.data <= key) {

            PostS(node.right, key, post);
        }
        if (node.data > key) {
            post[0] = node;
            PostS(node.left, key, post);
        }
    }


}
