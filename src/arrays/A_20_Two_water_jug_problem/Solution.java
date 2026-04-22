package arrays.A_20_Two_water_jug_problem;

/*
    Two water Jug problem
    Difficulty: Medium

    You are at the side of a river. You are given a m litre jug and a n litre jug . Both the jugs are initially empty.
    The jugs dont have markings to allow measuring smaller quantities. You have to use the jugs to measure d litres of water .
     Determine the minimum no of operations to be performed to obtain d litres of water in one of the jugs.
    The operations you can perform are:

    Empty a Jug
    Fill a Jug
    Pour water from one jug to the other until one of the jugs is either empty or full.
    Examples:

    Input: m = 3, n = 5, d = 4
    Output: 6
    Explanation: Operations are as follow-
    1. Fill up the 5 litre jug.
    2. Then fill up the 3 litre jug using 5 litre jug. Now 5 litre jug contains 2 litre water.
    3. Empty the 3 litre jug.
    4. Now pour the 2 litre of water from 5 litre jug to 3 litre jug.
    5. Now 3 litre jug contains 2 litre of water and 5 litre jug is empty.
      Now fill up the 5 litre jug.
    6. Now fill one litre of water from 5 litre jug to 3 litre jug. Now we have 4 litre water in 5 litre jug.
    Input: m = 8, n = 56, d = 46
    Output: -1
    Explanation: Not possible to fill any one of the jug with 46 litre of water.
    Constraints:
    1 ≤ n, m ≤ 106
    1 ≤ d ≤ 106
 */
public class Solution {
    public static void main(String[] args) {

        int n = 3;
        int m = 5;
        int d = 4;
        System.out.println(minSteps(n, m, d));
    }
    static int minSteps(int m, int n, int d) {
        // code here
        if(d > Math.max(n, m)) return -1;

        if(d == n || d == m) return 1;


        int c = gosagu(n, m);
        if(d % c != 0) return -1;

        return Math.min(transfer(n, m, d), transfer(m, n, d));

    }


    static int gosagu(int a, int b){
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static int transfer(int n, int m, int d){

        int from = n;
        int to = 0;
        int count = 1;
        int i = 0;
        while(i < Math.max(n, m)){

            if(from == d || to == d){
                return count;

            }

            if(from == 0){
                from = n;
                count++;
            }

            if(to >= m){
                to = 0;
                count++;
            }

            int remain = m - to;
            to += from;
            if(remain < from)
                from -= remain;
            else
                from = 0;

            count++;
        }
        return Integer.MAX_VALUE;
    }
}
