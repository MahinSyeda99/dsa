package graphs.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    //TC: O(m * n)
    //SC: O(m * n)
    //Using BFS in graph
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // delRow and delCol are used to traverse in
        // up, right, bottom, and left respectively.
        //First combination is up: i - 1, j. Second is right: i, j + 1, Third is Down: i + 1, j. Fourth is left: i, j - 1
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        //visited array to keep track of visited cells
        int[][] visited = new int[m][n];

        Queue<int[]> q = new LinkedList<>();

        int freshOranges = 0;

        //Iterate through all cells, add rotten oranges to queue
        //Also, find number of fresh oranges
        //Initially, time frame is 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[]{i, j, 0});
                    //Mark as visited
                    visited[i][j] = 2;
                } else {
                    //mark as unvisited
                    visited[i][j] = 0;
                }
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        //Count of oranges rotten as we mark them rotten
        int count = 0;
        int time = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];
            int t = curr[2];
            time = Math.max(time, t);

            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                //If the cell is 1, and it is not yet visited
                if (newRow < m && newRow >= 0 && newCol < n && newCol >= 0 && grid[newRow][newCol] == 1 && visited[newRow][newCol] != 2) {
                    visited[newRow][newCol] = 2;
                    q.add(new int[]{newRow, newCol, t + 1});
                    count++;
                }
            }
        }
        //If final rotten oranges count is same as initial freshOranges, then all oranges are rotten
        if (count == freshOranges) {
            return time;
        }
        //All oranges could not be rotten, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottenOranges rottenOranges = new RottenOranges();
        System.out.println(rottenOranges.orangesRotting(grid));
    }
}
