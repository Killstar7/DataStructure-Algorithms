package Graph.G_08_findDegrees;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = {
                {0 ,1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        int[] arr = findDegrees(matrix);
        for (int i : arr)
            System.out.println(i);
    }
    static int[] findDegrees(int[][] matrix) {

        int n = matrix.length;
        ArrayList<ArrayList<Integer>> AdjL = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            AdjL.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1){
                    AdjL.get(i).add(j);
                    AdjL.get(j).add(i);
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = AdjL.get(i).size() / 2;
        }
        return ans;

    }
}
