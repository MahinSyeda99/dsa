package tries;

class Trie {
    TrieNode root;

    public Trie() {
        //Initialize trie root node
        root = new TrieNode();
    }

    //O(n): length of word
    public void insert(String word) {
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

    //O(n): length of word
    public boolean search(String word) {
        // Initialize the curr pointer with the root node
        TrieNode curr = this.root;

        // Iterate across the length of the string
        for (char c : word.toCharArray()) {
            // Check if the node exists for the current character in the Trie
            //If not found, then there is no such word
            if (curr.child[c - 'a'] == null) {
                return false;
            }
            // Move the curr pointer to the already existing node for the current character
            curr = curr.child[c - 'a'];
        }
        // Return true if the word exists and is marked as ending
        return curr.wordEnd;
    }

    public boolean startsWith(String prefix) {
        // Initialize the curr pointer with the root node
        TrieNode curr = this.root;

        // Iterate across the length of the string
        for (char c : prefix.toCharArray()) {
            // Check if the node exists for the current character in the Trie
            //If not found, then there is no such prefix
            if (curr.child[c - 'a'] == null) {
                return false;
            }
            // Move the curr pointer to the already existing node for the current character
            curr = curr.child[c - 'a'];
        }
        // Return true if the prefix exists
        return true;
    }

    public TrieNode delete(String word) {
        return deleteH(root, word, 0);
    }

    private TrieNode deleteH(TrieNode root, String word, int depth) {
        if (root == null) {
            return null;
        }

        //If we reached end of word
        if (depth == word.length()) {
            //The word should be deleted, so we set wordEnd as false
            if (root.wordEnd) {
                root.wordEnd = false;
            }
            //Not a prefix => no children
            if (isEmpty(root)) {
                root = null; //Set it as null => delete
            }
            return root;
        }

        //If it is not last character, then we recur for child
        int index = word.charAt(depth) - 'a';
        root.child[index] = deleteH(root.child[index], word, depth + 1);

        //If root does not have any child or its only child got deleted, and it is not a word(i.e. wordEnd is false.
        // Because, if it is a word, then we don't want to delete the word)
        if (isEmpty(root) && !root.wordEnd) {
            root = null; //Set it as null => delete
        }

        return root;
    }

    public void display() {
        displayH(root, "");
    }

    private void displayH(TrieNode root, String str) {
        if (root == null) {
            return;
        }
        if (root.wordEnd) {
            System.out.println(str);
        }
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                displayH(root.child[i], str + (char) (i + 'a'));
            }
        }
    }

    private boolean isEmpty(TrieNode root) {
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                return false;
            }
        }
        return true;
    }

    class TrieNode {
        //Identify word ends
        boolean wordEnd = false;
        //All 26 character's children
        TrieNode[] child = new TrieNode[26];
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("an");
        trie.insert("and");
        trie.insert("ant");
        trie.insert("dad");
        trie.insert("do");
        trie.insert("geek");
        System.out.println("Trie:");
        trie.display();
        //o/p is true
        System.out.println(trie.search("and"));
        //o/p is false
        System.out.println(trie.search("ane"));
        //Should delete it completely
        trie.delete("geek");
        System.out.println("Trie:");
        trie.display();
        //Should set "an" wordEnd as false
        trie.delete("an");
        System.out.println("Trie:");
        trie.display();
        //Should delete d only
        trie.delete("and");
        System.out.println("Trie:");
        trie.display();
    }
}
