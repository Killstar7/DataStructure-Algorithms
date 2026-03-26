package Graph.G_04_minHeightRoot;

import java.util.*;

public class solution {
    public static void main(String[] args) {
        int[][] edges = {
                {0, 2},
                {1, 2},
                {2, 3},
                {3, 4}
        };
        int V = 5;
        ArrayList<Integer> ans = minHeightRoot(V, edges);
        System.out.println("ans is : " + ans.get(0) + " " + ans.get(1));
    }

    static ArrayList<Integer> minHeightRoot(int V, int[][] edges) {
        List<HashSet<Integer>> AdjL = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            AdjL.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            AdjL.get(u).add(v);
            AdjL.get(v).add(u);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (AdjL.get(i).size() == 1) q.add(i);
        }
        int total = V;
        while (total > 2) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                int next = AdjL.get(cur).iterator().next();
                AdjL.get(next).remove(cur);

                if (AdjL.get(next).size() == 1) q.add(next);
            }
            total -= size;
        }
        return new ArrayList<>(q);
    }

}
