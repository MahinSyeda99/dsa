package matrix;

/**
 * https://leetcode.com/problems/count-servers-that-communicate/description/
 */
public class CountServers {

    //Solution 2
    //O(m*n) TC and O(max(m, n)) SC
    public int countServers2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //Keep server count in rows and columns
        int[] rowCount = new int[m];
        int[] columnCount = new int[n];
        int serverCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    columnCount[j]++;
                    serverCount++;
                }
            }
        }

        int isolatedServerCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //If there is no other server in that row or column, then it is isolated
                if (grid[i][j] == 1 && rowCount[i] <= 1 && columnCount[j] <= 1) {
                    isolatedServerCount++;
                }
            }
        }

        //serverCount - isolatedCount is the count of communicating servers
        return serverCount - isolatedServerCount;
    }

    //Solution 1
    //O(m*n) TC and O(m*n) SC
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //Keep track of visited, if the server has already been included in the count
        boolean[][] visited = new boolean[m][n];
        int result = 0;

        //Iterate through rows
        for (int i = 0; i < m; i++) {
            int r = -1;
            int c = -1;
            //Keep track of server count
            int serverCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (r == -1 && c == -1) {//If this is the first element with value 1 encountered
                        r = i;
                        c = j;
                    } else {
                        if (!visited[i][j]) {
                            //If it's not counted, then count it in the result
                            visited[i][j] = true;
                            result = result + 1;
                        }
                        //Increase server count
                        serverCount++;
                    }
                }
            }
            //serverCount > 0 => there are more than 1 server
            if (serverCount > 0) {
                //If the first encountered element with value 1 is not added to count, add it
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    result = result + 1;
                }
            }
        }

        //Iterate through columns
        for (int j = 0; j < n; j++) {
            int r = -1;
            int c = -1;
            int serverCount = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    if (r == -1 && c == -1) {
                        r = i;
                        c = j;
                    } else {
                        if (!visited[i][j]) {
                            visited[i][j] = true;
                            result = result + 1;
                        }
                        serverCount++;
                    }
                }
            }
            if (serverCount > 0) {
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    result = result + 1;
                }
            }
        }
        return result;
    }
}
