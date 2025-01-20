package one_dimensional_dp;

import java.util.*;

public class WordBreak {

    //Bottom up approach
    //O(n * m * k) TC, where n is length of input string, m is number of words in dict and k is average size of substrings and O(n) SC
    public boolean wordBreakUsing1DDp(String s, List<String> wordDict) {
        //dp to store the word breaks possibilities at each character
        //True if word break possible at that index
        boolean[] dp = new boolean[s.length() + 1];

        //Base case
        dp[0] = true; //For empty string

        //For each character in string
        for(int i = 1; i <= s.length(); i++) {
            //For each word in dictionary
            for(String word: wordDict) {
                //Start will be current index subtracted by current word length
                int start = i - word.length();
                //If starting index is greater than 0 and a word break was possible at start indx and the substring from start to i is equal to the current word, then we set the value of dp at this index to true and break
                if(start >= 0 && dp[start] && s.substring(start, i).equals(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        //Finally, return value of s.length()'s index, indicating we were able to do a word break at that index
        return dp[s.length()];
    }

    //n <= 300 So, O(300^3) => ~O(10^7)
    //O(n^3) TC and O(n^2) SC solution using 2D dp
    public boolean wordBreak(String s, List<String> wordDict) {
        //Convert wordDict to a dictionary set
        Set<String> dict = new HashSet<>(wordDict);
        //Initialize a 2D dp
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        //If words are present in dictionary with proper breaks, then return true
        //Initial call of whole string i.e i = 0, j = n - 1
        return wordBreakH(s, dict, 0, s.length() - 1, dp) == 1;
    }

    private int wordBreakH(String s, Set<String> wordDict, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        //If a sub string is found, then set value 1(possible break) and return
        if (wordDict.contains(s.substring(i, j + 1))) {
            return dp[i][j] = 1;
        }

        //Else, we break the string between given i & j and check for the left and right parts
        for (int k = i; k < j; k++) {
            boolean result = (wordBreakH(s, wordDict, i, k, dp) == 1) && (wordBreakH(s, wordDict, k + 1, j, dp) == 1);
            if (result) {
                return dp[i][j] = 1;
            }
        }
        return dp[i][j] = 0;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        //o/p true
        System.out.println(wordBreak.wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet","code"))));
        //o/p is true
        System.out.println(wordBreak.wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple","pen"))));
        //o/p is false
        System.out.println(wordBreak.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat"))));

        //o/p true
        System.out.println(wordBreak.wordBreakUsing1DDp("leetcode", new ArrayList<>(Arrays.asList("leet","code"))));
        //o/p is true
        System.out.println(wordBreak.wordBreakUsing1DDp("applepenapple", new ArrayList<>(Arrays.asList("apple","pen"))));
        //o/p is false
        System.out.println(wordBreak.wordBreakUsing1DDp("catsandog", new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat"))));
    }
}
