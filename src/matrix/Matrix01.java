package matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/01-matrix/description/
 */
public class Matrix01 {


    int[] dR = new int[] {0, -1, 0, 1};
    int[] dC = new int[] {-1, 0, 1, 0};

    //O(m * n) TC and O(m * n) SC
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] distance = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    distance[i][j] = 0;
                    q.add(new int[]{i, j, 0});
                }
            }
        }
        //We are doing BFS on all 0 cells and finding smallest distance to every 1 cell
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            for (int i = 0; i < 4; i++) {
                int newR = r + dR[i];
                int newC = c + dC[i];
                if (newR < m && newR >=0 && newC < n && newC >= 0 && mat[newR][newC] == 1) {
                    distance[newR][newC] = curr[2] + 1;
                    mat[newR][newC] = 0; //Indicating the cell is visited
                    q.add(new int[]{newR, newC, distance[newR][newC]});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Matrix01 matrix01 = new Matrix01();
        int[][] mat = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        //Output: [[0,0,0],[0,1,0],[1,2,1]]
        System.out.println(Arrays.deepToString(matrix01.updateMatrix(mat)));
    }
}
