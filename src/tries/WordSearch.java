package tries;

import java.util.*;

public class WordSearch {

    public List<String> findWords(char[][] board, String[] words) {
        //Create a trie of words
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }

        List<String> result = new ArrayList<>();

        //Consider each element as starting of the path to verify if words exist
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                existH(board, i, j, root, result);
            }
        }
        return result;
    }

    //O(N * M * 4^S) where N - number of rows, M - Number of columns, S - word length
    //For each position there are four ways. We need to form word of length S, so 4^S ways to search and for each way, we visit N * M elements at max.
    // So, total: O(N*M*4^S)
    public void existH(char[][] board, int i, int j, TrieNode root, List<String> result) {
        char c = board[i][j];
        //If visited or the character doesn't exist, then return
        if (c == '*' || root.child[c - 'a'] == null) {
            return;
        }
        //Go to the child corresponding to character
        root = root.child[c - 'a'];
        //If wordEnd, then we found a word in trie which can be formed from board
        if (root.word != null) {
            result.add(root.word);
            root.word = null; //To avoid duplicates
        }

        //Mark current element as visited by setting it to '*' and recursively call in all four directions (right, down, left, up).
        board[i][j] = '*';
        //right, down, left, up
        if (j < board[0].length - 1) existH(board, i, j + 1, root, result);
        if (i < board.length - 1) existH(board, i + 1, j, root, result);
        if (j > 0) existH(board, i, j - 1, root, result);
        if (i > 0) existH(board, i - 1, j, root, result);

        //Mark current as element as not visited by reverting to actual value
        board[i][j] = c;
    }

    private void insert(TrieNode root, String word) {
        // Initialize the curr pointer with the root node
        TrieNode curr = root;

        // Iterate across the length of the string
        for (char c : word.toCharArray()) {
            // Check if the node exists for the current character in the Trie
            int i = c - 'a';
            if (curr.child[i] == null) {
                // If node for current character does not exist, then make a new node
                curr.child[i] = new TrieNode();
            }
            // Keep the reference for the newly created node
            curr = curr.child[i];
        }
        // Mark the end of the word
        curr.word = word;
    }

    class TrieNode {
        //Identify word ends
        String word = null;
        //All 26 character's children
        TrieNode[] child = new TrieNode[26];
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();
        char[][] board = {{'o', 'a', 'a', 'n' }, {'e', 't', 'a', 'e' }, {'i', 'h', 'k', 'r' }, {'i', 'f', 'l', 'v' }};
        String[] words = {"oath", "pea", "eat", "rain"};
        //o/p is ["oath","eat"]
        System.out.println(wordSearch.findWords(board, words));

        board = new char[][]{{'a'}};
        words = new String[]{"a"};
        //o/p is ["a"]
        System.out.println(wordSearch.findWords(board, words));
    }
}
