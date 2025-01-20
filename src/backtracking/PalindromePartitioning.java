package backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    //O(n * 2^n) T.C i.e 2^n possible ways of partitioning a string of length n, and for each possibility, we check is string is a palindrome, so O(n).
    //Hence, total TC:O(n*2^n)
    //S.C: O(2^n), to store 2^n partitions in worst case
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionH(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void partitionH(String s, int i, List<String> partition, List<List<String>> result) {
        //If we reach end of the string, then add the partition to result
        if (i == s.length()) {
            result.add(new ArrayList<>(partition));
            return;
        }

        String current = ""; //To store the string into consideration
        for (int j = i; j < s.length(); j++) {
            //Add the current character to the current string
            current = current + s.charAt(j);
            if (isPalindrome(current)) {
                //If it's a palindrome, then we add it to partition
                partition.add(current);
                //Then we call it recursively with the next index, dfs
                partitionH(s, j + 1, partition, result);
                //Back track, we remove it from partition
                partition.remove(partition.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String current) {
        if (current == null) {
            return false;
        }
        int i = 0;
        int j = current.length() - 1;
        while (i <= j) {
            if (current.charAt(i) != current.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning palindromePartitioning = new PalindromePartitioning();
        //[["a","a","b"],["aa","b"]]
        System.out.println(palindromePartitioning.partition("aab"));
        //[["a"]]
        System.out.println(palindromePartitioning.partition("a"));
        //[["a","a","a","b"],["a","aa","b"],["aa","a","b"],["aaa","b"]]
        System.out.println(palindromePartitioning.partition("aaab"));
        //[["a","b","c","a","a"],["a","b","c","aa"]]
        System.out.println(palindromePartitioning.partition("abcaa"));
        //[["a","b","b","a","b"],["a","b","bab"],["a","bb","a","b"],["abba","b"]]
        System.out.println(palindromePartitioning.partition("abbab"));
        //[["a","b","a","c","a"],["a","b","aca"],["aba","c","a"]]
        System.out.println(palindromePartitioning.partition("abaca"));
        //[["a","a","a"],["a","aa"],["aa","a"],["aaa"]]
        System.out.println(palindromePartitioning.partition("aaa"));
    }
}
