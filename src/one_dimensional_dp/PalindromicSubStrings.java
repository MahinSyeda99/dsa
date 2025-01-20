package one_dimensional_dp;

/**
 * https://leetcode.com/problems/palindromic-substrings/description/
 */
public class PalindromicSubStrings {

    private static int count = 0;

    //O(n^2) TC and O(1) SC
    public int countSubstrings(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        count = 0;
        //Using centre expansion
        for (int i = 0; i < n; i++) {
            //Expand considering odd length
            String odd = expandAtCenter(s, i, i);
            //Expand considering even length
            String even = expandAtCenter(s, i, i + 1);
        }
        return count;
    }

    private String expandAtCenter(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            //We found one palindrome, so increase count, there is no chance of duplicates because we are expanding at different centres always
            count++;
            low--;
            high++;
        }
        return s.substring(low + 1, high);
    }

    public static void main(String[] args) {
        PalindromicSubStrings palindromicSubStrings = new PalindromicSubStrings();
        //o/p is 3
        System.out.println(palindromicSubStrings.countSubstrings("abc"));
        //o/p is 6
        System.out.println(palindromicSubStrings.countSubstrings("aaa"));
    }
}
