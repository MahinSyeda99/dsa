package graphs.bfs_and_dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/sliding-puzzle/
 */
public class SlidingPuzzle {

    private int[] dR = {0, -1, 0, 1};
    private int[] dC = {-1, 0, 1, 0};

    //Solution 2
    //BFS
    public int slidingPuzzle2(int[][] board) {
        //Keep track of visited
        Set<String> visited = new HashSet<>();
        //Queue to add
        Queue<String> q = new LinkedList<>();
        //Add converted string pattern to queue
        q.add(convertMatrixToString(board));
        int nodesInNextLayer = 0;
        int nodesLeftInLayer = 1;
        int move_count = 0;
        int[][] validSwaps = new int[][]{
                {1, 3}, //If 0 is at 0th index in string => we can swap with 1 and 3 character positions index in string, which will be [0][1] and [1][0] in matrix
                {0, 2, 4}, //If 0 is at 1 index in string => we can swap with 0, 2 and 4 character positions in string, which will be [0][0], [0, 2], [1, 1] in matrix
                {1, 5},
                {0, 4},
                {1, 3, 5},
                {2, 4}
        };
        while (!q.isEmpty()) {
            String s = q.poll();
            //If we found the required state, return the move count
            if (s.equals("123450")) {
                return move_count;
            }
            //Mark it as visited
            visited.add(s);
            //Find index of zero
            int index = indexOfZero(s);
            //Visit neighbors
            for (int i : validSwaps[index]) {
                String pattern = swap(s, i, index);
                //If neighbor not visited, then add it to queue
                if (!visited.contains(pattern)) {
                    q.add(pattern);
                    nodesInNextLayer++;
                }
            }
            nodesLeftInLayer--;
            if (nodesLeftInLayer == 0) {
                nodesLeftInLayer = nodesInNextLayer;
                nodesInNextLayer = 0;
                move_count++;
            }
        }
        return -1;
    }

    private String swap(String s, int newIndex, int oldIndex) {
        char[] c = s.toCharArray();
        c[oldIndex] = c[newIndex];
        c[newIndex] = '0';
        return new String(c);
    }

    private int indexOfZero(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                return i;
            }
        }
        return -1;
    }

    //We can use BFS on grid here(Dungeon problem), because we want to reach certain state
    //Solution 1
    //O(6! * 4) TC where k <= 4{direction}
    //O(number of string formed) = O(6!) is SC
    public int slidingPuzzle(int[][] board) {
        //Keep track of visited
        Set<String> visited = new HashSet<>();
        //Queue to add
        Queue<String> q = new LinkedList<>();
        //Add converted string pattern to queue
        q.add(convertMatrixToString(board));
        int nodesInNextLayer = 0;
        int nodesLeftInLayer = 1;
        int move_count = 0;
        while (!q.isEmpty()) {
            String s = q.poll();
            //If we found the required state, return the move count
            if (s.equals("123450")) {
                return move_count;
            }
            //Mark it as visited
            visited.add(s);
            //Update board with pattern
            matrix(s, board);
            //Find index of zero
            int[] index = indexOfZero(board);
            //Visit neighbors
            for (int i = 0; i < 4; i++) {
                int newR = index[0] + dR[i];
                int newC = index[1] + dC[i];
                if (newR >= 0 && newR < 2 && newC >= 0 && newC < 3) {
                    //Swap 0 with neighbor
                    board[index[0]][index[1]] = board[newR][newC];
                    board[newR][newC] = 0;
                    String pattern = convertMatrixToString(board);
                    //If neighbor not visited, then add it to queue
                    if (!visited.contains(pattern)) {
                        q.add(pattern);
                        nodesInNextLayer++;
                    }
                    //Reset the board state
                    board[newR][newC] = board[index[0]][index[1]];
                    board[index[0]][index[1]] = 0;
                }
            }
            nodesLeftInLayer--;
            if (nodesLeftInLayer == 0) {
                nodesLeftInLayer = nodesInNextLayer;
                nodesInNextLayer = 0;
                move_count++;
            }
        }
        return -1;
    }

    private void matrix(String s, int[][] board) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = s.charAt(3 * i + j) - '0';
            }
        }
    }

    private String convertMatrixToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[] indexOfZero(int[][] board) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        SlidingPuzzle slidingPuzzle = new SlidingPuzzle();
        //o/p is 1
        System.out.println(slidingPuzzle.slidingPuzzle2(board));
    }
}
