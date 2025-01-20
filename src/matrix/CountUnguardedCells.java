package matrix;

public class CountUnguardedCells {
    //O(M * N + g * (M + N)), where g is number of guards
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        //G is 2
        for (int i = 0; i < guards.length; i++) {
            grid[guards[i][0]][guards[i][1]] = 2;
        }
        //W is 3
        for (int i = 0; i < walls.length; i++) {
            grid[walls[i][0]][walls[i][1]] = 3;
        }

        for (int g = 0; g < guards.length; g++) {
            int i = guards[g][0];
            int j = guards[g][1];
            //Go left and mark till 'G' or 'W' is seen
            for (int k = j - 1; k >= 0; k--) {
                if (grid[i][k] != 2 && grid[i][k] != 3) {
                    grid[i][k] = 1; //Mark it as guarded, '1'
                } else {
                    break;
                }
            }
            //Go right
            for (int k = j + 1; k < n; k++) {
                if (grid[i][k] != 2 && grid[i][k] != 3) {
                    grid[i][k] = 1;
                } else {
                    break;
                }
            }
            //Go up
            for (int k = i - 1; k >= 0; k--) {
                if (grid[k][j] != 2 && grid[k][j] != 3) {
                    grid[k][j] = 1;
                } else {
                    break;
                }
            }
            //Go down
            for (int k = i + 1; k < m; k++) {
                if (grid[k][j] != 2 && grid[k][j] != 3) {
                    grid[k][j] = 1;
                } else {
                    break;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
