package arrays.A_10_count_increasing_subArr;

public class Solution {
    public static void main(String[] args) {

        int[] arr = {1, 4, 5, 3, 7, 9};
        System.out.println(countIncreasing(arr));
    }
    static int countIncreasing(int[] arr) {
        // code here
        int n = arr.length;
        int count = 0;
        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] > arr[i]){
                count++;
                ans += count;
            }
            else count = 0;
        }

        return ans;
    }
}
