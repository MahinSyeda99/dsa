package graphs.topological_sorting;

import java.util.*;

public class KahnsAlgo {

    //Using queue
    //TC: O(V+E)
    //SC: O(V)
    public List<Integer> topologicalSort(int[][] adj) {
        int V = adj.length;
        Queue<Integer> q = new LinkedList<>(); //To add the result
        int[] inDegree = new int[V];
        Arrays.fill(inDegree, 0);
        //Calculate in-degree of each node
        for (int i = 0; i < V; i++) {
            for (int u : adj[i]) {
                inDegree[u]++;
            }
        }
        //Add all 0 in-degree nodes to queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> result = new ArrayList<>(); //To keep track of result
        //BFS
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node); //Add it to result

            //Visit each of its neighbors
            for (int u : adj[node]) {
                inDegree[u]--; //reduce the inDegree by 1
                if (inDegree[u] == 0) {
                    q.add(u); //If inDegree becomes zero, add it to queue
                }
            }
        }

        //Check for cycle
        if (result.size() != V) {
            System.out.println("There exists a cycle in the graph. No topological sort.");
            return null;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] adj = new int[][] {
                {},
                {},
                {3},
                {1},
                {0, 1},
                {0, 2}
        };
        //o/p is [4, 5, 0, 2, 3, 1]
        KahnsAlgo kahnsAlgo = new KahnsAlgo();
        System.out.println(kahnsAlgo.topologicalSort(adj));
    }
}
