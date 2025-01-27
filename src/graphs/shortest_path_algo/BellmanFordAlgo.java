package graphs.shortest_path_algo;

import java.util.Arrays;

public class BellmanFordAlgo {

    /**
     * Single source shortest path algorithm(SSSP) for -ve weighted graph
     *
     * It effectively works in the cases of negative edges and is able to detect negative cycles as well.
     * It works on the principle of relaxation of the edges.
     *
     *
     * Relaxation means updating the shortest distance to a node if a shorter path is found through another node.
     * For an edge (u, v) with weight w:
     * If going through u gives a shorter path to v from the source node (i.e., distance[v] > distance[u] + w), we update the distance[v] as distance[u] + w.
     * In the bellman-ford algorithm, this process is repeated (V – 1) times for all the edges.
     *
     *
     * Why Relaxing Edges (V – 1) times gives us Single Source Shortest Path?
     * A shortest path between two vertices can have at most (V – 1) edges. It is not possible to have a simple path with more than (V – 1) edges (otherwise it would form a cycle).
     * Therefore, repeating the relaxation process (V – 1)times ensures that all possible paths between source and any other node have been covered.
     *
     * we need (V – 1) relaxations of all the edges to achieve single source shortest path. If one additional relaxation (Vth)
     * for any edge is possible, it indicates that some edges with overall negative weight has been traversed once more.
     * This indicates the presence of a negative weight cycle in the graph.
     *
     * Negative weight cycle:
     * A negative weight cycle is a cycle in a graph, whose sum of edge weights is negative.
     * If you traverse the cycle, the total weight accumulated would be less than zero.
     *
     * In the presence of negative weight cycle in the graph, the shortest path doesn’t exist because with each traversal of the cycle shortest path keeps decreasing.
     *
     * O(V*E) TC and O(V) SC
     */

    public int[] findShortestPath(int[][] edges, int V, int s) {
        int[] dist = new int[V]; //Distance array to keep track of distances from source to all other nodes
        Arrays.fill(dist, Integer.MAX_VALUE); //Fill it with positive INF initially
        dist[s] = 0; //Mark the distance of source node 's' as zero

        //Relax edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            //Relax all edges
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];
                if (dist[u] == Integer.MIN_VALUE) {
                    dist[v] = Integer.MIN_VALUE; //If the 'from' node has weight of Integer.MIN_VALUE, then it is part of -ve cycle, so mark v as part of -ve cycle
                } else if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = Integer.MIN_VALUE; //We found a better value than known best value. So, it is either part of -ve cycle or reachable from -ve cycle
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        BellmanFordAlgo bellmanFordAlgo = new BellmanFordAlgo();
        int[][] edges = new int[][] {
                {0, 1, 5},
                {1, 2, 20},
                {1, 5, 30},
                {1, 6, 60},
                {2, 3, 10},
                {2, 4, 75},
                {3, 2, -15},
                {4, 9, 100},
                {5, 6, 5},
                {5, 8, 50},
                {6, 7, -50},
                {7, 8, -10}
        };
        int V = 10;
        int[] dist = bellmanFordAlgo.findShortestPath(edges, V, 0);
        System.out.println("dist array: " + Arrays.toString(dist));
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MIN_VALUE) {
                System.out.println("This node is part of negative cycle. No shortest path.");
            } else {
                System.out.println("Shortest distance from source node 0 to " + i + " is " + dist[i]);
            }
        }
    }
}
