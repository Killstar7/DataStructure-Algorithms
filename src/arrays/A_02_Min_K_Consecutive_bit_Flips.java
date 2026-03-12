package arrays;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class A_02_Min_K_Consecutive_bit_Flips {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the arr:");
        String[] s=sc.nextLine().split(" ");
        int n=s.length;
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(s[i]);
        }
        System.out.println("enter the k:");
        int k= sc.nextInt();
        System.out.println("ans is "+kBitFlips(arr,k));

    }
    static int kBitFlips(int[] arr, int k){
        int ans=0;
        int n=arr.length;

        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<n;i++){

            if(!q.isEmpty() && i>q.peek()){
                q.poll();
            }
            if(q.size()%2==arr[i]){
                if(i+k-1>=n) return -1;
                q.add(i+k-1);
                ans++;
            }

        }
        return ans;
    }
}
