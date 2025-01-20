package graphs.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    //TC: O(m * n)
    //SC: O(m * n)
    //Using BFS in graph
    public void floodFill(int[][] img, int x, int y, int newClr) {
        int prevClr = img[x][y];
        if (prevClr == newClr) {
            return;
        }

        // Rows and columns of the display
        int m = img.length;
        int n = img[0].length;
        // delRow and delCol are used to traverse in
        // up, right, bottom, and left respectively.
        //First combination is up: i - 1, j. Second is right: i, j + 1, Third is Down: i + 1, j. Fourth is left: i, j - 1
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        Queue<int[]> q = new LinkedList<>();
        // Append the position of the starting pixel of the component
        q.add(new int[]{x, y});
        img[x][y] = newClr;

        //While the queue is not empty, i.e., the whole component having prevClr color is not colored with newClr color
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int row = curr[0];
            int col = curr[1];

            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];
                //If the cell is same as prevClr, then update it to newClr and add it to queue
                if (newRow < m && newRow >= 0 && newCol < n && newCol >= 0 && img[newRow][newCol] == prevClr) {
                    img[newRow][newCol] = newClr;
                    q.add(new int[]{newRow, newCol});
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] img = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int x = 1, y = 1;
        int newClr = 3;

        FloodFill floodFill = new FloodFill();
        floodFill.floodFill(img, x, y, newClr);

        // Printing the updated img
        for (int[] row : img) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
