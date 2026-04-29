package Sliding_Window.SW_02_Longest_Repeating_char_replacement;

/*
        `Longest Repeating Character Replacement
        Difficulty: Medium

        Given a string s of length n consisting of uppercase English letters and an integer k,
        you are allowed to perform at most k operations.  In each operation,
        you can change any character of the string to any other uppercase English letter.

        Determine the length of the longest substring that can be transformed into a string with all identical characters after
        performing at most k such operations.

        Examples:

        Input: s = "ABBA", k = 2
        Output: 4
        Explanation: The string "ABBA" can be fully converted into the same character using at most 2 changes. By replacing both
         'A' with 'B', it becomes "BBBB". Hence, the maximum length is 4.
        Input: s = "ADBD", k = 1
        Output: 3
        Explanation: In the string "ADBD", we can make at most 1 change. By changing 'B' to 'D', the string becomes "ADDD",
         which contains a substring "DDD" of length 3.
        Constraints:
        1 ≤ n, k ≤ 105
        s consists of only uppercase English letters.`
 */

public class Solution {
    public static void main(String[] args) {
        String s = "ABBA";
        int k = 2;

    }

/**************************************************  With extra time  **************************************************/
/****************************************** Time Complexity:  O(n × 26) ≈ O(n) ******************************************/
    static int longestSubstr(String s, int k) {
        // code here
        int n = s.length();

        int[] freq = new int[26];
        int ans = 0;
        int max_freq = 0;
        int left = 0, right = 0;

        while(right < n){
            freq[s.charAt(right) - 'A']++;
            max_freq = getmax(freq);

            while((right - left + 1) - max_freq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
                max_freq = getmax(freq);
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }
    static int getmax(int[] freq){
        int max_f = 0;
        for(int f : freq){
            max_f = Math.max(max_f, f);
        }
        return max_f;

    }
/**************************************************  Without extra time  **************************************************/
/************************************************* Time Complexity: O(n) *************************************************/
    static int longestSubstr1(String s, int k) {
        // code here
        int n = s.length();

        int[] freq = new int[26];
        int ans = 0;
        int max_freq = 0;
        int left = 0, right = 0;

        while(right < n){
            freq[s.charAt(right) - 'A']++;
            max_freq = Math.max(max_freq, freq[s.charAt(right) - 'A']);


            while((right - left + 1) - max_freq > k){
                freq[s.charAt(left) - 'A']--;
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }
}
