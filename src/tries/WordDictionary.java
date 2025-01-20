package tries;

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/description/
 */
class WordDictionary {
    TrieNode root;

    public WordDictionary() {
        //Initialize trie root node
        root = new TrieNode();
    }

    public void addWord(String word) {
        // Initialize the curr pointer with the root node
        TrieNode curr = this.root;

        // Iterate across the length of the string
        for (char c : word.toCharArray()) {
            // Check if the node exists for the current character in the Trie
            if (curr.child[c - 'a'] == null) {
                // If node for current character does not exist, then make a new node
                curr.child[c - 'a'] = new TrieNode();
            }
            // Keep the reference for the newly created node
            curr = curr.child[c - 'a'];
        }
        // Mark the end of the word
        curr.wordEnd = true;
    }

    public boolean search(String word) {
        return searchH(word, this.root, 0);
    }

    public boolean searchH(String word, TrieNode root, int index) {
        if (root == null) {
            return false;
        }

        //If index is greater than word length, then we already have the word, just check if it is wordEnd or not
        if (index >= word.length()) {
            // Return true if the word exists and is marked as ending
            return root.wordEnd;
        }

        //If character is not '.', then we check if the remaining part of word can be achieved from the current child
        if (word.charAt(index) != '.') {
            return searchH(word, root.child[word.charAt(index) - 'a'], index + 1);
        }

        //If character is '.' then we check from all the characters, if the remaining word can be formed from other characters
        for (int i = 0; i < 26; i++) {
            if (searchH(word, root.child[i], index + 1)) {
                return true;
            }
        }
        return false;
    }

    class TrieNode {
        //Identify word ends
        boolean wordEnd = false;
        //All 26 character's children
        TrieNode[] child = new TrieNode[26];
    }
}
