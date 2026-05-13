package arrays.A_22_Valid_elements_in_an_array_3912;

import java.util.ArrayList;
import java.util.List;
/*
        3912. Valid Elements in an Array

        Easy

        You are given an integer array nums.

        An element nums[i] is considered valid if it satisfies at least one of the following conditions:

        It is strictly greater than every element to its left.
        It is strictly greater than every element to its right.
        The first and last elements are always valid.

        Return an array of all valid elements in the same order as they appear in nums.

        Example 1:

        Input: nums = [1,2,4,2,3,2]

        Output: [1,2,4,3,2]

        Explanation:

        nums[0] and nums[5] are always valid.
        nums[1] and nums[2] are strictly greater than every element to their left.
        nums[4] is strictly greater than every element to its right.
        Thus, the answer is [1, 2, 4, 3, 2].
        Example 2:

        Input: nums = [5,5,5,5]

        Output: [5,5]

        Explanation:

        The first and last elements are always valid.
        No other elements are strictly greater than all elements to their left or to their right.
        Thus, the answer is [5, 5].
        Example 3:

        Input: nums = [1]

        Output: [1]

        Explanation:

        Since there is only one element, it is always valid. Thus, the answer is [1].



        Constraints:

        1 <= nums.length <= 100
        1 <= nums[i] <= 100
 */

public class Solution {
    public static void main(String[] args) {

    }
    static List<Integer> findValidElements(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        int[] left = new int[n];
        left[0] = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++){
            left[i] = Math.max(left[i - 1], nums[i - 1]);

        }

        int[] right = new int[n];
        right[n - 1] = Integer.MIN_VALUE;
        for(int i = n -2; i >= 0; i--){
            right[i] = Math.max(right[i + 1], nums[i + 1]);

        }

        for(int i = 0; i < n; i++){
            if(i == 0 ||  i == n - 1 || nums[i] > left[i] || nums[i] > right[i]){
                list.add(nums[i]);
            }
        }
        return list;
    }
}
