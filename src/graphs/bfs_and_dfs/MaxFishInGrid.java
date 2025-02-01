package graphs.bfs_and_dfs;

/**
 * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid
 */
public class MaxFishInGrid {

    private int[] dR = new int[]{0, -1, 0, 1};
    private int[] dC = new int[]{-1, 0, 1, 0};

    //TC: O(m * n)
    //SC: O(m * n)
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    //If node is not visited, and it's a water cell
                    result = Math.max(result, dfsForMaxFish(grid, i, j));
                }
            }
        }
        return result;
    }

    public int dfsForMaxFish(int[][] grid, int r, int c) {
        int count = grid[r][c]; //Count as he catches the fish
        grid[r][c] = 0; //Mark the node as visited
        //Recursively visit each adj neighbor;
        for (int i = 0; i < 4; i++) {
            int newR = r + dR[i];
            int newC = c + dC[i];
            if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length && grid[newR][newC] != 0) {
                count = count + dfsForMaxFish(grid, newR, newC);
            }
        }
        return count;
    }
}
