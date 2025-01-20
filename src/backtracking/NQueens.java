package backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    //O(N!) T.C In the worst case, We try to place a queen in every column of every row. For the first row, there are N possible positions. For the second row, there are N-1 possible positions, and so on. This leads to a total of N × (N-1) × (N-2) × ... × 1 = N! possibilities.
    //O(N^2) S.C. To store N × N grid.
    public List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        solveNQueensH(board, 0, n, result);
        return result;
    }

    private void solveNQueensH(boolean[][] board, int row, int n, List<List<String>> result) {
        //All queens are successfully placed
        if (row == n) {
            result.add(formBoard(board, n));
            return;
        }

        //For each column, verify if the queen can be placed or not
        for (int j = 0; j < n; j++) {
            if (isSafe(board, row, j, n)) {
                //Place the queen
                board[row][j] = true;
                //Recursively call to plce queens in next rows
                solveNQueensH(board, row + 1, n, result);
                //Don't place the queen, backtrack
                board[row][j] = false;
            }
        }
    }

    private boolean isSafe(boolean[][] board, int row, int col, int n) {
        //No other queen is in same column
        for (int i = 0; i < n; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        //We are verifying only in upper halves of diagonals because we haven't visisted the next rows, so we needn't verify the lower diagonal
        //No other queen in left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        //No other queen in right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }
        return true;
    }

    private List<String> formBoard(boolean[][] board, int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String res = "";
            for (int j = 0; j < n; j++) {
                if (board[i][j]) {
                    res = res + "Q";
                } else {
                    res = res + ".";
                }
            }
            ans.add(res);
        }
        return ans;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        //[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        System.out.println(nQueens.solveNQueens(4));
        //[]
        System.out.println(nQueens.solveNQueens(3));
        //[["Q"]]
        System.out.println(nQueens.solveNQueens(1));
    }
}
