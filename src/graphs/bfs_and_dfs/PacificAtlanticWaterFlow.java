package graphs.bfs_and_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
public class PacificAtlanticWaterFlow {

    private int[] dR = new int[]{0, -1, 0, 1}; //Row directions
    private int[] dC = new int[]{-1, 0, 1, 0}; //Column directions

    //O(M * N) TC solution and O(M * N) SC solution
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int M = heights.length;
        int N = heights[0].length;
        //Queue to store coordinates of pairs when doing bfs for pacific ocean coordinates
        Queue<int[]> q1 = new LinkedList<>();
        //Queue to store coordinates of pairs when doing bfs for atlantic ocean coordinates
        Queue<int[]> q2 = new LinkedList<>();

        //Store visited coordinates during traversal
        boolean[][] visited1 = new boolean[M][N]; //First tarversal
        boolean[][] visited2 = new boolean[M][N]; //Second traversal

        //Insert the coordinates directly connected with pacific ocean and atlantic ocean into q1 and q2 respectively
        for (int i = 0; i < M; i++) {
            q1.add(new int[]{i, 0}); //Pacific - left
            visited1[i][0] = true;
            q2.add(new int[]{i, N - 1}); //Atlantic - right
            visited2[i][N - 1] = true;
        }

        for (int j = 0; j < N; j++) {
            q1.add(new int[]{0, j}); //Pacific - top
            visited1[0][j] = true;
            q2.add(new int[]{M - 1, j}); //Atlantic - bottom
            visited2[M - 1][j] = true;
        }

        //BFS for pacific ocean
        //We move inward amd find all the cells that can flow into pacific ocean
        //traverse the grid from cells adjacent to pacific ocean and moving into cells with height equal or greater. (Because water can flow from equal high to low or equal heights)
        bfs(heights, M, N, q1, visited1);
        //BFS for atlantic ocean
        bfs(heights, M, N, q2, visited2);

        //Result
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited1[i][j] && visited2[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private void bfs(int[][] heights, int M, int N, Queue<int[]> q, boolean[][] visited) {
        while (!q.isEmpty()) {
            int[] curr = q.remove();

            //Explore neighbours
            for (int i = 0; i < 4; i++) {
                int nR = curr[0] + dR[i];
                int nC = curr[1] + dC[i];
                //If its not visited and greater than or equal to current, add it to queue and mark as visited
                if (nR >= 0 && nC >= 0 && nR < M && nC < N && !visited[nR][nC] && heights[nR][nC] >= heights[curr[0]][curr[1]]) {
                    visited[nR][nC] = true;
                    q.add(new int[]{nR, nC});
                }
            }
        }
    }
}
