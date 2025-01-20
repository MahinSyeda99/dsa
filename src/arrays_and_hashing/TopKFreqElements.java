package arrays_and_hashing;

import java.util.*;

public class TopKFreqElements {

    //O(N+M) solution using count sort
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            mp.put(nums[i], mp.getOrDefault(nums[i], 0) + 1);
        }
        //Create an array where we store the values of that frequency
        List<Integer>[] freq = new ArrayList[nums.length + 1];
        for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
            int frequency = entry.getValue();
            if (freq[frequency] == null) {
                freq[frequency] = new ArrayList<>();
            }
            freq[frequency].add(entry.getKey());
        }

        int[] result = new int[k];
        int idx = 0;
        //Traverse from right, and keep adding to the result till we get top k frequent elements
        for (int i = freq.length - 1; i >= 0; i--) {
            if (freq[i] != null) {
                for (int num : freq[i]) {
                    result[idx++] = num;
                    if (idx == k) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        TopKFreqElements topKFreqElements = new TopKFreqElements();
        System.out.println(Arrays.toString(topKFreqElements.topKFrequent(nums, k)));
    }
}
