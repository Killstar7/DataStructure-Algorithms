package Binary_Tree.BT_04_Burning_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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


    }
    static int d = 0;
//    static int minTime(Node root, int target) {
//        // code here
//        Node node = find(root, target);
//        int down_height = height(node);
//
//    }

    //finding the height
    static int height(Node root){
        if (root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        return 1 + Math.max( lh, rh);
    }

    //finding the diameter
    static int diameter(Node node) {
        if (node == null)
            return 0;

        int leftHeight = diameter(node.left);
        int rightHeight = diameter(node.right);

        d = Math.max(d, leftHeight + rightHeight);

        // Return height
        return 1 + Math.max(leftHeight, rightHeight);
    }

    //finding the element
    static Node find(Node node, int target) {

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.data == target) {
                return cur;
            }
            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);

        }
        return null;
    }

}
