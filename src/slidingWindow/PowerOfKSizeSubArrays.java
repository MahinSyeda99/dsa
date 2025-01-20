package slidingWindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/description/
 */
public class PowerOfKSizeSubArrays {

    //O(n) TC solution
    //Sliding window with two pointers
    public int[] resultsArray3(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int left = 0;
        int right = 1;
        int l = 0;
        while (right < n) {
            // Check if current sequence is not consecutive
            boolean isNotConsecutive = (nums[right] - nums[right - 1] != 1);
            if (isNotConsecutive) {
                //Mark all the invalid sequences as -1
                //left + k - 1 < n also because, we can only form k size subarrays from i if i + k -1 < n
                while (left < right && left + k - 1 < n) {
                    result[l++] = -1;
                    left++;
                }
            } // Found valid k-length sequence
            else if (right - left == k - 1) {
                result[l++] = nums[right];
                left++;
            }
            right++;
        }
        return result;
    }

    //Solution 2
    //Sliding window with monotonic increasing deque
    //O(n) TC solution
    //O(k) SC solution - queue
    public int[] resultsArray2(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        //Create a dequeue of indices
        Deque<Integer> dq = new ArrayDeque<>();
        int l = 0;
        for (int i = 0; i < n; i++) {
            // Remove elements outside window
            while (!dq.isEmpty() && i - dq.getFirst() >= k) {
                dq.removeFirst();
            }

            //Check for consecutive sequence
            if (dq.isEmpty() || nums[i] - nums[i - 1] == 1) {
                dq.addLast(i);
            } else {
                //Clear everything, because we cant form an increasing consecutive sequence till current index
                dq.clear();
                //Add current index
                dq.add(i);
            }

            // Add result when window size is k
            if (i >= k - 1) {
                result[l++] = dq.size() == k ? nums[i] : -1;
            }
        }
        return result;
    }

    //Solution 1 - Brute force
    //O(n * k) TC solution
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int l = 0;
        for (int i = 0; i < n - k + 1; i++) {
            int j = i + 1;
            for (; j < i + k; j++) {
                if (!(nums[j] - nums[j - 1] == 1)) {
                    break;
                }
            }
            if (j == i + k) {
                result[l++] = nums[i + k - 1];
            } else {
                result[l++] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 3, 2, 5};
        PowerOfKSizeSubArrays powerOfKSizeSubArrays = new PowerOfKSizeSubArrays();
        System.out.println(Arrays.toString(powerOfKSizeSubArrays.resultsArray3(nums, 3)));
    }
}
