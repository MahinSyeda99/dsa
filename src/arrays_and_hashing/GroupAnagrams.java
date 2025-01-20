package arrays_and_hashing;

import java.util.*;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> mp = new HashMap<>();
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String sorted = new String(s);
            if (mp.containsKey(sorted)) {
                List<String> l = mp.get(sorted);
                l.add(str);
                mp.put(sorted, l);
            } else {
                List<String> l = new ArrayList<>();
                l.add(str);
                mp.put(sorted, l);
            }
        }
        for (Map.Entry<String, List<String>> entry : mp.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }
}
