package Binary_Tree.BT_08_check_if_an_arr_is_max_heap;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {90, 15, 10, 7, 12, 2};
        System.out.println(isMaxHeap(arr));
    }
    static boolean isMaxHeap(int[] arr) {
        // code here

        for (int i = 1, n = arr.length; i < n; i++) {
            int par = arr[(i - 1) / 2];
            int cur = arr[i];
            if (cur > par){
                return false;
            }

        }
        return true;
    }
}
