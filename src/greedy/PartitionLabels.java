package greedy;

import java.util.*;

public class PartitionLabels {

    //O(n) TC, 2 pointers approach
    public List<Integer> partitionLabelsNew(String s) {
        //Initialize an array of last occurrences of the lower case alphabets
        int[] indexes = new int[26];
        for (int i = 0; i < s.length(); i++) {
            indexes[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        //Initialize length and maxIndex
        int start = 0;
        int maxIndex = -1;
        for (int i = 0; i < s.length(); i++) {
            //Find the max of current character's las occurrence index and maxIndex
            maxIndex = Math.max(indexes[s.charAt(i) - 'a'], maxIndex);
            //If maxIndex is same as current index, then we got one window after which the character's in window will not repeat
            if (maxIndex == i) {
                result.add(maxIndex - start + 1);
                //Set length to 0
                start = i + 1;
            }
        }
        return result;
    }

    //O(n) TC and O(26) S.C
    public List<Integer> partitionLabels(String s) {
        //Map of frequencies of characters
        Map<Character, Integer> mp = new HashMap<>();
        //Set of unique characters for a window
        Set<Character> uniqueChar = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0) + 1);
        }
        //Length of window such that each character appears at most one window
        int length = 0;
        //Our result
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (mp.get(s.charAt(i)) > 1) {
                //If the current characters frequency is greater than 1, add it to unique characters set, decrement its frequency
                uniqueChar.add(s.charAt(i));
                mp.put(s.charAt(i), mp.getOrDefault(s.charAt(i), 0) - 1);
            } else {
                //If the current character's frequency is 1, then it will not be encountered again, so remove it from unique characters set and from map
                uniqueChar.remove(s.charAt(i));
                mp.remove(s.charAt(i));
            }
            // Increase window length
            length++;
            //If the size of unique characters set is zero, then we found a window
            if (uniqueChar.isEmpty()) {
                result.add(length);
                //reset length to zero
                length = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
        String s = "ababcbacadefegdehijhklij";
        //Output should be [9, 7, 8]
        System.out.println(partitionLabels.partitionLabelsNew(s));
        s = "eccbbbbdec";
        //Output should be [10]
        System.out.println(partitionLabels.partitionLabelsNew(s));
    }
}
