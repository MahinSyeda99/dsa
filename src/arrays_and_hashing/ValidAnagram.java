package arrays_and_hashing;

import java.util.Arrays;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        Arrays.fill(count, 0);
        for (int i = 0; i < s.length(); i++) {
            count[(int) s.charAt(i) - 97]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (count[(int) t.charAt(i) - 97] <= 0) {
                return false;
            }
            count[(int) t.charAt(i) - 97]--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
