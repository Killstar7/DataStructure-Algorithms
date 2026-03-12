package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class A_01_TwoSum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter arr elements");
        String[] s=sc.nextLine().split(" ");
        System.out.println("enter the sum");
        int sum=sc.nextInt();
        int n=s.length;
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(s[i],sum);
        }
        System.out.println(TwoSum(arr,sum));
    }

    static boolean TwoSum(int[] arr,int sum){
        HashSet<Integer> set=new HashSet<>();

        for (int j : arr) {
            if (set.contains(sum - j)) return true;
            set.add(j);
        }

        return false;
    }
}
