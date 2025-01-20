package slidingWindow;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 */
public class LongestRepeatingCharacterReplacement {

    //TC: O(2*n) worst case when each char is processed twice once by right and then again by left, i.e. ~ O(n) TC
    //This is a dynamic variant with auxiliary space problem
    public int characterReplacement(String s, int k) {
        //Store frequency of character
        int[] count = new int[26];
        //Left pointer
        int l = 0;
        int res = 0;
        //Max frequency
        int maxF = 0;
        for (int r = 0; r < s.length(); r++) {
            count[s.charAt(r) - 'A']++;
            //Update max frequency
            maxF = Math.max(maxF, count[s.charAt(r) - 'A']);
            //Number of distinct characters in window = window length - max frequency
            //The number of distinct characters in our window should be less than or equal to k, for our condition to be satisfied
            //So, when window length - max frequency is greater than k, condition is violated. So decrement the frequency of left pointer and
            // increase our left pointer
            while ((r - l + 1) - maxF > k) {
                count[s.charAt(l) - 'A']--;
                l = l + 1;
            }
            //Update result
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
