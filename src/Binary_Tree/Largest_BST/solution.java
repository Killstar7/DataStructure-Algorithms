package Binary_Tree.Largest_BST;

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

    static int largestBst(Node root) {
        // Write your code here
        return largestBSTBT(root).mxSz;



    }
    static BSTInfo largestBSTBT(Node root) {
        if (root == null)
            return new BSTInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        BSTInfo left = largestBSTBT(root.left);
        BSTInfo right = largestBSTBT(root.right);

        // Check if the current subtree is a BST
        if (left.maxi < root.data && right.mini > root.data) {
            return new BSTInfo(Math.min(left.mini, root.data),
                    Math.max(right.maxi, root.data),
                    1 + left.mxSz + right.mxSz);
        }

        return new BSTInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.mxSz, right.mxSz));
    }

        static class BSTInfo {
            int mini;
            int maxi;
            int mxSz;

            BSTInfo(int mn, int mx, int sz) {
                mini = mn;
                maxi = mx;
                mxSz = sz;
            }
        }

}
