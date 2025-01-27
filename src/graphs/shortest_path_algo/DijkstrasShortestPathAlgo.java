package graphs.shortest_path_algo;

import java.util.*;

public class DijkstrasShortestPathAlgo {
    /**
     * Single source shortest path(SSSP) algorithm for graph with no negative weights
     * The time complexity of Dijkstra’s algorithm using an adjacency list and a priority queue is O(E * log(V)) because of how the algorithm processes vertices and edges while maintaining a priority queue.
     * <p>
     * 1. Understanding Dijkstra's Algorithm
     * Dijkstra's algorithm finds the shortest paths from a source vertex to all other vertices in a graph with non-negative edge weights. Here's how it works:
     * <p>
     * It uses a priority queue to always expand the vertex with the smallest known distance (greedy strategy).
     * It processes all edges of the current vertex and updates the distances to its neighbors if a shorter path is found.
     * This continues until all vertices are processed.
     * 2. Components of Time Complexity
     * Vertex Extraction Using the Priority Queue (log(V)):
     * The priority queue is implemented as a min-heap.
     * Each extraction of a vertex from the queue takes O(log(V)).
     * There are V vertices, each vertex is extracted from the queue at most once, so the total cost for priority queue operations due to vertices is O(V * log(V)).
     * Edge Relaxation for Neighbors (E * log(V)):
     * When a vertex is processed, the algorithm examines all its adjacent edges (relaxation step).
     * For a graph with E edges, every edge is considered once.
     * Each edge relaxation might involve updating the priority queue (to update the distance of a neighboring vertex), which takes O(log(V)).
     * Since there are E edges in total, the total cost for edge processing is O(E * log(V)).
     * 3. Total Time Complexity
     * The total time complexity is the sum of:
     * <p>
     * The cost of extracting vertices from the priority queue: O(V * log(V)).
     * The cost of edge relaxation and updating the priority queue: O(E * log(V)).
     * Thus, the overall complexity is: O(V⋅log(V))+O(E⋅log(V))=O((V+E)⋅log(V)).
     * 4. Why Simplified to O(E * log(V)):
     * In a connected graph, the number of edges E is at least V - 1 (a tree).
     * In dense graphs, E can be as large as V^2 , but typically  E≥V. The term O(E⋅log(V)) dominates O(V⋅log(V)), especially for sparse graphs,
     * leading to the simplified complexity O(E * log(V)).
     * 5. Key Insights
     * Adjacency List: Ensures that we only process the edges connected to a vertex, keeping the edge processing cost proportional to E.
     * Priority Queue: Efficiently manages updates and extraction of the minimum distance vertex, adding the log(V) factor.
     * Sparse Graphs: For E≈V, the complexity becomes O(V * log(V)).
     * Dense Graphs: For E≈V^2, the complexity grows but is still manageable for many practical scenarios.
     * This efficiency makes Dijkstra’s algorithm suitable for graphs with a large number of vertices but not too many edges.
     */

    //Find minimum distance and the path from source to all other nodes
    private Pair<int[], int[]> findShortestPath(Edge[][] graph, int s) {
        //Number of vertices
        int V = graph.length;
        //Key is vertex and value is distance
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> {
            if (a.getValue() < b.getValue()) {
                return -1;
            } else if (a.getValue() > b.getValue()) {
                return 1;
            } else {
                return 0;
            }
        });
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[V]; //To keep track of visited(whose distance is calculated and can be the only min possible)
        int[] prev = new int[V]; //To keep track of node that took you took to get to node i
        Arrays.fill(prev, -1); //Initialize all parents to -1

        dist[s] = 0; //Distance from source to source is zero
        pq.add(new Pair<>(s, 0)); //Add source to pq

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> p = pq.poll();
            int index = p.getKey();
            int minValue = p.getValue();
            visited[index] = true; //Mark it as visited
            if (dist[index] < minValue) {
                continue; //Because, we already got a better path routing from other nodes to this node
            }
            //Visit the edges from index
            for (Edge edge : graph[index]) {
                if (!visited[edge.to]) {
                    int newDist = dist[index] + edge.cost;
                    //if we find a better distance, update distance, add it to pq and update prev
                    if (newDist < dist[edge.to]) {
                        dist[edge.to] = newDist;
                        prev[edge.to] = index;
                        pq.add(new Pair<>(edge.to, newDist));
                    }
                }
            }
        }
        return new Pair<>(dist, prev); //Return dist and prev to reconstruct the path
    }

    //Find the shortest path from source to given node
    public List<Integer> findShortestPath(Edge[][] graph, int s, int e) {
        Pair<int[], int[]> p = findShortestPath(graph, s);
        int[] dist = p.getKey();
        int[] prev = p.getValue();
        System.out.println("Distance from " + s + " to all nodes: " + Arrays.toString(dist));
        System.out.println("Prev of all nodes: " + Arrays.toString(prev));

        List<Integer> path = new ArrayList<>();
        if (dist[e] == Integer.MAX_VALUE) {
            System.out.println("The node " + e + " cannot be reached from node " + s);
            return path;
        }

        //Traverse the path from e to s using prev
        for (int at = e; at != -1; at = prev[at]) {
            path.add(at);
        }

        //Reverse the path to get the path from s to e
        Collections.reverse(path);
        return path;
    }

    static class Edge {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
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
        DijkstrasShortestPathAlgo dijkstrasShortestPathAlgo = new DijkstrasShortestPathAlgo();
        Edge[][] graph = new Edge[][]{
                {new Edge(1, 4), new Edge(2, 1)},
                {new Edge(3, 1)},
                {new Edge(1, 2), new Edge(3, 5)},
                {new Edge(4, 3)},
                {}
        };
        //o/p is [0]
        System.out.println(dijkstrasShortestPathAlgo.findShortestPath(graph, 0, 0));
        //o/p is [0, 2, 1]
        System.out.println(dijkstrasShortestPathAlgo.findShortestPath(graph, 0, 1));
        //o/p is [0, 2]
        System.out.println(dijkstrasShortestPathAlgo.findShortestPath(graph, 0, 2));
        //o/p is [0, 2, 1, 3]
        System.out.println(dijkstrasShortestPathAlgo.findShortestPath(graph, 0, 3));
        //o/p is [0, 2, 1, 3, 4]
        System.out.println(dijkstrasShortestPathAlgo.findShortestPath(graph, 0, 4));
    }
}
