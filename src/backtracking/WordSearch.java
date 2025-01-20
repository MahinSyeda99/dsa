package backtracking;

import java.util.Arrays;

public class WordSearch {

    //O(N * M * 4^S) where N - number of rows, M - Number of columns, S - word length
    //For each position there are four ways. We need to form word of length S, so 4^S ways to search and for each way, we visit N * M elements at max.
    // So, total: O(N*M*4^S)
    public boolean exist(char[][] board, String word) {
        //Maintain visited array
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(visited[i], false);
        }
        //Consider each element as starting of the path to verify if word exists
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (existH(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean existH(char[][] board, String word, int i, int j, int x, boolean[][] visited) {
        //If x equals the length of the word, it means all characters have been matched, so return true
        if (x == word.length()) {
            return true;
        }

        //If the current cell is out of bounds, already visited, or the character does not match the current character in the word, return false
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || visited[i][j] || board[i][j] != word.charAt(x)) {
            return false;
        }

        //Mark current element as visited and recursively call in all four directions (right, down, left, up).
        visited[i][j] = true;
        //right, down, left, up
        boolean exists = existH(board, word, i, j + 1, x + 1, visited) ||
                existH(board, word, i + 1, j, x + 1, visited) ||
                existH(board, word, i, j - 1, x + 1, visited) ||
                existH(board, word, i - 1, j, x + 1, visited);
        //If solution found, return true
        if (exists) {
            return true;
        }
        //Mark current as element as not visited
        visited[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        //Expected true
        System.out.println(wordSearch.exist(board, word));
        word = "SEE";
        //Expected true
        System.out.println(wordSearch.exist(board, word));
        word = "ABCB";
        //Expected false
        System.out.println(wordSearch.exist(board, word));
        board = new char[][]{{'A'}};
        //Expected true
        System.out.println(wordSearch.exist(board, "A"));
    }
}
