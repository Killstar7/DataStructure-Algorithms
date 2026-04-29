package Sliding_Window.SW_01_Min_swaps_to_group_1s;

/*

        Min Swaps to Group 1s
        Difficulty: Medium
        You are given a binary array arr[] consisting only of 0s and 1s.
         Determine the minimum number of swaps required to group all the 1s together in a contiguous subarray.

        A swap operation allows you to choose any two indices i and j and exchange their values (i.e., swap(arr[i], arr[j])).

        If the array contains no 1s, return -1.

        Examples:

        Input: arr[] = [1, 0, 1, 0, 1]
        Output: 1
        Explanation: Only 1 swap is required to group all 1's together. Swapping index 1 and 4 will give arr[] = [1, 1, 1, 0, 0].
        Input: arr[] = [1, 0, 1, 0, 1, 1]
        Output: 1
        Explanation: Only 1 swap is required to group all 1's together. Swapping index 0 and 3 will give arr[] = [0, 0, 1, 1, 1, 1].
        Input: arr[] = [0, 0, 0]
        Output: -1
        Explanation: No 1s are present in the array, so return -1.
        Constraints:
        1 ≤ arr.size() ≤ 106
        0 ≤ arr[i] ≤ 1
 */


public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 1};
    }
    static int minSwaps(int[] arr) {
        // code here
        int n = arr.length;
        int count0 = 0;

        for(int i : arr){
            if(i == 0) count0++;
        }
        if(count0 == n) return -1;
        if(count0 == 0) return 0;

        int count1 = n - count0;

        int count = Integer.MAX_VALUE;
        int temp = 0;

        for(int i = 0; i < count1; i++){
            if(arr[i] == 0) temp++;
        }
        count = Math.min(count, temp);
        int left = 0;
        for(int i = count1; i < n; i++){
            if(arr[i] == 0) temp++;
            if(arr[left++] == 0) temp--;

            count = Math.min(count, temp);
        }
        return count;
    }
}
