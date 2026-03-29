package Dynamic_Programming.DP_02_Partion_with_given_difference;

import java.util.Arrays;

public class solution {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1};
        int dif = 0;
        System.out.println(countPartitions(arr, dif));

    }

    static int countPartitions(int[] arr, int diff) {
        // code here
        int n = arr.length;
        int total = 0;
        for (int i : arr) {
            total += i;
        }
        if ((total + diff) % 2 != 0 || total < diff) return 0;
        int target = (total + diff) / 2;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        return helper(arr, 0, target, n, 0, dp);

    }

    static int helper(int[] arr, int cur, int target, int n, int i, int[][] dp) {
        if (cur > target) return 0;
        if (i == n) {
            if (cur == target) {
                return 1;
            } else return 0;
        }
        if (dp[i][cur] != -1) return dp[i][cur];

        int pick = helper(arr, cur + arr[i], target, n, i + 1, dp);
        int notPick = helper(arr, cur, target, n, i + 1, dp);

        return dp[i][cur] = notPick + pick;

    }
}
