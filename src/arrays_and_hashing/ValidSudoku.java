package arrays_and_hashing;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        //Verify rows
        Set<Character> s;
        for (int i = 0; i < 9; i++) {
            s = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (s.contains(board[i][j])) {
                    return false;
                } else {
                    s.add(board[i][j]);
                }
            }
        }
        //Verify columns
        for (int i = 0; i < 9; i++) {
            s = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (s.contains(board[j][i])) {
                    return false;
                } else {
                    s.add(board[j][i]);
                }
            }
        }

        //Verify 3*3 grid
        for (int i = 0; i < 9; i = i + 3) {
            for (int j = 0; j < 9; j = j + 3) {
                s = new HashSet<>();
                for (int k = i; k < i + 3; k++) {
                    for (int m = j; m < j + 3; m++) {
                        if (board[k][m] == '.') {
                            continue;
                        }
                        if (s.contains(board[k][m])) {
                            return false;
                        } else {
                            s.add(board[k][m]);
                        }
                    }
                }
            }
        }
        return true;
    }
}