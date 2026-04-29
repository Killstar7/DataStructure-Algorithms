package String.S_04_Remove_Spaces;

/*
  Remove Spaces
  Difficulty: Basic
  Given a string s, remove all the spaces from the string and return the modified string.

  Examples:

  Input: s = "g eeks for ge eeks"
  Output: "geeksforgeeks"
  Explanation: All space characters are removed from the given string while preserving the order of the remaining characters, resulting in the final string "geeksforgeeks".
  Input: s = "abc d "
  Output: "abcd"
  Explanation:  All space characters are removed from the given string while preserving the order of the remaining characters, resulting in the final string "abcd".
  Constraints:
  1 ≤ |s| ≤ 105
 */
public class Solution {
    public static void main(String[] args) {

    }
    static String removeSpaces(String s) {
        // code here
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' '){
                ans = ans + s.charAt(i);
            }
        }
        return ans;

    }
}
