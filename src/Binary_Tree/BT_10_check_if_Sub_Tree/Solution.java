package Binary_Tree.BT_10_check_if_Sub_Tree;
/*
    Check if subtree Q

    Difficulty: Mediu
    Given two binary trees with roots root1 (for tree T) and root2 (for tree S), each containing at most N nodes.
    Determine whether tree S is a subtree of tree T.

    Return true if S is a subtree of T, otherwise return false.

    Note: A tree S is considered a subtree of T if there exists a node in T such that the subtree rooted at that node is identical to S.
     Two trees are identical if they have the same structure and the same node values.

    Examples:

    Input: root1 = [1, 2, 3, N, N, 4], root2 = [3, 4]

    Output: true
    Explanation: In the tree rooted at root1, the subtree starting at node 3 is identical to the tree rooted at root2
    (same structure and node values). Hence, root2 is a subtree of root1, so the output is true.
    Input: root1 = [26, 10, N, 20, 30, 40, 60], root2 = [26, 10, N, 20, 30, 40, 60]

    Output: true
    Explanation: Both root1 and root2 represent identical trees. So, root2 is a subtree of root1, and the output is true.
    Constraints:
    1 ≤ n ≤ 103
    1 ≤ value of nodes ≤ 104


 */

class Node {
    int data;
    Node left;
    Node right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
public class Solution {
    public static void main(String[] args) {

    }
    boolean isSubTree(Node root1, Node root2) {
        // code here
        return check(root1, root2);

    }
    static boolean check(Node source, Node target){
        if(source == null) return false;

        if(source.data == target.data){
            if(traverse(source, target))
                return true;
        }
        if(check(source.left, target)) return true;
        if(check(source.right, target)) return true;

        return false;
    }
    static boolean traverse(Node source, Node target){
        if(source == null && target == null) return true;
        if(source == null || target == null) return false;

        if(source.data != target.data)
            return false;

        boolean left = traverse(source.left, target.left);
        boolean right = traverse(source.right, target.right);

        return left && right;
    }
}
