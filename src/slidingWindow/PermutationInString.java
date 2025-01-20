package slidingWindow;

/**
 * https://leetcode.com/problems/permutation-in-string/description/
 */
public class PermutationInString {

    //O(26*n) TC solution and O(26) SC solution
    public boolean checkInclusion(String s1, String s2) {
        //Create a map for s2 character frequency
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }

        //left pointer
        int l = 0;
        //Map for s2 character frequency
        int[] countNew = new int[26];
        for (int r = 0; r < s2.length(); r++) {
            //Add the character to our window
            countNew[s2.charAt(r) - 'a']++;
            //If window length is greater than s1 length, then remove from left
            while ((r - l + 1) > s1.length()) {
                countNew[s2.charAt(l) - 'a']--;
                l = l + 1;
            }
            //If window length is same as s1 and map frequencies are same, then our solution is found
            if ((r - l + 1) == s1.length() && isEqual(count, countNew)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEqual(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
