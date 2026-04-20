package Dynamic_Programming.DP_11_Count_derangements;

import java.util.Arrays;

/*
Count Derangements
Difficulty: Medium

    Given a number n, find the total number of Derangements of elements from 1 to n. A Derangement is a permutation of n elements,
    such that no element appears in its original position, i.e., 1 should not be the first element, 2 should not be second,
    etc. For example, [5, 3, 2, 1, 4] is a Derangement of first 5 elements.

    Note: The answer will always fit into a 32-bit integer.

    Examples:

    Input: n = 2
    Output: 1
    Explanation: For [1, 2], there is only one possible derangement: [2, 1].
    Input: n = 3
    Output: 2
    Explanation: For the set [1, 2, 3], there are only two possible derangements: [2, 3, 1] and [3, 1, 2].
    Constraints:
    1 ≤ n ≤ 12

 */
public class Solution {
    public static void main(String[] args) {
        int n = 5;

    }
/*
******************************************************-Brute-Force-******************************************************
*/
    static int bruteforce(int n){
        if(n == 1) return 0;
        if(n == 2) return 1;

        return (n - 1) *(bruteforce(n - 1) + bruteforce(n - 2));
    }
/*
*******************************************************-Memoization-****************************************************
 */
    static int memoization(int n){
        // code here
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);

        return helper(n, dp);
    }
    static int helper(int n , int[] dp){
        if(n == 1) return 0;
        if(n == 2) return 1;
        if(dp[n] != -1) return dp[n];

        return dp[n] = (n - 1) *(helper(n - 1, dp) + helper(n - 2, dp));
    }

/*
******************************************************--Tabulation--****************************************************
 */
    static int tabulation(int n){
        if(n == 1) return 0;
        int[] dp = new int[n + 1];

        Arrays.fill(dp, -1);
        dp[1] = 0;
        dp[2] = 1;
        for(int i = 3; i <= n; i++){

            dp[i] = (i - 1) *(dp[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }
/*
******************************************************Space-Optimized***************************************************
 */
    static int spaceOptimization(int n){
        if(n == 1) return 0;

        int prev2 = 0;
        int prev1 = 1;
        int cur = 1;
        for(int i = 3; i <= n; i++){

            cur = (i - 1) *(prev1 + prev2);
            prev2 = prev1;
            prev1 = cur;
        }
        return cur;
    }
}
