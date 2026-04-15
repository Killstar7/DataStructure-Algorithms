package String.S_03_Create_Pallindrome_String;


public class Solution {
    public static void main(String[] args) {
        String s = "abcdabcd";
        System.out.println(f(s));

    }
    static String f(String s){
        int n = s.length();
        int[] chars = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            chars[c - 'a'] = 1;
        }
        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (chars[i] == 1){
                char c = (char) (i + 'a');
                ans += c;
            }
        }

        int idx = n / 2 - 1;

        while (idx >= 0){
            ans += ans.charAt(idx);
            idx--;
        }
        return ans;
    }
}
