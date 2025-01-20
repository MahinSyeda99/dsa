package slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaximumSumOfDistinctSubarraysWithLengthK {

    //Solution 2
    //O(n) TC and O(k) SC
    //Sliding window using set, as we maintain a set to store the elements in the current window of size k
    public long maximumSubarraySum2(int[] nums, int k) {
        //Maintain a count of elements in the window using a hash map (mp)
        Set<Integer> s = new HashSet<>();
        long result = 0;
        int r = 0;
        int l = 0;
        long sum = 0;
        while(r < nums.length) {
            sum = sum + nums[r];
            //As the window slides, we remove the left-most element and adjust the sum: sum−=nums[l]. Also, if we have seen nums[r] in he window, then we keep sliding till there is no nums[r] in set
            while (s.contains(nums[r]) || r - l + 1> k) {
                s.remove(nums[l]);
                sum = sum - nums[l];
                l++;
            }
            //If the window contains exactly k distinct elements, we compute the sum of the window and update the maximum sum: result=max(result, sum)
            if (r - l == k - 1) {
                result = Math.max(result, sum);
            }
            s.add(nums[r]);
            r++;
        }
        return result;
    }

    //Solution1
    //O(n) TC and O(k) SC
    //Sliding window using hashmap, as we maintain a hash map to store the frequency of elements in the current window of size k
    public long maximumSubarraySum(int[] nums, int k) {
        //Maintain a count of elements in the window using a hash map (mp)
        Map<Integer, Integer> mp = new HashMap<>();
        long result = 0;
        int r = 0;
        long sum = 0;
        while (r < nums.length) {
            sum = sum + nums[r];
            //For each element in the current window, we update its count in map
            mp.put(nums[r], mp.getOrDefault(nums[r], 0) + 1);
            // If the window size exceeds 'k', slide the window
            if (r >= k) {
                //As the window slides, we remove the left-most element and adjust the sum: sum−=nums[l] We also update
                // the frequency count of the element being removed: mp[nums[l]]−=1 If its count becomes zero, we remove it from the map.
                sum = sum - nums[r - k];
                if (mp.get(nums[r - k]) == 1) {
                    mp.remove(nums[r - k]);
                } else {
                    mp.put(nums[r - k], mp.get(nums[r - k]) - 1);
                }
            }
            //If the window contains exactly k distinct elements, we compute the sum of the window and update the maximum sum: result=max(result, sum)
            if (r >= k - 1 && mp.size() == k) {
                result = Math.max(result, sum);
            }
            r++;
        }
        return result;
    }
}
