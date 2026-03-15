package Binary_Tree.BT_02_VerticalTreeTraversal;

import java.util.*;

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
//        Node root = new Node(1);
//
//        root.left = new Node(2);
//        root.right = new Node(3);
//
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//
//        root.left.right.right = new Node(8);
//
//        root.right.left.right = new Node(9);
//
//        root.right.right.right = new Node(10);
//
//        root.left.right.right.left = new Node(11);
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.right = new Node(6);


        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        list=verticalOrder(root);
        System.out.println("ans is:");
        System.out.println(list);



    }
    static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        // code here
        int left_l=0;
        int right_l=0;
        ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        Map<Integer,ArrayList<Integer>> mp=new HashMap<>();
        Queue<pair> q=new LinkedList<>();
        int vertical=0;
        q.add(new pair(root,vertical));
        while (!q.isEmpty()){
            int s=q.size();

            for (int i = 0; i < s; i++) {
                pair pair=q.poll();
                mp.putIfAbsent(pair.second, new ArrayList<>());
                mp.get(pair.second).add(pair.first.data);
                if (pair.first.left!=null) {
                    q.add(new pair(pair.first.left, pair.second- 1));
                    left_l=Math.min(left_l, pair.second-1);
                }
                if (pair.first.right!=null) {
                    q.add(new pair(pair.first.right, pair.second + 1));
                    right_l=Math.max(right_l, pair.second+1);
                }
            }
        }
        for (int i=left_l;i<=right_l;i++){
            list.add(new ArrayList<>(mp.get(i)));
        }
        return list;

    }


    static class pair {
        Node first;//stores the node
        int second;//stores the vertical distance

        pair(Node first, int second) {
            this.first = first;
            this.second = second;
        }
    }

}