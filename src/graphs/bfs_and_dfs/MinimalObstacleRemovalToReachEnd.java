package graphs.bfs_and_dfs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/***
 * https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/
 */
public class MinimalObstacleRemovalToReachEnd {

    int[] dR = {0, -1, 0, 1};
    int[] dC = {-1, 0, 1, 0};

    //O(m * n + m * n * log(m * n))
    public int minimumObstacles(int[][] grid) {
        //We will use Dijkstra's single source the shortest path algorithm for weighted graphs to find minimum obstacles
        // to be removed to reach m-1,n-1 cell.
        //Edges to cells with obstacles have weight of 1 (as we need to remove it)
        //Edges to all other cells have cost of 0
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n]; //Keep track of distance from source(0,0)
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        //Keep track of visited
        boolean[][] visited = new boolean[m][n];
        //PQ with key as index, and value as distance
        PriorityQueue<Pair<int[], Integer>> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Pair<int[], Integer> a, Pair<int[], Integer> b) {
                if (a.getSecond() < b.getSecond()) {
                    return -1;
                } else if (a.getSecond() > b.getSecond()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        //Add (0,0) with distance 0 to pq
        pq.add(new Pair<>(new int[]{0, 0}, 0));
        while (!pq.isEmpty()) {
            Pair<int[], Integer> curr = pq.poll();
            int i = curr.getFirst()[0];
            int j = curr.getFirst()[1];
            int distance = curr.getSecond();
            visited[i][j] = true;
            if (dist[i][j] < distance) {
                continue;//We already found a better path routing through other nodes before we got to processing this node
            }
            //Visit neighbors
            for (int k = 0; k < 4; k++) {
                int newR = i + dR[k];
                int newC = j + dC[k];
                //We cannot get the shortest path by revisiting a node already visited because of greedy approach
                if (newR >= 0 && newC >= 0 && newR < m && newC < n && !visited[newR][newC]) {
                    //Cost of edge is 1 if neighbor is an obstacle, else 0
                    int newDist = distance + (grid[newR][newC] == 1 ? 1 : 0);
                    //We found a better distance, so update the distance and add it to pq
                    if (newDist < dist[newR][newC]) {
                        dist[newR][newC] = newDist;
                        pq.add(new Pair<>(new int[]{newR, newC}, newDist));
                    }
                }
            }
        }
        return dist[m - 1][n - 1];
    }

    class Pair<T, V> {
        private T first;
        private V second;

        public Pair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return this.first;
        }

        public V getSecond() {
            return this.second;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0}, {0, 1}, {1, 1}, {1, 0}};
        MinimalObstacleRemovalToReachEnd minimalObstacleRemovalToReachEnd = new MinimalObstacleRemovalToReachEnd();
        System.out.println(minimalObstacleRemovalToReachEnd.minimumObstacles(grid));
    }
}
