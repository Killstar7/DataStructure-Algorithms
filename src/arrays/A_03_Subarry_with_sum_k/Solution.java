package arrays.A_03_Subarry_with_sum_k;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {10, 2, -2, -20, 10};

    }

    static int cntSubarrays(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        int pre_sum = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pre_sum += arr[i];

        }

        return count;
    }
}
