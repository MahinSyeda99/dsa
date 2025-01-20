package sorting;

import java.util.Arrays;

public class CountNoOfFairPairs {

    //Solution 2
    //O(nlogn) solution
    //Two pointer solution
    public long countFairPairs2(int[] nums, int lower, int upper) {
        //Sort the array
        //O(nlogn)
        Arrays.sort(nums);

        //We need to find the count of pairs whose sum is <= upper and >= lower
        //So, we can find number of pairs whose sum is <= upper and number of pairs whose sum is <= lower - 1 and then our result will be subtracting latter from former.
        //O(n)
        return (countPairs(nums, upper) - countPairs(nums, lower - 1));
    }

    private long countPairs(int[] nums, int sum) {
        int l = 0;
        int r = nums.length - 1;
        long result = 0;
        while (l < r) {
            if (nums[l] + nums[r] > sum) {
                r--;
            } else {
                result += (long) (r - l);
                l++;
            }
        }
        return result;
    }

    //Solution 1
    //O(nlogn) solution
    public long countFairPairs(int[] nums, int lower, int upper) {
        //Sort nums array
        //Although given i < j, since its sum, nums[i] + nums[j] = nums[j] + nums[i]
        //So, eventually, we want the pairs with i != j
        //O(nlogn)
        Arrays.sort(nums);

        long result = 0;
        //lower <= nums[i] + nums[j] <= upper
        // => lower - nums[i] <= nums[j] <= upper - nums[i]
        //So, we need to find the number of values >= lower - nums[i] and <= higher - nums[i] in the rest of the array
        //O(nlogn)
        for (int i = 0; i < nums.length - 1; i++) {
            int lower_bound = lowerBound(i + 1, nums, lower - nums[i]);
            int upper_bound = upperBound(i + 1, nums, upper - nums[i]);
            result += upper_bound - lower_bound + 1;
        }
        return result;
    }

    //Find the index of lowest element >= target using binary search
    //O(logn)
    private int lowerBound(int i, int[] nums, int target) {
        int low = i;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    //Find the index of highest element <= target using binary search
    //O(logn)
    // Finds the index of the first element > target
    private int upperBound(int i, int[] nums, int target) {
        int low = i;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int[] nums = {1, 7, 9, 2, 5};
        CountNoOfFairPairs countNoOfFairPairs = new CountNoOfFairPairs();
        System.out.println(countNoOfFairPairs.countFairPairs(nums, 11, 11));
    }
}
