package Dynamic_Programming.DP_03_Buy_Sell_product;

public class solution {
    public static void main(String[] args) {

    }
    static int tabulation(int[] arr){
        int profit = 0;
        for (int i = 1; i < arr.length ; i++) {
            if (arr[i] > arr[i-1]) profit += arr[i] - arr[i-1];
        }
        return profit;
    }
}
