package arrays.A_17_flip_to_maximize_1;

/*
Flip to Maximize 1s
Difficulty: Medium
Given an array arr[] consisting of 0’s and 1’s. A flip operation involves changing all 0's to 1's and
 all 1's to 0's within a contiguous subarray. Formally, select a range (l, r) in the array arr[],
 such that (0 ≤ l ≤ r < n) holds and flip the elements in this range.

Return the maximum number of 1's you can get in the array after doing at most 1 flip operation.

Examples :

Input: arr[] = [1, 0, 0, 1, 0]
Output: 4
Explanation: By flipping the subarray from index 1 to 2, the array becomes [1, 1, 1, 1, 0].
This results in a total of 4 ones, which is the maximum possible after at most one flip.
Input: arr[] = [1, 0, 0, 1, 0, 0, 1]
Output: 6
Explanation: By flipping the subarray from index 1 to 5, the array becomes [1, 1, 1, 0, 1, 1, 1].
This results in a total of 6 ones, which is the maximum possible after at most one flip.
Constraints:
1 ≤ n ≤ 106
0 ≤ arr[i] ≤ 1
 */

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 0};
        System.out.println(maxOnes(arr));
    }

    static int maxOnes(int[] arr) {
        // code here
        int n = arr.length;
        int one = 0;
        for (int i : arr) {
            if (i == 1)
                one++;
        }
        int max_len = 0;
        int cur_len = 0;
        for (int i = 0; i < n; i++) {

            if (arr[i] == 0) {
                cur_len += 1;
            } else cur_len += -1;

            if (cur_len < 0) cur_len = 0;
            max_len = Math.max(max_len, cur_len);
        }
        return one + max_len;
    }
}
