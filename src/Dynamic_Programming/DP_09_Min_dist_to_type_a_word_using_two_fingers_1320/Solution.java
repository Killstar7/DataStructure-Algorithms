package Dynamic_Programming.DP_09_Min_dist_to_type_a_word_using_two_fingers_1320;

public class Solution {
    public static void main(String[] args) {

        String word = "CAKE";
        System.out.println(minimumDistance(word));
    }

    static int minimumDistance(String word) {
        int n = word.length();

        int[][][] dp = new int[n][27][27];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 26; j++) {
                for (int k = 0; k <= 26; k++) {
                    dp[i][j][k] = -1;
                }
            }

        }

//        return help(word, 0, 0, 0, dp);
        return Tabulation(word);

    }
    //Recursion + Memoization
    static int help(String word, int idx, int left, int right, int[][][] dp){
        if (idx == word.length()) return 0;
        if (dp[idx][left][right] != -1) return dp[idx][left][right];

        // recursive
        int cur = word.charAt(idx) - 'A' + 1;
        int pickLeft = findDist(left, cur) + help(word, idx + 1, cur, right, dp);
        int pickRight = findDist(right, cur) + help(word, idx + 1, left, cur, dp);

        return dp[idx][left][right] = Math.min(pickLeft, pickRight);
    }

    static int findDist(int start, int target){
        if (start == 0) return 0;
        int x1 = (start-1) / 6;
        int y1 = (start-1) % 6;
        int x2 = (target-1) /6;
        int y2 = (target-1) % 6;

        int x = Math.abs(x2 - x1);
        int y = Math.abs(y2 - y1);
        return x + y;

    }

    //Tabulation
    static int Tabulation(String word){
        int n =  word.length();

        int[][][] dp = new int[n + 1][27][27];

        for (int idx = n-1; idx >= 0; idx--) {
            for (int left = 26; left >= 0; left--) {
                for (int right = 26; right >= 0 ; right--) {

                    int cur = word.charAt(idx) - 'A' + 1;
                    int pickLeft = findDist(left, cur) + dp[idx + 1][cur][right];
                    int pickRight = findDist(right, cur) + dp[idx + 1][left][cur];

                    dp[idx][left][right] = Math.min(pickLeft, pickRight);

                }
            }

        }

        return dp[0][0][0];
    }

    static int spaceOpt(String word){
        int n =  word.length();

        int[][] next = new int[27][27];
        int[][] curr = new int[27][27];

        for (int idx = n-1; idx >= 0; idx--) {
            for (int left = 26; left >= 0; left--) {
                for (int right = 26; right >= 0 ; right--) {

                    int cur = word.charAt(idx) - 'A' + 1;
                    int pickLeft = findDist(left, cur) + next[cur][right];
                    int pickRight = findDist(right, cur) + next[left][cur];

                    curr[left][right] = Math.min(pickLeft, pickRight);

                }
            }

            int[][] temp = curr;
            curr = next;
            next = temp;
        }

        return next[0][0];
    }
}


/*

{Scope}
 */