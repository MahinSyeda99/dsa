package graphs.bfs_and_dfs;

public class NumberOfIslands {

    //These arrays are used to get r and c numbers of 4 neighbours of a given cell
    int[] rowNum = {-1, 0, 1, 0};
    int[] colNum = {0, 1, 0, -1};

    //TC: O(m * n)
    //SC: O(m * n)
    //DFS solution to find number of connected components
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //Keep track of visited cells
        boolean[][] visited = new boolean[m][n];
        //Number of islands
        int count = 0;
        //Visit each cell if not visited and do dfs on it
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If a cell with value '1' is not visited yet, then a new island is found
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        // Mark this cell as visited
        visited[i][j] = true;

        for (int k = 0; k < 4; k++) {
            int newRow = i + rowNum[k];
            int newCol = j + colNum[k];

            //If value is '1' and not yet visited, then do dfs on the neighbour
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == '1' && !visited[newRow][newCol]) {
                dfs(grid, newRow, newCol, visited);
            }
        }
    }
}
