package arrays.A_04_Robot_Collisions_2751;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] positions = {1, 2, 5, 6};
        int[] healths = {10, 10, 11, 11};
        String directions = "RLRL";
        List<Integer> ans = survivedRobotsHealths(positions, healths, directions);
        for (int i : ans) {
            System.out.print(i + " ");
        }

    }

    static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {

        int n = positions.length;
        List<values> list = new ArrayList<>();   //creation of container to store the values

        for (int i = 0; i < n; i++) {
            list.add(new values(positions[i], healths[i], i, directions.charAt(i)));
        }
        list.sort(Comparator.comparing(values -> values.position));
        Stack<values> st = new Stack<>();

        int[] temp = new int[n];
        Arrays.fill(temp, -1);
        int idx = 0;

        while (idx < n) {

            values cur = list.get(idx);
            if (cur.direction == 'R') {
                st.add(cur);
            } else {
                boolean survives = true;
                while (!st.isEmpty() && st.peek().direction == 'R') {
                    values top = st.peek();
                    if (cur.health > top.health) { // new value is greater than the top value(L)
                        st.pop();
                        cur.health--;

                    } else if (cur.health < top.health) { // old value is greater(R)
                        top.health--;
                        survives = false;
                        break; //while loop break

                    } else { // same health so both out
                        st.pop();
                        survives = false;
                        //both robot out so new robot needed
                        break;
                    }
                }
                if (st.isEmpty() && survives) {
                    //if there exist a L robot which is the winner but there is no robot in the stack to fight,
                    // then we have to store that result
                    temp[cur.index] = cur.health;
                }
            }
            idx++;
        }

        //scanned all elements so now add remaining elements health
        while (!st.isEmpty()) {
            values value = st.pop();
            temp[value.index] = value.health;
        }
        List<Integer> result = new ArrayList<>();
        for (int i : temp) {
            if (i != -1) result.add(i);
        }


        return result;
    }

    static class values {
        int position;
        int health;
        int index;
        char direction;

        values(int position, int health, int index, char direction) {
            this.position = position;
            this.health = health;
            this.index = index;
            this.direction = direction;
        }
    }

}
