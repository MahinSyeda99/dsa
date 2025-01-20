package binarySearch;

public class MinInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums[0] < nums[nums.length - 1]) {
            //Not rotated
            return nums[0];
        }
        //Rotated
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[low] <= nums[high]) {
                return nums[low];
            } else if (nums[mid] < nums[high]) {
                //Min will be in left side
                high = mid;
            } else {
                //When nums[mid] > nums[high], min will be in right side
                low = mid + 1;
            }
        }
        return nums[low];
    }
}
