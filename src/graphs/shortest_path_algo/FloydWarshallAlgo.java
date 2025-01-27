package graphs.shortest_path_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Floyd Warshall Algorithm is an all pair shortest path algorithm(APSP), unlike Dijkstra and Bellman Ford which are SSSP algorithms.
 * This algorithm works for both the directed and undirected weighted graphs.
 * It follows Dynamic Programming approach to check every possible path going via every possible node in order to calculate the shortest distance between every pair of nodes.
 * <p>
 * Idea Behind Floyd Warshall Algorithm:
 * Suppose we have a graph G[][] with V vertices from 1 to N. Now we have to evaluate a shortestPathMatrix[][] where shortestPathMatrix[i][j] represents the shortest path between vertex i to j.
 * Obviously the shortest path between i to j will have some k number of intermediate nodes.
 * The idea behind floyd warshall algorithm is to treat each and every vertex from 1 to N as an intermediate node one by one.
 * <p>
 * For every pair (i, j) of the source and destination vertices respectively, there are two possible cases.
 * k is not an intermediate vertex in the shortest path from i to j. We keep the value of dist[i][j] as it is.
 * k is an intermediate vertex in the shortest path from i to j. We update the value of dist[i][j] as dist[i][k] + dist[k][j], if dist[i][k] + dist[k][j] <  dist[i][j]
 *
 *
 * //TC is O(V^3), SC: O(V^2)
 *
 * Useful for dense graphs, for sparse graphs, use Johnson's algo with TC: O(VElogV + VE)
 */
public class FloydWarshallAlgo {
    private int V;
    private int[][] dp; //Store the shortest distances
    private Integer[][] next; //Store the next when going from node i to node j, to reconstruct the path
    private int[][] graph;

    public FloydWarshallAlgo(int[][] graph) {
        int V = graph.length;
        this.V = V;
        this.dp = new int[V][V];
        this.next = new Integer[V][V]; //Contains null values by default
        this.graph = graph;

        //Deep copy of i/p matrix for dp and setup next
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dp[i][j] = graph[i][j];
                if (graph[i][j] != Integer.MAX_VALUE) {
                    next[i][j] = j; //Initially, when going from node i to node j, next of node i is node j
                }
            }
        }
    }

    public int[][] findAllPairsShortestPath() {
        //Execute FW all pairs shortest path algo
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE && dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        //Detect and propagate negative cycles
        propagateNegativeCycles();
        return dp;
    }

    private void propagateNegativeCycles() {
        //Execute FW all pairs shortest path second time. This time, if the distance from the node to itself is less than zero,
        // then it implies there is negative cycle
        //Mark the distance from i to j with -Infinity, indicating the path from i to j is either part of or reaches into negative cycle
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE && dp[k][k] < 0) {
                        //There is a path from i to k, and from k to j and distance of node k from itself is less than zero, indicating i and j are either part of or reachable from negative cycle
                        dp[i][j] = Integer.MIN_VALUE;
                        next[i][j] = -1; //Indicates path is affected by negative cycle
                    }
                }
            }
        }
    }

    /**
     * Reconstruct the shortest path between nodes start and end
     * @param s source
     * @param e end
     * @return path
     */
    public List<Integer> reconstructPath(int s, int e) {
        List<Integer> path = new ArrayList<>();
        //If there is no path between s and e, return empty path
        if (dp[s][e] == Integer.MAX_VALUE) {
            return path;
        }
        int at = s;
        for (; at != e; at = next[at][e]) {
            if (at == -1) { //Implies, part of negative cycle, return null
                return null;
            }
            path.add(at);
        }
        if (next[at][e] == -1) {
            return null; //If end is part of negative cycle, return null
        }
        path.add(e);
        return path;
    }

    class Pair<T, V> {
        T key;
        V value;

        Pair(T first, V second) {
            this.key = first;
            this.value = second;
        }

        public T getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        int V = 7;
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }

        // Add some edge values.
        graph[0][1] = 2;
        graph[0][2] = 5;
        graph[0][6] = 10;
        graph[1][2] = 2;
        graph[1][4] = 11;
        graph[2][6] = 2;
        graph[6][5] = 11;
        graph[4][5] = 1;
        graph[5][4] = -2;

        FloydWarshallAlgo floydWarshallAlgo = new FloydWarshallAlgo(graph);
        int[][] dist = floydWarshallAlgo.findAllPairsShortestPath();

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.println("The node " + j + " cannot be reached from node " + i + "\n");
                } else if (dist[i][j] == Integer.MIN_VALUE) {
                    System.out.println("The nodes " + i + " and " + j + " are part of negative cycle. No shortest path." + "\n");
                } else {
                    System.out.println("Shortest distance from source node " + i + " to " + j + " is " + dist[i][j]);
                    System.out.println("Shortest path is: " + floydWarshallAlgo.reconstructPath(i, j) + "\n");
                }
            }
            System.out.println("--------------------------------------------------------------");
        }
    }
}
