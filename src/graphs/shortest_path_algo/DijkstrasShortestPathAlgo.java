package graphs.shortest_path_algo;

public class DijkstrasShortestPathAlgo {
    /**
     * The time complexity of Dijkstra’s algorithm using an adjacency list and a priority queue is O(E * log(V)) because of how the algorithm processes vertices and edges while maintaining a priority queue.
     *
     * 1. Understanding Dijkstra's Algorithm
     * Dijkstra's algorithm finds the shortest paths from a source vertex to all other vertices in a graph with non-negative edge weights. Here's how it works:
     *
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
     *
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
}
