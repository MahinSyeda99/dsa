package two_dimensional_dp;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/">...</a>
 */
public class LongestIncreasingPathInAMatrix {

    //Backtracking Solution, DFS
    //O(n * m) TC solution. Because, maximum we will visit all the matrix values and after that and have values in dp, and for the remaining values as starting points, we already have the values in dp.
    //So, n * m + n * m ~ O(n * m)
    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        //dp[i][j] represents longest increasing path possible from dp[i][j]
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, pathH(matrix, i, j, dp));
            }
        }
        return max;
    }

    private int pathH(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int max = 1;
        //Up
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            max = Math.max(max, 1 + pathH(matrix, i - 1, j, dp));
        }
        //Down
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            max = Math.max(max, 1 + pathH(matrix, i + 1, j, dp));
        }
        //Left
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            max = Math.max(max, 1 + pathH(matrix, i, j - 1, dp));
        }
        //Right
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            max = Math.max(max, 1 + pathH(matrix, i, j + 1, dp));
        }
        return dp[i][j] = max;
    }
}
