package matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/map-of-highest-peak/
 */
public class MapOfHighestPeak {

    int[] dR = new int[]{0, -1, 0, 1};
    int[] dC = new int[]{-1, 0, 1, 0};

    //O(m*n) TC solution
    //O(m*n) SC solution
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] height = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    visited[i][j] = true;
                    q.add(new int[]{i, j, 0});
                }
            }
        }
        //Doing BFS on each water cell
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            for (int i = 0; i < 4; i++) {
                int newR = r + dR[i];
                int newC = c + dC[i];
                if (newR < m && newR >= 0 && newC < n && newC >= 0 && !visited[newR][newC]) {
                    height[newR][newC] = curr[2] + 1;
                    visited[newR][newC] = true;
                    q.add(new int[]{newR, newC, height[newR][newC]});
                }
            }
        }
        return height;
    }

    public static void main(String[] args) {
        MapOfHighestPeak mapOfHighestPeak = new MapOfHighestPeak();
        int[][] isWater = new int[][]{
                {0, 0, 1},
                {1, 0, 0},
                {0, 0, 0}
        };
        //o/p should be [[1,1,0],[0,1,1],[1,2,2]]
        System.out.println(Arrays.deepToString(mapOfHighestPeak.highestPeak(isWater)));
    }
}
