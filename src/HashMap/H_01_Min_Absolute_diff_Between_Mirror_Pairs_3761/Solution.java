package HashMap.H_01_Min_Absolute_diff_Between_Mirror_Pairs_3761;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {

        int[] nums = {12,21,45,33,54};
        int ans = minMirrorPairDistance(nums);
        System.out.println(ans);
    }
    static int minMirrorPairDistance(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                min = Math.min(min, i - map.get(nums[i]));
            }
            int r = reverse(nums[i]);
            map.put(r, i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    static int reverse(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10; // Extract last digit
            reversed = reversed * 10 + digit; // Append to result
            num /= 10; // Remove last digit
        }
        return reversed;
    }
}
