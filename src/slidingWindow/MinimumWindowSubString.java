package slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class MinimumWindowSubString {

    //Solution 2 without need to compare the frequencies of window and string t at each point
    //O(2n) TC ~ O(n) TC and O(52) SC
    public String minWindow2(String s, String t) {
        //Create a map of char frequencies of string t
        int[] count = new int[52];
        //Add all distinct characters to set
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            if ((int) t.charAt(i) >= 65 && (int) t.charAt(i) <= 90) {
                count[t.charAt(i) - 'A']++;
                set.add(t.charAt(i));
            } else {
                count[t.charAt(i) - 'a' + 26]++;
                set.add(t.charAt(i));
            }
        }

        //Create a new map for char frequencies of string s
        int[] countNew = new int[52];
        //Left pointer
        int l = 0;
        int min = Integer.MAX_VALUE;
        //Number of character's with same freq as in t that we have so far
        int have = 0;
        //Number of character's we need
        int need = set.size();
        int start = 0, end = 0;
        for (int r = 0; r < s.length(); r++) {
            if (set.contains(s.charAt(r))) {
                //If the character exists in t
                if ((int) s.charAt(r) >= 65 && (int) s.charAt(r) <= 90) {
                    //Increment the character count
                    countNew[s.charAt(r) - 'A']++;
                    //If the character count is same as in t, then increase have count
                    if (countNew[s.charAt(r) - 'A'] == count[s.charAt(r) - 'A']) {
                        have++;
                    }
                } else {
                    countNew[s.charAt(r) - 'a' + 26]++;
                    if (countNew[s.charAt(r) - 'a' + 26] == count[s.charAt(r) - 'a' + 26]) {
                        have++;
                    }
                }
            }
            //If have is same as need
            while (have == need) {
                //Our window length is r - l + 1
                if (min > r - l + 1) {
                    //Update min
                    min = r - l + 1;
                    //Update start of window
                    start = l;
                    //update end of window
                    end = r + 1;
                }
                if (set.contains(s.charAt(l))) {
                    //If the character exists in t
                    if ((int) s.charAt(l) >= 65 && (int) s.charAt(l) <= 90) {
                        //Decrement character count
                        countNew[s.charAt(l) - 'A']--;
                        //If the character count is less than in t, then decrease have count
                        if (countNew[s.charAt(l) - 'A'] < count[s.charAt(l) - 'A']) {
                            have--;
                        }
                    } else {
                        countNew[s.charAt(l) - 'a' + 26]--;
                        if (countNew[s.charAt(l) - 'a' + 26] < count[s.charAt(l) - 'a' + 26]) {
                            have--;
                        }
                    }
                }
                //Move left pointer
                l = l + 1;
            }
        }
        //Return our minimum window substring
        return min != Integer.MAX_VALUE ? s.substring(start, end) : "";
    }

    //Solution 1 using maps
    //O(2n) TC ~ O(n) TC and O(52) SC
    public String minWindow(String s, String t) {
        //Create a map of char frequencies of string t
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            mp.put(t.charAt(i), mp.getOrDefault(t.charAt(i), 0) + 1);
        }

        //Create a new map for char frequencies of string s
        Map<Character, Integer> mpNew = new HashMap<>();
        int l = 0;
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int r = 0; r < s.length(); r++) {
            mpNew.put(s.charAt(r), mpNew.getOrDefault(s.charAt(r), 0) + 1);
            while (found(mp, mpNew)) {
                if (min > r - l + 1) {
                    min = r - l + 1;
                    result = s.substring(l, r + 1);
                }
                mpNew.put(s.charAt(l), mpNew.get(s.charAt(l)) - 1);
                l = l + 1;
            }
        }
        return result;
    }

    public boolean found(Map<Character, Integer> mp1, Map<Character, Integer> mp2) {
        for (Map.Entry<Character, Integer> entry : mp1.entrySet()) {
            if (mp2.get(entry.getKey()) == null || entry.getValue() > mp2.get(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubString minimumWindowSubString = new MinimumWindowSubString();
        System.out.println(minimumWindowSubString.minWindow("ADOBECODEBANC", "ABC"));
    }
}
