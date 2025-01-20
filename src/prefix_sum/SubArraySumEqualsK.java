package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */
public class SubArraySumEqualsK {

    //O(n) TC and O(n) SC
    public int subarraySum(int[] nums, int k) {
        //Create a map to store the count of prefix sums seen
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, 1); //Add 0 with count 1 which is edge case for k = 0 (empty subarray with zero sum)
        int result = 0;
        int sum = 0;
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            prevSum = sum - k;
            //Check if prevSum exists in prefixSum, i.e a subarray starting at i = 0 with sum = sum - k which can be removed from current subarray to form sum equal to k
            if (prefixSum.containsKey(prevSum)) {
                result = result + prefixSum.get(prevSum); //Add the number of subarrays with sum - k that can be removed to form sum k
            }
            prefixSum.put(sum, prefixSum.getOrDefault(sum, 0) + 1); //Add current sum to map
        }
        return result;
    }
}
