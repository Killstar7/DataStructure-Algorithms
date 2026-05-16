package arrays.A_24_Not_a_subset_sum;

import java.util.Arrays;
/*
Not a subset sum
Difficulty: MediumAccuracy: 45.92%Submissions: 89K+Points: 4
Given a array arr[] of positive integers, find the smallest positive integer such that
 it cannot be represented as the sum of elements of any subset of the given array set.

Examples:

Input: arr[] = [3, 1, 2]
Output: 7
Explanation: 7 is the smallest positive number for which no subset is there with sum 7.
Input: arr[] = [3, 10, 9, 6, 20, 28]
Output: 1
Explanation: 1 is the smallest positive number for which no subset is there with sum 1.
Constraints
1 ≤ arr.size() ≤ 105
1 ≤ arr[i] ≤ 103
 */

public class Solution {
    public static void main(String[] args) {

    }
    static int findSmallest(int[] arr) {
        Arrays.sort(arr);

        int res = 1; // smallest sum we cannot make yet

        for (int num : arr) {
            if (num > res) {
                break;
            }

            res += num;
        }

        return res;
    }
}
