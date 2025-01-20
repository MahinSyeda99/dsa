package one_dimensional_dp;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    //TODO - Lean Manacher's algo for palindromic substrings
//    //Linear solution, O(n) T.C
//    public String longestPalindromeUsingManachersAlgo(String s) {
//
//    }

    //O(n^2) solution
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) {
            return "";
        }

        //Initialize maxLength possible
        int maxLength = 1;
        //Initialize result with first character, as a string of size 1 is also a palindrome
        String result = String.valueOf(s.charAt(0));
        //We are using expand around centre concept here
        for (int i = 0; i < n; i++) {
            //We will first consider the substring we are going to form is odd length and expand around s[i]
            String odd = maxLengthPalindrome(s, i, i);
            //If length of the substring found is greater than maxLength, then assign it to maxLength and assign the string to result
            if (odd.length() > maxLength) {
                maxLength = odd.length();
                result = odd;
            }
            //Let's consider the substring we are going to form is of even length, then we expand it between s[i] and s[i+1] ex: abba, expand like ab | ba
            String even = maxLengthPalindrome(s, i, i + 1);
            //If length of the substring found is greater than maxLength, then assign it to maxLength and assign the string to result
            if (even.length() > maxLength) {
                maxLength = even.length();
                result = even;
            }
        }
        return result;
    }

    private String maxLengthPalindrome(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            low--;
            high++;
        }
        return s.substring(low + 1, high);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
        //o/p is bab
        System.out.println(longestPalindromicSubstring.longestPalindrome("babad"));
        //o/p is bb
        System.out.println(longestPalindromicSubstring.longestPalindrome("cbbd"));
        //o/p is babab
        System.out.println(longestPalindromicSubstring.longestPalindrome("babab"));

    }
}
