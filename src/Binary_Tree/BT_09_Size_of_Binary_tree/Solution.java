package Binary_Tree.BT_09_Size_of_Binary_tree;

/*
        Size of Binary Tree
        Difficulty: BasicAccuracy: 82.91%Submissions: 77K+Points: 1
        Given the root of a binary tree, return the size of the tree. The size of a binary tree is the total number of nodes in the tree.

        Examples:

        Input:

        Output:  3
        Explanation: There are 3 nodes in the given binary tree, so its size is 3.
        Input:

        Output: 6
        Explanation: There are 6 nodes in the given binary tree, so its size is 6.
        Constraints:
        1 ≤ number of nodes ≤ 105
        1 ≤ node->data ≤ 105
 */


class Node
{
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    int getSize(Node root) {
        // code here
        return size(root);
    }
    static int size(Node node){
        if(node == null) return 0;

        int left = size(node.left);
        int right = size(node.right);

        return 1 + left + right;
    }
}
