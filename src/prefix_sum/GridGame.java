package prefix_sum;

/**
 * https://leetcode.com/problems/grid-game/description/
 */
public class GridGame {

    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[] prefixSum1 = new long[n];
        long[] prefixSum2 = new long[n];
        prefixSum1[0] = grid[0][0];
        prefixSum2[0] = grid[1][0];
        for (int i = 1; i < n; i++) {
            prefixSum1[i] = prefixSum1[i - 1] + grid[0][i];
            prefixSum2[i] = prefixSum2[i - 1] + grid[1][i];
        }

        long min = Long.MAX_VALUE;
        long max;
        //If robot1 moves down at i = 0, then it goes through row2 completely, as it cant move up
        //Then, if robot2 also goes down, then the points will be 0
        //It tries to maximize itself, so it goes through row1 completely
        //So, the points of robot2 will be prefixSum1[n-1]-prefixSum[i]
        //robot1 wants to minimize the maximums of robot 2, so he takes min
        max = Math.max(prefixSum1[n - 1] - prefixSum1[0], 0);
        min = Math.min(min, max);
        //i is the index where robot1 goes down
        for (int i = 1; i < n; i++) {
            //Max points of robot2 if robot1 goes down at i
            max = Math.max(prefixSum1[n - 1] - prefixSum1[i], prefixSum2[i - 1]);
            min = Math.min(min, max);
        }
        return min;
    }

    public static void main(String[] args) {
        GridGame gridGame = new GridGame();
        int[][] grid = new int[][]{
                {2, 5, 4},
                {1, 5, 1}
        };
        //o/p should be 4
        System.out.println(gridGame.gridGame(grid));
    }
}
