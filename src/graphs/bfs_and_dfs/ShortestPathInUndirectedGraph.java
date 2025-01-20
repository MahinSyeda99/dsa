package graphs.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInUndirectedGraph {
    int[] dR = {0, -1, 0, 1};
    int[] dC = {-1, 0, 1, 0};

    //Dungeon problem
    //Shortest path in a Binary Maze
    //O(M * N) TC solution and O(M * N) SC solution (Because of visited array)
    public int findShortestPathLength(int[][] mat, int[] src, int[] dest) {
        //We will do BFS to find the shortest path
        //BFS works here because it doesn’t consider a single path at once.
        // It considers all the paths starting from the source and moves ahead one unit in all those paths at the same time
        // which makes sure that the first time when the destination is visited, it is the shortest path.
        int move_count = 0; //Track number of steps taken
        int nodes_in_next_layer = 0; //How many nodes we added
        int nodes_left_in_layer = 1; //How many nodes we need to dequeue

        int M = mat.length;
        int N = mat[0].length;
        //Track whether cell (i, j) is visited
        boolean[][] visited = new boolean[M][N];

        //Queue to do BFS
        Queue<int[]> q = new LinkedList<>();
        q.add(src); //Add start cell to queue
        //Mark source cell as visited
        visited[src[0]][src[1]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            //If we reached destination, return move_count
            if (r == dest[0] && c == dest[1]) {
                return move_count;
            }
            //Explore neighbours
            for (int i = 0; i < 4; i++) {
                int nR = r + dR[i];
                int nC = c + dC[i];

                //If it is not visited and the value is 1, then we enqueue it
                if (nR >= 0 && nC >= 0 && nR < M && nC < N && !visited[nR][nC] && mat[nR][nC] == 1) {
                    q.add(new int[]{nR, nC});
                    //Mark it as visited
                    visited[nR][nC] = true;
                    //Increment nodes in next layer
                    nodes_in_next_layer++;
                }
            }
            nodes_left_in_layer--; //Decrement as we have explored one node
            //If we have explored all nodes in the layer
            if (nodes_left_in_layer == 0) {
                move_count++; //Increment step
                nodes_left_in_layer = nodes_in_next_layer;
                nodes_in_next_layer = 0;
            }
        }
        //If we didn't reach destination
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}
        };

        int[] src = {0, 0};
        int[] dest = {3, 4};
        ShortestPathInUndirectedGraph shortestPathInUndirectedGraph = new ShortestPathInUndirectedGraph();
        int dist = shortestPathInUndirectedGraph.findShortestPathLength(mat, src, dest);
        //o/p is 11
        if (dist != -1)
            System.out.print("Shortest Path is " + dist);
        else
            System.out.print("Shortest Path doesn't exist");
    }
}
