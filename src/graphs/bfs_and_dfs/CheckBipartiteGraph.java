package graphs.bfs_and_dfs;

import java.util.*;

//Check whether a graph is bipartite or not
public class CheckBipartiteGraph {

    //TC: O(V+E), since we explore each vertex and edge only once
    //SC: O(V), queue and color array
    //Using BFS
    public boolean isBipartite(int V, List<List<Integer>> adj) {
        int[] color = new int[V];
        //Initialize color array with -1
        Arrays.fill(color, -1);

        //Iterate through all vertices to handle disconnected graphs
        for (int i = 0; i < V; i++) {
            //If vertex is uncolored, start BFS from it
            if (color[i] == -1) {
                //Assign initial color 0
                color[i] = 0;

                //Add it to queue
                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                while (!q.isEmpty()) {
                    int u = q.poll();

                    //Traverse adjacent vertices
                    for (int v : adj.get(u)) {
                        //If it is uncolored, assign alternate color
                        if (color[v] == -1) {
                            color[v] = 1 - color[u];
                        } else if (color[v] == color[u]) {
                            //If adjacent vertex has same color, then not a bipartite graph
                            return false;
                        }
                    }
                }
            }
        }

        //No conflict in coloring, so bipartite graph
        return true;
    }

    public static void main(String[] args) {
        // Graph Structure:
        // 0 - 1
        // |   |
        // 3 - 2
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        // Adding edges (undirected)
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(0);
        adj.get(0).add(3);

        CheckBipartiteGraph checkBipartiteGraph = new CheckBipartiteGraph();
        if (checkBipartiteGraph.isBipartite(V, adj))
            System.out.println("true");
        else
            System.out.println("false");
    }
}
