package matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-completely-painted-row-or-column/
 */
public class FirstCompletelyPaintedRowOrColumn {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, int[]> indices = new HashMap<>();
        int m = mat.length;
        int n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                indices.put(mat[i][j], new int[]{i, j});
            }
        }
        int[] rowFilled = new int[m + 1];
        int[] columnFilled = new int[n + 1];
        int[] curr;
        for (int i = 0; i < arr.length; i++) {
            curr = indices.get(arr[i]);
            rowFilled[curr[0]]++;
            columnFilled[curr[1]]++;
            if (rowFilled[curr[0]] == n || columnFilled[curr[1]] == m) {
                return i;
            }
        }
        return -1;
    }
}
