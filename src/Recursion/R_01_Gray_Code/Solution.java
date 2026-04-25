package Recursion.R_01_Gray_Code;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int n = 3;

        System.out.println("Recursive Gray Code:");
        ArrayList<String> recursiveAns = graycode(n);
        for (String s : recursiveAns) {
            System.out.println(s);
        }

        System.out.println("\nBit Manipulation Gray Code:");
        ArrayList<String> bitAns = graycodeBitManipulation(n);
        for (String s : bitAns) {
            System.out.println(s);
        }
    }

    // Recursive + Reflection Method
    static ArrayList<String> graycode(int n) {
        return helper(n);
    }

    static ArrayList<String> helper(int n) {
        if (n == 1) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add("0");
            temp.add("1");
            return temp;
        }

        ArrayList<String> cur = helper(n - 1);
        ArrayList<String> ans = new ArrayList<>();

        for (int i = 0; i < cur.size(); i++) {
            ans.add("0" + cur.get(i));
        }

        for (int i = cur.size() - 1; i >= 0; i--) {
            ans.add("1" + cur.get(i));
        }

        return ans;
    }

    // Bit Manipulation Method
    static ArrayList<String> graycodeBitManipulation(int n) {
        ArrayList<String> ans = new ArrayList<>();

        int total = 1 << n; // 2^n

        for (int i = 0; i < total; i++) {
            int gray = i ^ (i >> 1);

            String binary = Integer.toBinaryString(gray);

            while (binary.length() < n) {
                binary = "0" + binary;
            }

            ans.add(binary);
        }

        return ans;
    }
}